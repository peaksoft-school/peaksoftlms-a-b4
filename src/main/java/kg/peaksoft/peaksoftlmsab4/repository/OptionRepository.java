package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity,Long> {

    @Query("select o.options from QuestionEntity o where o.id =?1")
    List<OptionEntity> findAllWithQuestionId(Long id);

    @Query("select count (q.options) from QuestionEntity q where q.id=?1")
    int getCount(Long questionId);
}
