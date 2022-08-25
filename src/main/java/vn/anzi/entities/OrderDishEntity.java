package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_order_dish")
@Data
public class OrderDishEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int fkManagementOrder;
    @Column
    private int fkManagementDish;
    @Column
    private int quantity;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
