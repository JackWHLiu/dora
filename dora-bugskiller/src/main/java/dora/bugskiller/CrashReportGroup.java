package dora.bugskiller;

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
