package ma.tp.backend.web;

import ma.tp.backend.dtos.RepaymentDTO;
import ma.tp.backend.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/repayments")
@CrossOrigin("*")
public class RepaymentController {

    private final CreditService creditService;

    public RepaymentController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/{id}")
    public RepaymentDTO getRepaymentById(@PathVariable Long id) {
        RepaymentDTO repayment = creditService.getRepaymentById(id);
        return repayment;
    }

    @GetMapping("/credit/{creditId}")
    public List<RepaymentDTO> getRepaymentsByCreditId(@PathVariable Long creditId) {
        return creditService.getRepaymentsByCreditId(creditId);
    }

    @PostMapping
    public RepaymentDTO createRepayment(@RequestBody RepaymentDTO repaymentDTO) {
        return creditService.saveRepayment(repaymentDTO);
    }

    @PutMapping("/{id}")
    public RepaymentDTO updateRepayment(
            @PathVariable Long id,
            @RequestBody RepaymentDTO repaymentDTO) {
        repaymentDTO.setId(id);
        RepaymentDTO updatedRepayment = creditService.updateRepayment(repaymentDTO);
       return updatedRepayment;
    }

    @DeleteMapping("/{id}")
    public void deleteRepayment(@PathVariable Long id) {
        creditService.deleteRepayment(id);
    }
}