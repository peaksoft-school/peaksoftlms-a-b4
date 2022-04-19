package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseServiceImplTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCourseTest() {
        CourseEntity courseEntity = CourseEntity.builder()
                .courseName("Peaksoft")
                .description("description")
                .image("image")
                .dateOfStart(LocalDate.now())
                .build();

        courseRepository.save(courseEntity);
        System.out.println("save");
        System.out.println(courseEntity);

        assertThat(courseEntity.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void getCourseTest() {
        CourseEntity course = courseRepository.findById(1L).get();
        assertThat(course.getId()).isEqualTo(1l);
        System.out.println(course.getId());
    }

    @Test
    @Order(3)
    public void getListCourseTest() {
        List<CourseEntity> courses = courseRepository.findAll();
        assertThat(courses.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCourseTest() {
        CourseEntity course = courseRepository.findById(1L).get();
        course.setCourseName("Megacom");
        CourseEntity courseUpdated = courseRepository.save(course);
        assertThat(courseUpdated.getCourseName()).isEqualTo("Megacom");
        System.out.println("continue");
    }

    @Test
    @Order(5)
    public void deleteCourseTest() {
        CourseEntity course = courseRepository.findById(1L).get();
        courseRepository.delete(course);
        String name = "Megacom";
        boolean expected =
                courseRepository.existsByCourseName(name);
        System.out.println(expected);
        assertThat(expected).isFalse();
    }

}