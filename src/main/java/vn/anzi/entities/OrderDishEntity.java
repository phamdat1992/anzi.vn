package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_order_dish")
@Data
public class OrderDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Long order;
    @Column
    private Long dish;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
