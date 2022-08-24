package vn.anzi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_image")
@Data
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1000)
    private String file_name;
    @Column
    private Long created_time;
    @Column
    private Long updated_time;
}
