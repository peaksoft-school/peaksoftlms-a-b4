package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestResultRepository extends JpaRepository<TestResultEntity, Long> {

    Long countAllByAnswerTrue();

    Long countAllByAnswerFalse();

    @Query("select count (id) from  TestResultEntity ")
    Long countAllById();
}
