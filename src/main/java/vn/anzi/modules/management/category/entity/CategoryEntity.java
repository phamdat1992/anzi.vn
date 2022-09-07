package vn.anzi.modules.management.category.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_category")
@Data
public class CategoryEntity {
    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name="fk_management_eatery")
    private Long eateryId;
    @Column(name="is_active")
    private Boolean isActive;
}
