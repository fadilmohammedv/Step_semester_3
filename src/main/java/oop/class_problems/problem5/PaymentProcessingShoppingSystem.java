package oop.class_problems.problem5;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessingShoppingSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("Customer X");
        Order order = customer.createOrder();
        order.addProduct(new Product("Product A", 20.0), 2);
        order.addProduct(new Product("Product B", 15.0), 1);
        System.out.println(order.pay(new CreditCardPayment()));
        System.out.println(order.pay(new PayPalPayment()));

        Customer emptyCustomer = new Customer("Customer Y");
        System.out.println(emptyCustomer.createOrder().pay(new PayPalPayment()));
    }
}

class Customer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public Order createOrder() {
        return new Order(this);
    }

    public String getName() {
        return name;
    }
}

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }
        this.product = product;
        this.quantity = quantity;
    }

    public double total() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);

    String getName();
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return amount > 0;
    }

    @Override
    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return amount > 0;
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return amount > 0;
    }

    @Override
    public String getName() {
        return "Bank Transfer";
    }
}

enum OrderStatus {
    PENDING, PAID
}

class Order {
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Paid orders cannot be changed");
        }
        items.add(new OrderItem(product, quantity));
    }

    public String pay(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            return "Cannot process payment for an empty order";
        }
        if (status == OrderStatus.PAID) {
            return "Order is already paid";
        }
        double amount = total();
        if (paymentMethod.processPayment(amount)) {
            status = OrderStatus.PAID;
            return "Payment initiated via " + paymentMethod.getName() + " for Order of "
                    + customer.getName() + ". Order status: Paid";
        }
        return "Payment via " + paymentMethod.getName() + " failed. Order status: " + status;
    }

    private double total() {
        return items.stream().mapToDouble(OrderItem::total).sum();
    }
}
