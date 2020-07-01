package dora.bugskiller;

import android.util.Log;

/**
 * A system that controls log output globally. When flag is closed, you can't output logs anywhere.
 * Instead, you can output logs anywhere.<note>The log system is opened by default.</note>
 * 控制全局日志输出的系统。当关闭标志时，您不能将日志输出到任何地方。
 * 相反，您可以将日志输出到任何地方。注意：日志系统默认开启。
 */
public class DoraLog {

    /**
     * The flag that represents the log system is opened or closed, default is opened.
     * 表示日志系统打开或关闭的标志，默认为打开。
     */
    private static boolean DEBUG = true;

    /**
     * The default log output tag.
     * 默认日志输出标记。
     */
    private static final String TAG = DoraLog.class.getSimpleName().toLowerCase();

    private static DoraLog CHANNEL = new DoraLog();
    private Collector mCollector;
    private Policy mLogPolicy;

    private DoraLog() {
        mCollector = new LogCollector();
        mLogPolicy = new LogFilePolicy();
    }

    private DoraLog(LogReportPolicy policy) {
        this();
        setLogPolicy(policy);
    }

    public void setLogPolicy(LogReportPolicy policy) {
        this.mLogPolicy = policy;
    }

    public static DoraLog getChannel() {
        return CHANNEL;
    }

    private void _print(LogInfo info) {
        mCollector.collect(info);
        mCollector.report(mLogPolicy);
    }

    public static void print(LogInfo info) {
        CHANNEL._print(info);
    }

    public static void close() {
        DEBUG = false;
    }

    public static void open() {
        DEBUG = true;
    }

    public static boolean isOpened() {
        return DEBUG;
    }

    public static boolean isClosed() {
        return !DEBUG;
    }

    public static void info(String msg) {
        info(TAG, msg);
    }

    public static void info(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void error(String msg) {
        error(TAG, msg);
    }

    public static void error(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void debug(String msg) {
        debug(TAG, msg);
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void warn(String msg) {
        warn(TAG, msg);
    }

    public static void warn(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void verbose(String msg) {
        verbose(TAG, msg);
    }

    public static void verbose(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }
}