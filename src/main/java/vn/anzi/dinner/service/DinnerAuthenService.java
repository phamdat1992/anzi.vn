package vn.anzi.dinner.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anzi.entities.DinnerEntity;
import vn.anzi.repository.DinnerRepository;

import java.util.UUID;

@Service
public class DinnerAuthenService {
    @Autowired
    private DinnerRepository dinnerRepository;

    public String getNewDinner() {
        DinnerEntity dinner = new DinnerEntity();
        dinner.setAuthenName(UUID.randomUUID().toString());
        dinner.setCreatedTime(new DateTime());
        dinner.setUpdatedTime(dinner.getCreatedTime());

        dinnerRepository.save(dinner);

        return dinner.getAuthenName();
    }
}
