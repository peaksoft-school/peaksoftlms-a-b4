package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("SELECT case WHEN COUNT (s)>0 then true else false end FROM StudentEntity s WHERE s.authInfo.email =?1")
    boolean existsByEmail(String email);

    @Query("SELECT f FROM StudentEntity f WHERE LOWER(CONCAT(f.firstName,f.lastName) ) LIKE %?1%")
    List<StudentEntity> findByStudentName(@Param("fullName") String fullName);

    Page<StudentEntity> findStudentEntitiesByStudyFormat(Pageable pageable, StudyFormat studyFormat);

    @Query("SELECT s FROM StudentEntity s WHERE s.authInfo.email=?1")
    StudentEntity getByEmail(String email);

}