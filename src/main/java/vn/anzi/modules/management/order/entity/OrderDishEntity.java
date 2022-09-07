package vn.anzi.modules.management.order.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_order_dish")
@Data
public class OrderDishEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fk_management_order")
    private int fkManagementOrder;
    @Column(name = "fk_management_dish")
    private int fkManagementDish;
    @Column
    private int quantity;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
