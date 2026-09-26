package oop.class_problems.problem2;

public class WarehouseLabelPrinter {
    public static void main(String[] args) {
        PackageBox packageBox = new PackageBox("TRK-88");
        Invoice invoice = new Invoice("INV-42");
        packageBox.printLabel();
        invoice.printLabel();
        printAll(new Printable[] {packageBox, invoice});
    }

    public static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }
}

interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private final String trackingId;

    public PackageBox(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {
    private final String invoiceNumber;

    public Invoice(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}
