package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentEditMapper {
    public StudentEntity convertToStudent(StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }
        StudentEntity student = new StudentEntity();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setMobilePhone(studentRequest.getMobilePhone());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        return student;
    }

    public void updateStudent(StudentEntity studentEntity, StudentRequest studentRequest) {
        studentEntity.setFirstName(studentRequest.getFirstName());
        studentEntity.setLastName(studentRequest.getLastName());
        studentEntity.setMobilePhone(studentRequest.getMobilePhone());
        studentEntity.setEmail(studentRequest.getEmail());
        studentEntity.setStudyFormat(studentRequest.getStudyFormat());
    }
}
