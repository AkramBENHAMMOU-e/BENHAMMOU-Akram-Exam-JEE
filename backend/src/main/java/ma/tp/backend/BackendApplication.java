package ma.tp.backend;

import ma.tp.backend.entities.*;
import ma.tp.backend.enums.CreditStatus;
import ma.tp.backend.enums.PropertyType;
import ma.tp.backend.enums.RepaymentType;
import ma.tp.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(
            ClientRepository clientRepository,
            PersonalCreditRepository personalCreditRepository,
            RealEstateCreditRepository realEstateCreditRepository,
            ProfessionalCreditRepository professionalCreditRepository,
            RepaymentRepository repaymentRepository) {

        return args -> {
            System.out.println("Initializing database with sample data...");

            // Create clients
            Client client1 = new Client();
            client1.setName("John Doe");
            client1.setEmail("john.doe@example.com");
            client1.setCredits(new ArrayList<>());

            Client client2 = new Client();
            client2.setName("Jane Smith");
            client2.setEmail("jane.smith@example.com");
            client2.setCredits(new ArrayList<>());

            clientRepository.save(client1);
            clientRepository.save(client2);

            // Create personal credit
            PersonalCredit personalCredit = new PersonalCredit();
            personalCredit.setRequestDate(new Date());
            personalCredit.setStatus(CreditStatus.ACCEPTED);
            personalCredit.setAcceptanceDate(new Date());
            personalCredit.setAmount(10000.0);
            personalCredit.setRepaymentDuration(24);
            personalCredit.setInterestRate(5.5);
            personalCredit.setReason("Car purchase");
            personalCredit.setClient(client1);
            personalCredit.setRepayments(new ArrayList<>());

            personalCreditRepository.save(personalCredit);

            // Create real estate credit
            RealEstateCredit realEstateCredit = new RealEstateCredit();
            realEstateCredit.setRequestDate(new Date());
            realEstateCredit.setStatus(CreditStatus.IN_PROGRESS);
            realEstateCredit.setAmount(200000.0);
            realEstateCredit.setRepaymentDuration(240);
            realEstateCredit.setInterestRate(3.2);
            realEstateCredit.setPropertyType(PropertyType.APARTMENT);
            realEstateCredit.setClient(client1);
            realEstateCredit.setRepayments(new ArrayList<>());

            realEstateCreditRepository.save(realEstateCredit);

            // Create professional credit
            ProfessionalCredit professionalCredit = new ProfessionalCredit();
            professionalCredit.setRequestDate(new Date());
            professionalCredit.setStatus(CreditStatus.ACCEPTED);
            professionalCredit.setAcceptanceDate(new Date());
            professionalCredit.setAmount(50000.0);
            professionalCredit.setRepaymentDuration(36);
            professionalCredit.setInterestRate(4.0);
            professionalCredit.setReason("Business expansion");
            professionalCredit.setCompanyName("Smith Enterprises");
            professionalCredit.setClient(client2);
            professionalCredit.setRepayments(new ArrayList<>());

            professionalCreditRepository.save(professionalCredit);

            // Create repayments for personal credit
            Repayment repayment1 = new Repayment();
            repayment1.setDate(new Date());
            repayment1.setAmount(450.0);
            repayment1.setType(RepaymentType.MONTHLY_PAYMENT);
            repayment1.setCredit(personalCredit);

            Repayment repayment2 = new Repayment();
            repayment2.setDate(new Date());
            repayment2.setAmount(450.0);
            repayment2.setType(RepaymentType.MONTHLY_PAYMENT);
            repayment2.setCredit(personalCredit);

            repaymentRepository.save(repayment1);
            repaymentRepository.save(repayment2);

            // Create repayment for professional credit
            Repayment repayment3 = new Repayment();
            repayment3.setDate(new Date());
            repayment3.setAmount(1500.0);
            repayment3.setType(RepaymentType.MONTHLY_PAYMENT);
            repayment3.setCredit(professionalCredit);

            repaymentRepository.save(repayment3);

            System.out.println("Database initialized with sample data!");
        };
    }
}
