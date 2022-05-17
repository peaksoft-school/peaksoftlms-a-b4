package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    @Query("select case when count(s)>0 then true else false end" +
            " from StudentEntity s where s.authInfo.email =?1")
    boolean existsByEmail(String email);

    @Query("select s from StudentEntity s")
    List<StudentEntity> findAllPag(Pageable pageable);

    StudentEntity findByAuthInfoEmail(String email);
}