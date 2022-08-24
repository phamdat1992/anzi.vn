package vn.anzi.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.mail.search.DateTerm;
import javax.persistence.*;

@Entity
@Table(name = "management_image")
@Data
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    @Column(length = 1000)
    private String fileName;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
