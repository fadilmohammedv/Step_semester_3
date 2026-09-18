public class ExamSeatDuplicateChecker {
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;

        for (int index = 0; index < seatNumbers.length; index++) {
            for (int previous = 0; previous < index; previous++) {
                if (seatNumbers[index] == seatNumbers[previous]) {
                    boolean alreadyReported = false;
                    for (int earlier = 0; earlier < previous; earlier++) {
                        if (seatNumbers[earlier] == seatNumbers[index]) {
                            alreadyReported = true;
                            break;
                        }
                    }
                    if (!alreadyReported) {
                        System.out.println("Duplicate Seat Number Found: " + seatNumbers[index]);
                        foundDuplicate = true;
                    }
                    break;
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        checkDuplicateSeats(new int[] {101, 102, 103, 102, 105});
        checkDuplicateSeats(new int[] {101, 102, 103, 104, 105});
    }
}