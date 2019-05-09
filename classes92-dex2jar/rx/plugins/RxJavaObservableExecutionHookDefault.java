// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

final class RxJavaObservableExecutionHookDefault extends RxJavaObservableExecutionHook
{
    private static final RxJavaObservableExecutionHookDefault INSTANCE;
    
    static {
        INSTANCE = new RxJavaObservableExecutionHookDefault();
    }
    
    private RxJavaObservableExecutionHookDefault() {
    }
    
    public static RxJavaObservableExecutionHook getInstance() {
        return RxJavaObservableExecutionHookDefault.INSTANCE;
    }
}
