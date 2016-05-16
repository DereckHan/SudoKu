/**
 * Created by Dereck on 5/14/16.
 */
public class Head extends AbstractCell {
    public Head() {
        left=this;
        right=this;
    }

    protected String getDescription() {
        return "head";
    }
}
