package vn.anzi.modules.management.order.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class OrderEventEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    private Long id;
    @Column(name="fk_diner")
    private Long dinerId;
    @Column(name="fk_management_table")
    private Long tableId;
    @Column(name="fk_management_order_type")
    private Long orderTypeId;
    @Column
    private Boolean isConfirmed;
    @Column (name="created_time")
    private String createdTime;
}
