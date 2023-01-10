package hanu.gdsc.inventory.domaindriven.domain.repository;

import hanu.gdsc.inventory.domaindriven.domain.model.Inventory;

public interface InventoryRepository {
    public void save(Inventory inventory);

    public Inventory getById(int id);
}
