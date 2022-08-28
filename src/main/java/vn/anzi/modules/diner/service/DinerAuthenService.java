package vn.anzi.modules.diner.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anzi.modules.diner.entity.DinerEntity;
import vn.anzi.modules.diner.repository.DinerRepository;

import java.util.UUID;

@Service
public class DinerAuthenService {
    @Autowired
    private DinerRepository dinerRepository;

    public Long getNewDiner() {
        DinerEntity diner = new DinerEntity();
        diner.setAuthenName(UUID.randomUUID().toString());

        dinerRepository.save(diner);

        return diner.getId();
    }
}
