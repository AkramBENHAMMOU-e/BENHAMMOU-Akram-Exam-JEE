package ma.tp.backend.web;

import ma.tp.backend.dtos.PersonalCreditDTO;
import ma.tp.backend.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/personal-credits")
@CrossOrigin("*")
public class PersonalCreditController {

    private final CreditService creditService;

    public PersonalCreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<PersonalCreditDTO> getAllPersonalCredits() {
        return creditService.getAllPersonalCredits();
    }

    @GetMapping("/{id}")
    public PersonalCreditDTO getPersonalCreditById(@PathVariable Long id) {
        PersonalCreditDTO credit = creditService.getPersonalCreditById(id);
        return credit;
    }

    @PostMapping
    public PersonalCreditDTO createPersonalCredit(@RequestBody PersonalCreditDTO creditDTO) {
        return creditService.savePersonalCredit(creditDTO);
    }

    @PutMapping("/{id}")
    public PersonalCreditDTO updatePersonalCredit(
            @PathVariable Long id,
            @RequestBody PersonalCreditDTO creditDTO) {
        creditDTO.setId(id);
        PersonalCreditDTO updatedCredit = creditService.updatePersonalCredit(creditDTO);
        return updatedCredit;
    }

    @DeleteMapping("/{id}")
    public void deletePersonalCredit(@PathVariable Long id) {
        creditService.deletePersonalCredit(id);
    }
}
