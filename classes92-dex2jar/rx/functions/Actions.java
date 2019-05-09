// 
// Decompiled by Procyon v0.5.34
// 

package rx.functions;

public final class Actions
{
    private static final EmptyAction EMPTY_ACTION;
    
    static {
        EMPTY_ACTION = new EmptyAction();
    }
    
    private Actions() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty() {
        return (EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8>)Actions.EMPTY_ACTION;
    }
    
    public static <T> Action1<T> toAction1(final Action0 action0) {
        return new Action1CallsAction0<T>(action0);
    }
    
    public static Func0<Void> toFunc(final Action0 action0) {
        return toFunc(action0, (Void)null);
    }
    
    public static <R> Func0<R> toFunc(final Action0 action0, final R r) {
        return new Func0<R>() {
            @Override
            public R call() {
                action0.call();
                return r;
            }
        };
    }
    
    public static <T1> Func1<T1, Void> toFunc(final Action1<T1> action1) {
        return toFunc(action1, (Void)null);
    }
    
    public static <T1, R> Func1<T1, R> toFunc(final Action1<T1> action1, final R r) {
        return new Func1<T1, R>() {
            @Override
            public R call(final T1 t1) {
                action1.call(t1);
                return r;
            }
        };
    }
    
    public static <T1, T2> Func2<T1, T2, Void> toFunc(final Action2<T1, T2> action2) {
        return toFunc(action2, (Void)null);
    }
    
    public static <T1, T2, R> Func2<T1, T2, R> toFunc(final Action2<T1, T2> action2, final R r) {
        return new Func2<T1, T2, R>() {
            @Override
            public R call(final T1 t1, final T2 t2) {
                action2.call(t1, t2);
                return r;
            }
        };
    }
    
    public static <T1, T2, T3> Func3<T1, T2, T3, Void> toFunc(final Action3<T1, T2, T3> action3) {
        return toFunc(action3, (Void)null);
    }
    
    public static <T1, T2, T3, R> Func3<T1, T2, T3, R> toFunc(final Action3<T1, T2, T3> action3, final R r) {
        return new Func3<T1, T2, T3, R>() {
            @Override
            public R call(final T1 t1, final T2 t2, final T3 t3) {
                action3.call(t1, t2, t3);
                return r;
            }
        };
    }
    
    public static <T1, T2, T3, T4> Func4<T1, T2, T3, T4, Void> toFunc(final Action4<T1, T2, T3, T4> action4) {
        return toFunc(action4, (Void)null);
    }
    
    public static <T1, T2, T3, T4, R> Func4<T1, T2, T3, T4, R> toFunc(final Action4<T1, T2, T3, T4> action4, final R r) {
        return new Func4<T1, T2, T3, T4, R>() {
            @Override
            public R call(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
                action4.call(t1, t2, t3, t4);
                return r;
            }
        };
    }
    
    public static <T1, T2, T3, T4, T5> Func5<T1, T2, T3, T4, T5, Void> toFunc(final Action5<T1, T2, T3, T4, T5> action5) {
        return toFunc(action5, (Void)null);
    }
    
    public static <T1, T2, T3, T4, T5, R> Func5<T1, T2, T3, T4, T5, R> toFunc(final Action5<T1, T2, T3, T4, T5> action5, final R r) {
        return new Func5<T1, T2, T3, T4, T5, R>() {
            @Override
            public R call(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
                action5.call(t1, t2, t3, t4, t5);
                return r;
            }
        };
    }
    
    public static <T1, T2, T3, T4, T5, T6> Func6<T1, T2, T3, T4, T5, T6, Void> toFunc(final Action6<T1, T2, T3, T4, T5, T6> action6) {
        return toFunc(action6, (Void)null);
    }
    
    public static <T1, T2, T3, T4, T5, T6, R> Func6<T1, T2, T3, T4, T5, T6, R> toFunc(final Action6<T1, T2, T3, T4, T5, T6> action6, final R r) {
        return new Func6<T1, T2, T3, T4, T5, T6, R>() {
            @Override
            public R call(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
                action6.call(t1, t2, t3, t4, t5, t6);
                return r;
            }
        };
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7> Func7<T1, T2, T3, T4, T5, T6, T7, Void> toFunc(final Action7<T1, T2, T3, T4, T5, T6, T7> action7) {
        return toFunc(action7, (Void)null);
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, R> Func7<T1, T2, T3, T4, T5, T6, T7, R> toFunc(final Action7<T1, T2, T3, T4, T5, T6, T7> action7, final R r) {
        return new Func7<T1, T2, T3, T4, T5, T6, T7, R>() {
            @Override
            public R call(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7) {
                action7.call(t1, t2, t3, t4, t5, t6, t7);
                return r;
            }
        };
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8> Func8<T1, T2, T3, T4, T5, T6, T7, T8, Void> toFunc(final Action8<T1, T2, T3, T4, T5, T6, T7, T8> action8) {
        return toFunc(action8, (Void)null);
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> toFunc(final Action8<T1, T2, T3, T4, T5, T6, T7, T8> action8, final R r) {
        return new Func8<T1, T2, T3, T4, T5, T6, T7, T8, R>() {
            @Override
            public R call(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8) {
                action8.call(t1, t2, t3, t4, t5, t6, t7, t8);
                return r;
            }
        };
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, Void> toFunc(final Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> action9) {
        return toFunc(action9, (Void)null);
    }
    
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> toFunc(final Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> action9, final R r) {
        return new Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R>() {
            @Override
            public R call(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
                action9.call(t1, t2, t3, t4, t5, t6, t7, t8, t9);
                return r;
            }
        };
    }
    
    public static FuncN<Void> toFunc(final ActionN actionN) {
        return toFunc(actionN, (Void)null);
    }
    
    public static <R> FuncN<R> toFunc(final ActionN actionN, final R r) {
        return new FuncN<R>() {
            @Override
            public R call(final Object... array) {
                actionN.call(array);
                return r;
            }
        };
    }
    
    static final class Action1CallsAction0<T> implements Action1<T>
    {
        final Action0 action;
        
        public Action1CallsAction0(final Action0 action) {
            this.action = action;
        }
        
        @Override
        public void call(final T t) {
            this.action.call();
        }
    }
    
    static final class EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> implements Action0, Action1<T0>, Action2<T0, T1>, Action3<T0, T1, T2>, Action4<T0, T1, T2, T3>, Action5<T0, T1, T2, T3, T4>, Action6<T0, T1, T2, T3, T4, T5>, Action7<T0, T1, T2, T3, T4, T5, T6>, Action8<T0, T1, T2, T3, T4, T5, T6, T7>, Action9<T0, T1, T2, T3, T4, T5, T6, T7, T8>, ActionN
    {
        @Override
        public void call() {
        }
        
        @Override
        public void call(final T0 t0) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2, final T2 t3) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2, final T2 t3, final T3 t4) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2, final T2 t3, final T3 t4, final T4 t5) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2, final T2 t3, final T3 t4, final T4 t5, final T5 t6) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2, final T2 t3, final T3 t4, final T4 t5, final T5 t6, final T6 t7) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2, final T2 t3, final T3 t4, final T4 t5, final T5 t6, final T6 t7, final T7 t8) {
        }
        
        @Override
        public void call(final T0 t0, final T1 t2, final T2 t3, final T3 t4, final T4 t5, final T5 t6, final T6 t7, final T7 t8, final T8 t9) {
        }
        
        @Override
        public void call(final Object... array) {
        }
    }
}
