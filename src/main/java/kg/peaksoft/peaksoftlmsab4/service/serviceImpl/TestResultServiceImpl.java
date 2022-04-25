package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestResultEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestResultViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.TestResultRepository;
import kg.peaksoft.peaksoftlmsab4.service.TestResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestResultViewMapper viewMapper;
    private final TestResultRepository resultRepository;
    private final TestResultEditMapper editMapper;
    @Override
    public TestResultResponse create(TestResultRequest testResultRequest) {
        return viewMapper.viewTestResult(resultRepository.save(editMapper.create(testResultRequest)));
    }

    @Override
    public TestResultResponse update(Long id, TestResultRequest testResultRequest) {
        TestResultEntity testResult=resultRepository.findById(id).get();
        editMapper.update(testResult,testResultRequest);
        return viewMapper.viewTestResult(resultRepository.save(testResult));
    }

    @Override
    public TestResultResponse findById(Long id) {
        return viewMapper.viewTestResult(resultRepository.findById(id).get());
    }

    @Override
    public TestResultResponse deleteById(Long id) {
        TestResultEntity testResult=resultRepository.findById(id).get();
        resultRepository.deleteById(id);
        return viewMapper.viewTestResult(testResult);
    }

    @Override
    public List<TestResultResponse> findAll() {
        return viewMapper.vewTestResults(resultRepository.findAll());
    }
}
