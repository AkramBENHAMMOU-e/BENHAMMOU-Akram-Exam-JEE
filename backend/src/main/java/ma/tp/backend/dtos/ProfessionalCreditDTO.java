package ma.tp.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tp.backend.enums.CreditStatus;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalCreditDTO extends CreditDTO {
    private String reason;
    private String companyName;
    
    public ProfessionalCreditDTO(Long id, Date requestDate, CreditStatus status, Date acceptanceDate, 
                                Double amount, Integer repaymentDuration, Double interestRate, 
                                Long clientId, List<Long> repaymentIds, String reason, String companyName) {
        super(id, requestDate, status, acceptanceDate, amount, repaymentDuration, interestRate, clientId, repaymentIds);
        this.reason = reason;
        this.companyName = companyName;
    }
}