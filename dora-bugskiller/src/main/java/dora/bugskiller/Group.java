package dora.bugskiller;

/**
 * It took me a long time to make the architecture that named group, groups are used to distribute
 * policies.
 * 制作名叫分组的架构花费了我很长时间，分组被用来分发策略。
 */
public interface Group {

    /**
     * Through calculation, determine whether the group of rules.
     * 通过计算，判断是否符合该组规则。
     */
    boolean counts();

    /**
     * The name of group.
     * 分组的名称。
     */
    String name();
}
