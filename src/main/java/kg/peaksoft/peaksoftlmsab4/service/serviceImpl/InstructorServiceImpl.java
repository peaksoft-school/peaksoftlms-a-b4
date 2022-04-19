package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.InstructorEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.InstructorViewMapper;
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

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        String email = instructorRequest.getEmail();
        checkEmail(email);

        String encoderPassword = passwordEncoder.encode(instructorRequest.getPassword());
        instructorRequest.setPassword(encoderPassword);
        InstructorEntity instructor = instructorRepository.save(instructorEditMapper
                .convertToInstructor(instructorRequest));
        log.info(" Instructor with name : {} has successfully saved to database", instructor.getFirstName());
        return instructorViewMapper
                .convertToInstructorResponse(instructor);
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
        InstructorEntity instructor = instructorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Instructor with id = {} does not exists", id);
                    throw new NotFoundException(
                            String.format("Instructor with id = %s does not exists", id)
                    );
                });
        return instructorViewMapper.convertToInstructorResponse(instructor);
    }

    @Transactional
    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        InstructorEntity instructor = instructorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Instructor with id = {} does not exists", id);
                    throw new NotFoundException(
                            String.format("Instructor with id = %s does not exists", id)
                    );
                });
        instructorEditMapper.updateInstructor(instructor, instructorRequest);
        return instructorViewMapper.convertToInstructorResponse(instructorRepository.save(instructor));
    }

    @Override
    public void deleteInstructor(Long id) {
        boolean existById = instructorRepository.existsById(id);
        if (!existById) {
            log.error("Instructor with id = {} does not exists, you can not delete it", id);
            throw new BadRequestException(
                    String.format("Instructor with id = %s does not exists, you can not delete it", id)
            );
        }
        instructorRepository.deleteById(id);
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
}