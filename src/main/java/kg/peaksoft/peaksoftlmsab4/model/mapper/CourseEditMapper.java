package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AWSS3Service;
import org.springframework.stereotype.Component;

@Component
public class CourseEditMapper {

    AWSS3Service awss3Service;

    public CourseEntity create(CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        CourseEntity course = new CourseEntity();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        course.setImage(awss3Service.uploadFile(courseRequest.getImage()));

        return course;
    }

    public CourseEntity update(CourseEntity course, CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        course.setImage(awss3Service.uploadFile(courseRequest.getImage()));
        return course;
    }
}
