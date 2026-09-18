package oop.assignment_problems.problem5;

public class MembershipNumbersReferralAudit {
    public static void main(String[] args) {
        GymMember member = new GymMember(1000);
        member.payFee(500);
        member.payFee(500, "UPI");
        System.out.println(member.getFeesPaid());
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4 || code.charAt(0) != 'G'
                || !Character.isDigit(code.charAt(1))
                || !Character.isDigit(code.charAt(2))
                || !Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public static int getMembersEnrolled() {
        return GymMember.getMembersEnrolled();
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;
        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
            } else {
                processed++;
                if (member instanceof GroupClassMember) {
                    group++;
                } else {
                    individual++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }
}

class GymMember {
    private static int membersEnrolled;
    private static int nextMemberNumber = 2000;

    public final String membershipNumber;
    private final int monthlyFee;
    private int feesPaid;
    private String lastPaymentMode;

    public GymMember(int monthlyFee) {
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException();
        }
        this.monthlyFee = monthlyFee;
        membershipNumber = "GYM-" + ++nextMemberNumber;
        membersEnrolled++;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        lastPaymentMode = mode;
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getLastPaymentMode() {
        return lastPaymentMode;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class GroupClassMember extends GymMember {
    private final String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}