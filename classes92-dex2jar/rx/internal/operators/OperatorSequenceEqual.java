// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorSequenceEqual
{
    static final Object LOCAL_ON_COMPLETED;
    
    static {
        LOCAL_ON_COMPLETED = new Object();
    }
    
    private OperatorSequenceEqual() {
        throw new IllegalStateException("No instances!");
    }
    
    static <T> Observable<Object> materializeLite(final Observable<T> observable) {
        return Observable.concat((Observable<?>)observable, (Observable<?>)Observable.just(OperatorSequenceEqual.LOCAL_ON_COMPLETED));
    }
    
    public static <T> Observable<Boolean> sequenceEqual(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Func2<? super T, ? super T, Boolean> func2) {
        return Observable.zip((Observable<?>)materializeLite((Observable<Object>)observable), (Observable<?>)materializeLite((Observable<Object>)observable2), (Func2<? super Object, ? super Object, ?>)new Func2<Object, Object, Boolean>() {
            @Override
            public Boolean call(final Object o, final Object o2) {
                boolean b;
                if (o == OperatorSequenceEqual.LOCAL_ON_COMPLETED) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (o2 == OperatorSequenceEqual.LOCAL_ON_COMPLETED) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    return true;
                }
                if (b || b2) {
                    return false;
                }
                return func2.call(o, o2);
            }
        }).all((Func1<? super Object, Boolean>)UtilityFunctions.identity());
    }
}
