package dora.bugskiller;

/**
 * It took me a long time to make the architecture that named group.
 * 制作名叫分组的架构花费了我很长时间。
 */
public interface CrashReportGroup {

    /**
     * 计算是否符合该组规则。
     */
    boolean counts();

    /**
     * 分组的名称。
     *
     * @return
     */
    String name();
}
