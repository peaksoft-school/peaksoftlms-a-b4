package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.StudentResponse;

import java.util.List;

public interface GroupService {

    GroupResponse update(Long id, GroupRequest request);

    GroupResponse getById(Long id);

    GroupResponse deleteById(Long id);

    List<GroupResponse> getAllGroup();

    GroupResponse saveGroup(GroupRequest request);

    GroupResponse setGroupToCourse(Long groupId, Long courseId);

    PaginationResponse<GroupResponse> getGroupPagination(int page, int size);

    List<StudentResponse> getAllStudentsByGroupId(Long id);

}
