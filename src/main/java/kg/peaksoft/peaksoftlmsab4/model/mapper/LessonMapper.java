package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LessonResponseForGet;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LessonMapper {

    private final LinkMapper linkMapper;
    private final PresentationMapper presentationMapper;
    private final VideoMapper videoMapper;
    private final TestMapper testMapper;
    private final TaskMapper taskMapper;

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

    public List<LessonResponse> mapToResponse(List<LessonEntity> lessons) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (LessonEntity lessonEntity : lessons) {
            lessonResponses.add(mapToResponse(lessonEntity));
        }
        return lessonResponses;
    }

    public LessonResponse mapToResponse(LessonEntity lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .lessonName(lesson.getLessonName())
                .build();
    }

    public LessonResponseForGet mapToResponseForGetMethod(LessonEntity lesson) {
        if (lesson == null) {
            return null;
        }
        return LessonResponseForGet.builder()
                .id(lesson.getId())
                .lessonName(lesson.getLessonName())
                .linkResponse(linkMapper.mapToResponse(lesson.getLinkEntity()))
                .presentationResponse(presentationMapper.mapToResponse(lesson.getPresentationEntity()))
                .videoResponse(videoMapper.mapToResponse(lesson.getVideoEntity()))
                .taskResponse(taskMapper.mapToResponse(lesson.getTaskEntity()))
                .testResponse(testMapper.viewTest(lesson.getTestEntity()))
                .build();
    }

    public List<LessonResponseForGet> mapToResponseForGetMethod(List<LessonEntity> lessons) {
        List<LessonResponseForGet> lessonResponses = new ArrayList<>();
        for (LessonEntity lessonEntity : lessons) {
            lessonResponses.add(mapToResponseForGetMethod(lessonEntity));
        }
        return lessonResponses;
    }
}
