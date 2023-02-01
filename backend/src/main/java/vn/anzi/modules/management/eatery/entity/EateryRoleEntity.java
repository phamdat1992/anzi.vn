package vn.anzi.modules.management.eatery.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "")
@Data
public class EateryRoleEntity {
    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column(name = "role_id")
    private Long roleId;
}
