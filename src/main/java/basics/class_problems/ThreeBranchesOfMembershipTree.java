package oop.class_problems.problem2;

public class ThreeBranchesOfMembershipTree {
    public static void main(String[] args) {
        StudentMember student = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember honors = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty = new FacultyMember("STU4", 5, "Physics");
        student.borrowBook();
        honors.borrowBook();
        faculty.borrowBook();
        System.out.println(LibraryMember.getTotalBooksBorrowed(new LibraryMember[] {student, honors, faculty}));
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

    String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "General member";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
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

    String getCourse() {
        return course;
    }

    @Override
    String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + booksBorrowed;
    }
}

class HonorsStudentMember extends StudentMember {
    private final int bonusLimit;

    HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    String displayInfo() {
        return "Honors Student Member | Course: " + getCourse() + " | Bonus Limit: "
                + bonusLimit + " | Books Borrowed: " + booksBorrowed;
    }
}

class FacultyMember extends LibraryMember {
    private final String department;

    FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed;
    }
}
