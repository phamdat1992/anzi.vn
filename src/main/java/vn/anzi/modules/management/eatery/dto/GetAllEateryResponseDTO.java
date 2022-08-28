package vn.anzi.modules.management.eatery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.eatery.entity.EateryEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEateryResponseDTO {
    private List<EateryEntity> eatery;
}
