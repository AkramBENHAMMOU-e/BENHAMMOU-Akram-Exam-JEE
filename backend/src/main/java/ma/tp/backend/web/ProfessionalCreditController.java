package ma.tp.backend.web;

import ma.tp.backend.dtos.ProfessionalCreditDTO;
import ma.tp.backend.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/professional-credits")
@CrossOrigin("*")
public class ProfessionalCreditController {

    private final CreditService creditService;

    public ProfessionalCreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<ProfessionalCreditDTO> getAllProfessionalCredits() {
        return creditService.getAllProfessionalCredits();
    }

    @GetMapping("/{id}")
    public ProfessionalCreditDTO getProfessionalCreditById(@PathVariable Long id) {
        ProfessionalCreditDTO credit = creditService.getProfessionalCreditById(id);
       return credit;
    }

    @PostMapping
    public ProfessionalCreditDTO createProfessionalCredit(@RequestBody ProfessionalCreditDTO creditDTO) {
        return creditService.saveProfessionalCredit(creditDTO);
    }

    @PutMapping("/{id}")
    public ProfessionalCreditDTO updateProfessionalCredit(
            @PathVariable Long id,
            @RequestBody ProfessionalCreditDTO creditDTO) {
        creditDTO.setId(id);
        ProfessionalCreditDTO updatedCredit = creditService.updateProfessionalCredit(creditDTO);
        return updatedCredit;
    }

    @DeleteMapping("/{id}")
    public void deleteProfessionalCredit(@PathVariable Long id) {
        creditService.deleteProfessionalCredit(id);
    }
}
