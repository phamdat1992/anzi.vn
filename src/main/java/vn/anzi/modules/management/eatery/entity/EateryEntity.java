package vn.anzi.modules.management.eatery.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_eatery")
@Data
public class EateryEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
}
