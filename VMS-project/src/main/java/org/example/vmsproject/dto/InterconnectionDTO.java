package org.example.vmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterconnectionDTO {
    private double timeEstimate;
    private double timeActual;
}
