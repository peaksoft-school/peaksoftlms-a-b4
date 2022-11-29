package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse create(Long id, QuestionRequest request);

    QuestionResponse update(Long id, QuestionRequest request);

    QuestionResponse findById(Long id);

    QuestionResponse deleteById(Long id);

    List<QuestionResponse> findAll();

    QuestionResponse findByQuestionToTest(Long testId, Long questionId);

}
