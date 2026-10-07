package com.smartitt.config;

import com.smartitt.driver.entity.Driver;
import com.smartitt.driver.repository.DriverRepository;
import com.smartitt.supervisor.entity.Supervisor;
import com.smartitt.supervisor.repository.SupervisorRepository;
import com.smartitt.terminal.entity.Terminal;
import com.smartitt.terminal.repository.TerminalRepository;
import com.smartitt.user.entity.AccountStatus;
import com.smartitt.user.entity.Role;
import com.smartitt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TerminalRepository terminalRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final SupervisorRepository supervisorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        seedTerminals();
        seedDemoUsers();
    }

    private void seedTerminals() {
        if (terminalRepository.count() == 0) {
            log.info("Seeding the 6 Sri Lankan Port Terminals...");
            List<Terminal> terminals = List.of(
                Terminal.builder().code("CICT").name("Colombo International Container Terminals").active(true).build(),
                Terminal.builder().code("CWIT").name("Colombo West International Terminal").active(true).build(),
                Terminal.builder().code("ECT").name("East Container Terminal").active(true).build(),
                Terminal.builder().code("JCT").name("Jaya Container Terminal").active(true).build(),
                Terminal.builder().code("UCT").name("Unity Container Terminal").active(true).build(),
                Terminal.builder().code("SAGT").name("South Asia Gateway Terminals").active(true).build()
            );
            terminalRepository.saveAll(terminals);
            log.info("Successfully seeded 6 terminals.");
        }
    }

    private void seedDemoUsers() {
        if (!userRepository.existsByEmail("supervisor@smartitt.lk")) {
            log.info("Seeding demo supervisor...");
            Terminal cict = terminalRepository.findByCode("CICT").orElse(null);
            Supervisor supervisor = new Supervisor();
            supervisor.setFullName("Nimal Silva");
            supervisor.setUsername("supervisor@smartitt.lk");
            supervisor.setEmployeeId("SUP001");
            supervisor.setNic("199012345678");
            supervisor.setMobileNumber("0711234567");
            supervisor.setEmail("supervisor@smartitt.lk");
            supervisor.setAddress("No. 12, Galle Face Road, Colombo 03");
            supervisor.setDateOfBirth(LocalDate.of(1990, 5, 20));
            supervisor.setEmergencyContactName("Amara Silva");
            supervisor.setEmergencyContactNumber("0719876543");
            supervisor.setPassword(passwordEncoder.encode("supervisor"));
            supervisor.setRole(Role.SUPERVISOR);
            supervisor.setStatus(AccountStatus.ACTIVE);
            supervisor.setAssignedTerminal(cict);
            supervisorRepository.save(supervisor);
            log.info("Demo supervisor created: supervisor@smartitt.lk / supervisor");
        }

        if (!userRepository.existsByEmail("driver@smartitt.lk")) {
            log.info("Seeding demo driver...");
            Driver driver = new Driver();
            driver.setDriverCode("DRV-00001");
            driver.setUsername("DRV-00001");
            driver.setFullName("Kamal Perera");
            driver.setEmployeeId("DRV001");
            driver.setNic("198512345678");
            driver.setMobileNumber("0779876543");
            driver.setEmail("driver@smartitt.lk");
            driver.setAddress("No. 45, Harbour View Road, Colombo 15");
            driver.setDateOfBirth(LocalDate.of(1985, 4, 12));
            driver.setDrivingLicenceNumber("LIC-001234");
            driver.setLicenseExpiryDate(LocalDate.of(2028, 12, 31));
            driver.setEmergencyContactName("Sunil Perera");
            driver.setEmergencyContactNumber("0771122334");
            driver.setPassword(passwordEncoder.encode("driver"));
            driver.setRole(Role.DRIVER);
            driver.setStatus(AccountStatus.ACTIVE);
            driver.setVehicleNumber("WP-BA-1234");
            driverRepository.save(driver);
            log.info("Demo driver created: Driver Code: DRV-00001, Email: driver@smartitt.lk, Password: driver");
        }
    }
}
