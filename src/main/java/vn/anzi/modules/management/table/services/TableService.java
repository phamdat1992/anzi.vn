package vn.anzi.modules.management.table.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.table.dto.DeleteTableRequestDTO;
import vn.anzi.modules.management.table.dto.NewTableRequestDTO;
import vn.anzi.modules.management.table.dto.UpdateTableRequestDTO;
import vn.anzi.modules.management.table.entity.TableEntity;
import vn.anzi.modules.management.table.repository.TableRepository;

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
        tableRepository.deleteTable(table.getId());
    }

    public List<TableEntity> getAllTableByEateryId(Long eateryId) {
        return tableRepository.getAllTableByEateryId(eateryId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTable(UpdateTableRequestDTO table) {
        tableRepository.updateTable(table.getId(), table.getName(), table.getLocation());
    }

    public TableEntity getTableById(Long tableId) {
        return tableRepository.findById(tableId).orElse(null);
    }
}
