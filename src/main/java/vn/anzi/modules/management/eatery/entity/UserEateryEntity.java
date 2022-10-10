package vn.anzi.modules.management.eatery.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_user_eatery")
@Data
public class UserEateryEntity {
    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "fk_management_user")
    private Long userId;
    @Column(name = "fk_management_eatery")
    private Long eateryId;

    @Column(name = "fk_management_role")
    private Long roleId;

    @Column(name = "is_active")
    private Boolean isActive;
}