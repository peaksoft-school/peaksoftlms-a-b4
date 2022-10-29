package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestMapper {
    private final QuestionMapper mapper;
    private final LessonRepository lessonRepository;

    public TestEntity create(TestRequest testRequest) {
        List<QuestionEntity> questions = new ArrayList<>();
        TestEntity test = new TestEntity();
        test.setTestName(testRequest.getTestName());
        for (QuestionRequest q : testRequest.getQuestions()) {
            int counter = 0;
            if (q.getQuestionType() == QuestionType.ONE) {
                for (OptionRequest v : q.getOptions()) {
                    if (v.getIsTrue()) {
                        counter++;
                    }
                }
                if (counter > 1) {
                    throw new BadRequestException("You can't choose multiply answer");
                } else {
                    questions.add(mapper.create(q));
                }
            } else
                questions.add(mapper.create(q));
        }
        test.setQuestions(questions);
        return test;
    }

    public TestResponse viewTest(TestEntity test) {
        if (test == null) {
            log.error("The test db is null!");
            return null;
        }
        TestResponse testResponse = new TestResponse();
        if (test.getId() != null) {
            testResponse.setId(test.getId());
        }
        testResponse.setTestName(test.getTestName());
        testResponse.setQuestions(mapper.viewQuestions(test.getQuestions()));
        testResponse.setLessonId(test.getLessonEntity().getId());
        return testResponse;

    }

    public List<TestResponse> viewTests(List<TestEntity> tests) {
        List<TestResponse> testResponses = new ArrayList<>();
        for (TestEntity t : tests) {
            testResponses.add(viewTest(t));
        }
        return testResponses;
    }

    public TestEntity update(TestEntity test, TestRequest testRequest) {

        test.setTestName(testRequest.getTestName());
        return test;

    }
}
