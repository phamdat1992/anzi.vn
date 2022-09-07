package vn.anzi.modules.diner.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "diner")
@Data
public class DinerEntity
{
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="authen_name")
    private String authenticateName; //index
}
