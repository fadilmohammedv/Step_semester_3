package basics.class_problems;

public class BankTransactionReferenceGenerator {

    public String normalizeReference(String raw) {
        String trimmed = raw.trim();
        String first3 = trimmed.substring(0, 3).toUpperCase();
        return first3 + trimmed.substring(3);
    }

    public String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            System.out.println("Invalid: wrong length");
            return "Invalid";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                System.out.println("Invalid: bank code must be 3 letters");
                return "Invalid";
            }
        }
        for (int i = 3; i < reference.length(); i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                System.out.println("Invalid: body must be digits");
                return "Invalid";
            }
        }
        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String seq = reference.substring(9);
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ");
        sb.append(date.charAt(0)).append(date.charAt(1)).append("/");
        sb.append(date.charAt(2)).append(date.charAt(3)).append("/");
        sb.append(date.charAt(4)).append(date.charAt(5)).append(" | SEQ: ");
        sb.append(seq);
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        BankTransactionReferenceGenerator gen = new BankTransactionReferenceGenerator();
        String normalized = gen.normalizeReference(" hdf03022600042 ");
        gen.validateAndFormat(normalized);
        String normalized2 = gen.normalizeReference("12F03022600042");
        gen.validateAndFormat(normalized2);
    }
}