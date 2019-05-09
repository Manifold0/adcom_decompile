package rx.internal.util;

import rx.functions.Func1;

public final class UtilityFunctions {

    /* renamed from: rx.internal.util.UtilityFunctions$1 */
    static class C33001 implements Func1<T, T> {
        C33001() {
        }

        public T call(T o) {
            return o;
        }
    }

    enum AlwaysFalse implements Func1<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object o) {
            return Boolean.valueOf(false);
        }
    }

    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object o) {
            return Boolean.valueOf(true);
        }
    }

    private UtilityFunctions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return AlwaysFalse.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return new C33001();
    }
}
