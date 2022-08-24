package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int fkDiner;
    @Column
    private int fkManagementTable;
    @Column
    private int fkManagementOrderType;
    @Column
    private Boolean isConfirmed;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
