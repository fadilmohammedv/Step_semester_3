package basics.assigment_problems;

public class LibraryIsbnNormalizer {

    public String normalizeCode(String raw) {
        String trimmed = raw.trim();
        String first3 = trimmed.substring(0, 3).toUpperCase();
        return first3 + trimmed.substring(3);
    }

    public String validateAndFormat(String code) {
        if (code.length() != 13) {
            System.out.println("Invalid: wrong length");
            return "Invalid";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                System.out.println("Invalid: publisher code must be 3 letters");
                return "Invalid";
            }
        }
        for (int i = 3; i < code.length(); i++) {
            if (!Character.isDigit(code.charAt(i))) {
                System.out.println("Invalid: body must be digits");
                return "Invalid";
            }
        }
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ");
        sb.append(year.charAt(0)).append(year.charAt(1)).append(year.charAt(2)).append(year.charAt(3)).append(" | CATALOG: ");
        sb.append(catalog);
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        LibraryIsbnNormalizer normalizer = new LibraryIsbnNormalizer();
        String normalized = normalizer.normalizeCode(" pen2026004251 ");
        normalizer.validateAndFormat(normalized);
        String normalized2 = normalizer.normalizeCode("12N2026004251");
        normalizer.validateAndFormat(normalized2);
    }
}