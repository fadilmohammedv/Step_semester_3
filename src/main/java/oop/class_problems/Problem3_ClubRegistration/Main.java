import java.util.*;

class Student {
    final String roll;
    final String name;

    Student(String roll, String name) {
        this.roll = roll;
        this.name = name;
    }

    // uniqueness is by roll number only
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        return roll.equals(((Student) o).roll);
    }

    @Override
    public int hashCode() {
        return roll.hashCode();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new HashSet<>();
        List<String> addOut = new ArrayList<>();
        List<String> containsOut = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] t = line.split("\\s+", 3);
            String name = t.length > 2 ? t[2] : "";
            Student s = new Student(t[1], name);

            if (t[0].equalsIgnoreCase("ADD")) {
                addOut.add(members.add(s) ? "Added" : "duplicate rejected");
            } else if (t[0].equalsIgnoreCase("CONTAINS")) {
                containsOut.add("contains: " + members.contains(s));
            }
        }

        addOut.forEach(System.out::println);
        System.out.println("member count " + members.size());
        containsOut.forEach(System.out::println);
    }
}
