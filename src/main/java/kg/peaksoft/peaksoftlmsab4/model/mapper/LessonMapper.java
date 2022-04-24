package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LessonMapper {

    public LessonEntity mapToEntity(LessonRequest lessonRequest,Long id) {
        if (lessonRequest == null) {
            return null;
        }
        LessonEntity lesson = new LessonEntity();
        lesson.setId(id);
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }

    public LessonResponse mapToResponse(LessonEntity lesson){
        if(lesson==null){
            return null;
        }
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setLessonName(lesson.getLessonName());
        return lessonResponse;
    }
}
