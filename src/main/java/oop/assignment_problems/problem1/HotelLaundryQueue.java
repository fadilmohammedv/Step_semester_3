package oop.assignment_problems.problem1;

import java.util.ArrayList;
import java.util.List;

public class HotelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine machine1 = new WashingMachine("M1");
        WashingMachine machine2 = new WashingMachine("M2");
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        System.out.println(machine1.startWash(asha, new QuickWash()));
        System.out.println(machine1.startWash(ravi, new HeavyWash()));
        System.out.println(machine2.startWash(ravi, new HeavyWash()));
        System.out.println(machine1.completeWash());
        System.out.println(machine1.startWash(neha, new NormalWash()));
    }
}

class Student {
    private final String name;

    public Student(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface WashType {
    int getDurationMinutes();
    double getCharge();
    String getName();
}

class QuickWash implements WashType {
    public int getDurationMinutes() {
        return 30;
    }

    public double getCharge() {
        return 20.0;
    }

    public String getName() {
        return "Quick";
    }
}

class NormalWash implements WashType {
    public int getDurationMinutes() {
        return 45;
    }

    public double getCharge() {
        return 30.0;
    }

    public String getName() {
        return "Normal";
    }
}

class HeavyWash implements WashType {
    public int getDurationMinutes() {
        return 60;
    }

    public double getCharge() {
        return 45.0;
    }

    public String getName() {
        return "Heavy";
    }
}

class WashingMachine {
    private final String machineId;
    private boolean busy;
    private Student currentStudent;
    private WashType currentWash;
    private final List<String> completedWashes = new ArrayList<>();

    public WashingMachine(String machineId) {
        if (machineId == null || machineId.trim().isEmpty()) {
            throw new IllegalArgumentException("Machine id cannot be empty");
        }
        this.machineId = machineId;
    }

    public String startWash(Student student, WashType washType) {
        if (student == null || washType == null) {
            throw new IllegalArgumentException("Student and wash type are required");
        }
        if (busy) {
            return "Machine " + machineId + " is currently busy.";
        }
        busy = true;
        currentStudent = student;
        currentWash = washType;
        return washType.getName() + " wash started on " + machineId + " for " + student.getName()
                + " (" + washType.getDurationMinutes() + " min). Charge: Rs."
                + String.format("%.2f", washType.getCharge());
    }

    public String completeWash() {
        if (!busy) {
            return "Machine " + machineId + " is already free.";
        }
        String result = "Machine " + machineId + " cycle completed. " + machineId + " is now free.";
        completedWashes.add(currentStudent.getName() + " - " + currentWash.getName());
        busy = false;
        currentStudent = null;
        currentWash = null;
        return result;
    }

    public boolean isBusy() {
        return busy;
    }
}
