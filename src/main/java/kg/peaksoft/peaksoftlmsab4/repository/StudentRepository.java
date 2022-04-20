package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    @Query("select case when count(s)>0 then true else false end" +
            " from StudentEntity s where s.email =?1")
    boolean existsByEmail(String email);
}