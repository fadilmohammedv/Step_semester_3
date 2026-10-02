package oop.assignment_problems.problem5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();
        board.registerStudent(new Student("Asha", "CSE", Set.of("Email", "App")));
        board.registerStudent(new Student("Ravi", "ECE", Set.of("SMS")));

        System.out.println(board.post(new Notice("Lab Closed Tomorrow", Set.of("CSE"))));
        System.out.println(board.post(new Notice("Fee Deadline Extended", Set.of("CSE", "ECE"))));
        System.out.println(board.post(new Notice("Sports Day", Set.of())));
    }
}

class Student {
    private final String name;
    private final String department;
    private final Set<String> preferredChannels;

    public Student(String name, String department, Set<String> preferredChannels) {
        this.name = name;
        this.department = department;
        this.preferredChannels = new HashSet<>(preferredChannels);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Set<String> getPreferredChannels() {
        return preferredChannels;
    }
}

class Notice {
    private final String title;
    private final Set<String> targetDepartments;

    public Notice(String title, Set<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = new HashSet<>(targetDepartments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTargetDepartments() {
        return targetDepartments;
    }
}

interface NotificationChannel {
    String getName();
    String send(Notice notice, Student student);
}

class EmailChannel implements NotificationChannel {
    public String getName() {
        return "Email";
    }

    public String send(Notice notice, Student student) {
        return "[Email -> " + student.getName() + "] " + notice.getTitle();
    }
}

class SMSChannel implements NotificationChannel {
    public String getName() {
        return "SMS";
    }

    public String send(Notice notice, Student student) {
        return "[SMS -> " + student.getName() + "] " + notice.getTitle();
    }
}

class AppChannel implements NotificationChannel {
    public String getName() {
        return "App";
    }

    public String send(Notice notice, Student student) {
        return "[App -> " + student.getName() + "] " + notice.getTitle();
    }
}

class NoticeBoard {
    private final List<Student> students = new ArrayList<>();
    private final List<NotificationChannel> channels = List.of(
            new EmailChannel(), new SMSChannel(), new AppChannel());

    public void registerStudent(Student student) {
        students.add(student);
    }

    public String post(Notice notice) {
        if (notice == null || notice.getTitle() == null || notice.getTitle().trim().isEmpty()) {
            return "Cannot post notice without a title.";
        }
        if (notice.getTargetDepartments().isEmpty()) {
            return "Cannot post notice: at least one target department is required.";
        }

        StringBuilder result = new StringBuilder("Notice '")
                .append(notice.getTitle()).append("' posted to ")
                .append(String.join(", ", notice.getTargetDepartments())).append(".");
        for (Student student : students) {
            if (!notice.getTargetDepartments().contains(student.getDepartment())) {
                continue;
            }
            for (NotificationChannel channel : channels) {
                if (student.getPreferredChannels().contains(channel.getName())) {
                    result.append(System.lineSeparator()).append(channel.send(notice, student));
                }
            }
        }
        return result.toString();
    }
}
