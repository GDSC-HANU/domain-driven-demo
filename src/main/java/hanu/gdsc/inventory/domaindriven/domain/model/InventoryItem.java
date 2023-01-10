package hanu.gdsc.inventory.domaindriven.domain.model;

public class InventoryItem {
    private String name;
    private double height;
    private double length;
    private double width;
    public double volumn() {
        return height * width * length;
    }
}
