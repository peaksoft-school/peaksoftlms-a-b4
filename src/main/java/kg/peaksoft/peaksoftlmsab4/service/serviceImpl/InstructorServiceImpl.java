package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.dto.mapper.edit.InstructorEditMapper;
import kg.peaksoft.peaksoftlmsab4.dto.mapper.view.InstructorViewMapper;
import kg.peaksoft.peaksoftlmsab4.dto.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.entity.Instructor;
import kg.peaksoft.peaksoftlmsab4.repository.InstructorRepository;
import kg.peaksoft.peaksoftlmsab4.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorEditMapper instructorEditMapper;
    private final InstructorViewMapper instructorViewMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        return instructorViewMapper
                .convertToInstructorResponse(instructorRepository
                        .save(instructorEditMapper
                                .convertToInstructor(instructorRequest)));
    }

    @Override
    public List<InstructorResponse> getAllInstructor() {
        return instructorRepository.findAll()
                .stream()
                .map(instructorViewMapper::convertToInstructorResponse)
                .toList();
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow();
        return instructorViewMapper.convertToInstructorResponse(instructor);
    }

    @Transactional
    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow();

        String currentName = instructor.getFirstName();
        String newName = instructorRequest.getFirstName();

        if (!currentName.equals(newName)) {
            instructor.setFirstName(newName);
        }

        String currentLastName = instructor.getLastName();
        String newSurname = instructorRequest.getLastName();

        if (!currentLastName.equals(newSurname)) {
            instructor.setLastName(newSurname);
        }

        String currentEmail = instructor.getAuthInfo().getEmail();
        String newEmail = instructorRequest.getEmail();

        if (!currentEmail.equals(newEmail)) {
            instructor.getAuthInfo().setEmail(newEmail);
        }

        String currentPhoneNumber = instructor.getMobilePhone();
        String newPhoneNumber = instructorRequest.getMobilePhone();

        if (!currentPhoneNumber.equals(newPhoneNumber)) {
            instructor.setMobilePhone(newPhoneNumber);
        }

        String currentSpecialization = instructor.getSpecialization();
        String newSpecialization = instructorRequest.getSpecialization();

        if (!currentSpecialization.equals(newSpecialization)) {
            instructor.setSpecialization(newSpecialization);
        }

        //update password
        String currentPassword = instructor.getAuthInfo().getPassword(); // encoded
        String newPassword = instructorRequest.getPassword(); // not encoded

        if (!passwordEncoder.matches(newPassword, currentPassword)) {
            instructor.getAuthInfo().setPassword(passwordEncoder.encode(newPassword));
        }

        return instructorViewMapper.convertToInstructorResponse(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}