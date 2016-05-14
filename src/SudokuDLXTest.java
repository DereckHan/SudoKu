/**
 * Created by Derek on 5/14/16.
 */
public class SudokuDLXTest {
    private static final int[] ONE_TO_NINE = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static DancingLinks dlx = new DancingLinks();
    static int count =0;

    static class SudokuCell {
        int x;
        int y;
        int n;


        SudokuCell(int x, int y, int n) {
            super();
            this.x = x;
            this.y = y;
            this.n = n;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d) -> %d", x, y, n);
        }
    }

    public static void main(String[] args) {
        Head h = new Head();
        ColumnHeader[] columnHeaders = DancingLinks.buildColumnHeaders(h, 324);
        int[][] map = {
                {6, 9, 1, 0, 0, 7, 0, 3, 0},
                {0, 7, 0, 6, 0, 9, 0, 0, 0},
                {0, 0, 3, 0, 2, 0, 6, 0, 0},
                {5, 6, 4, 0, 0, 8, 3, 0, 1},
                {1, 0, 0, 2, 0, 0, 0, 0, 0},
                {9, 2, 8, 1, 5, 0, 4, 6, 7},
                {0, 0, 2, 8, 9, 0, 0, 4, 3},
                {4, 0, 6, 3, 1, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 0, 0, 0},
        };
        for (int i = 0; i <map.length ; i++) {
            for (int j = 0; j <map[0].length ; j++) {
                if(map[i][j]==0)
                    appendSudokuCell(columnHeaders, i, j);
                else
                    appendSudokuCell(columnHeaders, i, j, map[i][j]);

            }

        }
        SudokuCell[] solution = new SudokuCell[81];
        count=dlx.search(h, 0, solution,count);
        System.out.println(count);
        int[][] board = new int[9][9];
        for (SudokuCell cell : solution) {
            board[cell.x][cell.y] = cell.n;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    private static void appendSudokuCell(ColumnHeader[] columnHeaders, int x, int y) {
        for (int n : ONE_TO_NINE) {
            appendSudokuCell(columnHeaders, x, y, n);
        }
    }

    private static void appendSudokuCell(ColumnHeader[] columnHeaders, int x, int y, int n) {
        DancingLinks.appendCell(columnHeaders, new int[]{x + y * 9, y * 9 + n - 1 + 81,
                x * 9 + n - 1 + 162, 9 * (x / 3) + 27 * (y / 3) + n - 1 + 243}, new SudokuCell(x, y, n));
    }

}
