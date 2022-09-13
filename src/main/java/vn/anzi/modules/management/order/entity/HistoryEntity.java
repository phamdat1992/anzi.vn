package vn.anzi.modules.management.order.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "management_order")
@Data
public class HistoryEntity {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
