import java.util.*;

interface Chargeable {
    // marker for vehicles that can book a charging bay
}

abstract class Vehicle {
    final String passNumber;
    final String owner;
    final String type;

    Vehicle(String type, String passNumber, String owner) {
        this.type = type;
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int passFee();
}

class Bike extends Vehicle {
    Bike(String type, String pass, String owner) { super(type, pass, owner); }
    int passFee() { return 300; }
}

class Car extends Vehicle {
    Car(String type, String pass, String owner) { super(type, pass, owner); }
    int passFee() { return 1000; }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String type, String pass, String owner) { super(type, pass, owner); }
    int passFee() { return 300; }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String type, String pass, String owner) { super(type, pass, owner); }
    int passFee() { return 1000; }
}

public class Main {
    static Vehicle create(String type, String pass, String owner) {
        switch (type.toLowerCase().replace("-", "")) {
            case "bike":  return new Bike(type, pass, owner);
            case "car":   return new Car(type, pass, owner);
            case "ebike": return new EBike(type, pass, owner);
            case "ecar":  return new ECar(type, pass, owner);
            default:      return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] t = line.split("\\s+");
            String op = t[0].toUpperCase();

            if (op.equals("PASS")) {
                Vehicle v = create(t[1], t[2], t[3]);
                if (v == null) {
                    System.out.println("Unknown vehicle type " + t[1]);
                    continue;
                }
                vehicles.put(v.passNumber, v);
                System.out.println(v.passNumber + " (" + v.type + ") pass fee " + v.passFee());
            } else if (op.equals("CHARGE")) {
                Vehicle v = vehicles.get(t[1]);
                if (v == null) {
                    System.out.println("Vehicle not found");
                } else if (v instanceof Chargeable) {
                    System.out.println(v.passNumber + " charging bay allotted");
                } else {
                    System.out.println(v.passNumber + " rejected: charging unsupported");
                }
            }
        }
    }
}
