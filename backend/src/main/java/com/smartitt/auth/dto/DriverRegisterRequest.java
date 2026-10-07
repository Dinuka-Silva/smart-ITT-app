package com.smartitt.auth.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRegisterRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "NIC number is required")
    private String nic;

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Driving license number is required")
    @JsonAlias({"licenseNumber", "drivingLicenceNumber", "drivingLicenseNumber"})
    private String drivingLicenceNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate licenseExpiryDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Emergency contact name is required")
    private String emergencyContactName;

    @NotBlank(message = "Emergency contact number is required")
    private String emergencyContactNumber;

    @NotBlank(message = "Password is required")
    private String password;

    private String confirmPassword;

    private String email;

    private String profilePhoto;

    private String vehicleNumber;

    public String getLicenseNumber() {
        return drivingLicenceNumber;
    }
}
