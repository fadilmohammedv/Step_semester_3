package oop.class_problems.problem1;

public class TheTalkingToyBox {
    public static void main(String[] args) {
        ToyCar car = new ToyCar("Speedster");
        ToyRobot robot = new ToyRobot("Bolt");
        System.out.println(car.makeSound());
        System.out.println(robot.makeSound());
        System.out.println(car.getToyId());
        System.out.println(robot.getToyId());
    }
}

abstract class Toy {
    private static int nextToyNumber = 1000;
    private final String toyId;
    protected final String name;

    public Toy(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.toyId = "TOY-" + (++nextToyNumber);
    }

    public abstract String makeSound();

    String getToyId() {
        return toyId;
    }
}

class ToyCar extends Toy {
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}
