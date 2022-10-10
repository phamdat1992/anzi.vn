package vn.anzi.modules.management.order.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "management_order")
@Data
public class OrderDetailEntity {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private String image;
    @Column
    private Long price;
    @Column
    private Long quantity;
}
