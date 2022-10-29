package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity,Long> {
    @Query("select t.questions from TestEntity t where t.id=?1")
    List<QuestionEntity> allQuestionsFromTest(Long testId);
}
