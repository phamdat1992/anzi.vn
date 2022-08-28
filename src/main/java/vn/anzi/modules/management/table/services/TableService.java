package vn.anzi.modules.management.table.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.eatery.repository.EateryRepository;
import vn.anzi.modules.management.table.dto.DeleteTableRequestDTO;
import vn.anzi.modules.management.table.dto.NewTableRequestDTO;
import vn.anzi.modules.management.table.entity.TableEntity;
import vn.anzi.modules.management.table.repository.TableRepository;

import javax.persistence.Table;
import java.util.List;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;

    @Transactional(rollbackFor = Exception.class)
    public TableEntity createTable(NewTableRequestDTO table) {
        TableEntity newTable = new TableEntity();
        newTable.setName(table.getName());
        newTable.setLocation(table.getLocation());
        newTable.setEateryId(table.getEateryId());
        newTable.setIsActive(true);
        return tableRepository.save(newTable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTable(DeleteTableRequestDTO table) {
        TableEntity deleteTable = new TableEntity();
        deleteTable.setId(table.getTableId());
        deleteTable.setIsActive(false);
        tableRepository.save(deleteTable);
    }

    public List<TableEntity> getAllTableByEateryId(Long eateryId) {
        return tableRepository.getAllTableByEateryId(eateryId);
    }
}
