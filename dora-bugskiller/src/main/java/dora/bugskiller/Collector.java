package dora.bugskiller;

/**
 * Abstract procedure of collecting and reporting crash information.
 * 抽象了收集和上报崩溃信息的过程。
 */
public abstract class Collector<I extends Info, P extends Policy> {

    /**
     * Collects crash info, just cache the crash information for Collector.
     * 收集崩溃信息，仅仅将CrashInfo缓存在Collector。
     */
    public abstract void collect(I info);

    /**
     * Feed back the crash information collected to the developer as needed.
     * 根据需要将收集到的崩溃信息反馈给开发者。
     *
     * @param policy 崩溃报告的方针/策略
     */
    public abstract void report(P policy);
}