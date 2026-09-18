package basics.assigment_problems;

public class AtmPinLengthValidator {

    public void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN \u2014 must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        AtmPinLengthValidator validator = new AtmPinLengthValidator();
        validator.checkPinLength("482");
        validator.checkPinLength("4820");
    }
}