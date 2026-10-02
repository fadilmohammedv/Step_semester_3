package oop.assignment_problems.problem4;

public class FitzZoneMembershipDesk {
    public static void main(String[] args) {
        Membership asha = new Membership(new Member("Asha"), new QuarterlyPlan());
        Membership ravi = new Membership(new Member("Ravi"), new MonthlyPlan());

        System.out.println(asha);
        System.out.println(ravi);
        System.out.println(asha.checkIn());
        System.out.println(asha.freeze());
        System.out.println(asha.checkIn());
        System.out.println(ravi.expire());
        System.out.println(ravi.freeze());
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Member {
    private final String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface MembershipPlan {
    String getName();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {
    public String getName() {
        return "Monthly";
    }

    public double calculateFee() {
        return 1000.0;
    }
}

class QuarterlyPlan implements MembershipPlan {
    public String getName() {
        return "Quarterly";
    }

    public double calculateFee() {
        return 1000.0 * 3 * 0.90;
    }
}

class AnnualPlan implements MembershipPlan {
    public String getName() {
        return "Annual";
    }

    public double calculateFee() {
        return 1000.0 * 12 * 0.75;
    }
}

class Membership {
    private final Member member;
    private final MembershipPlan plan;
    private MembershipStatus status = MembershipStatus.ACTIVE;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
    }

    public String checkIn() {
        if (status != MembershipStatus.ACTIVE) {
            return "Check-in denied: " + member.getName() + "'s membership is " + status + ".";
        }
        return member.getName() + " checked in successfully.";
    }

    public String freeze() {
        if (status == MembershipStatus.EXPIRED) {
            return "Cannot freeze an expired membership.";
        }
        if (status == MembershipStatus.FROZEN) {
            return member.getName() + "'s membership is already Frozen.";
        }
        status = MembershipStatus.FROZEN;
        return member.getName() + "'s membership frozen. Status: Frozen.";
    }

    public String unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            return "Cannot unfreeze an expired membership.";
        }
        status = MembershipStatus.ACTIVE;
        return member.getName() + "'s membership unfrozen. Status: Active.";
    }

    public String expire() {
        status = MembershipStatus.EXPIRED;
        return member.getName() + "'s membership expired. Status: Expired.";
    }

    @Override
    public String toString() {
        return plan.getName() + " membership created for " + member.getName() + ". Fee: Rs."
                + String.format("%.2f", plan.calculateFee()) + ". Status: " + status + ".";
    }
}
