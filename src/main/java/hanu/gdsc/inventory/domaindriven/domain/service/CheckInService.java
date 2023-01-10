package hanu.gdsc.inventory.domaindriven.domain.service;

import hanu.gdsc.inventory.domaindriven.domain.model.Inventory;
import hanu.gdsc.inventory.domaindriven.domain.model.InventoryItem;
import hanu.gdsc.inventory.domaindriven.domain.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CheckInService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public void checkIn(int inventoryId, List<InventoryItem> inventoryItems) {
        final Inventory inventory = inventoryRepository.getById(inventoryId);
        for (InventoryItem inventoryItem : inventoryItems)
            inventory.checkIn(inventoryItem);
        inventoryRepository.save(inventory);
    }
}
