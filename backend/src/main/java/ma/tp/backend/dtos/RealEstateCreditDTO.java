package ma.tp.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tp.backend.enums.CreditStatus;
import ma.tp.backend.enums.PropertyType;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateCreditDTO extends CreditDTO {
    private PropertyType propertyType;
    
    public RealEstateCreditDTO(Long id, Date requestDate, CreditStatus status, Date acceptanceDate, 
                              Double amount, Integer repaymentDuration, Double interestRate, 
                              Long clientId, List<Long> repaymentIds, PropertyType propertyType) {
        super(id, requestDate, status, acceptanceDate, amount, repaymentDuration, interestRate, clientId, repaymentIds);
        this.propertyType = propertyType;
    }
}