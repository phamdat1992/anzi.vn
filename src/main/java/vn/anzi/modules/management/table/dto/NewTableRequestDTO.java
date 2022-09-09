package vn.anzi.modules.management.table.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewTableRequestDTO {
    @NotNull
    @NotEmpty
    private Long eateryId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String location;
}
