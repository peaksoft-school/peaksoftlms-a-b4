package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.dto.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.InstructorResponse;

import java.util.List;

public interface InstructorService {

    InstructorResponse saveInstructor(InstructorRequest instructorRequest);

    List<InstructorResponse> getAllInstructor();

    InstructorResponse getInstructorById(Long id) ;

    InstructorResponse updateInstructor(Long id,InstructorRequest instructorRequest);

    void deleteInstructor(Long id);




}
