package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponse viewCourse(CourseEntity course) {
        if (course == null) {
            return null;
        }
        CourseResponse courseResponse = new CourseResponse();
        if (course.getId() != null) {
            courseResponse.setId(course.getId());
        }
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDateOfStart(course.getDateOfStart());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setImage(course.getImage());
        return courseResponse;
    }

    public List<CourseResponse> viewCourses(List<CourseEntity> courses) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (CourseEntity c : courses) {
            courseResponses.add(viewCourse(c));
        }
        return courseResponses;
    }

}
