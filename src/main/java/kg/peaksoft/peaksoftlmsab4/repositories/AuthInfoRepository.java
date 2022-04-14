package kg.peaksoft.peaksoftlmsab4.repositories;

import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfo, UUID> {
    Optional<AuthInfo> findByEmail(String email);
}