package vn.anzi.modules.management.order.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class TotalDishByUserEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    @Column
    private Long quantity;
}
