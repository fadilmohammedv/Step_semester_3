package oop.assignment_problems.problem3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show("7 PM Show");
        show.addSeat(new Seat("A1", SeatCategory.REGULAR));
        show.addSeat(new Seat("A2", SeatCategory.REGULAR));
        show.addSeat(new Seat("F5", SeatCategory.PREMIUM));
        show.addSeat(new Seat("R1", SeatCategory.RECLINER));

        Booking ashaBooking = show.book(new Customer("Asha"), List.of("A1", "A2", "F5"));
        System.out.println(ashaBooking);
        System.out.println(show.book(new Customer("Ravi"), List.of("A2")));
        ashaBooking.cancel();
        System.out.println("Asha's booking cancelled. Seats A1, A2, F5 released.");
        System.out.println(show.book(new Customer("Neha"), List.of("A2")));
    }
}

enum SeatCategory {
    REGULAR(150),
    PREMIUM(250),
    RECLINER(400);

    private final double price;

    SeatCategory(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
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

class Seat {
    private final String number;
    private final SeatCategory category;
    private boolean booked;

    public Seat(String number, SeatCategory category) {
        this.number = number;
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public boolean isBooked() {
        return booked;
    }

    void setBooked(boolean booked) {
        this.booked = booked;
    }
}

class Show {
    private final String name;
    private final Map<String, Seat> seats = new HashMap<>();
    private final List<Booking> bookings = new ArrayList<>();
    private boolean started;

    public Show(String name) {
        this.name = name;
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getNumber(), seat);
    }

    public Booking book(Customer customer, List<String> requestedSeats) {
        if (started) {
            return Booking.failed("Booking denied: the show has already started.");
        }
        if (requestedSeats.isEmpty() || requestedSeats.size() > 6) {
            return Booking.failed("A booking must contain between 1 and 6 seats.");
        }

        List<Seat> selectedSeats = new ArrayList<>();
        for (String seatNumber : requestedSeats) {
            Seat seat = seats.get(seatNumber);
            if (seat == null) {
                return Booking.failed("Seat " + seatNumber + " does not exist.");
            }
            if (seat.isBooked()) {
                return Booking.failed("Seat " + seatNumber + " is already booked for this show.");
            }
            selectedSeats.add(seat);
        }

        double total = 0;
        for (Seat seat : selectedSeats) {
            seat.setBooked(true);
            total += seat.getCategory().getPrice();
        }
        Booking booking = new Booking(customer, this, selectedSeats, total);
        bookings.add(booking);
        return booking;
    }

    public void release(List<Seat> seatsToRelease) {
        for (Seat seat : seatsToRelease) {
            seat.setBooked(false);
        }
    }
}

class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;
    private final double total;
    private final String failureMessage;
    private boolean cancelled;

    public Booking(Customer customer, Show show, List<Seat> seats, double total) {
        this(customer, show, seats, total, null);
    }

    private Booking(Customer customer, Show show, List<Seat> seats, double total, String failureMessage) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.total = total;
        this.failureMessage = failureMessage;
    }

    public static Booking failed(String message) {
        return new Booking(null, null, List.of(), 0, message);
    }

    public void cancel() {
        if (!cancelled && failureMessage == null) {
            show.release(seats);
            cancelled = true;
        }
    }

    @Override
    public String toString() {
        if (failureMessage != null) {
            return failureMessage;
        }
        if (cancelled) {
            return "Booking cancelled for " + customer.getName();
        }
        List<String> numbers = new ArrayList<>();
        for (Seat seat : seats) {
            numbers.add(seat.getNumber());
        }
        return "Booking confirmed for " + customer.getName() + ": " + String.join(", ", numbers)
                + ". Total: Rs." + String.format("%.2f", total) + ".";
    }
}
