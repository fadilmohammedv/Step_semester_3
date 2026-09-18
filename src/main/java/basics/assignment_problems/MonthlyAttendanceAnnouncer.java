package oop.assignment_problems.problem4;

public class MonthlyAttendanceAnnouncer {
    public static void main(String[] args) {
        System.out.println(batchPrint(new GymMember[] {
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        }));
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder announcement = new StringBuilder();
        for (GymMember member : members) {
            announcement.append(member.displayInfo()).append(" | ");
            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                announcement.append("Trainer via downcast: ")
                        .append(premium.getTrainerName()).append(" | ");
            }
        }
        return announcement.toString();
    }
}

class GymMember {
    protected final String memberId;
    protected final int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4 || monthlyFee <= 0) {
            throw new IllegalArgumentException();
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    private final String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: "
                + getSessionsAttended();
    }
}