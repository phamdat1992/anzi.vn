package vn.anzi.dinner.service;

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
        dinner.setAuthen_name(UUID.randomUUID().toString());
        dinner.setCreated_time(System.nanoTime());
        dinner.setUpdated_time(System.nanoTime());

        dinnerRepository.save(dinner);

        return dinner.getAuthen_name();
    }
}
