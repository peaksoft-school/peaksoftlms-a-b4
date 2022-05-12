package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultService {


    ResultResponse createResult(ResultRequest resultRequest);

    ResultResponse updateResult(Long id, ResultRequest resultRequest);

    ResultResponse getById(Long id);

    ResultResponse deleteById(Long id);

    List<ResultResponse> getAll();
}
