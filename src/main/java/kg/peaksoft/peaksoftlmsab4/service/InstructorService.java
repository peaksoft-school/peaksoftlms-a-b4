package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.*;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.InstructorRequest;

import java.util.List;

public interface InstructorService {

    InstructorResponse saveInstructor(InstructorRequest instructorRequest);

    List<InstructorResponse> getAllInstructor();

    InstructorResponse getInstructorById(Long id);

    InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest);

    InstructorResponse deleteInstructor(Long id);

    InstructorResponse addInstructorToCourse(Long courseId, Long instructorId);


    List<CourseResponse> getInstructorsCourses(String email);

    CourseResponse assignInstructorsToCourse(Long courseId, List<Long> instructorId);

    PaginationResponse<InstructorResponse> getInstructorPagination(int i, int size);

}
