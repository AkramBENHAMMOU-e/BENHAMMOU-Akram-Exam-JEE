package ma.tp.backend.web;

import ma.tp.backend.dtos.ClientDTO;
import ma.tp.backend.dtos.CreditDTO;
import ma.tp.backend.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    private final CreditService creditService;

    public ClientController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return creditService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        ClientDTO client = creditService.getClientById(id);
        return client;
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return creditService.saveClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        ClientDTO updatedClient = creditService.updateClient(clientDTO);
        return updatedClient;
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        creditService.deleteClient(id);
    }

    @GetMapping("/{id}/credits")
    public List<CreditDTO> getClientCredits(@PathVariable Long id) {
        return creditService.getCreditsByClientId(id);
    }
}
