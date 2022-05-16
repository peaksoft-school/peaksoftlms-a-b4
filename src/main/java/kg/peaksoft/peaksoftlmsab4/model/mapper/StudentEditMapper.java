package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentEditMapper {
    private final PasswordEncoder passwordEncoder;
    public StudentEntity convertToStudent(StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }
        StudentEntity student = new StudentEntity();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setMobilePhone(studentRequest.getMobilePhone());
        student.setStudyFormat(studentRequest.getStudyFormat());

        AuthInfo authInfo=new AuthInfo();
        authInfo.setEmail(studentRequest.getEmail());
        authInfo.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
        authInfo.setRole(Role.STUDENT);

        student.setAuthInfo(authInfo);

        return student;
    }

    public void updateStudent(StudentEntity studentEntity, StudentRequest studentRequest) {
        studentEntity.setFirstName(studentRequest.getFirstName());
        studentEntity.setLastName(studentRequest.getLastName());
        studentEntity.setMobilePhone(studentRequest.getMobilePhone());
        studentEntity.setStudyFormat(studentRequest.getStudyFormat());

        AuthInfo authInfo=new AuthInfo();
        authInfo.setEmail(studentRequest.getEmail());
        authInfo.setPassword(studentRequest.getPassword());
        authInfo.setRole(Role.STUDENT);
    }
}
