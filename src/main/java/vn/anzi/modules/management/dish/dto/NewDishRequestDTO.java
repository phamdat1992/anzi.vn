package vn.anzi.modules.management.dish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDishRequestDTO {
    private String code;
    @NotNull
    @NotEmpty
    private String image;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private Long price;
    @NotNull
    @NotEmpty
    private Boolean status;
    @NotNull
    @NotEmpty
    private Long categoryId;
    @NotNull
    @NotEmpty
    private Long eateryId;
}
