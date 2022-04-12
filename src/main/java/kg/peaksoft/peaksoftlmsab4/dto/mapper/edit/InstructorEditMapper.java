package kg.peaksoft.peaksoftlmsab4.dto.mapper.edit;

import kg.peaksoft.peaksoftlmsab4.dto.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.entity.Instructor;
import kg.peaksoft.peaksoftlmsab4.enumPackage.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InstructorEditMapper {

    public Instructor saveInstructor( InstructorRequest instructorRequest){
        if(instructorRequest==null){
            return null;
        }

        Instructor instructor=new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setMobilePhone(instructorRequest.getMobilePhone());
        instructor.setSpecialization(instructorRequest.getSpecialization());

        AuthInfo authInfo=new AuthInfo();
        authInfo.setEmail(instructorRequest.getEmail());
        authInfo.setPassword(instructorRequest.getPassword());
        authInfo.setRole(Role.INSTRUCTOR);

        instructor.setAuthInfo(authInfo);
        return instructor;
    }

}
