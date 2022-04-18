package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.GroupEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.GroupViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsab4.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupViewMapper viewMapper;
    private final GroupRepository repository;
    private final GroupEditMapper editMapper;

    public GroupResponse create(GroupRequest groupRequest) {

        return viewMapper.viewGroup(repository
                .save(editMapper.create(groupRequest)));
    }

    public GroupResponse update(Long id, GroupRequest groupRequest) {

        GroupEntity group = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("group not found this=%s", id)));

        editMapper.update(group, groupRequest);
        return viewMapper.viewGroup(repository.save(group));
    }

    public GroupResponse getById(Long id) {
        return viewMapper.viewGroup(repository.findById(id).get());
    }

    public GroupResponse deleteById(Long id) {
        GroupEntity group = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("group don't deleted this= %s", id)));

        repository.deleteById(id);
        return viewMapper.viewGroup(group);
    }

    public List<GroupResponse> getAllGroup() {
        return viewMapper.viewGroups(repository.findAll());
    }
}
