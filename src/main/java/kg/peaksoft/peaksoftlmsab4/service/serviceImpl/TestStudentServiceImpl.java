package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestStudentMapper;
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

    private final TestStudentRepository testStudentRepository;
    private final TestStudentMapper editMapper;

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
        return editMapper.viewTestResults(testStudentRepository.saveAll(editMapper.createResult(testStudentRequest)));

    }
}




