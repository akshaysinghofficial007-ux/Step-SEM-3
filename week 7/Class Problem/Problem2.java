// Problem 2: Warehouse Label Printer
interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private final String trackingId;
    public PackageBox(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty())
            throw new IllegalArgumentException("trackingId must not be blank");
        this.trackingId = trackingId;
    }
    @Override public String printLabel() { return "Package label: " + trackingId; }
}

class Invoice implements Printable {
    private final String invoiceNumber;
    public Invoice(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.trim().isEmpty())
            throw new IllegalArgumentException("invoiceNumber must not be blank");
        this.invoiceNumber = invoiceNumber;
    }
    @Override public String printLabel() { return "Invoice label: " + invoiceNumber; }
}

public class Problem2 {
    static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        Invoice i = new Invoice("INV-42");
        System.out.println(p.printLabel());
        System.out.println(i.printLabel());
        System.out.println("--- printAll ---");
        printAll(new Printable[]{ p, i });
    }
}
