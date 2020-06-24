package dora.bugskiller;

/**
 * It can combine two or more groups.
 * 可以用来组合2种或2种以上的分组。
 */
public class ComplexGroup implements CrashReportGroup {

    /**
     * Intersection.
     * 交集。
     */
    public static final String SYMBOL_INTERSECTION = "∩";

    /**
     * Union set.
     * 并集。
     */
    public static final String SYMBOL_UNION_SET = "∪";

    /**
     * Supplementary set.
     * 补集。
     */
    public static final String SYMBOL_SUPPLEMENTARY_SET = "^";

    private String mSymbol;
    private CrashReportGroup mLeft;
    private CrashReportGroup mRight;

    public static ComplexGroup and(CrashReportGroup left, CrashReportGroup right) {
        return new ComplexGroup(SYMBOL_INTERSECTION, left, right);
    }

    public static ComplexGroup or(CrashReportGroup left, CrashReportGroup right) {
        return new ComplexGroup(SYMBOL_UNION_SET, left, right);
    }

    public static ComplexGroup not(CrashReportGroup left, CrashReportGroup right) {
        return new ComplexGroup(SYMBOL_SUPPLEMENTARY_SET, left, right);
    }

    ComplexGroup(String symbol, CrashReportGroup left, CrashReportGroup right) {
        this.mSymbol = symbol;
        this.mLeft = left;
        this.mRight = right;
    }

    @Override
    public boolean counts() {
        if (mSymbol.equals(SYMBOL_INTERSECTION)) {
            return mLeft.counts() && mRight.counts();
        }
        if (mSymbol.equals(SYMBOL_UNION_SET)) {
            return mLeft.counts() || mRight.counts();
        }
        if (mSymbol.equals(SYMBOL_SUPPLEMENTARY_SET)) {
            return !(mLeft.counts() || mRight.counts());
        }
        return false;
    }

    @Override
    public String name() {
        return mSymbol.equals(SYMBOL_SUPPLEMENTARY_SET) ? (mSymbol + "(" + mLeft + " " +
                mRight + ")") : "(" + mLeft + mSymbol + mRight + ")";
    }
}
