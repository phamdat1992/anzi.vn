//package vn.anzi.diner.service;
//
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import vn.anzi.entities.DinerEntity;
//import vn.anzi.repository.DinerRepository;
//
//import java.util.UUID;
//
//@Service
//public class DinerAuthenService {
//    @Autowired
//    private DinerRepository dinerRepository;
//
//    public String getNewDiner() {
//        DinerEntity diner = new DinerEntity();
//        diner.setAuthenName(UUID.randomUUID().toString());
//        diner.setCreatedTime(new DateTime());
//        diner.setUpdatedTime(diner.getCreatedTime());
//
//        dinerRepository.save(diner);
//
//        return diner.getAuthenName();
//    }
//}
