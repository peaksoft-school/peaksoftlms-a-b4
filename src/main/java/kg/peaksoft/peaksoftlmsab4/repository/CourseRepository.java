package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    boolean existsByCourseName(String courseName);

    @Query("SELECT c FROM CourseEntity c")
    List<CourseEntity> findAllPag(Pageable pageable);

    @Query("SELECT s FROM StudentEntity s INNER JOIN s.courses c WHERE c.id = :id ORDER BY s.id ASC")
    List<StudentEntity> getStudents(Long id);

}
