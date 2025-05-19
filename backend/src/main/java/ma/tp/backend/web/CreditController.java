package ma.tp.backend.web;

import ma.tp.backend.dtos.CreditDTO;
import ma.tp.backend.enums.CreditStatus;
import ma.tp.backend.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin("*")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/status/{status}")
    public List<CreditDTO> getCreditsByStatus(@PathVariable CreditStatus status) {
        return creditService.getCreditsByStatus(status);
    }

    @PutMapping("/{id}/status")
    public void changeCreditStatus(
            @PathVariable Long id,
            @RequestParam CreditStatus status) {
        creditService.changeCreditStatus(id, status);
    }
}
