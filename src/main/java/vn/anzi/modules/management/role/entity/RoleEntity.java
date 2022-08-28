package vn.anzi.modules.management.role.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_role")
@Data
public class RoleEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
