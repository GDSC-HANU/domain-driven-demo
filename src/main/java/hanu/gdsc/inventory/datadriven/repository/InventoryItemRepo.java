package hanu.gdsc.inventory.datadriven.repository;

import hanu.gdsc.inventory.datadriven.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepo extends JpaRepository<InventoryItem, Integer> {
    public List<InventoryItem> getByInventoryId(int inventoryId);
}
