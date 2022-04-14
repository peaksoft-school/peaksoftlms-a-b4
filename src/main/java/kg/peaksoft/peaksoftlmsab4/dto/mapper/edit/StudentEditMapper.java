package kg.peaksoft.peaksoftlmsab4.dto.mapper.edit;

import kg.peaksoft.peaksoftlmsab4.dto.request.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentEditMapper {

    public Student convertToStudent(StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setMobilePhone(studentRequest.getMobilePhone());
        student.setStudyFormat(studentRequest.getStudyFormat());
        return student;

    }
}
