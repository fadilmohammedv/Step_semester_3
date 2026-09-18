package oop.class_problems.problem3;

public class StudentDiscountFineLedger {
    public static void main(String[] args) {
        StudentMember member = new StudentMember("STU5", 3, "CSE");
        member.applyFineForTesting(100);
        System.out.println(member.getTotalFine());
    }
}

class LibraryMember {
    protected final String memberId;
    protected final int borrowLimit;
    private final int[] fineHistory = new int[10];
    private int fineCount;

    LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4 || borrowLimit <= 0) {
            throw new IllegalArgumentException();
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    int[] getFineHistory() {
        int[] copy = new int[fineCount];
        System.arraycopy(fineHistory, 0, copy, 0, fineCount);
        return copy;
    }

    int getTotalFine() {
        int total = 0;
        for (int index = 0; index < fineCount; index++) {
            total += fineHistory[index];
        }
        return total;
    }
}

class StudentMember extends LibraryMember {
    private final String course;

    StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }

    void applyFineForTesting(int amount) {
        chargeFine(amount);
    }
}
