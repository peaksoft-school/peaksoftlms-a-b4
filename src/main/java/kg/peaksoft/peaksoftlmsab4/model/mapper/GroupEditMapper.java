package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GroupEditMapper {

    public GroupEntity create(CourseEntity course, GroupRequest groupRequest) {
        if (groupRequest == null) {
            log.error("The request is null!");
            return null;
        }
        GroupEntity group = new GroupEntity();
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setDescription(groupRequest.getDescription());
        group.setImage(groupRequest.getImage());
        group.setCourse(course);
        return group;
    }

    public GroupEntity convert(GroupRequest groupRequest) {

        GroupEntity group = new GroupEntity();
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setDescription(groupRequest.getDescription());
        group.setImage(groupRequest.getImage());
        return group;
    }

    public GroupEntity update(GroupEntity group, GroupRequest groupRequest) {

        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setDescription(groupRequest.getDescription());
        group.setImage(groupRequest.getImage());

        return group;

    }
}
