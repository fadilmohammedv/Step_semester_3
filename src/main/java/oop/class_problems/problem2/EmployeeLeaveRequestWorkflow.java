package oop.class_problems.problem2;

import java.time.LocalDate;

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        LeaveWorkflow workflow = new LeaveWorkflow();
        LeaveRequest john = workflow.submit(new FullTimeEmployee("John"), LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        LeaveRequest jane = workflow.submit(new PartTimeEmployee("Jane"), LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));

        System.out.println(workflow.approve(john, "Alice"));
        System.out.println(workflow.reject(jane, "Bob"));
        System.out.println(workflow.approve(john, "Alice"));
    }
}

abstract class Employee {
    private final String name;

    protected Employee(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean permits(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean permits(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean permits(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean permits(int days) {
        return days <= 5;
    }
}

enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

class LeaveRequest {
    private final Employee employee;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException();
        }
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String approve() {
        if (status != LeaveStatus.PENDING || !employee.permits(numberOfDays())) {
            return "Cannot approve leave request for " + employee.getName() + ". Status: " + status;
        }
        status = LeaveStatus.APPROVED;
        return summary();
    }

    public String reject() {
        if (status != LeaveStatus.PENDING) {
            return "Cannot reject leave request for " + employee.getName() + ". Status: " + status;
        }
        status = LeaveStatus.REJECTED;
        return summary();
    }

    private int numberOfDays() {
        return (int) (endDate.toEpochDay() - startDate.toEpochDay()) + 1;
    }

    public String summary() {
        return "Leave request for " + employee.getName() + " (" + startDate + " - " + endDate + ") Status: " + status;
    }
}

class LeaveWorkflow {
    public LeaveRequest submit(Employee employee, LocalDate startDate, LocalDate endDate) {
        return new LeaveRequest(employee, startDate, endDate);
    }

    public String approve(LeaveRequest request, String reviewer) {
        return reviewer + ": " + request.approve();
    }

    public String reject(LeaveRequest request, String reviewer) {
        return reviewer + ": " + request.reject();
    }
}
