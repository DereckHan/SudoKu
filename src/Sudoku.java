import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Derek on 5/11/16.
 */
public class Sudoku {
    static ArrayList<LinkedList<Integer>> squareArray = new ArrayList<LinkedList<Integer>>();
    static ArrayList<LinkedList<Integer>> rowArray = new ArrayList<LinkedList<Integer>>();
    static ArrayList<LinkedList<Integer>> colArray = new ArrayList<LinkedList<Integer>>();

    public static void main(String[] args) {
        int[][] map = new int[9][9];
        int squareNum, number;
        fillLinkedList(squareArray, 9);
        fillLinkedList(rowArray, 9);
        fillLinkedList(colArray, 9);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i / 3 == 0) {
                    int[] squareNumArray = {0, 1, 2};
                    squareNum = squareNumArray[j / 3];
                } else {
                    if (i / 3 == 1) {
                        int[] squareNumArray = {3, 4, 5};
                        squareNum = squareNumArray[j / 3];
                    } else {
                        int[] squareNumArray = {6, 7, 8};
                        squareNum = squareNumArray[j / 3];
                    }
                }
                number = generateNumber(i, j, squareNum);
                if (number != 0) {
                    map[i][j] = number;
                    System.out.println("-------i= " + i + " j= " + j + " number " + number + "-----------------");
                    System.out.println(rowArray.get(i));
                    System.out.println(colArray.get(j));
                    System.out.println(squareArray.get(squareNum));
                    rowArray.get(i).remove(rowArray.get(i).indexOf(number));
                    colArray.get(j).remove(colArray.get(j).indexOf(number));
                    squareArray.get(squareNum).remove(squareArray.get(squareNum).indexOf(number));
                } else {
                    if (j == 0) {
                        i = i - 1;
                        j = 8;
                    } else {
                        j--;
                    }

                }


            }

        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(map[i][j] + "|");
            }
            System.out.println();
        }

    }

    public static void fillLinkedList(ArrayList arrayList, int number) {
        for (int i = 0; i < number; i++) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            for (int j = 1; j < 10; j++) {
                linkedList.add(j);
            }
            arrayList.add(linkedList);
        }
    }

    public static int generateNumber(int row, int col, int squareNum) {

        ArrayList<LinkedList<Integer>> calList = new ArrayList<LinkedList<Integer>>();
        LinkedList<Integer> possibleNum = new LinkedList<Integer>();
        calList.add(rowArray.get(row));
        calList.add(colArray.get(col));
        calList.add(squareArray.get(squareNum));
        int index = calListSize(rowArray.get(row).size(), colArray.get(col).size(), squareArray.get(squareNum).size());
        possibleNum = calList.get(index);
        int number, numIndex = 0;
        while (numIndex < possibleNum.size()) {
            number = possibleNum.get(numIndex);
            if (calList.get(0).contains(number) && calList.get(1).contains(number) && calList.get(2).contains(number)) {
                return number;
            } else {
                numIndex++;
            }
        }
        return 0;
    }

    public static int calListSize(int a, int b, int c) {
        if (a > b) {
            if (b > c)
                return 2;
            else
                return 1;
        } else {
            if (a > c)
                return 2;
            else
                return 0;
        }
    }
}
