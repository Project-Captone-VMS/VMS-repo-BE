package org.example.vmsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.Item;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Warehouse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShipment {
    private Long shipmentId;
    private boolean status;
    private Warehouse warehouse;
    private Route route;
    private List<Item>items;


    public boolean isStatus(boolean b) {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
