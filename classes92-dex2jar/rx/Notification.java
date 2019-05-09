// 
// Decompiled by Procyon v0.5.34
// 

package rx;

public final class Notification<T>
{
    private static final Notification<Void> ON_COMPLETED;
    private final Kind kind;
    private final Throwable throwable;
    private final T value;
    
    static {
        ON_COMPLETED = new Notification<Void>(Kind.OnCompleted, null, null);
    }
    
    private Notification(final Kind kind, final T value, final Throwable throwable) {
        this.value = value;
        this.throwable = throwable;
        this.kind = kind;
    }
    
    public static <T> Notification<T> createOnCompleted() {
        return (Notification<T>)Notification.ON_COMPLETED;
    }
    
    @Deprecated
    public static <T> Notification<T> createOnCompleted(final Class<T> clazz) {
        return (Notification<T>)Notification.ON_COMPLETED;
    }
    
    public static <T> Notification<T> createOnError(final Throwable t) {
        return new Notification<T>(Kind.OnError, null, t);
    }
    
    public static <T> Notification<T> createOnNext(final T t) {
        return new Notification<T>(Kind.OnNext, t, null);
    }
    
    public void accept(final Observer<? super T> observer) {
        if (this.kind == Kind.OnNext) {
            observer.onNext(this.getValue());
            return;
        }
        if (this.kind == Kind.OnCompleted) {
            observer.onCompleted();
            return;
        }
        observer.onError(this.getThrowable());
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        if (o != null) {
            if (this == o) {
                return true;
            }
            if (o.getClass() == this.getClass()) {
                final Notification notification = (Notification)o;
                if (notification.getKind() != this.getKind() || (this.value != notification.value && (this.value == null || !this.value.equals(notification.value)))) {
                    return false;
                }
                boolean b2 = b;
                if (this.throwable != notification.throwable) {
                    if (this.throwable == null || !this.throwable.equals(notification.throwable)) {
                        return false;
                    }
                    b2 = b;
                }
                return b2;
                b2 = false;
                return b2;
            }
        }
        return false;
    }
    
    public Kind getKind() {
        return this.kind;
    }
    
    public Throwable getThrowable() {
        return this.throwable;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public boolean hasThrowable() {
        return this.isOnError() && this.throwable != null;
    }
    
    public boolean hasValue() {
        return this.isOnNext() && this.value != null;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        final int n = hashCode = this.getKind().hashCode();
        if (this.hasValue()) {
            hashCode = n * 31 + this.getValue().hashCode();
        }
        int n2 = hashCode;
        if (this.hasThrowable()) {
            n2 = hashCode * 31 + this.getThrowable().hashCode();
        }
        return n2;
    }
    
    public boolean isOnCompleted() {
        return this.getKind() == Kind.OnCompleted;
    }
    
    public boolean isOnError() {
        return this.getKind() == Kind.OnError;
    }
    
    public boolean isOnNext() {
        return this.getKind() == Kind.OnNext;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder(64).append('[').append(super.toString()).append(' ').append(this.getKind());
        if (this.hasValue()) {
            append.append(' ').append(this.getValue());
        }
        if (this.hasThrowable()) {
            append.append(' ').append(this.getThrowable().getMessage());
        }
        append.append(']');
        return append.toString();
    }
    
    public enum Kind
    {
        OnCompleted, 
        OnError, 
        OnNext;
    }
}
