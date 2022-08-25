package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_user")
@Data
public class UserEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String email;
    @Column
    private DateTime created_time;
    @Column
    private DateTime updated_time;
}
