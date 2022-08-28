package vn.anzi.modules.management.table.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.table.entity.TableEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTableResponseDTO {
    private List<TableEntity> table;
}
