package ma.tp.backend.repositories;

import ma.tp.backend.entities.ProfessionalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalCreditRepository extends JpaRepository<ProfessionalCredit, Long> {
    // You can add custom query methods here if needed
}