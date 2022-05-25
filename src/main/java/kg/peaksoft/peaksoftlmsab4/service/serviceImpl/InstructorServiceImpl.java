package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.model.entity.Validator;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.InvalidArgumentException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.CourseViewMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.InstructorEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.InstructorViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.InstructorRepository;
import kg.peaksoft.peaksoftlmsab4.service.InstructorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorEditMapper instructorEditMapper;
    private final InstructorViewMapper instructorViewMapper;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;
    private final CourseViewMapper courseViewMapper;
    private final Validator validator;

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        String email = instructorRequest.getEmail();
        if (!validator.patternMatches(email)) {
            throw new InvalidArgumentException(email + " is not valid");
        } else if (!validator.isValid(instructorRequest.getPhoneNumber())){
            throw new InvalidArgumentException(instructorRequest.getPhoneNumber() + " is not valid");
        }
        checkEmail(email);

        String encoderPassword = passwordEncoder.encode(instructorRequest.getPassword());
        instructorRequest.setPassword(encoderPassword);
        InstructorEntity instructor = instructorRepository.save(instructorEditMapper
                .convertToInstructor(instructorRequest));
        log.info(" Instructor with name : {} has successfully saved to database", instructor.getFirstName());
        return instructorViewMapper.convertToInstructorResponse(instructor);
    }

    @Override
    public List<InstructorResponse> getAllInstructor() {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (InstructorEntity instructors : instructorRepository.findAll()) {
            instructorResponses.add(instructorViewMapper.convertToInstructorResponse(instructors));
        }
        log.info("Found {} instructors ", instructorResponses.size());
        return instructorResponses;
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        InstructorEntity instructor = getByIdMethod(id);
        return instructorViewMapper.convertToInstructorResponse(instructor);
    }

    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        InstructorEntity instructor = getByIdMethod(id);
        String email = instructorRequest.getEmail();
        String entityEmail = instructor.getAuthInfo().getEmail();
        if (!validator.patternMatches(email)) {
            throw new InvalidArgumentException(email + " is not valid");
        } else if (!validator.isValid(instructorRequest.getPhoneNumber())){
            throw new InvalidArgumentException(instructorRequest.getPhoneNumber() + " is not valid");
        }
        if (!email.equals(entityEmail)) {
            checkEmail(email);
        }
        String encoderPassword = passwordEncoder.encode(instructorRequest.getPassword());
        instructorRequest.setPassword(encoderPassword);
        instructorEditMapper.updateInstructor(instructor, instructorRequest);
        InstructorEntity savedInstructor = instructorRepository.save(instructor);
        log.info(" Instructor with name : {} has successfully updated", savedInstructor.getFirstName());
        return instructorViewMapper.convertToInstructorResponse(savedInstructor);
    }

    @Override
    public InstructorResponse deleteInstructor(Long id) {
        boolean existById = instructorRepository.existsById(id);
        if (!existById) {
            log.error("Instructor with id = {} does not exists, you can not delete it", id);
            throw new BadRequestException(
                    String.format("Instructor with id = %s does not exists, you can not delete it", id)
            );
        }
        InstructorEntity instructorEntity = getByIdMethod(id);
        instructorRepository.deleteById(id);
        log.info("Instructor with id = {} has successfully deleted", id);
        return instructorViewMapper.convertToInstructorResponse(instructorEntity);
    }

    @Override
    public InstructorResponse addInstructorToCourse(Long courseId, Long instructorId) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(() ->
                new NotFoundException(String.format("Course with id = %s does not exists", courseId)));
        InstructorEntity instructor = getByIdMethod(instructorId);
        instructor.setCourse(course);
        log.info("Instructor with id = {} has successfully added to course", courseId);
        return instructorViewMapper.convertToInstructorResponse(instructorRepository.save(instructor));
    }

    @Override
    public CourseResponse assignInstructorsToCourse(Long courseId, List<Long> instructorId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exists", courseId);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exists", courseId)
                    );
                });
        for (Long id : instructorId) {
            course.setInstructor(getByIdMethod(id));
        }
        log.info("Successfully assign instructor with id = {} to course", instructorId);
        return courseViewMapper.viewCourse(courseRepository.save(course));
    }

    @Override
    public List<CourseResponse> getInstructorsCourses(String email) {
        InstructorEntity instructor = instructorRepository.findInstructorByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        "Vendor with email = " + email + " does not exists!"
                ));

        return courseViewMapper.viewCourses(instructor.getCourses());
    }

    private void checkEmail(String email) {
        boolean exists = instructorRepository.existsByEmail(email);
        if (exists) {
            log.info("Instructor with email = {} already exists", email);
            throw new BadRequestException(
                    "Instructor with email = " + email + " already exists"
            );
        }
    }

    private InstructorEntity getByIdMethod(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Instructor with id = {} does not exists", id);
                    throw new NotFoundException(
                            String.format("Instructor with id = %s does not exists", id)
                    );
                });
    }
}