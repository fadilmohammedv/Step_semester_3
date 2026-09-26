package oop.assignment_problems.problem1;

public class MorningWakeUpCircuit {
    public static void main(String[] args) {
        AlarmClock alarmClock = new AlarmClock("7:00 AM");
        Doorbell doorbell = new Doorbell("Front Door");
        ringAll(new Ringable[] {alarmClock, doorbell});
    }

    public static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }
}

interface Ringable {
    String ring();
}

class AlarmClock implements Ringable {
    private final String time;

    public AlarmClock(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

class Doorbell implements Ringable {
    private final String location;

    public Doorbell(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}
