package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import java.util.List;

public interface LessonService {
    LessonResponse addLesson(LessonRequest lessonRequest,Long courseId);

    List<LessonResponse> getAllLessons();

    LessonResponse getLessonById(Long id);

    LessonResponse updateLesson(Long id, LessonRequest lessonRequest);

    void deleteLesson(Long id);
}
