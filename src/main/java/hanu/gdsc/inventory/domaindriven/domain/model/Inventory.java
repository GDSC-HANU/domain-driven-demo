package hanu.gdsc.inventory.domaindriven.domain.model;

import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class Inventory {
    private int id;
    private double height;
    private double length;
    private double width;
    private List<InventoryItem> inventoryItems;

    /*
    Đây là cách Domain Driven khắc phục lỗi sai lệch data của Data Driven
    Danh sách Inventory Item được encapsulate trong model Inventory
    Mọi hành động nhập hàng phải đi qua function checkIn() dưới dây
    và trong hàm checkIn() này đã có logic check điều kiện đề bài (chỉ khi kho còn diện tích thì mới được nhập hàng)
    vì vậy data sẽ luôn đúng => khắc phục được lỗi của Data Driven
    ngoài ra code còn rất là clean, đọc là hiểu được nghiệp vụ
     */

    public void checkIn(InventoryItem inventoryItem) {
        if (inventoryItem.volumn() > capacity())
            throw new RuntimeException("Insufficient capacity");
        inventoryItems.add(inventoryItem);
    }

    public double capacity() {
        double totalInventoryItemVolumn = 0;
        for (InventoryItem inventoryItem : inventoryItems)
            totalInventoryItemVolumn += inventoryItem.volumn();
        return volumn() - totalInventoryItemVolumn;
    }

    public double volumn() {
        return height * width * length;
    }

    public List<InventoryItem> getInventoryItems() {
        return Collections.unmodifiableList(inventoryItems);
    }
}
