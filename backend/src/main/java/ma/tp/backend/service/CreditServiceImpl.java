package ma.tp.backend.service;

import ma.tp.backend.dtos.*;
import ma.tp.backend.entities.*;
import ma.tp.backend.enums.CreditStatus;
import ma.tp.backend.mappers.CreditMapper;
import ma.tp.backend.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final PersonalCreditRepository personalCreditRepository;
    private final RealEstateCreditRepository realEstateCreditRepository;
    private final ProfessionalCreditRepository professionalCreditRepository;
    private final RepaymentRepository repaymentRepository;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(ClientRepository clientRepository,
                            CreditRepository creditRepository,
                            PersonalCreditRepository personalCreditRepository,
                            RealEstateCreditRepository realEstateCreditRepository,
                            ProfessionalCreditRepository professionalCreditRepository,
                            RepaymentRepository repaymentRepository,
                            CreditMapper creditMapper) {
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.personalCreditRepository = personalCreditRepository;
        this.realEstateCreditRepository = realEstateCreditRepository;
        this.professionalCreditRepository = professionalCreditRepository;
        this.repaymentRepository = repaymentRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = creditMapper.toClient(clientDTO);
        client.setCredits(new ArrayList<>());
        Client savedClient = clientRepository.save(client);
        return creditMapper.fromClient(savedClient);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(creditMapper::fromClient)
                .orElse(null);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return creditMapper.fromClientList(clientRepository.findAll());
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        if (clientDTO.getId() == null || !clientRepository.existsById(clientDTO.getId())) {
            return null;
        }
        Client existingClient = clientRepository.findById(clientDTO.getId()).orElse(null);
        Client client = creditMapper.toClient(clientDTO);
        client.setCredits(existingClient.getCredits());
        Client updatedClient = clientRepository.save(client);
        return creditMapper.fromClient(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) return new ArrayList<>();
        
        List<CreditDTO> creditDTOs = new ArrayList<>();
        for (Credit credit : client.getCredits()) {
            if (credit instanceof PersonalCredit) {
                creditDTOs.add(creditMapper.fromPersonalCredit((PersonalCredit) credit));
            } else if (credit instanceof RealEstateCredit) {
                creditDTOs.add(creditMapper.fromRealEstateCredit((RealEstateCredit) credit));
            } else if (credit instanceof ProfessionalCredit) {
                creditDTOs.add(creditMapper.fromProfessionalCredit((ProfessionalCredit) credit));
            }
        }
        return creditDTOs;
    }

    @Override
    public PersonalCreditDTO savePersonalCredit(PersonalCreditDTO personalCreditDTO) {
        PersonalCredit personalCredit = creditMapper.toPersonalCredit(personalCreditDTO);
        if (personalCreditDTO.getClientId() != null) {
            Client client = clientRepository.findById(personalCreditDTO.getClientId()).orElse(null);
            personalCredit.setClient(client);
        }
        
        personalCredit.setRepayments(new ArrayList<>());
        PersonalCredit savedPersonalCredit = personalCreditRepository.save(personalCredit);
        return creditMapper.fromPersonalCredit(savedPersonalCredit);
    }

    @Override
    public PersonalCreditDTO getPersonalCreditById(Long id) {
        return personalCreditRepository.findById(id)
                .map(creditMapper::fromPersonalCredit)
                .orElse(null);
    }

    @Override
    public List<PersonalCreditDTO> getAllPersonalCredits() {
        List<PersonalCredit> personalCredits = personalCreditRepository.findAll();
        return personalCredits.stream()
                .map(creditMapper::fromPersonalCredit)
                .collect(Collectors.toList());
    }

    @Override
    public PersonalCreditDTO updatePersonalCredit(PersonalCreditDTO personalCreditDTO) {
        if (personalCreditDTO.getId() == null || !personalCreditRepository.existsById(personalCreditDTO.getId())) {
            return null;
        }
        
        PersonalCredit existingCredit = personalCreditRepository.findById(personalCreditDTO.getId()).orElse(null);
        PersonalCredit personalCredit = creditMapper.toPersonalCredit(personalCreditDTO);

        personalCredit.setClient(existingCredit.getClient());
        personalCredit.setRepayments(existingCredit.getRepayments());

        if (personalCreditDTO.getClientId() != null && 
            (existingCredit.getClient() == null || !existingCredit.getClient().getId().equals(personalCreditDTO.getClientId()))) {
            Client client = clientRepository.findById(personalCreditDTO.getClientId()).orElse(null);
            personalCredit.setClient(client);
        }
        
        PersonalCredit updatedCredit = personalCreditRepository.save(personalCredit);
        return creditMapper.fromPersonalCredit(updatedCredit);
    }

    @Override
    public void deletePersonalCredit(Long id) {
        personalCreditRepository.deleteById(id);
    }

    @Override
    public RealEstateCreditDTO saveRealEstateCredit(RealEstateCreditDTO realEstateCreditDTO) {
        RealEstateCredit realEstateCredit = creditMapper.toRealEstateCredit(realEstateCreditDTO);

        if (realEstateCreditDTO.getClientId() != null) {
            Client client = clientRepository.findById(realEstateCreditDTO.getClientId()).orElse(null);
            realEstateCredit.setClient(client);
        }
        
        realEstateCredit.setRepayments(new ArrayList<>());
        RealEstateCredit savedRealEstateCredit = realEstateCreditRepository.save(realEstateCredit);
        return creditMapper.fromRealEstateCredit(savedRealEstateCredit);
    }

    @Override
    public RealEstateCreditDTO getRealEstateCreditById(Long id) {
        return realEstateCreditRepository.findById(id)
                .map(creditMapper::fromRealEstateCredit)
                .orElse(null);
    }

    @Override
    public List<RealEstateCreditDTO> getAllRealEstateCredits() {
        List<RealEstateCredit> realEstateCredits = realEstateCreditRepository.findAll();
        return realEstateCredits.stream()
                .map(creditMapper::fromRealEstateCredit)
                .collect(Collectors.toList());
    }

    @Override
    public RealEstateCreditDTO updateRealEstateCredit(RealEstateCreditDTO realEstateCreditDTO) {
        if (realEstateCreditDTO.getId() == null || !realEstateCreditRepository.existsById(realEstateCreditDTO.getId())) {
            return null;
        }
        
        RealEstateCredit existingCredit = realEstateCreditRepository.findById(realEstateCreditDTO.getId()).orElse(null);
        RealEstateCredit realEstateCredit = creditMapper.toRealEstateCredit(realEstateCreditDTO);
        realEstateCredit.setClient(existingCredit.getClient());
        realEstateCredit.setRepayments(existingCredit.getRepayments());
        if (realEstateCreditDTO.getClientId() != null && 
            (existingCredit.getClient() == null || !existingCredit.getClient().getId().equals(realEstateCreditDTO.getClientId()))) {
            Client client = clientRepository.findById(realEstateCreditDTO.getClientId()).orElse(null);
            realEstateCredit.setClient(client);
        }
        
        RealEstateCredit updatedCredit = realEstateCreditRepository.save(realEstateCredit);
        return creditMapper.fromRealEstateCredit(updatedCredit);
    }

    @Override
    public void deleteRealEstateCredit(Long id) {
        realEstateCreditRepository.deleteById(id);
    }

    @Override
    public ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO) {
        ProfessionalCredit professionalCredit = creditMapper.toProfessionalCredit(professionalCreditDTO);

        if (professionalCreditDTO.getClientId() != null) {
            Client client = clientRepository.findById(professionalCreditDTO.getClientId()).orElse(null);
            professionalCredit.setClient(client);
        }
        
        professionalCredit.setRepayments(new ArrayList<>());
        ProfessionalCredit savedProfessionalCredit = professionalCreditRepository.save(professionalCredit);
        return creditMapper.fromProfessionalCredit(savedProfessionalCredit);
    }

    @Override
    public ProfessionalCreditDTO getProfessionalCreditById(Long id) {
        return professionalCreditRepository.findById(id)
                .map(creditMapper::fromProfessionalCredit)
                .orElse(null);
    }

    @Override
    public List<ProfessionalCreditDTO> getAllProfessionalCredits() {
        List<ProfessionalCredit> professionalCredits = professionalCreditRepository.findAll();
        return professionalCredits.stream()
                .map(creditMapper::fromProfessionalCredit)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessionalCreditDTO updateProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO) {
        if (professionalCreditDTO.getId() == null || !professionalCreditRepository.existsById(professionalCreditDTO.getId())) {
            return null;
        }
        
        ProfessionalCredit existingCredit = professionalCreditRepository.findById(professionalCreditDTO.getId()).orElse(null);
        ProfessionalCredit professionalCredit = creditMapper.toProfessionalCredit(professionalCreditDTO);

        professionalCredit.setClient(existingCredit.getClient());
        professionalCredit.setRepayments(existingCredit.getRepayments());
        if (professionalCreditDTO.getClientId() != null && 
            (existingCredit.getClient() == null || !existingCredit.getClient().getId().equals(professionalCreditDTO.getClientId()))) {
            Client client = clientRepository.findById(professionalCreditDTO.getClientId()).orElse(null);
            professionalCredit.setClient(client);
        }
        
        ProfessionalCredit updatedCredit = professionalCreditRepository.save(professionalCredit);
        return creditMapper.fromProfessionalCredit(updatedCredit);
    }

    @Override
    public void deleteProfessionalCredit(Long id) {
        professionalCreditRepository.deleteById(id);
    }
    @Override
    public RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO) {
        Repayment repayment = creditMapper.toRepayment(repaymentDTO);
        if (repaymentDTO.getCreditId() != null) {
            Credit credit = creditRepository.findById(repaymentDTO.getCreditId()).orElse(null);
            repayment.setCredit(credit);
        }
        
        Repayment savedRepayment = repaymentRepository.save(repayment);
        return creditMapper.fromRepayment(savedRepayment);
    }

    @Override
    public RepaymentDTO getRepaymentById(Long id) {
        return repaymentRepository.findById(id)
                .map(creditMapper::fromRepayment)
                .orElse(null);
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByCreditId(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit == null) return new ArrayList<>();
        
        return creditMapper.fromRepaymentList(credit.getRepayments());
    }

    @Override
    public RepaymentDTO updateRepayment(RepaymentDTO repaymentDTO) {
        if (repaymentDTO.getId() == null || !repaymentRepository.existsById(repaymentDTO.getId())) {
            return null;
        }
        
        Repayment existingRepayment = repaymentRepository.findById(repaymentDTO.getId()).orElse(null);
        Repayment repayment = creditMapper.toRepayment(repaymentDTO);

        repayment.setCredit(existingRepayment.getCredit());
        if (repaymentDTO.getCreditId() != null && 
            (existingRepayment.getCredit() == null || !existingRepayment.getCredit().getId().equals(repaymentDTO.getCreditId()))) {
            Credit credit = creditRepository.findById(repaymentDTO.getCreditId()).orElse(null);
            repayment.setCredit(credit);
        }
        
        Repayment updatedRepayment = repaymentRepository.save(repayment);
        return creditMapper.fromRepayment(updatedRepayment);
    }

    @Override
    public void deleteRepayment(Long id) {
        repaymentRepository.deleteById(id);
    }
    @Override
    public List<CreditDTO> getCreditsByStatus(CreditStatus status) {
        List<Credit> credits = creditRepository.findAll().stream()
                .filter(credit -> credit.getStatus() == status)
                .collect(Collectors.toList());
        
        List<CreditDTO> creditDTOs = new ArrayList<>();
        for (Credit credit : credits) {
            if (credit instanceof PersonalCredit) {
                creditDTOs.add(creditMapper.fromPersonalCredit((PersonalCredit) credit));
            } else if (credit instanceof RealEstateCredit) {
                creditDTOs.add(creditMapper.fromRealEstateCredit((RealEstateCredit) credit));
            } else if (credit instanceof ProfessionalCredit) {
                creditDTOs.add(creditMapper.fromProfessionalCredit((ProfessionalCredit) credit));
            }
        }
        return creditDTOs;
    }

    @Override
    public void changeCreditStatus(Long creditId, CreditStatus newStatus) {
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit != null) {
            credit.setStatus(newStatus);
            creditRepository.save(credit);
        }
    }
}