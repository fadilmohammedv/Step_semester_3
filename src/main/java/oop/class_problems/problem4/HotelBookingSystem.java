package oop.class_problems.problem4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Room standard = new StandardRoom("101");
        Room deluxe = new DeluxeRoom("201");
        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");

        System.out.println(hotel.checkAvailability(standard, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5)));
        System.out.println(hotel.reserve(customerA, standard, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5)));
        System.out.println(hotel.reserve(customerB, standard, LocalDate.of(2026, 1, 3), LocalDate.of(2026, 1, 7)));
        System.out.println(hotel.cancel(customerA, standard));
        System.out.println(hotel.reserve(customerB, deluxe, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 12)));
    }
}

class Customer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Room {
    private final String number;

    protected Room(String number) {
        this.number = number;
    }

    public abstract double calculatePrice(long nights);

    public String getNumber() {
        return number;
    }
}

class StandardRoom extends Room {
    public StandardRoom(String number) {
        super(number);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 100.0;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String number) {
        super(number);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 180.0;
    }
}

class Reservation {
    private final Customer customer;
    private final Room room;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Reservation(Customer customer, Room room, LocalDate startDate, LocalDate endDate) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean overlaps(LocalDate requestedStart, LocalDate requestedEnd) {
        return startDate.isBefore(requestedEnd) && requestedStart.isBefore(endDate);
    }

    public String summary() {
        long nights = endDate.toEpochDay() - startDate.toEpochDay();
        return "Reservation confirmed for " + customer.getName() + ", Room " + room.getNumber()
                + " (" + startDate + " - " + endDate + "). Price: $"
                + String.format("%.2f", room.calculatePrice(nights));
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }
}

class Hotel {
    private final List<Reservation> reservations = new ArrayList<>();

    public String checkAvailability(Room room, LocalDate startDate, LocalDate endDate) {
        return isAvailable(room, startDate, endDate)
                ? "Room " + room.getNumber() + " is available"
                : "Room " + room.getNumber() + " is not available";
    }

    public String reserve(Customer customer, Room room, LocalDate startDate, LocalDate endDate) {
        if (!isAvailable(room, startDate, endDate)) {
            return "Room " + room.getNumber() + " is not available";
        }
        Reservation reservation = new Reservation(customer, room, startDate, endDate);
        reservations.add(reservation);
        return reservation.summary();
    }

    public String cancel(Customer customer, Room room) {
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer() == customer && reservation.getRoom() == room) {
                reservations.remove(reservation);
                return "Reservation for Room " + room.getNumber() + " cancelled";
            }
        }
        return "Reservation not found";
    }

    private boolean isAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom() == room && reservation.overlaps(startDate, endDate)) {
                return false;
            }
        }
        return true;
    }
}
