package org.example.vmsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Warehouse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShipment {
    private Long shipmentId;
    private String status;
    private Warehouse warehouse;
    protected Route route;
}
