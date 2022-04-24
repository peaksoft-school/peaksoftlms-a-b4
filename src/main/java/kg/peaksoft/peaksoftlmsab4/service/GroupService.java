package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;

import java.util.List;


public interface GroupService {

    ResponseEntity create(Long id, GroupRequest groupRequest);

    ResponseEntity update(Long id, GroupRequest groupRequest);

    GroupResponse getById(Long id);

    ResponseEntity deleteById(Long id);

    List<GroupResponse> getAllGroup();
}
