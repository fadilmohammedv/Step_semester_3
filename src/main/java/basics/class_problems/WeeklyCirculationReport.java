package oop.class_problems.problem4;

public class WeeklyCirculationReport {
    public static void main(String[] args) {
        System.out.println(batchPrint(new LibraryMember[] {
                new LibraryMember("LB5", 3), new StudentMember("STU6", 3, "ECE")
        }));
    }

    static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();
        for (LibraryMember member : members) {
            report.append(member.displayInfo());
            if (member instanceof StudentMember student) {
                report.append(" [Course via downcast: ").append(student.getCourse()).append("]");
            }
            report.append(" | ");
        }
        return report.toString();
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

    String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private final String course;

    StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    String getCourse() {
        return course;
    }

    @Override
    String displayInfo() {
        return "Student | Course: " + course + " | Books: " + booksBorrowed;
    }
}
