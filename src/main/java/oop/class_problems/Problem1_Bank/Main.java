import java.util.*;

abstract class Account {
    protected final String id;
    protected double balance;

    Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract boolean canWithdraw(double amount);

    abstract String rejectReason();

    String withdraw(double amount) {
        if (!canWithdraw(amount)) {
            return id + " rejected: " + rejectReason();
        }
        balance -= amount;
        return id + " balance " + fmt(balance);
    }

    static String fmt(double v) {
        return v == (long) v ? String.valueOf((long) v) : String.format(Locale.US, "%.2f", v);
    }
}

class Savings extends Account {
    static final double MIN_BALANCE = 1000;

    Savings(String id, double balance) {
        super(id, balance);
    }

    boolean canWithdraw(double amount) {
        return balance - amount >= MIN_BALANCE;
    }

    String rejectReason() {
        return "minimum balance 1000";
    }
}

class Current extends Account {
    static final double OVERDRAFT_LIMIT = 5000;

    Current(String id, double balance) {
        super(id, balance);
    }

    boolean canWithdraw(double amount) {
        return balance - amount >= -OVERDRAFT_LIMIT;
    }

    String rejectReason() {
        return "overdraft limit 5000";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] t = line.split("\\s+");
            String op = t[0].toUpperCase();

            if (op.equals("SAVINGS")) {
                accounts.put(t[1], new Savings(t[1], Double.parseDouble(t[2])));
            } else if (op.equals("CURRENT")) {
                accounts.put(t[1], new Current(t[1], Double.parseDouble(t[2])));
            } else if (op.equals("WITHDRAW")) {
                Account a = accounts.get(t[1]);
                if (a == null) {
                    System.out.println("Account not found");
                } else {
                    System.out.println(a.withdraw(Double.parseDouble(t[2])));
                }
            }
        }
    }
}
