package vn.anzi.modules.management.order.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "management_order")
@Data
public class OrderInfoConfirmedEntity {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String location;
    @Column(name = "total_dish")
    private Long totalDish;
    @Column(name = "total_price")
    private Long totalPrice;
    @Column(name = "created_time")
    private String createdTime;
}
