package kg.peaksoft.peaksoftlmsab4.dto.mapper.edit;

import kg.peaksoft.peaksoftlmsab4.dto.request.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.entity.Student;
import kg.peaksoft.peaksoftlmsab4.enumPackage.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentEditMapper {
    public Student saveStudent( StudentRequest studentRequest){
        if(studentRequest==null){
            return null;
        }
        Student student=new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(student.getLastName());
        student.setMobilePhone(studentRequest.getMobilePhone());
        student.setStudyFormat(studentRequest.getStudyFormat());

        AuthInfo authInfo=new AuthInfo();
        authInfo.setEmail(studentRequest.getEmail());
        authInfo.setPassword(studentRequest.getPassword());
        authInfo.setRole(Role.STUDENT);
        student.setAuthInfo(authInfo);
        return student;

    }
}
