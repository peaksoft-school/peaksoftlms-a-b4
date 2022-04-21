package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InstructorEditMapper {
    private final PasswordEncoder passwordEncoder;

    public InstructorEntity convertToInstructor(InstructorRequest instructorRequest) {
        if (instructorRequest == null) {
            return null;
        }
        InstructorEntity instructor = new InstructorEntity();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setMobilePhone(instructorRequest.getMobilePhone());
        instructor.setSpecialization(instructorRequest.getSpecialization());

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(instructorRequest.getEmail());
        authInfo.setPassword(instructorRequest.getPassword());
        authInfo.setRole(Role.INSTRUCTOR);

        instructor.setAuthInfo(authInfo);
        return instructor;
    }

    public void updateInstructor(InstructorEntity instructorEntity,InstructorRequest instructorRequest){
        instructorEntity.setFirstName(instructorRequest.getFirstName());
        instructorEntity.setLastName(instructorRequest.getLastName());
        instructorEntity.setSpecialization(instructorRequest.getSpecialization());
        instructorEntity.setMobilePhone(instructorRequest.getMobilePhone());

        AuthInfo authInfo=new AuthInfo();
        authInfo.setEmail(instructorRequest.getEmail());
        authInfo.setPassword(instructorRequest.getPassword());
        authInfo.setRole(Role.INSTRUCTOR);

    }
}