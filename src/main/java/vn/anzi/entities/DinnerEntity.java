package vn.anzi.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "dinner")
@Data
public class DinnerEntity
{
    /**
     * Table save dinner entity. Authen_name use index to search more faster. Authen_pass is null. Dinner connect http to
     * sv, sv save a sessionId to cookie with expire time is 8 hours.
     */
    @Id
    @Column(length = 1000)
    private String authen_name; //index
    @Column(length = 1000)
    private String authen_pass; //null
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
