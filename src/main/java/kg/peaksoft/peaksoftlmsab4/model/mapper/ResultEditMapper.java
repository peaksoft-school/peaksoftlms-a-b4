package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResultEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ResultEditMapper {
    private final QuestionRepository questionRepository;
    private final TestStudentRepository testStudentRepository;

    public ResultEntity createResult(ResultRequest resultRequest) {
        int correct = 0;
        int wrong = 0;
        int point;

        List<TestStudentEntity> testStudents = testStudentRepository.findAll();

        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setAccepted(resultRequest.getAccepted());

        resultEntity.setStudentName("Nurmuhammed");

        if (resultEntity.getAccepted()) {
            for (TestStudentEntity t : testStudents) {


                if (t.getIsTrue()) {
                    correct++;
                    resultEntity.setCorrect(correct);
                } else {
                    wrong++;
                    resultEntity.setError(wrong);
                }
            }

            point = (correct * 100 / questionRepository.findAll().size());
            resultEntity.setPoints(point);


            resultEntity.setLocalDate(LocalDate.now());


        }
        return resultEntity;
    }

    public ResultEntity createStudent(StudentEntity studentEntity) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setStudentName(studentEntity.getFirstName());
        return resultEntity;
    }
}
