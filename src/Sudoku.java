/**
 * Created by Derek on 5/12/16.
 */

/**
 * Create Sudoku map
 */
public class Sudoku {

    static int[][] map = new int[9][9];
    static int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static boolean checkNum(int row, int col) {
        return (checkRow(row) & checkCol(col) & checkSquare(row, col));
    }


    public static boolean checkRow(int row) {
        for (int j = 0; j < 8; j++) {
            if (map[row][j] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (map[row][j] == map[row][k]) {
                    return false;
                }
            }
        }
        return true;

    }

    public static boolean checkCol(int col) {
        for(int j = 0;j < 8;j++){
            if(map[j][col] == 0){
                continue;
            }
            for(int k =j + 1;k< 9;k++){
                if(map[j][col] == map[k][col]){
                    return false;
                }
            }
        }
        return true;

    }


    public static boolean checkSquare(int row, int col) {
        //get the index location for the left corner
        int j = row / 3 * 3;
        int k = col /3 * 3;
        for(int i = 0;i < 8;i++){
            if(map[j + i/3][k + i % 3] == 0){
                continue;
            }
            for(int m = i+ 1;m < 9;m++){
                if(map[j + i/3][k + i % 3] == map[j + m/3][k + m % 3]){
                    return false;
                }
            }
        }
        return true;


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

