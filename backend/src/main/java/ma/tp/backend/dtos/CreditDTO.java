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
public abstract class CreditDTO {
    private Long id;
    private Date requestDate;
    private CreditStatus status;
    private Date acceptanceDate;
    private Double amount;
    private Integer repaymentDuration;
    private Double interestRate;
    private Long clientId;
    private List<Long> repaymentIds;
}