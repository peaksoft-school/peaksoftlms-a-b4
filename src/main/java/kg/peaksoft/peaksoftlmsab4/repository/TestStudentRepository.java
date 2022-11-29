package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestStudentRepository extends JpaRepository<TestStudentEntity, Long> {

    @Query("SELECT s FROM TestStudentEntity s WHERE s.studentEntity.authInfo.email=?1")
    TestStudentEntity getByEmail(String email);

}
