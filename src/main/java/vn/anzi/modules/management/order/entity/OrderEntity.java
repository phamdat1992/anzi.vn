package vn.anzi.modules.management.order.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class OrderEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fk_diner")
    private Long dinerId;
    @Column(name = "fk_management_table")
    private Long tableId;
    @Column(name = "fk_management_order_type")
    private Long orderTypeId;
    @Column
    private Boolean isConfirmed;

}
