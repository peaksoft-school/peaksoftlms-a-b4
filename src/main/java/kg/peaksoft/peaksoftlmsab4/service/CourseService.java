package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.AssignRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.LessonResponseForGet;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.StudentResponse;

import java.util.List;

public interface CourseService {

    CourseResponse saveCourse(CourseRequest request);

    List<CourseResponse> findAllCourse();

    CourseResponse getById(Long courseId);

    CourseResponse deleteCourseById(Long courseId);

    CourseResponse updateCourseById(Long courseId, CourseRequest request);

    List<StudentResponse> getAllStudentsByCourseId(Long id);

    List<InstructorResponse> getAllInstructorByCourseId(Long id);

    List<LessonResponseForGet> getAllLessonByCourseId(Long id);

    String assignInstructorToCourse(AssignRequest request);

    PaginationResponse<CourseResponse> getCoursePagination(int page, int size);

}
