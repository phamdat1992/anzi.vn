package vn.anzi.modules.management.eatery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.eatery.entity.EateryEntity;
import vn.anzi.modules.management.eatery.entity.UserEateryEntity;
import vn.anzi.modules.management.eatery.repository.EateryRepository;
import vn.anzi.modules.management.eatery.repository.UserEateryRepository;
import vn.anzi.modules.management.role.model.UserRoleModel;
import vn.anzi.modules.management.role.services.RoleService;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.repository.UserRepository;

@Service
public class EateryService {
    @Autowired
    private EateryRepository eateryRepository;

    @Autowired
    private UserEateryRepository userEateryRepository;

    @Autowired
    private RoleService roleService;

    @Transactional(rollbackFor = Exception.class)
    public EateryEntity createEatery(EateryEntity eatery, UserEntity userEntity) {
        EateryEntity newEntity = eateryRepository.save(eatery);

        UserEateryEntity userEateryEntity = new UserEateryEntity();
        userEateryEntity.setEateryId(newEntity.getId());
        userEateryEntity.setUserId(userEntity.getId());
        userEateryEntity = userEateryRepository.save(userEateryEntity);

        roleService.updateUserRole(userEateryEntity.getEateryId(), UserRoleModel.MANAGER);

        return newEntity;
    }
}
