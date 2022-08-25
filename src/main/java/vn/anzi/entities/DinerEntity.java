package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "diner")
@Data
public class DinerEntity
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="authen_name")
    private String authenName; //index
    @Column(name="authen_pass")
    private String authenPass; //null
    @Column(name="create_time")
    private DateTime createdTime;
    @Column(name="updated_time")
    private DateTime updatedTime;
}
