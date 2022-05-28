package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
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
    @Query("select case when count(s)>0 then true else false end" +
            " from StudentEntity s where s.authInfo.email =?1")
    boolean existsByEmail(String email);


    @Query("select f from StudentEntity f where f.firstName like %?1%")
    List<StudentEntity> findByStudentName(@Param("firstName") String firstName);

    Page<StudentEntity> findStudentEntitiesByStudyFormat(Pageable pageable, StudyFormat studyFormat);

    @Query("select s from StudentEntity s where s.authInfo.email=?1")
    StudentEntity getByEmail(String email);
}