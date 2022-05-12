package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestEditMapper {
    private final QuestionEditMapper editMapper;

    public TestEntity create(TestRequest testRequest) {
//        List<QuestionEntity> questions = new ArrayList<>();
//
//        TestEntity test = new TestEntity();
//        test.setTestName(testRequest.getTestName());
//        int counter = 0;
//        for (QuestionRequest q : testRequest.getQuestions()) {
//
//            if (q.getQuestionType() == QuestionType.ONE) {
//                for (OptionRequest o : q.getOptions()) {
//                    if (o.getIsTrue()) {
//                        counter++;
//                    }
//                }
//                if (counter > 1) {
//                    throw new BadRequestException("You can't choose multiple variants");
//                } else {
//                    questions.add(editMapper.create(q));
//                    test.setQuestions(questions);
//                    return test;
//                }
//
//            }else
//                questions.add(editMapper.create(q));
//                test.setQuestions(questions);
//                return test;
//        }
//        return null;
//    }
        List<QuestionEntity> questions = new ArrayList<>();
        TestEntity test = new TestEntity();
        test.setTestName(testRequest.getTestName());
        int counter = 0;
        for (QuestionRequest q : testRequest.getQuestions()) {
            if (q.getQuestionType() == QuestionType.ONE) {
                for (OptionRequest v : q.getOptions()) {
                    if (v.getIsTrue()) {
                        counter++;
                    }
                }
                if (counter > 1) {
                    throw new BadRequestException("You can't choose multiply answer");
                } else {
                    questions.add(editMapper.create(q));
                    test.setQuestions(questions);
                    return test;
                }

            } else
                questions.add(editMapper.create(q));
            test.setQuestions(questions);
            return test;

        }
        return null;
    }







    public TestEntity update(TestEntity test, TestRequest testRequest) {

        test.setTestName(testRequest.getTestName());
        return test;

    }
}
