package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LessonMapper {

    public LessonEntity mapToEntity(LessonRequest lessonRequest) {
        if (lessonRequest == null) {
            return null;
        }
        return LessonEntity.builder()
                .lessonName(lessonRequest.getLessonName())
                .build();
    }

    public LessonEntity update(LessonEntity lessonEntity, LessonRequest lessonRequest) {
        lessonEntity.setLessonName(lessonRequest.getLessonName());
        return lessonEntity;
    }

    public List<LessonResponse> mapToResponse(List<LessonEntity> lessons){
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (LessonEntity lessonEntity:lessons) {
            lessonResponses.add(mapToResponse(lessonEntity));
        }
        return lessonResponses;
    }

    public LessonResponse mapToResponse(LessonEntity lesson){
       return LessonResponse.builder()
               .id(lesson.getId())
               .lessonName(lesson.getLessonName())
               .build();
    }
}
