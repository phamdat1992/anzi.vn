package vn.anzi.modules.management.eatery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.eatery.entity.*;
import vn.anzi.modules.management.eatery.repository.EateryRepository;
import vn.anzi.modules.management.eatery.repository.EateryRoleRepository;
import vn.anzi.modules.management.eatery.repository.UserEateryRepository;
import vn.anzi.modules.management.role.model.RoleModel;
import vn.anzi.modules.management.user.entity.UserEntity;

import java.util.List;

@Service
public class EateryService {
    @Autowired
    private EateryRepository eateryRepository;

    @Autowired
    private EateryRoleRepository eateryRoleRepository;

    @Autowired
    private UserEateryRepository userEateryRepository;

    @Transactional(rollbackFor = Exception.class)
    public EateryEntity createEatery(EateryEntity eatery) {
        return eateryRepository.save(eatery);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserEateryEntity createUserEatery(EateryEntity eateryEntity, UserEntity userEntity, Long roleId, String name) {
        UserEateryEntity userEateryEntity = new UserEateryEntity();
        userEateryEntity.setEateryId(eateryEntity.getId());
        userEateryEntity.setUserId(userEntity.getId());
        userEateryEntity.setIsActive(true);
        userEateryEntity.setRoleId(roleId);
        userEateryEntity.setName(name);
        return userEateryRepository.save(userEateryEntity);
    }

    public List<EateryRoleEntity> getAllEateryByUserId(Long userId) {
        return eateryRoleRepository.getAllEateryByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeUserByUserId(Long userEateryId) {
        userEateryRepository.removeUserByUserId(userEateryId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Long id, String name, Long roleId) {
        userEateryRepository.updateUser(id, name, roleId);
    }

    public UserEateryEntity getUserEateryByUserIdAndEateryId(Long userId, Long eateryId) {
        return userEateryRepository.findByUserIdAndEateryId(userId, eateryId).orElse(null);
    }
}
