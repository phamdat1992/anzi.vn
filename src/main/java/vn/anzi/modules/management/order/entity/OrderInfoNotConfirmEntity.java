package vn.anzi.modules.management.order.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class OrderInfoNotConfirmEntity {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String location;
    @Column (name="type_id")
    private Long typeId;
    @Column (name="total_dish")
    private Long totalDish;
    @Column (name="total_price")
    private Long totalPrice;
    @Column (name="created_time")
    private String createdTime;
}
