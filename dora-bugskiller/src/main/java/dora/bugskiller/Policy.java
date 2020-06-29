package dora.bugskiller;

public interface Policy<I extends Info> {

    void report(I info, Group group);

    Group getGroup();
}
