package ma.tp.backend.web;

import ma.tp.backend.dtos.RealEstateCreditDTO;
import ma.tp.backend.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/real-estate-credits")
@CrossOrigin("*")
public class RealEstateCreditController {

    private final CreditService creditService;

    public RealEstateCreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<RealEstateCreditDTO> getAllRealEstateCredits() {
        return creditService.getAllRealEstateCredits();
    }

    @GetMapping("/{id}")
    public RealEstateCreditDTO getRealEstateCreditById(@PathVariable Long id) {
        RealEstateCreditDTO credit = creditService.getRealEstateCreditById(id);
        if (credit != null) {
            return credit;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Real estate credit not found");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RealEstateCreditDTO createRealEstateCredit(@RequestBody RealEstateCreditDTO creditDTO) {
        return creditService.saveRealEstateCredit(creditDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RealEstateCreditDTO updateRealEstateCredit(
            @PathVariable Long id,
            @RequestBody RealEstateCreditDTO creditDTO) {
        creditDTO.setId(id);
        RealEstateCreditDTO updatedCredit = creditService.updateRealEstateCredit(creditDTO);
        if (updatedCredit != null) {
            return updatedCredit;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Real estate credit not found");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRealEstateCredit(@PathVariable Long id) {
        creditService.deleteRealEstateCredit(id);
    }
}
