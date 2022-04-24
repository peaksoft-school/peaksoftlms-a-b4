package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;

import java.util.List;

public interface InstructorService {

    ResponseEntity saveInstructor(InstructorRequest instructorRequest);

    List<InstructorResponse> getAllInstructor();

    InstructorResponse getInstructorById(Long id);

    ResponseEntity updateInstructor(Long id, InstructorRequest instructorRequest);

    ResponseEntity deleteInstructor(Long id);

    ResponseEntity addInstructorToCourse(Long courseId, Long instructorId);
}
