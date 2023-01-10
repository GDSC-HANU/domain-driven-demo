package hanu.gdsc.inventory.datadriven.repository;

import hanu.gdsc.inventory.datadriven.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
}
