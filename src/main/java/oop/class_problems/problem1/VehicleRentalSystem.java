package oop.class_problems.problem1;

import java.util.ArrayList;
import java.util.List;

public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        Customer customer1 = new Customer("Customer 1");
        Customer customer3 = new Customer("Customer 3");
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        System.out.println(service.rent(sedan, customer1, 3));
        System.out.println(service.rent(sedan, new Customer("Customer 2"), 2));
        System.out.println(service.returnVehicle(sedan, customer1));
        System.out.println(service.rent(suv, customer3, 5));
    }
}

class Customer {
    private final String name;

    public Customer(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Vehicle {
    private final String name;
    private boolean available = true;

    protected Vehicle(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public abstract double calculateCharge(int days);

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Sedan extends Vehicle {
    public Sedan(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 40.0;
    }
}

class SUV extends Vehicle {
    public SUV(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 60.0;
    }
}

class Truck extends Vehicle {
    public Truck(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 85.0;
    }
}

class Rental {
    private final Vehicle vehicle;
    private final Customer customer;
    private final int days;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException();
        }
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public String summary() {
        return vehicle.getName() + " rented successfully by " + customer.getName()
                + ". Rental charge: $" + String.format("%.2f", vehicle.calculateCharge(days));
    }
}

class RentalService {
    private final List<Rental> rentals = new ArrayList<>();

    public String rent(Vehicle vehicle, Customer customer, int days) {
        if (!vehicle.isAvailable()) {
            return vehicle.getName() + " is currently unavailable";
        }
        Rental rental = new Rental(vehicle, customer, days);
        vehicle.setAvailable(false);
        rentals.add(rental);
        return rental.summary();
    }

    public String returnVehicle(Vehicle vehicle, Customer customer) {
        vehicle.setAvailable(true);
        return vehicle.getName() + " returned by " + customer.getName();
    }
}
