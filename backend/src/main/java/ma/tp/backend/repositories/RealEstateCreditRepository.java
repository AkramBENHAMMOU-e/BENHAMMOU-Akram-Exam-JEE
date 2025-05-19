package ma.tp.backend.repositories;

import ma.tp.backend.entities.RealEstateCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateCreditRepository extends JpaRepository<RealEstateCredit, Long> {
    // You can add custom query methods here if needed
}