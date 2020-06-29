package dora.bugskiller;

public class DefaultGroup implements Group {

    @Override
    public boolean counts() {
        return true;    //所有情况都在默认组
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }
}
