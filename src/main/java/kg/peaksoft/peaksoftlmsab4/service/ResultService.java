package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultService {


    ResultResponse createResult(ResultRequest resultRequest);


    List<ResultResponse> getAll();
}
