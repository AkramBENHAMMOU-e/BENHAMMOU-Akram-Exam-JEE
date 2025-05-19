package ma.tp.backend.repositories;

import ma.tp.backend.entities.PersonalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalCreditRepository extends JpaRepository<PersonalCredit, Long> {
    // You can add custom query methods here if needed
}