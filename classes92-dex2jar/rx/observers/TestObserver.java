// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import java.util.Collections;
import java.util.Collection;
import rx.exceptions.CompositeException;
import java.util.ArrayList;
import rx.Notification;
import java.util.List;
import rx.Observer;

@Deprecated
public class TestObserver<T> implements Observer<T>
{
    private static final Observer<Object> INERT;
    private final Observer<T> delegate;
    private final List<Notification<T>> onCompletedEvents;
    private final List<Throwable> onErrorEvents;
    private final List<T> onNextEvents;
    
    static {
        INERT = new Observer<Object>() {
            @Override
            public void onCompleted() {
            }
            
            @Override
            public void onError(final Throwable t) {
            }
            
            @Override
            public void onNext(final Object o) {
            }
        };
    }
    
    public TestObserver() {
        this.onNextEvents = new ArrayList<T>();
        this.onErrorEvents = new ArrayList<Throwable>();
        this.onCompletedEvents = new ArrayList<Notification<T>>();
        this.delegate = (Observer<T>)TestObserver.INERT;
    }
    
    public TestObserver(final Observer<T> delegate) {
        this.onNextEvents = new ArrayList<T>();
        this.onErrorEvents = new ArrayList<Throwable>();
        this.onCompletedEvents = new ArrayList<Notification<T>>();
        this.delegate = delegate;
    }
    
    public void assertReceivedOnNext(final List<T> list) {
        if (this.onNextEvents.size() != list.size()) {
            this.assertionError("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.onNextEvents.size() + ".\n" + "Provided values: " + list + "\n" + "Actual values: " + this.onNextEvents + "\n");
        }
        for (int i = 0; i < list.size(); ++i) {
            final T value = list.get(i);
            final T value2 = this.onNextEvents.get(i);
            if (value == null) {
                if (value2 != null) {
                    this.assertionError("Value at index: " + i + " expected to be [null] but was: [" + value2 + "]\n");
                }
            }
            else if (!value.equals(value2)) {
                final StringBuilder append = new StringBuilder().append("Value at index: ").append(i).append(" expected to be [").append(value).append("] (").append(value.getClass().getSimpleName()).append(") but was: [").append(value2).append("] (");
                String simpleName;
                if (value2 != null) {
                    simpleName = value2.getClass().getSimpleName();
                }
                else {
                    simpleName = "null";
                }
                this.assertionError(append.append(simpleName).append(")\n").toString());
            }
        }
    }
    
    public void assertTerminalEvent() {
        if (this.onErrorEvents.size() > 1) {
            this.assertionError("Too many onError events: " + this.onErrorEvents.size());
        }
        if (this.onCompletedEvents.size() > 1) {
            this.assertionError("Too many onCompleted events: " + this.onCompletedEvents.size());
        }
        if (this.onCompletedEvents.size() == 1 && this.onErrorEvents.size() == 1) {
            this.assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.onCompletedEvents.isEmpty() && this.onErrorEvents.isEmpty()) {
            this.assertionError("No terminal events received.");
        }
    }
    
    final void assertionError(final String s) {
        final StringBuilder sb = new StringBuilder(s.length() + 32);
        sb.append(s).append(" (");
        final int size = this.onCompletedEvents.size();
        sb.append(size).append(" completion");
        if (size != 1) {
            sb.append('s');
        }
        sb.append(')');
        if (!this.onErrorEvents.isEmpty()) {
            final int size2 = this.onErrorEvents.size();
            sb.append(" (+").append(size2).append(" error");
            if (size2 != 1) {
                sb.append('s');
            }
            sb.append(')');
        }
        final AssertionError assertionError = new AssertionError((Object)sb.toString());
        if (!this.onErrorEvents.isEmpty()) {
            if (this.onErrorEvents.size() == 1) {
                assertionError.initCause(this.onErrorEvents.get(0));
            }
            else {
                assertionError.initCause(new CompositeException(this.onErrorEvents));
            }
        }
        throw assertionError;
    }
    
    public List<Object> getEvents() {
        final ArrayList<List<T>> list = new ArrayList<List<T>>();
        list.add(this.onNextEvents);
        list.add((List<T>)this.onErrorEvents);
        list.add((List<T>)this.onCompletedEvents);
        return Collections.unmodifiableList((List<?>)list);
    }
    
    public List<Notification<T>> getOnCompletedEvents() {
        return Collections.unmodifiableList((List<? extends Notification<T>>)this.onCompletedEvents);
    }
    
    public List<Throwable> getOnErrorEvents() {
        return Collections.unmodifiableList((List<? extends Throwable>)this.onErrorEvents);
    }
    
    public List<T> getOnNextEvents() {
        return Collections.unmodifiableList((List<? extends T>)this.onNextEvents);
    }
    
    @Override
    public void onCompleted() {
        this.onCompletedEvents.add(Notification.createOnCompleted());
        this.delegate.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.onErrorEvents.add(t);
        this.delegate.onError(t);
    }
    
    @Override
    public void onNext(final T t) {
        this.onNextEvents.add(t);
        this.delegate.onNext(t);
    }
}
