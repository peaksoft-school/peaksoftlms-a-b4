package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResultEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.ResultEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.ResultViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.ResultRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ResultServiceImpl implements ResultService {
    private final ResultViewMapper viewMapper;
    private final ResultRepository resultRepository;
    private final ResultEditMapper editMapper;
    private final TestStudentRepository testStudentRepository;
    private final QuestionRepository questionRepository;

    @Override
    public ResultResponse createResult(ResultRequest resultRequest) {
//        List<TestStudentEntity> testStudent = testStudentRepository.findAll();
//        int correct = 0;
//        int wrong = 0;
//        int point;
//        List<TestStudentEntity> testStudents = testStudentRepository.findAll();
//
//        ResultEntity resultEntity = new ResultEntity();
//        resultEntity.setAccepted(resultRequest.getAccepted());
//
//        resultEntity.setStudentName("Nurmuhammed");
//
//        if (resultEntity.getAccepted()) {
//            for (TestStudentEntity t : testStudents) {
//
//
//                if (t.getIsTrue()) {
//                    correct++;
//                    resultEntity.setCorrect(correct);
//                } else {
//                    wrong++;
//                    resultEntity.setError(wrong);
//                }
//            }
//
//            point = (correct * 100 / questionRepository.findAll().size());
//            resultEntity.setPoints(point);
//
//
//            resultEntity.setLocalDate(LocalDate.now());
//
//
//        }
        return viewMapper.viewResult(resultRepository.save(editMapper.createResult(resultRequest)));

    }

    @Override
    public ResultResponse updateResult(Long id, ResultRequest resultRequest) {
        return null;
    }

    @Override
    public ResultResponse getById(Long id) {
        return null;
    }

    @Override
    public ResultResponse deleteById(Long id) {
        return null;
    }

    @Override
    public List<ResultResponse> getAll() {
        return viewMapper.viewResults(resultRepository.findAll());
    }
}
