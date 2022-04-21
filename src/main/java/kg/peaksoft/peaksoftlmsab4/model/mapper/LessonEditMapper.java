package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LessonEditMapper {

    public LessonEntity convertToLesson(LessonRequest lessonRequest) {
        if (lessonRequest == null) {
            return null;
        }
        LessonEntity lesson = new LessonEntity();
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }

    public void updateStudent(LessonEntity lessonEntity, LessonRequest lessonRequest) {
        lessonEntity.setLessonName(lessonRequest.getLessonName());
    }
}
