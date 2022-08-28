package vn.anzi.modules.management.eatery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.eatery.entity.*;
import vn.anzi.modules.management.eatery.repository.EateryRepository;
import vn.anzi.modules.management.eatery.repository.UserEateryRepository;
import vn.anzi.modules.management.role.model.UserRoleModel;
import vn.anzi.modules.management.role.services.RoleService;
import vn.anzi.modules.management.user.entity.UserEntity;

import java.util.List;

@Service
public class EateryService {
    @Autowired
    private EateryRepository eateryRepository;

    @Autowired
    private UserEateryRepository userEateryRepository;

    @Transactional(rollbackFor = Exception.class)
    public EateryEntity createEatery(EateryEntity eatery) {
        return eateryRepository.save(eatery);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserEateryEntity createUserEatery(EateryEntity eateryEntity, UserEntity userEntity) {
        UserEateryEntity userEateryEntity = new UserEateryEntity();
        userEateryEntity.setEateryId(eateryEntity.getId());
        userEateryEntity.setUserId(userEntity.getId());
        userEateryEntity.setIsActive(true);
        return userEateryRepository.save(userEateryEntity);
    }

    public List<EateryEntity> getAllEateryByUserId(long userId) {
        return eateryRepository.getAllEateryByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeUserByUserEateryId(Long userEateryId, Long eateryId) {
        userEateryRepository.removeUserByUserEateryId(userEateryId, eateryId);
    }
}
