public class Grid {

    public static final int SIZE = 50; //number of cells per side

    private Cell[][] grid;

    public boolean running = true;

    public Grid() {
        grid = new Cell[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell();
            }
        }

        //add specific configuration or 'seed'
        grid[20][20].setIsAlive(true);
        grid[20][21].setIsAlive(true);
        grid[20][22].setIsAlive(true);
    }

    public Cell getGridCell(int row, int col) {
        return grid[row][col];
    }

    private int checkNumberLiveNeighbors(int row, int col) {
        //returns number of living neighbors at point
        int numberLiveNeighbors = 0;

        //up
        if (row - 1 >= 0) {
            //ensure in bounds
            if (grid[row-1][col].getIsAlive()) numberLiveNeighbors++;
        }
        //up-right
        if (row - 1 >= 0 && col + 1 <= SIZE - 1) {
            if (grid[row-1][col+1].getIsAlive()) numberLiveNeighbors++;
        }
        //right
        if (col + 1 <= SIZE - 1) {
            if (grid[row][col+1].getIsAlive()) numberLiveNeighbors++;
        }
        //right-down
        if (row + 1 <= SIZE - 1 && col + 1 <= SIZE - 1) {
            if (grid[row+1][col+1].getIsAlive()) numberLiveNeighbors++;
        }
        //down
        if (row + 1 <= SIZE - 1) {
            if (grid[row+1][col].getIsAlive()) numberLiveNeighbors++;
        }
        //down-left
        if (row + 1 <= SIZE - 1 && col - 1 >= 0) {
            if (grid[row+1][col-1].getIsAlive()) numberLiveNeighbors++;
        }
        //left
        if (col - 1 >= 0) {
            if (grid[row][col-1].getIsAlive()) numberLiveNeighbors++;
        }
        //left-up
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (grid[row-1][col-1].getIsAlive()) numberLiveNeighbors++;
        }

        return numberLiveNeighbors;
    }

    public void advanceGeneration() {
        //enacts a step
        int numberLiveNeighbors;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                numberLiveNeighbors = checkNumberLiveNeighbors(i, j);

                if (numberLiveNeighbors < 2) grid[i][j].setMarkToDie(true);
                if (numberLiveNeighbors == 2 || numberLiveNeighbors == 3) grid[i][j].setMarkToDie(false);
                if (numberLiveNeighbors > 3) grid[i][j].setMarkToDie(true);
                if (numberLiveNeighbors == 3) grid[i][j].setMarkToLive(true);
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].getMarkToDie() && grid[i][j].getIsAlive()) grid[i][j].setIsAlive(false);
                if (grid[i][j].getMarkToLive() && !grid[i][j].getIsAlive()) grid[i][j].setIsAlive(true);
                grid[i][j].setMarkToLive(false);
                grid[i][j].setMarkToDie(false);
            }
        }
    }
}
