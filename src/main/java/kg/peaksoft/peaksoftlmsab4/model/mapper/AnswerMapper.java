package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    public AnswerResponse viewAnswer(TestStudentEntity testStudentEntity) {

        if (testStudentEntity == null) {
            return null;
        }
        AnswerResponse answerResponse = new AnswerResponse();
        if (testStudentEntity.getId() != null) {
            answerResponse.setId(testStudentEntity.getId());
        }
        answerResponse.setId(testStudentEntity.getId());
        answerResponse.setStudent(testStudentEntity.getStudentEntity().getFirstName()+" "+testStudentEntity.getStudentEntity().getLastName());
        answerResponse.setResult(testStudentEntity.getResult());
        answerResponse.setTestResult(testStudentEntity.getTestResult());
        answerResponse.setLocalDate(testStudentEntity.getLocalDate());

        return answerResponse;
    }
}
