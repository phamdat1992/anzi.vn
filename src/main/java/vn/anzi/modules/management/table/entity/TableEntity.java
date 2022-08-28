package vn.anzi.modules.management.table.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_table")
@Data
public class TableEntity {
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
    private String location;
    @Column(name="fk_management_eatery")
    private Long eateryId;
    @Column(name="is_active")
    private Boolean isActive;
}
