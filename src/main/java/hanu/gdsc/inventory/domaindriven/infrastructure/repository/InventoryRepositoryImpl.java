package hanu.gdsc.inventory.domaindriven.infrastructure.repository;

import hanu.gdsc.inventory.domaindriven.domain.model.Inventory;
import hanu.gdsc.inventory.domaindriven.domain.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {
    @Autowired
    private InventoryJpaRepo inventoryJpaRepo;

    @Override
    public void save(Inventory inventory) {
        final InventoryEntity entity = InventoryEntity.fromDomainModel(inventory);
        inventoryJpaRepo.save(entity);
    }

    @Override
    public Inventory getById(int id) {
        return null;
    }
}
