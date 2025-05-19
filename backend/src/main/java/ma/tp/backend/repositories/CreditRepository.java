package ma.tp.backend.repositories;

import ma.tp.backend.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    // You can add custom query methods here if needed
}