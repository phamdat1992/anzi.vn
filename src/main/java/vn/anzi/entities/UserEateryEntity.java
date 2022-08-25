package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_user_eatery")
@Data
public class UserEateryEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String name;
    @Column
    private int fkManagementUser;
    @Column
    private int fkManagementEatery;
    @Column
    private Boolean isActive;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedtime;
}
