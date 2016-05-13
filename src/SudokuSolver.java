/**
 * Created by Dereck on 5/13/16.
 */
public class SudokuSolver {
    static int[][] map = {
            {6, 9, 5, 4, 8, 7, 1, 3, 2},
            {2, 7, 1, 6, 3, 9, 8, 5, 4},
            {8, 4, 3, 5, 2, 1, 6, 7, 9},
            {5, 6, 4, 9, 7, 8, 3, 2, 1},
            {1, 3, 7, 2, 6, 4, 9, 8, 5},
            {9, 2, 8, 1, 5, 3, 4, 6, 7},
            {7, 1, 2, 8, 9, 6, 5, 4, 3},
            {4, 5, 6, 3, 1, 2, 7, 9, 8},
            {3, 8, 9, 7, 4, 5, 2, 1, 6},
    };

    public static void main(String[] args) {

    }

    public static void printMap(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
