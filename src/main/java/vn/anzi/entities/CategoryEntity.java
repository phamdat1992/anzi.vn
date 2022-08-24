package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_category")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1000)
    private String name;
    @Column
    private Integer eatery;
    @Column
    private Boolean is_active;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
