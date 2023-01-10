package hanu.gdsc.inventory.domaindriven.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.inventory.domaindriven.domain.model.Inventory;

public class InventoryEntity {
    private int id;
    private double height;
    private double length;
    private double width;
    private String inventoryItems;

    public static InventoryEntity fromDomainModel(Inventory inventory) {
        final InventoryEntity entity = new InventoryEntity();
        entity.id = inventory.getId();
        entity.height = inventory.getHeight();
        entity.width = inventory.getWidth();
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            entity.inventoryItems = objectMapper.writeValueAsString(inventory.getInventoryItems());
        } catch (Exception e) {
            throw new Error(e);
        }
        return entity;
    }
}
