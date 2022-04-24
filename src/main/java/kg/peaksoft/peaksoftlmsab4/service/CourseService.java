package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;

import java.util.List;

public interface CourseService {
    ResponseEntity saveCourse(CourseRequest courseRequest);

    List<CourseResponse> findAllCourse();

    CourseResponse getById(Long courseId);

    ResponseEntity deleteCourseById(Long courseId);

    ResponseEntity updateCourseById(Long courseId, CourseRequest courseRequest);


}
