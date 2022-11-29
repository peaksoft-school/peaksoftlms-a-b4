package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {

    @Query("SELECT case WHEN COUNT (a)>0 then true else false end FROM AuthInfo a WHERE a.email =?1")
    boolean existsByEmail(String email);

    @Query("SELECT i FROM InstructorEntity i WHERE i.authInfo.email = ?1")
    Optional<InstructorEntity> findInstructorByEmail(String email);

}