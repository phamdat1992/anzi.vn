package vn.anzi.modules.management.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderServerEvent {

    private Long id;

    private String name;

    private String location;

    private Long typeId;

    private Long totalDish;

    private Long totalPrice;

    private Boolean isConfirmed;

    private String createdTime;
}
