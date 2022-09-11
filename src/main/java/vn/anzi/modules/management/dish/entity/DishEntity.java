package vn.anzi.modules.management.dish.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_dish")
@Data
public class DishEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String name;
    @Column
    private float price;
    @Column(length = 1000)
    private String code; //allow null
    @Column(name = "fk_management_eatery")
    private Long eateryId;
    @Column(name = "fk_management_category")
    private Long categoryId;
    @Column(name = "fk_management_dish_status")
    private Long dishStatusId;
    @Column
    private String image;
}
