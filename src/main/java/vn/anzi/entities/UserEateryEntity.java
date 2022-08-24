package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_user_eatery")
@Data
public class ManagementUserEatery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1000)
    private String name;
    @Column
    private Integer manager;
    @Column
    private Integer eatery;
    @Column
    private Boolean is_active;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
