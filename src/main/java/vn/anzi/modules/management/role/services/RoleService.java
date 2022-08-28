package vn.anzi.modules.management.role.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.role.entity.RoleEntity;
import vn.anzi.modules.management.role.entity.UserEateryRoleEntity;
import vn.anzi.modules.management.role.model.UserRoleModel;
import vn.anzi.modules.management.role.repository.RoleRepository;
import vn.anzi.modules.management.role.repository.UserEateryRoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    UserEateryRoleRepository userEateryRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserEateryRoleEntity updateUserRole(Long userEateryId, UserRoleModel newRole) {
        UserEateryRoleEntity userEateryRoleEntity = userEateryRoleRepository.findByUserEateryId(userEateryId).orElse(null);

        if (userEateryRoleEntity != null) {
            if (userEateryRoleEntity.getRoleId().equals((long) newRole.getValue())) {
                return userEateryRoleEntity;
            }

            userEateryRoleEntity.setIsActive(false);
            return this.createUserRole(userEateryRoleEntity);
        }

        UserEateryRoleEntity newUserEntityRole = new UserEateryRoleEntity();
        newUserEntityRole.setRoleId((long) newRole.getValue());
        newUserEntityRole.setUserEateryId(userEateryId);
        newUserEntityRole.setIsActive(true);
        return this.createUserRole(newUserEntityRole);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserEateryRoleEntity createUserRole(UserEateryRoleEntity userEateryRoleEntity) {
        return userEateryRoleRepository.save(userEateryRoleEntity);
    }

    public UserEateryRoleEntity getUserEateryRoleEntity(Long userId, Long eateryId) {
        return userEateryRoleRepository.findByUserIdAndEateryId(userId, eateryId).orElse(null);

    }

    public List<RoleEntity> getAllRole() {
        return roleRepository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public void removeUserByUserEateryId(Long userEateryId) {
        userEateryRoleRepository.removeUserByUserEateryId(userEateryId);
    }
}
