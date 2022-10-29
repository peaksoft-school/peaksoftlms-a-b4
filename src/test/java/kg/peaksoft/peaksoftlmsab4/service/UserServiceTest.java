//package kg.peaksoft.peaksoftlmsab4.service;
//
//import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
//import kg.peaksoft.peaksoftlmsab4.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@RequiredArgsConstructor
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    @Order(1)
//    @Rollback(value = false)
//    public void saveUserTest() {
//        UserEntity userEntity = UserEntity.builder()
//                .firstName("Akul")
//                .lastName("nurbekov")
//                .build();
//        userRepository.save(userEntity);
//        assertThat(userEntity.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    @Order(2)
//    public void getUserTest() {
//        UserEntity userEntity = userRepository.findById(1L).get();
//        assertThat(userEntity.getId()).isEqualTo(1l);
//    }
//
//    @Test
//    @Order(3)
//    public void getListUserTest() {
//        List<UserEntity> users = userRepository.findAll();
//        assertThat(users.size()).isGreaterThan(0);
//    }
//
//    @Test
//    @Order(4)
//    @Rollback(value = false)
//    public void updateUserTest() {
//        UserEntity userEntity = userRepository.findById(1L).get();
//        userEntity.setFirstName("Akylbek");
//        UserEntity userUpdated = userRepository.save(userEntity);
//        assertThat(userUpdated.getFirstName()).isEqualTo("Akylbek");
//    }
//
//    @Test
//    @Order(5)
//    public void deleteUserTest() {
//        UserEntity user = userRepository.findById(1L).get();
//        userRepository.delete(user);
//        boolean expected = userRepository.existsById(1L);
//        assertThat(expected).isFalse();
//    }
//}
