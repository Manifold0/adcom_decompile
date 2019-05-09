// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

import java.util.HashSet;
import java.util.Set;
import rx.plugins.RxJavaPlugins;

public final class OnErrorThrowable extends RuntimeException
{
    private static final long serialVersionUID = -569558213262703934L;
    private final boolean hasValue;
    private final Object value;
    
    private OnErrorThrowable(final Throwable t) {
        super(t);
        this.hasValue = false;
        this.value = null;
    }
    
    private OnErrorThrowable(final Throwable t, final Object value) {
        super(t);
        this.hasValue = true;
        this.value = value;
    }
    
    public static Throwable addValueAsLastCause(Throwable finalCause, final Object o) {
        Throwable t = finalCause;
        if (finalCause == null) {
            t = new NullPointerException();
        }
        finalCause = Exceptions.getFinalCause(t);
        if (finalCause instanceof OnNextValue && ((OnNextValue)finalCause).getValue() == o) {
            return t;
        }
        Exceptions.addCause(t, new OnNextValue(o));
        return t;
    }
    
    public static OnErrorThrowable from(Throwable finalCause) {
        Throwable t = finalCause;
        if (finalCause == null) {
            t = new NullPointerException();
        }
        finalCause = Exceptions.getFinalCause(t);
        if (finalCause instanceof OnNextValue) {
            return new OnErrorThrowable(t, ((OnNextValue)finalCause).getValue());
        }
        return new OnErrorThrowable(t);
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public boolean isValueNull() {
        return this.hasValue;
    }
    
    public static class OnNextValue extends RuntimeException
    {
        private static final long serialVersionUID = -3454462756050397899L;
        private final Object value;
        
        public OnNextValue(final Object value) {
            super("OnError while emitting onNext value: " + renderValue(value));
            this.value = value;
        }
        
        static String renderValue(final Object o) {
            if (o == null) {
                return "null";
            }
            if (Primitives.INSTANCE.contains(o.getClass())) {
                return o.toString();
            }
            if (o instanceof String) {
                return (String)o;
            }
            if (o instanceof Enum) {
                return ((Enum)o).name();
            }
            final String handleOnNextValueRendering = RxJavaPlugins.getInstance().getErrorHandler().handleOnNextValueRendering(o);
            if (handleOnNextValueRendering != null) {
                return handleOnNextValueRendering;
            }
            return o.getClass().getName() + ".class";
        }
        
        public Object getValue() {
            return this.value;
        }
        
        static final class Primitives
        {
            static final Set<Class<?>> INSTANCE;
            
            static {
                INSTANCE = create();
            }
            
            private static Set<Class<?>> create() {
                final HashSet<Class<Boolean>> set = (HashSet<Class<Boolean>>)new HashSet<Class<Double>>();
                set.add((Class<Double>)Boolean.class);
                set.add((Class<Double>)Character.class);
                set.add((Class<Double>)Byte.class);
                set.add((Class<Double>)Short.class);
                set.add((Class<Double>)Integer.class);
                set.add((Class<Double>)Long.class);
                set.add((Class<Double>)Float.class);
                set.add(Double.class);
                return (Set<Class<?>>)set;
            }
        }
    }
}
