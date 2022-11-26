package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.LessonResponseForGet;

import java.util.List;

public interface LessonService {
    LessonResponse create (LessonRequest lessonRequest,Long courseId);

    List<LessonResponseForGet> getAll();

    LessonResponseForGet getById(Long id);

    LessonResponse update(Long id, LessonRequest lessonRequest);

    LessonResponse deleteById(Long id);
}
