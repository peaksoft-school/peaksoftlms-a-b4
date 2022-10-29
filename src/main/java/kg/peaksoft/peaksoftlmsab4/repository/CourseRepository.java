package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByCourseName(String courseName);

    @Query("select c from CourseEntity c")
    List<CourseEntity> findAllPag(Pageable pageable);

    @Query("select s from StudentEntity s inner join s.courses c where c.id = :id order by s.id asc ")
    List<StudentEntity> getStudents(Long id);
}
