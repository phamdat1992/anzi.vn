package vn.anzi.modules.management.order.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_order_type")
@Data
public class OrderTypeEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String name;
    @Column
    private DateTime created_time;
    @Column
    private DateTime updated_time;
}