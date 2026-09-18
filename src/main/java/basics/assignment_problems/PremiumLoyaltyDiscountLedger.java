package oop.assignment_problems.problem3;

public class PremiumLoyaltyDiscountLedger {
    public static void main(String[] args) {
        PremiumMember member = new PremiumMember("MEM5", 2000, "Coach Riya");
        member.chargeLateFee(200);
        System.out.println(member.getTotalLateFees());
    }
}

class GymMember {
    protected final String memberId;
    protected final int monthlyFee;
    private final int[] lateFeeHistory = new int[10];
    private int lateFeeCount;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4 || monthlyFee <= 0) {
            throw new IllegalArgumentException();
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    protected void chargeLateFee(int amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        int[] copy = new int[lateFeeCount];
        System.arraycopy(lateFeeHistory, 0, copy, 0, lateFeeCount);
        return copy;
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int index = 0; index < lateFeeCount; index++) {
            total += lateFeeHistory[index];
        }
        return total;
    }
}

class PremiumMember extends GymMember {
    private final String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}