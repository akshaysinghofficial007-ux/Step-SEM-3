import java.util.*;

// Question 5: Payment Processing for a Shopping System
public class Q5_PaymentProcessing {

    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
        String getName() { return name; }
    }

    static class Product {
        private final String name;
        private final double price;
        Product(String name, double price) { this.name = name; this.price = price; }
        double getPrice() { return price; }
    }

    // ---- Payment abstraction: each method processes payment its own way ----
    interface PaymentMethod {
        String name();
        boolean processPayment(double amount);
    }
    static class CreditCardPayment implements PaymentMethod {
        private final boolean approved;     // simulated gateway outcome
        CreditCardPayment(boolean approved) { this.approved = approved; }
        public String name() { return "Credit Card"; }
        public boolean processPayment(double amount) { return approved; }
    }
    static class PayPalPayment implements PaymentMethod {
        private final boolean approved;
        PayPalPayment(boolean approved) { this.approved = approved; }
        public String name() { return "PayPal"; }
        public boolean processPayment(double amount) { return approved; }
    }
    static class BankTransferPayment implements PaymentMethod {
        private final boolean approved;
        BankTransferPayment(boolean approved) { this.approved = approved; }
        public String name() { return "Bank Transfer"; }
        public boolean processPayment(double amount) { return approved; }
    }
    // A new payment method = a new PaymentMethod implementation; Order is unchanged.

    enum Status {
        PENDING("Pending"), PAID("Paid");
        final String label;
        Status(String label) { this.label = label; }
    }

    static class Order {
        private final Customer customer;
        private final Map<Product, Integer> items = new LinkedHashMap<>();
        private Status status = Status.PENDING;

        Order(Customer customer) { this.customer = customer; }
        Customer getCustomer() { return customer; }
        Status getStatus() { return status; }

        void addProduct(Product p, int qty) {
            if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive.");
            if (status == Status.PAID) throw new IllegalStateException("Order is already paid.");
            items.merge(p, qty, Integer::sum);
        }

        double total() {
            double t = 0;
            for (Map.Entry<Product, Integer> e : items.entrySet()) t += e.getKey().getPrice() * e.getValue();
            return t;
        }

        // Status becomes PAID only after a successful payment; failure leaves it untouched.
        boolean pay(PaymentMethod method) {
            if (items.isEmpty()) throw new IllegalStateException("Cannot process payment for an empty order.");
            if (status == Status.PAID) throw new IllegalStateException("Order is already paid.");
            System.out.println("Payment initiated via " + method.name() + " for Order " + shortName() + ".");
            boolean ok = method.processPayment(total());
            if (ok) status = Status.PAID;
            System.out.println("Payment for Order " + shortName() + (ok ? " successful." : " failed.")
                + " Order status: " + status.label + ".");
            return ok;
        }

        private String shortName() {
            String n = customer.getName();
            return n.substring(n.lastIndexOf(' ') + 1);      // "Customer X" -> "X"
        }
    }

    static Order createOrder(Customer c) {
        System.out.println("Order created for " + c.getName() + ".");
        return new Order(c);
    }

    public static void main(String[] args) {
        Product a = new Product("Product A", 10), b = new Product("Product B", 25), cProd = new Product("Product C", 40);

        Customer x = new Customer("Customer X"), y = new Customer("Customer Y"), z = new Customer("Customer Z");

        Order ox = createOrder(x);
        ox.addProduct(a, 2);
        ox.addProduct(b, 1);
        ox.pay(new CreditCardPayment(true));

        Order oy = createOrder(y);
        try {
            oy.pay(new CreditCardPayment(true));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        Order oz = createOrder(z);
        oz.addProduct(cProd, 1);
        oz.pay(new PayPalPayment(false));
    }
}
