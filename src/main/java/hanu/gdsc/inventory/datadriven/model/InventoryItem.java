package hanu.gdsc.inventory.datadriven.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
Đối với Data Driven, cách thiết kế thường thấy là
tách Inventory Item thành một table riêng như này (trong trường hợp dùng SQL)
 */
@Getter
@Setter
public class InventoryItem {
    private int inventoryId;
    private int id;
    private String name;
    private double height;
    private double length;
    private double width;


    public static double calculateCapacity(InventoryItem inventoryItem) {
        return inventoryItem.height * inventoryItem.width * inventoryItem.length;
    }

    public static double calculateCapacity(List<InventoryItem> inventoryItems) {
        double res = 0;
        for (InventoryItem inventoryItem : inventoryItems)
            res += InventoryItem.calculateCapacity(inventoryItem);
        return res;
    }
}
