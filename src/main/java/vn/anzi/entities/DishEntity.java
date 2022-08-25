package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_dish")
@Data
public class DishEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String name;
    @Column
    private float price;
    @Column(length = 1000)
    private String code; //allow null
    @Column
    private int eatery;
    @Column
    private int category;
    @Column
    private int dishStatus;
    @Column
    private DateTime created_time;
    @Column
    private DateTime updated_time;
}
