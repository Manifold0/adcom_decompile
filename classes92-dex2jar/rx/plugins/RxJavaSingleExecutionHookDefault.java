// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

final class RxJavaSingleExecutionHookDefault extends RxJavaSingleExecutionHook
{
    private static final RxJavaSingleExecutionHookDefault INSTANCE;
    
    static {
        INSTANCE = new RxJavaSingleExecutionHookDefault();
    }
    
    private RxJavaSingleExecutionHookDefault() {
    }
    
    public static RxJavaSingleExecutionHook getInstance() {
        return RxJavaSingleExecutionHookDefault.INSTANCE;
    }
}
