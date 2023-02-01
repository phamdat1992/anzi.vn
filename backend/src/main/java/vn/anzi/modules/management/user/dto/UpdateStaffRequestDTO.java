package vn.anzi.modules.management.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStaffRequestDTO {
    @NotNull
    @NotEmpty
    private Long roleId;
    @NotNull
    @NotEmpty
    private Long staffId;
    @NotNull
    @NotEmpty
    private String name;
}
