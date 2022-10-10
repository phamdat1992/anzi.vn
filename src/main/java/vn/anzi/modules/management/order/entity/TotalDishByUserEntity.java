package vn.anzi.modules.management.order.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "management_order")
@Data
public class TotalDishByUserEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    @Column
    private Long quantity;
}
