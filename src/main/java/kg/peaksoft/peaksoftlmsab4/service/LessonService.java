package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import java.util.List;

public interface LessonService {
    LessonResponse create (LessonRequest lessonRequest,Long courseId);

    List<LessonResponse> getAll();

    LessonResponse getById(Long id);

    LessonResponse update(Long id, LessonRequest lessonRequest);

    LessonResponse deleteById(Long id);
}
