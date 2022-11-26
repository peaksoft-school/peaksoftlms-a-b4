package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
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
        studentResponse.setFullName(student.getFirstName() + " " + student.getLastName());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setEmail(student.getAuthInfo().getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
        studentResponse.setRole(student.getAuthInfo().getRole());
        studentResponse.setGroupName(student.getGroup().getGroupName());
        return studentResponse;
    }

    public List<StudentResponse> convertToStudents(List<StudentEntity> studentEntityList){
        List<StudentResponse>studentResponses=new ArrayList<>();

        for (StudentEntity s:studentEntityList) {
            studentResponses.add(convertToStudentResponse(s));
        }

        return studentResponses;
    }
}
