package basics.class_problems;

public class MaskedPhoneNumberFormatter {

    public String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            System.out.println("Invalid phone number");
            return "Invalid";
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                System.out.println("Invalid phone number");
                return "Invalid";
            }
        }
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            masked.append('X');
        }
        masked.append('-');
        masked.append(phone.substring(6));
        System.out.println(masked.toString());
        return masked.toString();
    }

    public static void main(String[] args) {
        MaskedPhoneNumberFormatter formatter = new MaskedPhoneNumberFormatter();
        formatter.maskPhoneNumber("9876543210");
        formatter.maskPhoneNumber("98765");
    }
}