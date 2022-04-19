package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GroupViewMapper {


    public GroupResponse viewGroup(GroupEntity group) {
        if (group == null) {
            log.error("The group db is null!");
            return null;
        }
        GroupResponse groupResponse = new GroupResponse();
        if (group.getId() != null) {
            groupResponse.setId(group.getId());
        }
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setDescription(group.getDescription());
        groupResponse.setImage(group.getImage());

        return groupResponse;
    }
    public List<GroupResponse> viewGroups(List<GroupEntity>groups){
        List<GroupResponse> groupResponses=new ArrayList<>();
        for (GroupEntity g:groups) {
            groupResponses.add(viewGroup(g));
        }
        return groupResponses;
    }
}
