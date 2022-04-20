package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity,Long> {

    @Query("select case when count(i)>0 then true else false end" +
            " from InstructorEntity i where i.authInfo.email =?1")
    boolean existsByEmail(String email);
}