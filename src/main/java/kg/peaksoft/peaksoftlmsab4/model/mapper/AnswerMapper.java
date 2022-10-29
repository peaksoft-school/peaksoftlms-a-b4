package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        answerResponse.setStudentName(testStudentEntity.getStudentEntity().getFirstName()+" "+testStudentEntity.getStudentEntity().getLastName());
        answerResponse.setGrade(testStudentEntity.getResult());
        answerResponse.setStatus(testStudentEntity.getTestResult());
        answerResponse.setDate(testStudentEntity.getLocalDate());

        return answerResponse;
    }
    public List<AnswerResponse> mapToResponse(List<TestStudentEntity> studentEntities) {
        List<AnswerResponse> answerResponses = new ArrayList<>();
        for (TestStudentEntity studentEntity : studentEntities) {
            answerResponses.add(viewAnswer(studentEntity));
        }
        return answerResponses;
    }
}
