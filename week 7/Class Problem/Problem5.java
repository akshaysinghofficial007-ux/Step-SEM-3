// Problem 5: Package Drop-Off Log
abstract class DeliveryNote {
    protected final String trackingId;

    public DeliveryNote(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty())
            throw new IllegalArgumentException("trackingId must not be blank");
        this.trackingId = trackingId;
    }

    public abstract String confirmDelivery();

    // Overloaded: reuses the core message so it lives in only one place
    String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    public ParcelNote(String trackingId) { super(trackingId); }
    @Override public String confirmDelivery() { return "Parcel " + trackingId + " delivered"; }
}

class LetterNote extends DeliveryNote {
    public LetterNote(String trackingId) { super(trackingId); }
    @Override public String confirmDelivery() { return "Letter " + trackingId + " delivered"; }
}

public class Problem5 {
    static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote n : notes) {
            System.out.println(n.confirmDelivery());   // runtime polymorphism
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;   // upcasting
        System.out.println("--- logAll ---");
        logAll(new DeliveryNote[]{ ref, new LetterNote("TRK-2") });
    }
}
