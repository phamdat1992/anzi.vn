package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_dish")
@Data
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String name;
    @Column
    private Double price;
    @Column(length = 1000)
    private String code; //allow null
    @Column
    private Integer eatery;
    @Column
    private Integer category;
    @Column
    private Integer dishStatus;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
