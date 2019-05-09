// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Notification;
import rx.Observer;
import java.io.Serializable;

public final class NotificationLite<T>
{
    private static final NotificationLite INSTANCE;
    private static final Object ON_COMPLETED_SENTINEL;
    private static final Object ON_NEXT_NULL_SENTINEL;
    
    static {
        INSTANCE = new NotificationLite();
        ON_COMPLETED_SENTINEL = new Serializable() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public String toString() {
                return "Notification=>Completed";
            }
        };
        ON_NEXT_NULL_SENTINEL = new Serializable() {
            private static final long serialVersionUID = 2L;
            
            @Override
            public String toString() {
                return "Notification=>NULL";
            }
        };
    }
    
    private NotificationLite() {
    }
    
    public static <T> NotificationLite<T> instance() {
        return (NotificationLite<T>)NotificationLite.INSTANCE;
    }
    
    public boolean accept(final Observer<? super T> observer, final Object o) {
        if (o == NotificationLite.ON_COMPLETED_SENTINEL) {
            observer.onCompleted();
            return true;
        }
        if (o == NotificationLite.ON_NEXT_NULL_SENTINEL) {
            observer.onNext((Object)null);
            return false;
        }
        if (o == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        }
        if (o.getClass() == OnErrorSentinel.class) {
            observer.onError(((OnErrorSentinel)o).e);
            return true;
        }
        observer.onNext((Object)o);
        return false;
    }
    
    public Object completed() {
        return NotificationLite.ON_COMPLETED_SENTINEL;
    }
    
    public Object error(final Throwable t) {
        return new OnErrorSentinel(t);
    }
    
    public Throwable getError(final Object o) {
        return ((OnErrorSentinel)o).e;
    }
    
    public T getValue(final Object o) {
        Object o2 = o;
        if (o == NotificationLite.ON_NEXT_NULL_SENTINEL) {
            o2 = null;
        }
        return (T)o2;
    }
    
    public boolean isCompleted(final Object o) {
        return o == NotificationLite.ON_COMPLETED_SENTINEL;
    }
    
    public boolean isError(final Object o) {
        return o instanceof OnErrorSentinel;
    }
    
    public boolean isNext(final Object o) {
        return o != null && !this.isError(o) && !this.isCompleted(o);
    }
    
    public boolean isNull(final Object o) {
        return o == NotificationLite.ON_NEXT_NULL_SENTINEL;
    }
    
    public Notification.Kind kind(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        }
        if (o == NotificationLite.ON_COMPLETED_SENTINEL) {
            return Notification.Kind.OnCompleted;
        }
        if (o instanceof OnErrorSentinel) {
            return Notification.Kind.OnError;
        }
        return Notification.Kind.OnNext;
    }
    
    public Object next(final T t) {
        Object on_NEXT_NULL_SENTINEL = t;
        if (t == null) {
            on_NEXT_NULL_SENTINEL = NotificationLite.ON_NEXT_NULL_SENTINEL;
        }
        return on_NEXT_NULL_SENTINEL;
    }
    
    static final class OnErrorSentinel implements Serializable
    {
        private static final long serialVersionUID = 3L;
        final Throwable e;
        
        public OnErrorSentinel(final Throwable e) {
            this.e = e;
        }
        
        @Override
        public String toString() {
            return "Notification=>Error:" + this.e;
        }
    }
}
