package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.order.entity.DishBucketEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishBucketResponseDTO {
    private List<DishBucketEntity> bucket;
}
