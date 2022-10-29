package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity,Long> {

    @Query("select case when count(a)>0 then true else false end" +
            " from AuthInfo a where a.email =?1")
    boolean existsByEmail(String email);

    @Query("select i from InstructorEntity i where i.authInfo.email = ?1")
    Optional<InstructorEntity> findInstructorByEmail(String email);
}