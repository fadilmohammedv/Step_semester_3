public class Main {

    public static void warehouseSummary(int[][] grid){
        int totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for(int row = 0;row < grid.length; row++){
            for(int col = 0; col<grid[row].length ; col++){
                int current = grid[row][col];
                totalItems += current;

                if(current > maxItems){
                    maxItems = current;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        System.out.println("Total Items: " + totalItems);
        System.out.println("Max Coordinates: ("+ maxRow + ", "+maxCol + ")");
    }

    public static void main(String[] args) {
        int [][] grid = {
            {4,9,2},
            {7,1,6},
            {3,12,5}
        };

        warehouseSummary(grid);
        

    }
}