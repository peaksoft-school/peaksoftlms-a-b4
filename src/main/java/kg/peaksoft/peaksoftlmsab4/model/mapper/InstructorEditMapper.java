package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class InstructorEditMapper {

    public InstructorEntity convertToInstructor(InstructorRequest instructorRequest) {
        if (instructorRequest == null) {
            return null;
        }
        InstructorEntity instructor = new InstructorEntity();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(instructorRequest.getEmail());
        authInfo.setPassword(instructorRequest.getPassword());
        authInfo.setRole(Role.INSTRUCTOR);

        instructor.setAuthInfo(authInfo);
        return instructor;
    }

    public void updateInstructor(InstructorEntity instructorEntity, InstructorRequest instructorRequest) {
        instructorEntity.setFirstName(instructorRequest.getFirstName());
        instructorEntity.setLastName(instructorRequest.getLastName());
        instructorEntity.setSpecialization(instructorRequest.getSpecialization());
        instructorEntity.setPhoneNumber(instructorRequest.getPhoneNumber());

        instructorEntity.getAuthInfo().setEmail(instructorRequest.getEmail());
        instructorEntity.getAuthInfo().setPassword(instructorRequest.getPassword());
        instructorEntity.getAuthInfo().setRole(Role.INSTRUCTOR);

    }
}