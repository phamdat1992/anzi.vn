package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_eatery")
@Data
public class EateryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1000)
    private String name;
    @Column(length = 1000)
    private String address;
    @Column
    private Integer created_by;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
