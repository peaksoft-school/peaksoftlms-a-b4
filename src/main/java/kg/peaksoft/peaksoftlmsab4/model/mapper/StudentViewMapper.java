package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentViewMapper {
    public StudentResponse convertToStudentResponse(StudentEntity student){
        if(student==null){
            return null;
        }
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setId(String.valueOf(student.getId()));
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setMobilePhone(student.getMobilePhone());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
        return studentResponse;
    }
    public List<StudentResponse> viewStudents(Page<StudentEntity>students){
        List<StudentResponse>studentResponses=new ArrayList<>();
        for (StudentEntity s:students) {
            studentResponses.add(convertToStudentResponse(s));
        }
        return  studentResponses;
    }
}
