package kg.peaksoft.peaksoftlmsab4.dto.mapper.view;

import kg.peaksoft.peaksoftlmsab4.dto.response.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentViewMapper {

    public StudentResponse convertToStudentResponse(Student student){
        if(student==null){
            return null;
        }
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setId(String.valueOf(student.getId()));
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setMobilePhone(student.getMobilePhone());
        studentResponse.setStudyFormat(student.getStudyFormat());
        return studentResponse;
    }

}
