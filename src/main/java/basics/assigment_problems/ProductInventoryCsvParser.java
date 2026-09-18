package basics.assigment_problems;

public class ProductInventoryCsvParser {

    public void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Product: " + fields[0] + " | SKU: " + fields[1] + " | Qty: " + fields[2]);
    }

    public static void main(String[] args) {
        ProductInventoryCsvParser parser = new ProductInventoryCsvParser();
        parser.parseInventoryRecord("Wireless Mouse,WM-2201,150");
        parser.parseInventoryRecord("Wireless Mouse,150");
    }
}