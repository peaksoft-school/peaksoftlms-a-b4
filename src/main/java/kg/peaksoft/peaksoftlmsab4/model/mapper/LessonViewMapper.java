package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class LessonViewMapper {
    public LessonResponse convertToLessonResponse(LessonEntity lesson){
        if(lesson==null){
            return null;
        }
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setLessonName(lesson.getLessonName());
        return lessonResponse;
    }
}
