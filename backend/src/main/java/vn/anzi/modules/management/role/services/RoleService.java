package vn.anzi.modules.management.role.services;

import org.springframework.stereotype.Service;
import vn.anzi.modules.management.role.entity.RoleEntity;
import vn.anzi.modules.management.role.model.RoleModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    public List<RoleEntity> getAllRole() {
        List<RoleEntity> roles = new ArrayList<>();

        RoleEntity manager = new RoleEntity();
        manager.setId((long) RoleModel.MANAGER.getValue());
        manager.setName("manager");

        RoleEntity staff = new RoleEntity();
        manager.setId((long) RoleModel.STAFF.getValue());
        manager.setName("staff");

        roles.add(manager);
        roles.add(staff);

        return roles;
    }
}
