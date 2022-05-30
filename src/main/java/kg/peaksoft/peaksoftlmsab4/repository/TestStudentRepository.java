package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestStudentRepository extends JpaRepository<TestStudentEntity, Long> {

    @Query("select s from TestStudentEntity s where s.studentEntity.authInfo.email=?1")
    TestStudentEntity getByEmail(String email);

    @Query("select case when count(a)>0 then true else false end" +
            " from TestStudentEntity a where a.studentEntity.authInfo.email =?1")
    boolean existsByEmail(String email);
}
