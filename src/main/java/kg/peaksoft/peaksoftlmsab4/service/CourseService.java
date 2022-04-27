package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;

import java.util.List;

public interface CourseService {
    CourseResponse saveCourse(CourseRequest courseRequest);

    List<CourseResponse> findAllCourse();

    CourseResponse getById(Long courseId);

    CourseResponse deleteCourseById(Long courseId);

    CourseResponse updateCourseById(Long courseId, CourseRequest courseRequest);


    List<StudentResponse> getAllStudentsByCourseId(Long id);

    List<InstructorResponse> getAllTeacherByCourseId(Long id);
}
