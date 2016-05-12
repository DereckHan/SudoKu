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
                map[i][j] = generateNum(time);
                if (map[i][j] == 0) {
                    if (j > 0) {
                        j -= 2;
                        continue;
                    } else {
                        i--;
                        j = 8;
                        continue;
                    }
                }
                if (checkNum(i, j)) {
                    time = 0;
                } else {
                    time++;
                    j--;
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

    /**
     * 检查列是否符合要求
     *
     * @param col 检查的列号
     * @return true代表符合要求
     */
    public static boolean checkCol(int col) {
        for (int j = 0; j < 8; j++) {
            if (map[j][col] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (map[j][col] == map[k][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查3X3区域是否符合要求
     *
     * @param row 检查的行号
     * @param col 检查的列号
     * @return true代表符合要求
     */
    public static boolean checkSquare(int row, int col) {
        //获得左上角的坐标
        int j = row / 3 * 3;
        int k = col / 3 * 3;
        //循环比较
        for (int i = 0; i < 8; i++) {
            if (map[j + i / 3][k + i % 3] == 0) {
                continue;
            }
            for (int m = i + 1; m < 9; m++) {
                if (map[j + i / 3][k + i % 3] == map[j + m / 3][k + m % 3]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 产生1-9之间的随机数字
     * 规则：生成的随机数字放置在数组8-time下标的位置，随着time的增加，已经尝试过的数字将不会在取到
     * 说明：即第一次次是从所有数字中随机，第二次时从前八个数字中随机，依次类推，
     * 这样既保证随机，也不会再重复取已经不符合要求的数字，提高程序的效率
     * 这个规则是本算法的核心
     *
     * @param time 填充的次数，0代表第一次填充
     * @return
     */
    public static int generateNum(int time) {
        //第一次尝试时，初始化随机数字源数组
        if (time == 0) {
            for (int i = 0; i < 9; i++) {
                num[i] = i + 1;
            }
        }
        //第10次填充，表明该位置已经卡住，则返回0，由主程序处理退回
        if (time == 9) {
            return 0;
        }
        //不是第一次填充
        //生成随机数字，该数字是数组的下标，取数组num中该下标对应的数字为随机数字
        int ranNum = (int) (Math.random() * (9 - time));
        //把数字放置在数组倒数第time个位置，
        int temp = num[8 - time];
        num[8 - time] = num[ranNum];
        num[ranNum] = temp;
        //返回数字
        return num[8 - time];
    }
}

