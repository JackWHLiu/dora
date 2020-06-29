package dora.bugskiller;

import androidx.annotation.CallSuper;

/**
 * Wrap it so that various strategies can be combined freely, the innermost policy is invoked first,
 * and so on. To extend this class, override the report() method.
 * 包装一下，便于多种策略自由组合，最里面的策略最先被执行，以此类推，扩展此类请重写report()方法。
 */
public abstract class PolicyWrapper<I extends Info, P extends Policy> implements Policy<I> {

    private Group mGroup;   //策略自己的分组
    private P mBasePolicy;

    protected PolicyWrapper(Group group, P policy) {
        this.mGroup = group;
        this.mBasePolicy = policy;
    }

    @CallSuper
    @Override
    public void report(I info, Group group) {
        if (mBasePolicy != null) {
            mBasePolicy.report(info, mBasePolicy.getGroup());
        }
    }

    @Override
    public Group getGroup() {
        return mGroup;
    }
}
