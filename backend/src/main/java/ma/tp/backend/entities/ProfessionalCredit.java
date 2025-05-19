package ma.tp.backend.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalCredit extends Credit {
    private String reason;
    private String companyName; // "raison sociale" in French
}