import java.util.Arrays;

public class LoanReceiptSettlement {
    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("Circulation ledger loaded");
    }

    public LoanReceiptSettlement(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceiptSettlement withCorrectedBookId(int index, String newId) {
        String[] correctedBookIds = getBookIds();
        correctedBookIds[index] = newId;
        return new LoanReceiptSettlement(memberId, correctedBookIds);
    }

    public static String processNightlyCirculation(LoanReceiptSettlement[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceiptSettlement receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
            } else {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceiptSettlement) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceiptSettlement receipt = new LoanReceiptSettlement(
                "LIB-8841", new String[] {"BK-100", "BK-101"});
        String[] books = receipt.getBookIds();
        books[0] = "HACKED";
        System.out.println(receipt.getBookIds()[0]);

        LoanReceiptSettlement corrected = receipt.withCorrectedBookId(1, "BK-102");
        System.out.println(Arrays.toString(receipt.getBookIds()));
        System.out.println(Arrays.toString(corrected.getBookIds()));

        LoanReceiptSettlement[] receipts = {
            new ReferenceOnlyLoanReceiptSettlement("LIB-001", new String[] {"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceiptSettlement("LIB-002", new String[] {"BK-201"})
        };
        System.out.println(processNightlyCirculation(receipts));
    }
}

class ReferenceOnlyLoanReceiptSettlement extends LoanReceiptSettlement {
    private final String roomNumber;

    ReferenceOnlyLoanReceiptSettlement(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}