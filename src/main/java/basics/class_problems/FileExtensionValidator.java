package basics.class_problems;

public class FileExtensionValidator {

    public String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) {
            System.out.println("Rejected \u2014 invalid file type");
            return "Rejected";
        }
        String extension = filename.substring(dotIndex + 1).toLowerCase();
        if (extension.equals("pdf") || extension.equals("docx") || extension.equals("zip")) {
            System.out.println("Accepted");
            return "Accepted";
        }
        System.out.println("Rejected \u2014 invalid file type");
        return "Rejected";
    }

    public static void main(String[] args) {
        FileExtensionValidator validator = new FileExtensionValidator();
        validator.validateFileExtension("Assignment1.PDF");
        validator.validateFileExtension("notes.txt");
    }
}