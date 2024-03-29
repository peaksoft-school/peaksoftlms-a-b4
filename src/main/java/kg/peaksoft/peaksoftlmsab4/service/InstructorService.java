package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.PaginationResponse;

import java.util.List;

public interface InstructorService {

    InstructorResponse saveInstructor(InstructorRequest request);

    List<InstructorResponse> getAllInstructor();

    InstructorResponse getInstructorById(Long id);

    InstructorResponse updateInstructor(Long id, InstructorRequest request);

    InstructorResponse deleteInstructor(Long id);

    InstructorResponse addInstructorToCourse(Long courseId, Long instructorId);

    List<CourseResponse> getInstructorsCourses(String email);

    CourseResponse assignInstructorsToCourse(Long courseId, List<Long> instructorId);

    PaginationResponse<InstructorResponse> getInstructorPagination(int i, int size);

}
