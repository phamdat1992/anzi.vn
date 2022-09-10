package vn.anzi.modules.management.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.role.entity.RoleEntity;
import vn.anzi.modules.management.user.entity.StaffEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStaffResponseDTO {
    private List<RoleEntity> roles;
    private List<StaffEntity> staffs;
}
