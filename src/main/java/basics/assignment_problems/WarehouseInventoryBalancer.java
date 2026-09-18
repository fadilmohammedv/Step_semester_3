public class WarehouseInventoryBalancer {
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA == null || sectionB == null || sectionA.length != sectionB.length
                || sectionA.length == 0) {
            System.out.println("Sections must contain equal non-zero lengths");
            return;
        }

        int totalA = 0;
        int totalB = 0;
        int highestQuantity = sectionA[0];
        String highestSection = "Section A";
        int highestItem = 1;

        for (int index = 0; index < sectionA.length; index++) {
            totalA += sectionA[index];
            totalB += sectionB[index];
            if (sectionA[index] > highestQuantity) {
                highestQuantity = sectionA[index];
                highestSection = "Section A";
                highestItem = index + 1;
            }
            if (sectionB[index] > highestQuantity) {
                highestQuantity = sectionB[index];
                highestSection = "Section B";
                highestItem = index + 1;
            }
        }

        String status = totalA == totalB ? "Balanced" : "Not Balanced";
        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB
                + " | Status: " + status + " | Highest Quantity: " + highestQuantity
                + " (" + highestSection + ", Item " + highestItem + ")");
    }

    public static void main(String[] args) {
        analyzeInventory(new int[] {20, 15, 30}, new int[] {25, 10, 30});
    }
}