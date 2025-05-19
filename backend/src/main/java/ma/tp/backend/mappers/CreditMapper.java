package ma.tp.backend.mappers;

import ma.tp.backend.dtos.*;
import ma.tp.backend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditMapper {


    public ClientDTO fromClient(Client client) {
        if (client == null) return null;
        List<Long> creditIds = client.getCredits() != null ?
                client.getCredits().stream().map(Credit::getId).collect(Collectors.toList()) :
                null;

        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                creditIds
        );
    }

    public Client toClient(ClientDTO clientDTO) {
        if (clientDTO == null) return null;
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }


    public PersonalCreditDTO fromPersonalCredit(PersonalCredit personalCredit) {
        if (personalCredit == null) return null;
        List<Long> repaymentIds = personalCredit.getRepayments() != null ?
                personalCredit.getRepayments().stream().map(Repayment::getId).collect(Collectors.toList()) :
                null;

        return new PersonalCreditDTO(
                personalCredit.getId(),
                personalCredit.getRequestDate(),
                personalCredit.getStatus(),
                personalCredit.getAcceptanceDate(),
                personalCredit.getAmount(),
                personalCredit.getRepaymentDuration(),
                personalCredit.getInterestRate(),
                personalCredit.getClient() != null ? personalCredit.getClient().getId() : null,
                repaymentIds,
                personalCredit.getReason()
        );
    }

    public PersonalCredit toPersonalCredit(PersonalCreditDTO personalCreditDTO) {
        if (personalCreditDTO == null) return null;
        PersonalCredit personalCredit = new PersonalCredit();
        BeanUtils.copyProperties(personalCreditDTO, personalCredit);
        return personalCredit;
    }


    public RealEstateCreditDTO fromRealEstateCredit(RealEstateCredit realEstateCredit) {
        if (realEstateCredit == null) return null;
        List<Long> repaymentIds = realEstateCredit.getRepayments() != null ?
                realEstateCredit.getRepayments().stream().map(Repayment::getId).collect(Collectors.toList()) :
                null;

        return new RealEstateCreditDTO(
                realEstateCredit.getId(),
                realEstateCredit.getRequestDate(),
                realEstateCredit.getStatus(),
                realEstateCredit.getAcceptanceDate(),
                realEstateCredit.getAmount(),
                realEstateCredit.getRepaymentDuration(),
                realEstateCredit.getInterestRate(),
                realEstateCredit.getClient() != null ? realEstateCredit.getClient().getId() : null,
                repaymentIds,
                realEstateCredit.getPropertyType()
        );
    }

    public RealEstateCredit toRealEstateCredit(RealEstateCreditDTO realEstateCreditDTO) {
        if (realEstateCreditDTO == null) return null;
        RealEstateCredit realEstateCredit = new RealEstateCredit();
        BeanUtils.copyProperties(realEstateCreditDTO, realEstateCredit);
        return realEstateCredit;
    }


    public ProfessionalCreditDTO fromProfessionalCredit(ProfessionalCredit professionalCredit) {
        if (professionalCredit == null) return null;
        List<Long> repaymentIds = professionalCredit.getRepayments() != null ?
                professionalCredit.getRepayments().stream().map(Repayment::getId).collect(Collectors.toList()) :
                null;

        return new ProfessionalCreditDTO(
                professionalCredit.getId(),
                professionalCredit.getRequestDate(),
                professionalCredit.getStatus(),
                professionalCredit.getAcceptanceDate(),
                professionalCredit.getAmount(),
                professionalCredit.getRepaymentDuration(),
                professionalCredit.getInterestRate(),
                professionalCredit.getClient() != null ? professionalCredit.getClient().getId() : null,
                repaymentIds,
                professionalCredit.getReason(),
                professionalCredit.getCompanyName()
        );
    }

    public ProfessionalCredit toProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO) {
        if (professionalCreditDTO == null) return null;
        ProfessionalCredit professionalCredit = new ProfessionalCredit();
        BeanUtils.copyProperties(professionalCreditDTO, professionalCredit);
        return professionalCredit;
    }


    public RepaymentDTO fromRepayment(Repayment repayment) {
        if (repayment == null) return null;
        return new RepaymentDTO(
                repayment.getId(),
                repayment.getDate(),
                repayment.getAmount(),
                repayment.getType(),
                repayment.getCredit() != null ? repayment.getCredit().getId() : null
        );
    }

    public Repayment toRepayment(RepaymentDTO repaymentDTO) {
        if (repaymentDTO == null) return null;
        Repayment repayment = new Repayment();
        BeanUtils.copyProperties(repaymentDTO, repayment);
        return repayment;
    }


    public List<ClientDTO> fromClientList(List<Client> clients) {
        if (clients == null) return null;
        return clients.stream().map(this::fromClient).collect(Collectors.toList());
    }

    public List<RepaymentDTO> fromRepaymentList(List<Repayment> repayments) {
        if (repayments == null) return null;
        return repayments.stream().map(this::fromRepayment).collect(Collectors.toList());
    }
}
