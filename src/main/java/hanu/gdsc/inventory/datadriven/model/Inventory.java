package hanu.gdsc.inventory.datadriven.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {
    private int id;
    private double height;
    private double length;
    private double width;


    public static double calculateCapacity(Inventory inventory) {
        return inventory.height * inventory.width * inventory.length;
    }
}
