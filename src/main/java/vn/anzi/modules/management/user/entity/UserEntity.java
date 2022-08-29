package vn.anzi.modules.management.user.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_user")
@Data
public class UserEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email")
    private String email;
}