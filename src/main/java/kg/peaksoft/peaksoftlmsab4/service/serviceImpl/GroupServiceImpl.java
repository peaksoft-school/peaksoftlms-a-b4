package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.GroupEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.GroupViewMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsab4.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupViewMapper viewMapper;
    private final GroupRepository repository;
    private final GroupEditMapper editMapper;
    private final CourseRepository courseRepository;
    private final StudentViewMapper studentViewMapper;

    @Override
    public GroupResponse update(Long groupId, GroupRequest groupRequest) {
        GroupEntity group = getByMethod(groupId);
        String groupName = groupRequest.getGroupName();
        String groupEntityName = group.getGroupName();
        if (!groupName.equals(groupEntityName)) {
            checkByName(groupName);
        }
        editMapper.update(group, groupRequest);
        repository.save(group);
        return viewMapper.viewGroup(group);
    }

    @Override
    public GroupResponse getById(Long id) {
        return viewMapper.viewGroup(getByMethod(id));
    }

    @Override
    public GroupResponse deleteById(Long id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            log.error("Group with id = {} does not exists, you can't delete it", id);
            throw new BadRequestException(
                    String.format("Group with id = %d does not exists, you can't delete it", id)
            );
        }
        GroupEntity groupEntity = getByMethod(id);
        repository.deleteById(id);

        log.info("Group with id = {} has successfully deleted", id);

        return viewMapper.viewGroup(groupEntity);
    }

    @Override
    public List<GroupResponse> getAllGroup() {
        return viewMapper.viewGroups(repository.findAll());
    }

    @Override
    public GroupResponse saveGroup(GroupRequest groupRequest) {
        checkByName(groupRequest.getGroupName());

        GroupEntity group = editMapper.convert(groupRequest);
        GroupEntity savedGroup = repository.save(group);
        return viewMapper.viewGroup(savedGroup);
    }

    @Override
    @Transactional
    public GroupResponse setGroupToCourse(Long groupId, Long courseId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exists", courseId);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exists", courseId)
                    );
                });
        GroupEntity group = getByMethod(groupId);
        for (GroupEntity groupEntity : course.getGroups()) {
            if (group.getId().equals(groupEntity.getId())) {
                throw new AlreadyExistsException(groupEntity.getGroupName() + " already assigned to course " + course.getCourseName());
            }
        }
        for (StudentEntity s : group.getStudents()) {
            course.getStudents().removeIf(studentEntity -> s.getId().equals(studentEntity.getId()));
            s.setCourse(course);
        }

        group.setCourse(course);
        log.info("Group with id = {} h as successfully added to course with id = {}", groupId, courseId);
        return viewMapper.viewGroup(group);
    }

    @Override
    public PaginationResponse<GroupResponse> getGroupPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (GroupEntity group : repository.findAllPag(pageable)) {
            groupResponses.add(viewMapper.viewGroup(group));
        }
        PaginationResponse<GroupResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setResponseList(groupResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber() + 1);
        paginationResponse.setTotalPage(repository.findAll(pageable).getTotalPages());
        return paginationResponse;
    }

    @Override
    public List<StudentResponse> getAllStudentsByGroupId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (StudentEntity s : getByMethod(id).getStudents()) {
            studentResponses.add(studentViewMapper.convertToStudentResponse(s));
        }
        log.info("Successfully got all Students by Group Id");
        return studentResponses;
    }

    private GroupEntity getByMethod(Long groupId) {
        return repository.findById(groupId)
                .orElseThrow(() -> {
                    log.error("Group with id = {} does not exists", groupId);
                    throw new NotFoundException(
                            String.format("Group with id = %d does not exists", groupId)
                    );
                });
    }

    private void checkByName(String name) {
        boolean exists = repository.existsByGroupName(name);
        if (exists) {
            log.info("Group with name = {} already exists", name);
            throw new AlreadyExistsException(
                    "Group with name = " + name + " already exists"
            );
        }
    }
}
