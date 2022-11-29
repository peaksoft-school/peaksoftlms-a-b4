package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.response.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import org.springframework.stereotype.Component;

@Component
public class InstructorViewMapper {

    public InstructorResponse convertToInstructorResponse(InstructorEntity instructor) {
        if (instructor == null) {
            return null;
        }
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setId(String.valueOf(instructor.getId()));
        instructorResponse.setFullName(instructor.getFirstName() + " " + instructor.getLastName());
        instructorResponse.setPhoneNumber(instructor.getPhoneNumber());
        instructorResponse.setSpecialization(instructor.getSpecialization());
        instructorResponse.setEmail(instructor.getAuthInfo().getEmail());
        return instructorResponse;
    }

}