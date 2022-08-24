package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_eatery")
@Data
public class EateryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String name;
    @Column(length = 1000)
    private String address;
    @Column
    private int createdByFkManagementUser;
    @Column
    private DateTime created_time;
    @Column
    private DateTime updated_time;
}
