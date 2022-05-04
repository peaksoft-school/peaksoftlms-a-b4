package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionViewMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestResultEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestResultViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestResultRepository;
import kg.peaksoft.peaksoftlmsab4.service.TestResultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestResultViewMapper viewMapper;
    private final TestResultRepository resultRepository;
    private final TestResultEditMapper editMapper;
    private final OptionRepository optionRepository;
    private final OptionEditMapper optionEditMapper;
    private final OptionViewMapper optionViewMapper;


    private TestResultEntity getByIdMethod(Long testResultId) {
        return resultRepository.findById(testResultId).
                orElseThrow(() -> {
                    log.error("TestResult with id = {} does not exists", testResultId);
                    throw new NotFoundException(
                            String.format("TestResult with id = %s does not exists", testResultId)
                    );
                });
    }


    @Override
    public TestResultResponse create(TestResultRequest testResultRequest) {


        return viewMapper.viewTestResult(resultRepository.save(editMapper.create(testResultRequest)));
    }

//    @Override
//    public TestResultResponse update(Long id, TestResultRequest testResultRequest) {
//        TestResultEntity testResult = getByIdMethod(id);
//        editMapper.update(testResult, testResultRequest);
//
//        return viewMapper.viewTestResult(resultRepository.save(testResult));
//    }


    @Override
    public ResultResponse result() {

        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setStudentName("Nurmuhammed babaev");
        resultResponse.setCorrect(resultRepository.countAllByAnswerTrue());
        resultResponse.setError(resultRepository.countAllByAnswerFalse());
        Long results = (resultResponse.getCorrect() * 100 / (resultRepository.countAllById()));
        resultResponse.setPoint(results);
        return resultResponse;


    }
}
