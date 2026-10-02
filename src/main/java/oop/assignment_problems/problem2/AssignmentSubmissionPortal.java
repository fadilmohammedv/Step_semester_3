package oop.assignment_problems.problem2;

import java.time.LocalDate;

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Assignment coding = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment written = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission ashaSubmission = new Submission(asha, coding, LocalDate.of(2026, 3, 10));
        Submission raviSubmission = new Submission(ravi, written, LocalDate.of(2026, 3, 14));

        System.out.println(ashaSubmission.submit());
        System.out.println(raviSubmission.submit());
        System.out.println(ashaSubmission.grade(45));
        System.out.println(raviSubmission.grade(40));

        try {
            System.out.println(ashaSubmission.submit());
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Assignment {
    private final String title;
    private final int maximumMarks;
    private final LocalDate dueDate;

    protected Assignment(String title, int maximumMarks, LocalDate dueDate) {
        this.title = title;
        this.maximumMarks = maximumMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaximumMarks() {
        return maximumMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public abstract double calculatePenalty(int daysLate);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maximumMarks, LocalDate dueDate) {
        super(title, maximumMarks, dueDate);
    }

    public double calculatePenalty(int daysLate) {
        return getMaximumMarks() * daysLate * 0.10;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maximumMarks, LocalDate dueDate) {
        super(title, maximumMarks, dueDate);
    }

    public double calculatePenalty(int daysLate) {
        return getMaximumMarks() * daysLate * 0.20;
    }
}

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

class Submission {
    private final Student student;
    private final Assignment assignment;
    private final LocalDate submittedDate;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, LocalDate submittedDate) {
        this.student = student;
        this.assignment = assignment;
        this.submittedDate = submittedDate;
    }

    public String submit() {
        if (status == SubmissionStatus.GRADED) {
            throw new IllegalStateException("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
        }
        status = SubmissionStatus.SUBMITTED;
        int daysLate = Math.max(0, submittedDate.compareTo(assignment.getDueDate()));
        return student.getName() + "'s submission for '" + assignment.getTitle() + "' received ("
                + (daysLate == 0 ? "on time" : daysLate + " days late") + "). Status: Submitted.";
    }

    public String grade(int awardedMarks) {
        if (status != SubmissionStatus.SUBMITTED) {
            return "Cannot grade a submission that has not been submitted.";
        }
        int daysLate = Math.max(0, submittedDate.compareTo(assignment.getDueDate()));
        finalMarks = Math.max(0, awardedMarks - assignment.calculatePenalty(daysLate));
        status = SubmissionStatus.GRADED;
        return student.getName() + " graded: " + String.format("%.0f", finalMarks) + "/"
                + assignment.getMaximumMarks() + ". Status: Graded.";
    }
}
