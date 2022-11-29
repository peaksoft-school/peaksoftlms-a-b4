package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.CourseViewMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.InstructorEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.InstructorViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.InstructorRepository;
import kg.peaksoft.peaksoftlmsab4.service.InstructorService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.InvalidArgumentException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.validation.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorEditMapper instructorEditMapper;
    private final InstructorViewMapper instructorViewMapper;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;
    private final CourseViewMapper courseViewMapper;
    private final Validator validator;

    @Override
    public InstructorResponse saveInstructor(InstructorRequest request) {
        String email = request.getEmail();
        if (!validator.patternMatches(email)) {
            throw new InvalidArgumentException(email + " is not valid");
        } else if (!validator.isValid(request.getPhoneNumber())) {
            throw new InvalidArgumentException(request.getPhoneNumber() + " is not valid");
        }
        checkEmail(email);
        String encoderPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encoderPassword);
        InstructorEntity instructor = instructorRepository.save(instructorEditMapper.convertToInstructor(request));
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
    public InstructorResponse updateInstructor(Long id, InstructorRequest request) {
        InstructorEntity instructor = getByIdMethod(id);
        String email = request.getEmail();
        String entityEmail = instructor.getAuthInfo().getEmail();
        if (!validator.patternMatches(email)) {
            throw new InvalidArgumentException(email + " is not valid");
        } else if (!validator.isValid(request.getPhoneNumber())) {
            throw new InvalidArgumentException(request.getPhoneNumber() + " is not valid");
        }
        if (!email.equals(entityEmail)) {
            checkEmail(email);
        }
        String encoderPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encoderPassword);
        instructorEditMapper.updateInstructor(instructor, request);
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
                    String.format("Instructor with id = %s does not exists, you can not delete it", id));
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
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Course with id = {} does not exists", courseId);
            throw new NotFoundException(
                    String.format("Course with id = %s does not exists", courseId));
        });
        for (Long id : instructorId) {
            course.setInstructor(getByIdMethod(id));
        }
        log.info("Successfully assign instructor with id = {} to course", instructorId);
        return courseViewMapper.viewCourse(courseRepository.save(course));
    }

    @Override
    public PaginationResponse<InstructorResponse> getInstructorPagination(int i, int size) {
        Pageable pageable = PageRequest.of(i, size);
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (InstructorEntity instructor : instructorRepository.findAll(pageable)) {
            instructorResponses.add(instructorViewMapper.convertToInstructorResponse(instructor));
        }
        PaginationResponse<InstructorResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setResponseList(instructorResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber() + 1);
        paginationResponse.setTotalPage(instructorRepository.findAll(pageable).getTotalPages());
        return paginationResponse;
    }

    @Override
    public List<CourseResponse> getInstructorsCourses(String email) {
        InstructorEntity instructor = instructorRepository.findInstructorByEmail(email).orElseThrow(() ->
                new NotFoundException("Vendor with email = " + email + " does not exists!"));
        return courseViewMapper.viewCourses(instructor.getCourses());
    }

    private void checkEmail(String email) {
        boolean exists = instructorRepository.existsByEmail(email);
        if (exists) {
            log.info("Instructor with email = {} already exists", email);
            throw new BadRequestException("Instructor with email = " + email + " already exists");
        }
    }

    private InstructorEntity getByIdMethod(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> {
            log.error("Instructor with id = {} does not exists", id);
            throw new NotFoundException(String.format("Instructor with id = %s does not exists", id));
        });
    }

}