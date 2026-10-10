package com.smartitt.container.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddManualContainerRequest {

    private String tripId;

    @NotBlank(message = "Container number is required")
    private String containerNumber;

    @NotBlank(message = "Container size (20FT or 40FT) is required")
    private String size;

    @NotBlank(message = "Main terminal is required")
    private String mainTerminal;

    private String vesselName;

    private String chaiNo;

    private String destTerminal;
}
