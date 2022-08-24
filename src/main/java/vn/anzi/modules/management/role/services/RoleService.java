package vn.anzi.modules.management.role.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anzi.modules.management.role.entity.UserEateryRoleEntity;
import vn.anzi.modules.management.role.model.UserRoleModel;
import vn.anzi.modules.management.role.repository.UserEateryRoleRepository;

@Service
public class RoleService {
    @Autowired
    UserEateryRoleRepository userEateryRoleRepository;

    public UserEateryRoleEntity updateUserRole(Long userEateryId, UserRoleModel newRole) {
        UserEateryRoleEntity userEateryRoleEntity = userEateryRoleRepository.findByUserEateryId(userEateryId).orElse(null);

        if (userEateryRoleEntity != null) {
            if (userEateryRoleEntity.getRoleId().equals((long) newRole.getValue())) {
                return userEateryRoleEntity;
            }

            userEateryRoleEntity.setIsActive(false);
            userEateryRoleRepository.save(userEateryRoleEntity);
        }

        UserEateryRoleEntity newUserEntityRole = new UserEateryRoleEntity();
        newUserEntityRole.setRoleId((long) newRole.getValue());
        newUserEntityRole.setUserEateryId(userEateryId);
        return userEateryRoleRepository.save(newUserEntityRole);
    }
}
