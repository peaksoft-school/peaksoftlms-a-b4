package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseEditMapper {

    public CourseEntity create(CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        CourseEntity course = new CourseEntity();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        course.setImage(courseRequest.getImage());

        return course;
    }

    public CourseEntity update(CourseEntity course, CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        course.setImage(courseRequest.getImage());
        return course;
    }
}
