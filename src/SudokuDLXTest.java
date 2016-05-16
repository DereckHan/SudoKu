/**
 * Created by Derek on 5/14/16.
 */
public class SudokuDLXTest {
    private static final int[] ONE_TO_NINE = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static DancingLinks dlx = new DancingLinks();
    static int count = 0;

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
        SudokuCell[] solution = new SudokuCell[81];
        int[][] map = new int[9][9];
        map=Sudoku.createSudoku();
        int[][] board= map;
        while(count!=1){
            board=map;
            int size = 36;
            int x,y;
            for (int i = 0; i <36 ; i++) {
                x=(int)(Math.random()*9);
                y=(int)(Math.random()*9);
                if(board[x][y]!=0){
                    board[x][y]=0;
                }else{
                    i--;
                }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0)
                        appendSudokuCell(columnHeaders, i, j);
                    else
                        appendSudokuCell(columnHeaders, i, j, board[i][j]);

                }

            }
            count = dlx.search(h, 0, solution, count);
            if(count==1){
                System.out.println("Before Sudoku: ");
                printSudoku(board);
            }
        }
        for (SudokuCell cell : solution) {
            board[cell.x][cell.y] = cell.n;
        }
        System.out.println("After Sudoku: ");
        printSudoku(board);
    }

    public static void appendSudokuCell(ColumnHeader[] columnHeaders, int x, int y) {
        for (int n : ONE_TO_NINE) {
            appendSudokuCell(columnHeaders, x, y, n);
        }
    }

    public static void appendSudokuCell(ColumnHeader[] columnHeaders, int x, int y, int n) {
        DancingLinks.appendCell(columnHeaders, new int[]{x + y * 9, y * 9 + n - 1 + 81,
                x * 9 + n - 1 + 162, 9 * (x / 3) + 27 * (y / 3) + n - 1 + 243}, new SudokuCell(x, y, n));
    }
    public static void printSudoku(int[][] sudoku){
        for (int i = 0; i <sudoku.length ; i++) {
            for (int j = 0; j <sudoku[0].length ; j++) {
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println();
        }
    }

}
