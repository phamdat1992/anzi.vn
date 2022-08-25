package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_dish_image")
@Data
public class DishImageEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int fkManagementDish;
    @Column
    private int fkManagementImage;
    @Column
    private Boolean isActive;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
