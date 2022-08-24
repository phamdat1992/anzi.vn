package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_user_eatery_role")
@Data
public class UserEateryRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer user_eatery;
    @Column
    private Integer role;
    @Column
    private Boolean is_active;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
