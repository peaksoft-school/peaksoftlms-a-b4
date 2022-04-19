package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository.deleteAll();
    }

    @Test
    public void findCourseByName(){
        CourseEntity courseEntity = CourseEntity.builder()
                .courseName("Peaksoft")
                .description("description")
                .image("image")
                .dateOfStart(LocalDate.now())
                .build();

        courseRepository.save(courseEntity);
        String name = "Peaksoft";

         boolean expected = courseRepository.existsByCourseName(name);
        assertThat(expected).isTrue();
    }

    @Test
    public void findCourseByNameDoesNotExists(){
        String name = "Megacom";

        boolean expected = courseRepository.existsByCourseName(name);
        assertThat(expected).isFalse();
    }
}