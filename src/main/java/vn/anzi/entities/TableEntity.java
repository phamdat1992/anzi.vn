package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_table")
@Data
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String name;
    @Column(length = 1000)
    private String location;
    @Column
    private Integer eatery;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
