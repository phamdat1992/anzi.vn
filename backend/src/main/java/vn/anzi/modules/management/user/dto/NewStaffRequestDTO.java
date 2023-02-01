package vn.anzi.modules.management.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewStaffRequestDTO {
    @NotNull
    @NotEmpty
    private Long eateryId;
    @NotNull
    @NotEmpty
    private Long roleId;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String name;
}
