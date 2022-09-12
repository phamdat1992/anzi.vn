package vn.anzi.modules.management.order.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_order_dish")
@Data
public class OrderDishEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fk_management_order")
    private Long orderId;
    @Column(name = "fk_management_dish")
    private Long dishId;
    @Column
    private Long quantity;
}
