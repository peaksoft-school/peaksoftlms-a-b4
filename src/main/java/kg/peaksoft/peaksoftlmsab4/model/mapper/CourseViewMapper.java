package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseViewMapper {
    public CourseResponse viewCourse(CourseEntity course){

        if (course==null){
            return null;
        }
        CourseResponse courseResponse=new CourseResponse();
        if (course.getId()!=null){
            courseResponse.setId(course.getId());
        }
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDateOfStart(course.getDateOfStart());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setImage(course.getImage());

        return courseResponse;
    }
}
