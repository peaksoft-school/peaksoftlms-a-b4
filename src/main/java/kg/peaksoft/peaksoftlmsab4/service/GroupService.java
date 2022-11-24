package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.StudentResponse;

import java.util.List;


public interface GroupService {

    GroupResponse update(Long id, GroupRequest groupRequest);

    GroupResponse getById(Long id);

    GroupResponse deleteById(Long id);

    List<GroupResponse> getAllGroup();

    GroupResponse saveGroup(GroupRequest groupRequest);

    GroupResponse setGroupToCourse(Long groupId, Long courseId);

    PaginationResponse<GroupResponse> getGroupPagination(int page, int size);

    List<StudentResponse> getAllStudentsByGroupId(Long id);
}
