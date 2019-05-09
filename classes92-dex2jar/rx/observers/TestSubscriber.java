// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.Notification;
import java.util.concurrent.TimeUnit;
import rx.annotations.Experimental;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import rx.exceptions.CompositeException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.List;
import rx.Observer;
import rx.Subscriber;

public class TestSubscriber<T> extends Subscriber<T>
{
    private static final Observer<Object> INERT;
    private int completions;
    private final Observer<T> delegate;
    private final List<Throwable> errors;
    private volatile Thread lastSeenThread;
    private final CountDownLatch latch;
    private volatile int valueCount;
    private final List<T> values;
    
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
    
    public TestSubscriber() {
        this(-1L);
    }
    
    public TestSubscriber(final long n) {
        this(TestSubscriber.INERT, n);
    }
    
    public TestSubscriber(final Observer<T> observer) {
        this(observer, -1L);
    }
    
    public TestSubscriber(final Observer<T> delegate, final long n) {
        this.latch = new CountDownLatch(1);
        if (delegate == null) {
            throw new NullPointerException();
        }
        this.delegate = delegate;
        if (n >= 0L) {
            this.request(n);
        }
        this.values = new ArrayList<T>();
        this.errors = new ArrayList<Throwable>();
    }
    
    public TestSubscriber(final Subscriber<T> subscriber) {
        this(subscriber, -1L);
    }
    
    private void assertItem(final T t, final int n) {
        final T value = this.values.get(n);
        if (t == null) {
            if (value != null) {
                this.assertionError("Value at index: " + n + " expected to be [null] but was: [" + value + "]\n");
            }
        }
        else if (!t.equals(value)) {
            final StringBuilder append = new StringBuilder().append("Value at index: ").append(n).append(" expected to be [").append(t).append("] (").append(t.getClass().getSimpleName()).append(") but was: [").append(value).append("] (");
            String simpleName;
            if (value != null) {
                simpleName = value.getClass().getSimpleName();
            }
            else {
                simpleName = "null";
            }
            this.assertionError(append.append(simpleName).append(")\n").toString());
        }
    }
    
    public static <T> TestSubscriber<T> create() {
        return new TestSubscriber<T>();
    }
    
    public static <T> TestSubscriber<T> create(final long n) {
        return new TestSubscriber<T>(n);
    }
    
    public static <T> TestSubscriber<T> create(final Observer<T> observer) {
        return new TestSubscriber<T>(observer);
    }
    
    public static <T> TestSubscriber<T> create(final Observer<T> observer, final long n) {
        return new TestSubscriber<T>(observer, n);
    }
    
    public static <T> TestSubscriber<T> create(final Subscriber<T> subscriber) {
        return new TestSubscriber<T>(subscriber);
    }
    
    public void assertCompleted() {
        final int completions = this.completions;
        if (completions == 0) {
            this.assertionError("Not completed!");
        }
        else if (completions > 1) {
            this.assertionError("Completed multiple times: " + completions);
        }
    }
    
    public void assertError(final Class<? extends Throwable> clazz) {
        final List<Throwable> errors = this.errors;
        if (errors.isEmpty()) {
            this.assertionError("No errors");
        }
        else {
            if (errors.size() > 1) {
                final AssertionError assertionError = new AssertionError((Object)("Multiple errors: " + errors.size()));
                assertionError.initCause(new CompositeException(errors));
                throw assertionError;
            }
            if (!clazz.isInstance(errors.get(0))) {
                final AssertionError assertionError2 = new AssertionError((Object)("Exceptions differ; expected: " + clazz + ", actual: " + errors.get(0)));
                assertionError2.initCause(errors.get(0));
                throw assertionError2;
            }
        }
    }
    
    public void assertError(final Throwable t) {
        final List<Throwable> errors = this.errors;
        if (errors.isEmpty()) {
            this.assertionError("No errors");
        }
        else {
            if (errors.size() > 1) {
                this.assertionError("Multiple errors");
                return;
            }
            if (!t.equals(errors.get(0))) {
                this.assertionError("Exceptions differ; expected: " + t + ", actual: " + errors.get(0));
            }
        }
    }
    
    public void assertNoErrors() {
        if (!this.getOnErrorEvents().isEmpty()) {
            this.assertionError("Unexpected onError events");
        }
    }
    
    public void assertNoTerminalEvent() {
        final List<Throwable> errors = this.errors;
        final int completions = this.completions;
        if (!errors.isEmpty() || completions > 0) {
            if (errors.isEmpty()) {
                this.assertionError("Found " + errors.size() + " errors and " + completions + " completion events instead of none");
            }
            else {
                if (errors.size() == 1) {
                    this.assertionError("Found " + errors.size() + " errors and " + completions + " completion events instead of none");
                    return;
                }
                this.assertionError("Found " + errors.size() + " errors and " + completions + " completion events instead of none");
            }
        }
    }
    
    public void assertNoValues() {
        final int size = this.values.size();
        if (size != 0) {
            this.assertionError("No onNext events expected yet some received: " + size);
        }
    }
    
    public void assertNotCompleted() {
        final int completions = this.completions;
        if (completions == 1) {
            this.assertionError("Completed!");
        }
        else if (completions > 1) {
            this.assertionError("Completed multiple times: " + completions);
        }
    }
    
    public void assertReceivedOnNext(final List<T> list) {
        if (this.values.size() != list.size()) {
            this.assertionError("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.values.size() + ".\n" + "Provided values: " + list + "\n" + "Actual values: " + this.values + "\n");
        }
        for (int i = 0; i < list.size(); ++i) {
            this.assertItem(list.get(i), i);
        }
    }
    
    public void assertTerminalEvent() {
        if (this.errors.size() > 1) {
            this.assertionError("Too many onError events: " + this.errors.size());
        }
        if (this.completions > 1) {
            this.assertionError("Too many onCompleted events: " + this.completions);
        }
        if (this.completions == 1 && this.errors.size() == 1) {
            this.assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.completions == 0 && this.errors.isEmpty()) {
            this.assertionError("No terminal events received.");
        }
    }
    
    public void assertUnsubscribed() {
        if (!this.isUnsubscribed()) {
            this.assertionError("Not unsubscribed.");
        }
    }
    
    public void assertValue(final T t) {
        this.assertReceivedOnNext(Collections.singletonList(t));
    }
    
    public void assertValueCount(final int n) {
        final int size = this.values.size();
        if (size != n) {
            this.assertionError("Number of onNext events differ; expected: " + n + ", actual: " + size);
        }
    }
    
    public void assertValues(final T... array) {
        this.assertReceivedOnNext(Arrays.asList(array));
    }
    
    @Experimental
    public final void assertValuesAndClear(final T t, final T... array) {
        this.assertValueCount(array.length + 1);
        this.assertItem(t, 0);
        for (int i = 0; i < array.length; ++i) {
            this.assertItem(array[i], i + 1);
        }
        this.values.clear();
    }
    
    final void assertionError(final String s) {
        final StringBuilder sb = new StringBuilder(s.length() + 32);
        sb.append(s).append(" (");
        final int completions = this.completions;
        sb.append(completions).append(" completion");
        if (completions != 1) {
            sb.append('s');
        }
        sb.append(')');
        if (!this.errors.isEmpty()) {
            final int size = this.errors.size();
            sb.append(" (+").append(size).append(" error");
            if (size != 1) {
                sb.append('s');
            }
            sb.append(')');
        }
        final AssertionError assertionError = new AssertionError((Object)sb.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                assertionError.initCause(this.errors.get(0));
            }
            else {
                assertionError.initCause(new CompositeException(this.errors));
            }
        }
        throw assertionError;
    }
    
    public void awaitTerminalEvent() {
        try {
            this.latch.await();
        }
        catch (InterruptedException ex) {
            throw new IllegalStateException("Interrupted", ex);
        }
    }
    
    public void awaitTerminalEvent(final long n, final TimeUnit timeUnit) {
        try {
            this.latch.await(n, timeUnit);
        }
        catch (InterruptedException ex) {
            throw new IllegalStateException("Interrupted", ex);
        }
    }
    
    public void awaitTerminalEventAndUnsubscribeOnTimeout(final long n, final TimeUnit timeUnit) {
        try {
            if (!this.latch.await(n, timeUnit)) {
                this.unsubscribe();
            }
        }
        catch (InterruptedException ex) {
            this.unsubscribe();
        }
    }
    
    @Experimental
    public final boolean awaitValueCount(final int n, long n2, final TimeUnit timeUnit) {
        while (n2 != 0L && this.valueCount < n) {
            try {
                timeUnit.sleep(1L);
                --n2;
                continue;
            }
            catch (InterruptedException ex) {
                throw new IllegalStateException("Interrupted", ex);
            }
            break;
        }
        return this.valueCount >= n;
    }
    
    @Experimental
    public final int getCompletions() {
        return this.completions;
    }
    
    public Thread getLastSeenThread() {
        return this.lastSeenThread;
    }
    
    @Deprecated
    public List<Notification<T>> getOnCompletedEvents() {
        final int completions = this.completions;
        int n;
        if (completions != 0) {
            n = completions;
        }
        else {
            n = 1;
        }
        final ArrayList list = new ArrayList<Notification<Object>>(n);
        for (int i = 0; i < completions; ++i) {
            list.add(Notification.createOnCompleted());
        }
        return (List<Notification<T>>)list;
    }
    
    public List<Throwable> getOnErrorEvents() {
        return this.errors;
    }
    
    public List<T> getOnNextEvents() {
        return this.values;
    }
    
    public final int getValueCount() {
        return this.valueCount;
    }
    
    @Override
    public void onCompleted() {
        try {
            ++this.completions;
            this.lastSeenThread = Thread.currentThread();
            this.delegate.onCompleted();
        }
        finally {
            this.latch.countDown();
        }
    }
    
    @Override
    public void onError(final Throwable t) {
        try {
            this.lastSeenThread = Thread.currentThread();
            this.errors.add(t);
            this.delegate.onError(t);
        }
        finally {
            this.latch.countDown();
        }
    }
    
    @Override
    public void onNext(final T t) {
        this.lastSeenThread = Thread.currentThread();
        this.values.add(t);
        this.valueCount = this.values.size();
        this.delegate.onNext(t);
    }
    
    public void requestMore(final long n) {
        this.request(n);
    }
}
