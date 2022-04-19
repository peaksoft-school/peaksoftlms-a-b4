package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.GroupResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GroupService {

    GroupResponse create(Long id,GroupRequest groupRequest);
    GroupResponse update(Long id, GroupRequest groupRequest);
    GroupResponse getById(Long id);
    GroupResponse deleteById(Long id);
    List<GroupResponse> getAllGroup();
}
