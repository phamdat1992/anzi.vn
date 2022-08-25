package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_user_eatery_role")
@Data
public class UserEateryRoleEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int fkManagementUserEatery;
    @Column
    private int fkManagementRole;
    @Column
    private Boolean isActive;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
