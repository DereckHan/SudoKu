/**
 * Created by Dereck on 5/14/16.
 */
abstract class AbstractCell {
    AbstractCell left;
    AbstractCell right;
    AbstractCell up;
    AbstractCell down;

    void appendRight(AbstractCell cell) {
        right = cell;
        cell.left = this;
    }

    void appendDown(AbstractCell cell) {
        down = cell;
        cell.up = this;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    protected abstract String getDescription();
}
