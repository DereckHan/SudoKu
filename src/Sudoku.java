/**
 * Created by Derek on 5/12/16.
 */

/**
 * Create Sudoku map
 */
public class Sudoku {

    static int[][] map = new int[9][9];
    static int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static int[][] createSudoku() {
        for (int i = 0; i < 9; i++) {
            int time = 0;
            for (int j = 0; j < 9; j++) {
                map[i][j] = generateNum(i, j, time);
                if (map[i][j] == 0) {
                    if (j > 0) {
                        j -= 2;
                    } else {
                        i--;
                        j = 8;
                    }
                } else {
                    if (checkNum(i, j)) {
                        time = 0;
                    } else {
                        time++;
                        j--;
                    }
                }
            }
        }
        return map;
    }

    public static boolean checkNum(int row, int col) {
        return (checkRow(row, col) & checkCol(row, col) & checkSquare(row, col));
    }


    public static boolean checkRow(int row, int col) {
        for (int i = 0; i < col; i++) {
            if (map[row][i] == map[row][col])
                return false;
        }
        return true;
    }

    public static boolean checkCol(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (map[i][col] == map[row][col])
                return false;
        }
        return true;

    }


    public static boolean checkSquare(int row, int col) {
        //get the index location for the left corner
        int rowIndex = row / 3 * 3;
        int colIndex = col / 3 * 3;
        int count = 0;
        for (int i = rowIndex; i < 3 + rowIndex; i++) {
            for (int j = colIndex; j < 3 + colIndex; j++) {
                if (map[i][j] == map[row][col])
                    count++;
            }
        }
        if (count == 1)
            return true;
        else
            return false;
    }

    public static int generateNum(int row, int col, int time) {
        //initialize array num
        if (time == 0) {
            for (int i = 0; i < 9; i++) {
                num[i] = i + 1;
            }
        }
        //kill this loop, roll back to previous number
        if (time == 9) {
            return 0;
        }
        //get random index
        int ranNum = (int) (Math.random() * (9 - time));
        //get number based on the index
        //exchange the position of number, so that only number that hasn't been used can be selected
        int temp = num[8 - time];
        num[8 - time] = num[ranNum];
        num[ranNum] = temp;
        return num[8 - time];
    }
}

