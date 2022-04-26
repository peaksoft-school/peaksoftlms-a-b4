package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>,
        PagingAndSortingRepository<StudentEntity, Long> {

    @Query("select case when count(s)>0 then true else false end" +
            " from StudentEntity s where s.email =?1")
    boolean existsByEmail(String email);

    @Query("select f from StudentEntity f where f.firstName like %?1%")
    List<StudentEntity> findByStudentName(@Param("firstName") String firstName);

    List<StudentEntity>findStudentByStudyFormat(StudyFormat studyFormat);


}