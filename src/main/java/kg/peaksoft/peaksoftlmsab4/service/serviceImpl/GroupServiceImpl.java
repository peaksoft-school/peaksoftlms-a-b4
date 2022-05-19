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

    @Override
    public GroupResponse update(Long groupId, GroupRequest groupRequest) {
        GroupEntity group = getByMethod(groupId);

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
        boolean exists = repository.existsByGroupName(groupRequest.getGroupName());
        if (exists) {
            log.warn("Group with name = {} already exists", groupRequest.getGroupName());
            throw new AlreadyExistsException(
                    "Course with name = " + groupRequest.getGroupName() + " already exists"
            );
        }
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
        group.setCourse(course);
        log.info("Group with id = {} has successfully added to course with id = {}", groupId, courseId);
        return viewMapper.viewGroup(group);
    }

    @Override
    public PaginationResponse<GroupResponse> getGroupPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (GroupEntity group:repository.findAllPag(pageable)) {
            groupResponses.add(viewMapper.viewGroup(group));
        }
        PaginationResponse<GroupResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setResponseList(groupResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        paginationResponse.setTotalPage(repository.findAll().size());
        return paginationResponse;
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
}
