package hanu.gdsc.inventory.datadriven.service;

import hanu.gdsc.inventory.datadriven.repository.InventoryRepo;
import hanu.gdsc.inventory.datadriven.model.Inventory;
import hanu.gdsc.inventory.datadriven.model.InventoryItem;
import hanu.gdsc.inventory.datadriven.repository.InventoryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CheckInService {
    @Autowired
    private InventoryItemRepo inventoryItemRepo;
    @Autowired
    private InventoryRepo inventoryRepo;

    public void checkIn(int inventoryId, List<InventoryItem> inventoryItemsToCheckIn) {
        Inventory inventory = inventoryRepo.getById(inventoryId);
        List<InventoryItem> inventoryItems = inventoryItemRepo.getByInventoryId(inventoryId);
        double remainInventoryCapacity = calculateRemainInventoryCapacity(inventory, inventoryItems);
        double inventoryItemsToCheckInVolumn = InventoryItem.calculateCapacity(inventoryItemsToCheckIn);
        if (remainInventoryCapacity < inventoryItemsToCheckInVolumn)
            throw new ArithmeticException("Insufficent inventory capacity");
        /*
        Vấn đề của data driven design:
            Ví dụ có 2 thằng A và B cùng gọi vào function này để nhập hàng vào cùng một inventory
            2 threads sẽ được tạo ra và chạy song song để xử lý request của A và B
            Giả sử thread của A chạy xong dòng 23 thì bị đơ, lúc này đối với A, inventory vẫn còn diện tích để nhập hàng
            Trong lúc thread của A bị đơ, thread của B chen vào giữa và thực hiện xong từ đầu đến cuối function này
            Tuy nhiên, sau khi thread của B thực hiện xong, B đã nhập 1 số hàng vào kho, và kho đã hết diện tích để nhập hàng
            Tới lúc này, thread của A hết bị đơ, và thực hiện nốt dòng lệnh cuối (dòng 36)
            Lúc này, số hàng thực tế trong kho đã vượt quá diện tích cho phép của kho
            => Data sai lệch => Giải pháp là thiết kế lại model theo Domain Driven Design
         */

        inventoryItemRepo.saveAll(inventoryItemsToCheckIn);
    }

    public double calculateRemainInventoryCapacity(Inventory inventory, List<InventoryItem> inventoryItems) {
        double inventoryCapacity = Inventory.calculateCapacity(inventory);
        double totalInventoryItemVolumn = InventoryItem.calculateCapacity(inventoryItems);
        double remainInventoryCapacity = inventoryCapacity - totalInventoryItemVolumn;
        return remainInventoryCapacity;
    }
}
