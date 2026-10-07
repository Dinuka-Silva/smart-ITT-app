package com.smartitt.driver.entity;

import com.smartitt.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends User {

    @Column(unique = true, length = 30)
    private String driverCode;

    @Column(nullable = false, unique = true, length = 50)
    private String drivingLicenceNumber;

    private LocalDate licenseExpiryDate;

    @Column(length = 50)
    private String vehicleNumber;

}
