package kg.peaksoft.peaksoftlmsab4.dto.mapper.view;

import kg.peaksoft.peaksoftlmsab4.dto.response.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorViewMapper {

    InstructorResponse viewInstructor(Instructor instructor){
        if(instructor==null){
            return null;
        }
        InstructorResponse instructorResponse=new InstructorResponse();
        instructorResponse.setId(String.valueOf(instructor.getId()));
        instructorResponse.setFirstName(instructor.getFirstName());
        instructorResponse.setLastName(instructor.getLastName());
        instructorResponse.setMobilePhone(instructor.getMobilePhone());
        instructorResponse.setSpecialization(instructor.getSpecialization());
        instructorResponse.setEmail(instructor.getAuthInfo().getEmail());
        return instructorResponse;
    }
}
