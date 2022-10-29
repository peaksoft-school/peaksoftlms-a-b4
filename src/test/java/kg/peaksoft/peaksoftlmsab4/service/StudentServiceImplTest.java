//package kg.peaksoft.peaksoftlmsab4.service;
//
//import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
//import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
//import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
//import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@RequiredArgsConstructor
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class aStudentServiceImplTest {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Test
//    @Order(1)
//    @Rollback(value = false)
//    public void saveStudentTest() {
//        StudentEntity studentEntity = StudentEntity.builder()
//                .firstName("Akul")
//                .lastName("nurbekov")
//                .email("adfwd@mail.com")
//                .mobilePhone("90121290192")
//                .studyFormat(StudyFormat.ONLINE)
//                .build();
//        studentRepository.save(studentEntity);
//        assertThat(studentEntity.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    @Order(2)
//    public void getStudentTest() {
//        StudentEntity studentEntity = studentRepository.findById(1L).get();
//        assertThat(studentEntity.getId()).isEqualTo(1l);
//        System.out.println(studentEntity.getId());
//    }
//
//    @Test
//    @Order(3)
//    public void getListStudentTest() {
//        List<StudentEntity> students = studentRepository.findAll();
//        assertThat(students.size()).isGreaterThan(0);
//    }
//
//    @Test
//    @Order(4)
//    @Rollback(value = false)
//    public void updateUserTest() {
//        StudentEntity studentEntity = studentRepository.findById(1L).get();
//        studentEntity.setFirstName("Akylbek");
//        StudentEntity studentUpdated = studentRepository.save(studentEntity);
//        assertThat(studentUpdated.getFirstName()).isEqualTo("Akylbek");
//    }
//
//    @Test
//    @Order(5)
//    public void deleteUserTest() {
//        StudentEntity studentEntity = studentRepository.findById(1L).get();
//        studentRepository.delete(studentEntity);
//        boolean expected = studentRepository.existsById(1L);
//        assertThat(expected).isFalse();
//    }
//
//    @Test
//    void setStudentToGroup() {
//        StudentEntity studentEntity = StudentEntity.builder()
//                .firstName("Akul")
//                .lastName("nurbekov")
//                .email("adfwd@mail.com")
//                .mobilePhone("90121290192")
//                .studyFormat(StudyFormat.ONLINE)
//                .build();
//        GroupEntity groupEntity = GroupEntity.builder()
//                .groupName("Peaksoft")
//                .description("description")
//                .image("image")
//                .dateOfStart(LocalDate.now())
//                .build();
//        studentEntity.setGroup(groupEntity);
//        assertThat(studentEntity.getGroup().getGroupName()).isEqualTo("Peaksoft");
//    }
//}