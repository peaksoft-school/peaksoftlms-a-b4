package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseServiceImplTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Test
    @Order(1)
    public void saveCourseTest() {
        CourseRequest course = CourseRequest.builder()
                .courseName("Peaksoft")
                .description("description")
                .image("image")
                .dateOfStart(LocalDate.now())
                .build();
         courseService.saveCourse(course);
         CourseEntity courseEntity = courseRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException("not found"));
        assertThat(courseEntity.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getCourseTest() {
        CourseEntity course = courseRepository.findById(1L).get();
        assertThat(course.getId()).isEqualTo(1L);
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
    }

    @Test
    @Order(5)
    public void deleteCourseTest() {
        CourseEntity course = courseRepository.findById(1L).get();
        courseRepository.delete(course);
        boolean expected = courseRepository.existsById(1L);
        assertThat(expected).isFalse();
    }

}