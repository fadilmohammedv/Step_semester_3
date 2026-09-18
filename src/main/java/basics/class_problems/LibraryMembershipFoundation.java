package oop.class_problems.problem1;

public class LibraryMembershipFoundation {
    public static void main(String[] args) {
        System.out.println(LibraryMember.enrollBatch(new String[] {"STU1", "LB1", "STU2", " ", "STU3"}, 3));
    }
}

class LibraryMember {
    protected final String memberId;
    protected final int borrowLimit;
    protected int booksBorrowed;

    LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4 || borrowLimit <= 0) {
            throw new IllegalArgumentException();
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;
        for (String memberId : memberIds) {
            try {
                new LibraryMember(memberId, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException exception) {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

class StudentMember extends LibraryMember {
    private final String course;

    StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
}
