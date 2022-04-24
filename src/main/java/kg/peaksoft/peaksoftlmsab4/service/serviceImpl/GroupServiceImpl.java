package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.GroupEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.GroupViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsab4.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public ResponseEntity create(Long id, GroupRequest groupRequest) {
        boolean exists = repository.existsByGroupName(groupRequest.getGroupName());
        if (exists) {
            log.warn("Group with name = {} already exists", groupRequest.getGroupName());
            throw new AlreadyExistsException(
                    "Course with name = " + groupRequest.getGroupName() + " already exists"
            );
        }
        CourseEntity course = courseRepository.getById(id);
        GroupEntity savedGroup = repository.save(editMapper.create(course, groupRequest));
        return ResponseEntity.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Group with name = %s has successfully saved to database", savedGroup.getGroupName()))
                .build();
    }

    @Override
    public ResponseEntity update(Long groupId, GroupRequest groupRequest) {
        GroupEntity group = getByMethod(groupId);

        editMapper.update(group, groupRequest);
        repository.save(group);
        return ResponseEntity.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Group with id = %d has successfully updated", groupId))
                .build();
    }

    @Override
    public GroupResponse getById(Long id) {
        return viewMapper.viewGroup(getByMethod(id));
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            log.error("Group with id = {} does not exists, you can't delete it", id);
            throw new BadRequestException(
                    String.format("Group with id = %d does not exists, you can't delete it", id)
            );
        }
        repository.deleteById(id);

        log.info("Group with id = {} has successfully deleted", id);

        return ResponseEntity.builder()
                .httpStatus(HttpStatus.MOVED_PERMANENTLY)
                .message(String.format("Group with id = %d has successfully deleted", id))
                .build();
    }

    @Override
    public List<GroupResponse> getAllGroup() {
        return viewMapper.viewGroups(repository.findAll());
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
