package vn.anzi.modules.management.user.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "")
@Data
public class StaffEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email")
    private String email;
    @Column
    private String name;
    @Column(name="fk_management_role")
    private Long roleId;
}