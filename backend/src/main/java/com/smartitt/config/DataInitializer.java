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

import com.smartitt.vehicle.entity.Vehicle;
import com.smartitt.vehicle.repository.VehicleRepository;

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
    private final VehicleRepository vehicleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        seedTerminals();
        seedVehicles();
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

    private void seedVehicles() {
        if (vehicleRepository.count() == 0) {
            log.info("Seeding SCK ITT Vehicle Fleet (19 vehicles across SCK, SDR LINK, E3 Logistics)...");
            List<Vehicle> fleet = List.of(
                // SCK Logistics Main Fleet (10 vehicles)
                Vehicle.builder().chassisNumber("SCK 100").vehicleNumber("LY 5234").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 101").vehicleNumber("LY 5235").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 102").vehicleNumber("LY 5236").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 103").vehicleNumber("LY 5237").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 104").vehicleNumber("LY 5238").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 105").vehicleNumber("LY 5665").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 106").vehicleNumber("LY 5708").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 107").vehicleNumber("LY 5711").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 108").vehicleNumber("LY 5717").operator("SCK Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 109").vehicleNumber("LY 5721").operator("SCK Logistics").status("ACTIVE").build(),

                // SDR LINK Fleet (6 vehicles)
                Vehicle.builder().chassisNumber("SCK 115").vehicleNumber("LY 6528").operator("SDR LINK").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 117").vehicleNumber("LY 6529").operator("SDR LINK").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 122").vehicleNumber("LY 5596").operator("SDR LINK").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 119").vehicleNumber("LY 6530").operator("SDR LINK").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 120").vehicleNumber("LY 6531").operator("SDR LINK").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 121").vehicleNumber("LY 6532").operator("SDR LINK").status("ACTIVE").build(),

                // E3 Logistics Fleet (3 vehicles)
                Vehicle.builder().chassisNumber("SCK 123").vehicleNumber("LY 5597").operator("E3 Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 124").vehicleNumber("LY 5598").operator("E3 Logistics").status("ACTIVE").build(),
                Vehicle.builder().chassisNumber("SCK 125").vehicleNumber("LY 5600").operator("E3 Logistics").status("ACTIVE").build()
            );
            vehicleRepository.saveAll(fleet);
            log.info("Successfully seeded 19 fleet vehicles into database.");
        } else {
            // Update existing vehicles with revised operators
            List<Vehicle> existing = vehicleRepository.findAll();
            for (Vehicle v : existing) {
                if ("LY 6530".equals(v.getVehicleNumber()) || "LY 6531".equals(v.getVehicleNumber()) || "LY 6532".equals(v.getVehicleNumber())) {
                    v.setOperator("SDR LINK");
                } else if ("LY 5597".equals(v.getVehicleNumber()) || "LY 5598".equals(v.getVehicleNumber()) || "LY 5600".equals(v.getVehicleNumber())) {
                    v.setOperator("E3 Logistics");
                }
            }
            vehicleRepository.saveAll(existing);
            log.info("Successfully synchronized 19 fleet vehicle operators.");
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
            driver.setVehicleNumber("LY 5234");
            driver.setChassisNumber("SCK 100");
            driverRepository.save(driver);
            log.info("Demo driver created: Driver Code: DRV-00001, Vehicle: LY 5234, CHE: SCK 100, Email: driver@smartitt.lk, Password: driver");
        }
    }
}

