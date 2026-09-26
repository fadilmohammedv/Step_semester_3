package oop.class_problems.problem5;

public class PackageDropOffLog {
    public static void main(String[] args) {
        ParcelNote parcel = new ParcelNote("TRK-1");
        DeliveryNote reference = parcel;
        reference.confirmDelivery();
        reference.confirmDelivery("J. Smith");
        logAll(new DeliveryNote[] {reference, new LetterNote("TRK-2")});
    }

    public static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }
}

abstract class DeliveryNote {
    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    private final String trackingId;

    public ParcelNote(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    private final String trackingId;

    public LetterNote(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}
