package vn.anzi.modules.management.role.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_user_eatery_role")
@Data
public class UserEateryRoleEntity {
    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="fk_management_user_eatery")
    private Long userEateryId;
    @Column(name="fk_management_role")
    private Long roleId;
    @Column(name="is_active")
    private Boolean isActive;
}
