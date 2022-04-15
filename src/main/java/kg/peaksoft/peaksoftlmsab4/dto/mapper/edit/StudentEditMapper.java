package kg.peaksoft.peaksoftlmsab4.dto.mapper.edit;

import kg.peaksoft.peaksoftlmsab4.dto.request.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
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
        student.setEmail(studentRequest.getEmail());
        return student;
    }
}
