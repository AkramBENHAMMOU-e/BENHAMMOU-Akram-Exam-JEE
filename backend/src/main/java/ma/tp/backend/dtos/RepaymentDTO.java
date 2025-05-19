package ma.tp.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tp.backend.enums.RepaymentType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentDTO {
    private Long id;
    private Date date;
    private Double amount;
    private RepaymentType type;
    private Long creditId;
}