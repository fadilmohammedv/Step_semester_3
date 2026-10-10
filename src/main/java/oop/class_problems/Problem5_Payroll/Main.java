import java.util.*;

abstract class Staff {
    final String name;
    final String type;

    Staff(String name, String type) {
        this.name = name;
        this.type = type;
    }

    abstract double pay();

    static String fmt(double v) {
        return v == (long) v ? String.valueOf((long) v) : String.format(Locale.US, "%.2f", v);
    }

    @Override
    public String toString() {
        return "Payslip[name=" + name + ", type=" + type + ", pay=" + fmt(pay()) + "]";
    }
}

class FullTime extends Staff {
    final double salary;

    FullTime(String name, double salary) {
        super(name, "FullTime");
        this.salary = salary;
    }

    double pay() { return salary; }
}

class PartTime extends Staff {
    final double hours, rate;

    PartTime(String name, double hours, double rate) {
        super(name, "PartTime");
        this.hours = hours;
        this.rate = rate;
    }

    double pay() { return hours * rate; }
}

class Intern extends Staff {
    final double stipend;

    Intern(String name, double stipend) {
        super(name, "Intern");
        this.stipend = stipend;
    }

    double pay() { return stipend; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Staff> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] t = line.split("\\s+");
            switch (t[0].toLowerCase()) {
                case "fulltime": list.add(new FullTime(t[1], Double.parseDouble(t[2]))); break;
                case "parttime": list.add(new PartTime(t[1], Double.parseDouble(t[2]), Double.parseDouble(t[3]))); break;
                case "intern":   list.add(new Intern(t[1], Double.parseDouble(t[2]))); break;
            }
        }

        Staff[] staff = list.toArray(new Staff[0]);
        double total = 0;
        Staff top = null;
        for (Staff s : staff) {
            System.out.println(s);
            total += s.pay();
            if (top == null || s.pay() > top.pay()) top = s;
        }

        System.out.println("Total " + Staff.fmt(total));
        System.out.println("top earner " + (top == null ? "none" : top.name));
    }
}
