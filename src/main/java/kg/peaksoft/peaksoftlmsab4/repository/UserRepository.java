package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
