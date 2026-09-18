public class SubclassReachChecker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("protected".equals(fieldModifier)
                && "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
            return "ALLOWED";
        }
        if ("public".equals(fieldModifier)) {
            return "ALLOWED";
        }
        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts) {
        for (int index = 0; index < attempts.length; index++) {
            String[] attempt = attempts[index];
            if (attempt == null || attempt.length < 2) {
                return "Invalid attempt (#" + (index + 1) + ")";
            }
            if ("DENIED".equals(classifyAccess(attempt[0], attempt[1]))) {
                return attempt[0] + " via " + attempt[1] + " (attempt #" + (index + 1) + ")";
            }
        }
        return "None Denied";
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(attempts));
    }
}