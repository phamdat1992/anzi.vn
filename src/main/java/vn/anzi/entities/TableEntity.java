package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_table")
@Data
public class TableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String name;
    @Column(length = 1000)
    private String location;
    @Column
    private int fkManagementEatery;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
