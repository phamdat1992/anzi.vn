package vn.anzi.modules.management.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.user.entity.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponseDTO {
    private UserEntity user;
}
