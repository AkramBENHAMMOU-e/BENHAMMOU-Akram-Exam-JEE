package ma.tp.backend.service;

import ma.tp.backend.dtos.*;
import ma.tp.backend.enums.CreditStatus;

import java.util.List;

public interface CreditService {
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long id);
    List<CreditDTO> getCreditsByClientId(Long clientId);
    PersonalCreditDTO savePersonalCredit(PersonalCreditDTO personalCreditDTO);
    PersonalCreditDTO getPersonalCreditById(Long id);
    List<PersonalCreditDTO> getAllPersonalCredits();
    PersonalCreditDTO updatePersonalCredit(PersonalCreditDTO personalCreditDTO);
    void deletePersonalCredit(Long id);
    
    // Real Estate Credit operations
    RealEstateCreditDTO saveRealEstateCredit(RealEstateCreditDTO realEstateCreditDTO);
    RealEstateCreditDTO getRealEstateCreditById(Long id);
    List<RealEstateCreditDTO> getAllRealEstateCredits();
    RealEstateCreditDTO updateRealEstateCredit(RealEstateCreditDTO realEstateCreditDTO);
    void deleteRealEstateCredit(Long id);
    ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO);
    ProfessionalCreditDTO getProfessionalCreditById(Long id);
    List<ProfessionalCreditDTO> getAllProfessionalCredits();
    ProfessionalCreditDTO updateProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO);
    void deleteProfessionalCredit(Long id);

    RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO);
    RepaymentDTO getRepaymentById(Long id);
    List<RepaymentDTO> getRepaymentsByCreditId(Long creditId);
    RepaymentDTO updateRepayment(RepaymentDTO repaymentDTO);
    void deleteRepayment(Long id);

    List<CreditDTO> getCreditsByStatus(CreditStatus status);
    void changeCreditStatus(Long creditId, CreditStatus newStatus);
}