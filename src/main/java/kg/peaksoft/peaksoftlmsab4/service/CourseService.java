package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.*;

import java.util.List;

public interface CourseService {
    CourseResponse saveCourse(CourseRequest courseRequest);

    List<CourseResponse> findAllCourse();

    CourseResponse getById(Long courseId);

    CourseResponse deleteCourseById(Long courseId);

    CourseResponse updateCourseById(Long courseId, CourseRequest courseRequest);


    List<StudentResponse> getAllStudentsByCourseId(Long id);

    List<InstructorResponse> getAllInstructorByCourseId(Long id);

    List<LessonResponseForGet> getAllLessonByCourseId(Long id);

    String assignInstructorToCourse(AssignRequest assignRequest);

    PaginationResponse<CourseResponse> getCoursePagination(int page, int size);


}
