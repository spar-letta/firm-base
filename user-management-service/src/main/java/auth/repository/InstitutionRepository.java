package auth.repository;

import auth.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<Institution> findByPublicIdAndDeletedIsFalse(UUID institutionId);
}
