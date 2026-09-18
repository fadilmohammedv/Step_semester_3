public class TransactionReferenceValidator {
    public static String normalizeReference(String raw) {
        if (raw == null) {
            return null;
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        if (reference == null || reference.length() != 14) {
            return "Invalid: wrong length";
        }

        for (int index = 0; index < 3; index++) {
            if (!Character.isLetter(reference.charAt(index))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int index = 3; index < reference.length(); index++) {
            if (!Character.isDigit(reference.charAt(index))) {
                return "Invalid: body must contain only digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9);
        StringBuilder formatted = new StringBuilder();
        formatted.append('[').append(bankCode).append("] DATE: ")
                .append(date, 0, 2).append('/')
                .append(date, 2, 4).append('/')
                .append(date, 4, 6)
                .append(" | SEQ: ").append(sequence);
        return formatted.toString();
    }

    public static void main(String[] args) {
        String normalized = normalizeReference(" hdf03022600042 ");
        System.out.println(validateAndFormat(normalized));
        System.out.println(validateAndFormat(normalizeReference("12F03022600042")));
    }
}