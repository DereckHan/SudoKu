/**
 * Created by Derek on 5/14/16.
 */
public class SimpleExactCoverDLXTest {
    private static Head buildMatrix() {
        Head h = new Head();
        ColumnHeader[] columnHeaders = new ColumnHeader[8];
        AbstractCell lastColumnHeader = h;
        for (int i = 0; i < columnHeaders.length; i++) {
            ColumnHeader column = new ColumnHeader();
            column.up = column;
            column.down = column;
            column.index = i;
            lastColumnHeader.appendRight(column);
            lastColumnHeader = column;
            columnHeaders[i] = column;
        }
        lastColumnHeader.appendRight(h);
        appendLine(columnHeaders, 'A', new int[] {0, 3, 7});
        appendLine(columnHeaders, 'B', new int[] {0, 7});
        appendLine(columnHeaders, 'C', new int[] {3, 4, 6});
        appendLine(columnHeaders, 'D', new int[] {2, 4, 5});
        appendLine(columnHeaders, 'E', new int[] {1, 2, 5});
        appendLine(columnHeaders, 'F', new int[] {1, 6});
        return h;
    }
    private static  <T> void appendLine(ColumnHeader[] columnHeaders, T name, int[] elements) {
        if (elements.length == 0)
            return;
        AbstractCell lastCell = new Cell<T>();
        for (int i = 0; i < elements.length; i++) {
            Cell<T> cell = new Cell<T>();
            ColumnHeader column = columnHeaders[elements[i]];
            AbstractCell columnLastCell = column.up;
            columnLastCell.appendDown(cell);
            column.numberOfOnes++;
            column.up = cell;
            cell.down = column;
            cell.name = name;
            cell.columnHeader = column;
            lastCell.appendRight(cell);
            lastCell = cell;
        }
        AbstractCell firstCell = columnHeaders[elements[0]].up;
        lastCell.appendRight(firstCell);
    }

    public static void main(String[] args) {
        Character[] solution = new Character[7];
        (new DancingLinks()).search(buildMatrix(), 0, solution);

    }
//
//    @Test
//    public void test() {
//        Character[] solution = new Character[6];
//        (new DancingLinks()).search(buildMatrix(), 0, solution);
//        assertEquals('B', solution[0].charValue());
//        assertEquals('D', solution[1].charValue());
//        assertEquals('F', solution[2].charValue());
//        assertNull(solution[3]);
//    }
}
