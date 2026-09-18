package oop.class_problems.problem5;

public class MembershipNumbersRenewalAudit {
    private static int membersEnrolled;
    private static int nextMemberNumber = 100;

    public static void main(String[] args) {
        System.out.println(isValidRenewalCode("R12A"));
    }

    public static boolean isValidRenewalCode(String code) {
        return code != null && code.matches("R\\d{2}[A-Z]");
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int skipped = 0;
        int faculty = 0;
        int regular = 0;
        for (LibraryMember member : members) {
            if (member == null) {
                skipped++;
            } else {
                processed++;
                if (member instanceof FacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }
        return processed + " processed | " + skipped + " null skipped | "
                + faculty + " faculty | " + regular + " regular";
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    static class LibraryMember {
        final String memberNumber;
        final int borrowLimit;

        LibraryMember(int borrowLimit) {
            if (borrowLimit <= 0) {
                throw new IllegalArgumentException();
            }
            this.borrowLimit = borrowLimit;
            this.memberNumber = String.format("LIB-%03d", ++nextMemberNumber);
            membersEnrolled++;
        }
    }

    static class FacultyMember extends LibraryMember {
        FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
        }
    }
}
