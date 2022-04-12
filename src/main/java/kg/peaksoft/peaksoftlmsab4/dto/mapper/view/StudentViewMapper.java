package kg.peaksoft.peaksoftlmsab4.dto.mapper.view;

import kg.peaksoft.peaksoftlmsab4.dto.response.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.entity.Student;

public class StudentViewMapper {

    public StudentResponse viewStudent(Student student){
        if(student==null){
            return null;
        }
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setId(String.valueOf(student.getId()));
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setMobilePhone(student.getMobilePhone());
        studentResponse.setStudyFormat(student.getStudyFormat());
        studentResponse.setEmail(student.getAuthInfo().getEmail());
        return studentResponse;
    }

}
