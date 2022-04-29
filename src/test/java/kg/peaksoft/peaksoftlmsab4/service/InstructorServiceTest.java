package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstructorServiceTest {
    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveInstructorTest() {
        InstructorEntity instructorEntity = InstructorEntity.builder()
                .firstName("zamir")
                .lastName("sabyrjanov")
                .mobilePhone("700232322")
                .specialization("java")
                .build();

        instructorRepository.save(instructorEntity);
        assertThat(instructorEntity.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getInstructorTest() {
        InstructorEntity instructor = instructorRepository.findById(1L).get();
        assertThat(instructor.getId()).isEqualTo(1l);
    }

    @Test
    @Order(3)
    public void getListInstructorTest() {
        List<InstructorEntity> instructors = instructorRepository.findAll();
        assertThat(instructors.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateInstructorTest() {
        InstructorEntity instructor = instructorRepository.findById(1L).get();
        instructor.setFirstName("Chyngyz");
        InstructorEntity instructorUpdated = instructorRepository.save(instructor);
        assertThat(instructorUpdated.getFirstName()).isEqualTo("Chyngyz");
    }

    @Test
    @Order(5)
    public void deleteInstructorTest() {
        InstructorEntity instructor = instructorRepository.findById(1L).get();
        instructorRepository.delete(instructor);
        boolean expected = instructorRepository.existsById(1L);
        assertThat(expected).isFalse();
    }
}
