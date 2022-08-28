package vn.anzi.modules.management.image.entity;

import lombok.Data;
import org.joda.time.DateTime;
import javax.persistence.*;

@Entity
@Table(name = "management_image")
@Data
public class ImageEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 1000)
    private String fileName;
    @Column
    private DateTime createdTime;
    @Column
    private DateTime updatedTime;
}
