package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    @MockBean
    private  PasswordEncoder passwordEncoder;

    @Test
    void existsByEmail() {
        InstructorEntity instructor = new InstructorEntity();
        instructor.setFirstName("zamir");
        instructor.setLastName("sabyrjanov");
        instructor.setMobilePhone("7000343433");
        instructor.setSpecialization("java");

        AuthInfo authInfo = new AuthInfo();
        authInfo.setPassword(passwordEncoder.encode("123123"));
        authInfo.setEmail("zamir@mail.com");
        authInfo.setRole(Role.INSTRUCTOR);
        instructor.setAuthInfo(authInfo);

        instructorRepository.save(instructor);
        boolean exists = instructorRepository.existsByEmail("zamir@mail.com");

        assertThat(exists).isTrue();
    }

}