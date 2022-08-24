package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_dish_image")
@Data
public class DishImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Long dish;
    @Column
    private Integer image;
    @Column
    private Boolean is_active;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
