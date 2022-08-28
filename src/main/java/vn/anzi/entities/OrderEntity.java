package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class OrderEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fk_management_diner")
    private int fkDiner;
    @Column(name = "fk_management_table")
    private int fkManagementTable;
    @Column(name = "fk_management_order_type")
    private int fkManagementOrderType;
    @Column
    private Boolean isConfirmed;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
