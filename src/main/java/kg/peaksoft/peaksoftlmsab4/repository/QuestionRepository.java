package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    @Query("select t.questions from TestEntity t where t.id =?1")
    List<QuestionEntity> findAllWithTestId(Long testId);

    @Query("select q.id from QuestionEntity q")
    List<Long> findAllQuestionId();
}
