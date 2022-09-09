package vn.anzi.modules.management.user.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anzi.modules.management.eatery.entity.EateryEntity;
import vn.anzi.modules.management.eatery.entity.UserEateryEntity;
import vn.anzi.modules.management.eatery.services.EateryService;
import vn.anzi.modules.management.user.dto.NewStaffRequestDTO;
import vn.anzi.modules.management.user.entity.StaffEntity;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.repository.StaffRepository;
import vn.anzi.modules.management.user.repository.UserRepository;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @Autowired
    private EateryService eateryService;

    public List<StaffEntity> getAllStaffByEateryId(Long eateryId) {
        return staffRepository.getAllStaffByEateryId(eateryId);
    }

    public UserEateryEntity createNewStaff(NewStaffRequestDTO newStaffRequestDTO) {
        UserEntity newStaffUser = authenticateUserService.getUserFromDbByEmail(newStaffRequestDTO.getEmail());
        if (newStaffUser == null) {
            newStaffUser = authenticateUserService.createUser(newStaffUser.getEmail());
        }

        EateryEntity eateryEntity = new EateryEntity();
        eateryEntity.setId(newStaffRequestDTO.getEateryId());
        UserEateryEntity userEateryEntity = eateryService.createUserEatery(eateryEntity, newStaffUser, newStaffRequestDTO.getRoleId());

        return userEateryEntity;
    }
}
