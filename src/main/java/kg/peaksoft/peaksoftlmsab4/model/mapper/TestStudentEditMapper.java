package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentRequest;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor

public class TestStudentEditMapper {
    private final OptionRepository optionRepository;
    private final TestStudentRepository studentRepository;


    public List<TestStudentEntity> createResult(TestStudentRequest testStudentRequest) {


        List<OptionEntity> options = optionRepository.findAll();


        System.out.println(options);

        List<TestStudentEntity> resultEntities = new ArrayList<>();

        TestStudentEntity testStudent = new TestStudentEntity();

        Iterator<TestStudentEntity>iterator= resultEntities.iterator();
        for (OptionEntity o : options) {


            testStudent.setAnswer(o.getAnswer());
            testStudent.setIsTrue(o.getIsTrue());
resultEntities.add(testStudent);


        }
return resultEntities;
    }


    public TestStudentEntity update(TestStudentEntity testResult, TestStudentRequest testResultRequest) {

        return testResult;
    }

    private OptionEntity getByIdMethod(Long optionId) {
        return optionRepository.findById(optionId).
                orElseThrow(() -> {
                    log.error("Question with id = {} does not exists", optionId);
                    throw new NotFoundException(
                            String.format("Question with id = %s does not exists", optionId)
                    );
                });
    }
}
