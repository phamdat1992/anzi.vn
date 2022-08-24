package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_role")
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1000)
    private String name;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
