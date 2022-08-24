package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Long dinner;
    @Column
    private Long table;
    @Column
    private Integer order_type;
    @Column
    private Boolean is_confirmed;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
