package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionViewMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestStudentEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestStudentViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.TestStudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TestStudentServiceImpl implements TestStudentService {

    private final TestStudentViewMapper viewMapper;
    private final TestStudentRepository testStudentRepository;
    private final TestStudentEditMapper editMapper;
    private final StudentRepository studentRepository;
    private final OptionRepository optionRepository;
    private final OptionEditMapper optionEditMapper;
    private final OptionViewMapper optionViewMapper;


    private TestStudentEntity getByIdMethod(Long testResultId) {
        return testStudentRepository.findById(testResultId).
                orElseThrow(() -> {
                    log.error("TestResult with id = {} does not exists", testResultId);
                    throw new NotFoundException(
                            String.format("TestResult with id = %s does not exists", testResultId)
                    );
                });
    }


    @Override
    public List<TestStudentResponse> createResult(TestStudentRequest testStudentRequest) {



        return viewMapper.viewTestResults(testStudentRepository.saveAll(editMapper.createResult(testStudentRequest)));

    }

    @Override
    public ResultResponse result() {
        return null;
    }

    @Override
    public ResultResponse testResults() {

        int point;


        ResultResponse resultResponse = new ResultResponse();

        resultResponse.setStudentName("Muhammed");


        resultResponse.setCorrect(testStudentRepository.countAllByAnswerTrue());

        resultResponse.setError(testStudentRepository.countAllByAnswerFalse());

        point = testStudentRepository.countAllByAnswerTrue() * 100 / testStudentRepository.countAllById();
        resultResponse.setPoint(point);

        return resultResponse;
    }
}


//    @Override
//    public TestResultResponse update(Long id, TestResultRequest testResultRequest) {
//        TestResultEntity testResult = getByIdMethod(id);
//        editMapper.update(testResult, testResultRequest);
//
//        return viewMapper.viewTestResult(resultRepository.save(testResult));
//    }


//    @Override
//    public ResultResponse result() {
//
//        ResultResponse resultResponse = new ResultResponse();
//        resultResponse.setStudentName("Nurmuhammed babaev");
//        resultResponse.setCorrect(resultRepository.countAllByAnswerTrue());
//        resultResponse.setError(resultRepository.countAllByAnswerFalse());
//        Long results = (resultResponse.getCorrect() * 100 / (resultRepository.countAllById()));
//        resultResponse.setPoint(results);
//        return resultResponse;
//
//
//    }



