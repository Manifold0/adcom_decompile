// 
// Decompiled by Procyon v0.5.34
// 

package rx.functions;

public final class Functions
{
    private Functions() {
        throw new IllegalStateException("No instances!");
    }
    
    public static FuncN<Void> fromAction(final Action0 action0) {
        return new FuncN<Void>() {
            @Override
            public Void call(final Object... array) {
                if (array.length != 0) {
                    throw new IllegalArgumentException("Action0 expecting 0 arguments.");
                }
                action0.call();
                return null;
            }
        };
    }
    
    public static <T0> FuncN<Void> fromAction(final Action1<? super T0> action1) {
        return new FuncN<Void>() {
            @Override
            public Void call(final Object... array) {
                if (array.length != 1) {
                    throw new IllegalArgumentException("Action1 expecting 1 argument.");
                }
                action1.call(array[0]);
                return null;
            }
        };
    }
    
    public static <T0, T1> FuncN<Void> fromAction(final Action2<? super T0, ? super T1> action2) {
        return new FuncN<Void>() {
            @Override
            public Void call(final Object... array) {
                if (array.length != 2) {
                    throw new IllegalArgumentException("Action3 expecting 2 arguments.");
                }
                action2.call(array[0], array[1]);
                return null;
            }
        };
    }
    
    public static <T0, T1, T2> FuncN<Void> fromAction(final Action3<? super T0, ? super T1, ? super T2> action3) {
        return new FuncN<Void>() {
            @Override
            public Void call(final Object... array) {
                if (array.length != 3) {
                    throw new IllegalArgumentException("Action3 expecting 3 arguments.");
                }
                action3.call(array[0], array[1], array[2]);
                return null;
            }
        };
    }
    
    public static <R> FuncN<R> fromFunc(final Func0<? extends R> func0) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 0) {
                    throw new IllegalArgumentException("Func0 expecting 0 arguments.");
                }
                return func0.call();
            }
        };
    }
    
    public static <T0, R> FuncN<R> fromFunc(final Func1<? super T0, ? extends R> func1) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 1) {
                    throw new IllegalArgumentException("Func1 expecting 1 argument.");
                }
                return func1.call(array[0]);
            }
        };
    }
    
    public static <T0, T1, R> FuncN<R> fromFunc(final Func2<? super T0, ? super T1, ? extends R> func2) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 2) {
                    throw new IllegalArgumentException("Func2 expecting 2 arguments.");
                }
                return func2.call(array[0], array[1]);
            }
        };
    }
    
    public static <T0, T1, T2, R> FuncN<R> fromFunc(final Func3<? super T0, ? super T1, ? super T2, ? extends R> func3) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 3) {
                    throw new IllegalArgumentException("Func3 expecting 3 arguments.");
                }
                return func3.call(array[0], array[1], array[2]);
            }
        };
    }
    
    public static <T0, T1, T2, T3, R> FuncN<R> fromFunc(final Func4<? super T0, ? super T1, ? super T2, ? super T3, ? extends R> func4) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 4) {
                    throw new IllegalArgumentException("Func4 expecting 4 arguments.");
                }
                return func4.call(array[0], array[1], array[2], array[3]);
            }
        };
    }
    
    public static <T0, T1, T2, T3, T4, R> FuncN<R> fromFunc(final Func5<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func5) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 5) {
                    throw new IllegalArgumentException("Func5 expecting 5 arguments.");
                }
                return func5.call(array[0], array[1], array[2], array[3], array[4]);
            }
        };
    }
    
    public static <T0, T1, T2, T3, T4, T5, R> FuncN<R> fromFunc(final Func6<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func6) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 6) {
                    throw new IllegalArgumentException("Func6 expecting 6 arguments.");
                }
                return func6.call(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
        };
    }
    
    public static <T0, T1, T2, T3, T4, T5, T6, R> FuncN<R> fromFunc(final Func7<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func7) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 7) {
                    throw new IllegalArgumentException("Func7 expecting 7 arguments.");
                }
                return func7.call(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
        };
    }
    
    public static <T0, T1, T2, T3, T4, T5, T6, T7, R> FuncN<R> fromFunc(final Func8<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func8) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 8) {
                    throw new IllegalArgumentException("Func8 expecting 8 arguments.");
                }
                return func8.call(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
        };
    }
    
    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, R> FuncN<R> fromFunc(final Func9<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func9) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                if (array.length != 9) {
                    throw new IllegalArgumentException("Func9 expecting 9 arguments.");
                }
                return func9.call(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8]);
            }
        };
    }
}
