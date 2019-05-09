// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Task<TResult>
{
    public static final ExecutorService BACKGROUND_EXECUTOR;
    private static final Executor IMMEDIATE_EXECUTOR;
    private static Task<?> TASK_CANCELLED;
    private static Task<Boolean> TASK_FALSE;
    private static Task<?> TASK_NULL;
    private static Task<Boolean> TASK_TRUE;
    public static final Executor UI_THREAD_EXECUTOR;
    private static volatile UnobservedExceptionHandler unobservedExceptionHandler;
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations;
    private Exception error;
    private boolean errorHasBeenObserved;
    private final Object lock;
    private TResult result;
    private UnobservedErrorNotifier unobservedErrorNotifier;
    
    static {
        BACKGROUND_EXECUTOR = BoltsExecutors.background();
        IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
        UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
        Task.TASK_NULL = new Task<Object>(null);
        Task.TASK_TRUE = new Task<Boolean>(Boolean.valueOf(true));
        Task.TASK_FALSE = new Task<Boolean>(Boolean.valueOf(false));
        Task.TASK_CANCELLED = new Task<Object>(true);
    }
    
    Task() {
        this.lock = new Object();
        this.continuations = new ArrayList<Continuation<TResult, Void>>();
    }
    
    private Task(final TResult tResult) {
        this.lock = new Object();
        this.continuations = new ArrayList<Continuation<TResult, Void>>();
        this.trySetResult(tResult);
    }
    
    private Task(final boolean b) {
        this.lock = new Object();
        this.continuations = new ArrayList<Continuation<TResult, Void>>();
        if (b) {
            this.trySetCancelled();
            return;
        }
        this.trySetResult(null);
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable) {
        return call(callable, Task.IMMEDIATE_EXECUTOR, null);
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable, final CancellationToken cancellationToken) {
        return call(callable, Task.IMMEDIATE_EXECUTOR, cancellationToken);
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable, final Executor executor) {
        return call(callable, executor, null);
    }
    
    public static <TResult> Task<TResult> call(final Callable<TResult> callable, final Executor executor, final CancellationToken cancellationToken) {
        final bolts.TaskCompletionSource<TResult> taskCompletionSource = new bolts.TaskCompletionSource<TResult>();
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                        taskCompletionSource.setCancelled();
                        return;
                    }
                    try {
                        taskCompletionSource.setResult(callable.call());
                    }
                    catch (CancellationException ex) {
                        taskCompletionSource.setCancelled();
                    }
                    catch (Exception error) {
                        taskCompletionSource.setError(error);
                    }
                }
            });
            return taskCompletionSource.getTask();
        }
        catch (Exception ex) {
            taskCompletionSource.setError(new ExecutorException(ex));
            return taskCompletionSource.getTask();
        }
    }
    
    public static <TResult> Task<TResult> callInBackground(final Callable<TResult> callable) {
        return call(callable, Task.BACKGROUND_EXECUTOR, null);
    }
    
    public static <TResult> Task<TResult> callInBackground(final Callable<TResult> callable, final CancellationToken cancellationToken) {
        return call(callable, Task.BACKGROUND_EXECUTOR, cancellationToken);
    }
    
    public static <TResult> Task<TResult> cancelled() {
        return (Task<TResult>)Task.TASK_CANCELLED;
    }
    
    private static <TContinuationResult, TResult> void completeAfterTask(final bolts.TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, final Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                        taskCompletionSource.setCancelled();
                        return;
                    }
                    try {
                        if (continuation.then(task) == null) {
                            taskCompletionSource.setResult(null);
                            return;
                        }
                        goto Label_0064;
                    }
                    catch (CancellationException ex) {
                        taskCompletionSource.setCancelled();
                    }
                    catch (Exception error) {
                        taskCompletionSource.setError(error);
                    }
                }
            });
        }
        catch (Exception ex) {
            taskCompletionSource.setError(new ExecutorException(ex));
        }
    }
    
    private static <TContinuationResult, TResult> void completeImmediately(final bolts.TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, final Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                        taskCompletionSource.setCancelled();
                        return;
                    }
                    try {
                        taskCompletionSource.setResult(continuation.then(task));
                    }
                    catch (CancellationException ex) {
                        taskCompletionSource.setCancelled();
                    }
                    catch (Exception error) {
                        taskCompletionSource.setError(error);
                    }
                }
            });
        }
        catch (Exception ex) {
            taskCompletionSource.setError(new ExecutorException(ex));
        }
    }
    
    public static <TResult> TaskCompletionSource create() {
        final Task task = new Task();
        task.getClass();
        return task.new TaskCompletionSource();
    }
    
    public static Task<Void> delay(final long n) {
        return delay(n, BoltsExecutors.scheduled(), null);
    }
    
    public static Task<Void> delay(final long n, final CancellationToken cancellationToken) {
        return delay(n, BoltsExecutors.scheduled(), cancellationToken);
    }
    
    static Task<Void> delay(final long n, final ScheduledExecutorService scheduledExecutorService, final CancellationToken cancellationToken) {
        if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
            return cancelled();
        }
        if (n <= 0L) {
            return forResult((Void)null);
        }
        final bolts.TaskCompletionSource<Void> taskCompletionSource = new bolts.TaskCompletionSource<Void>();
        final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                taskCompletionSource.trySetResult(null);
            }
        }, n, TimeUnit.MILLISECONDS);
        if (cancellationToken != null) {
            cancellationToken.register(new Runnable() {
                @Override
                public void run() {
                    schedule.cancel(true);
                    taskCompletionSource.trySetCancelled();
                }
            });
        }
        return taskCompletionSource.getTask();
    }
    
    public static <TResult> Task<TResult> forError(final Exception error) {
        final bolts.TaskCompletionSource<TResult> taskCompletionSource = new bolts.TaskCompletionSource<TResult>();
        taskCompletionSource.setError(error);
        return taskCompletionSource.getTask();
    }
    
    public static <TResult> Task<TResult> forResult(final TResult result) {
        if (result == null) {
            return (Task<TResult>)Task.TASK_NULL;
        }
        if (!(result instanceof Boolean)) {
            final bolts.TaskCompletionSource<TResult> taskCompletionSource = new bolts.TaskCompletionSource<TResult>();
            taskCompletionSource.setResult(result);
            return taskCompletionSource.getTask();
        }
        if (result) {
            return (Task<TResult>)Task.TASK_TRUE;
        }
        return (Task<TResult>)Task.TASK_FALSE;
    }
    
    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return Task.unobservedExceptionHandler;
    }
    
    private void runContinuations() {
        final Object lock = this.lock;
        // monitorenter(lock)
        try {
            for (final Continuation<TResult, Void> continuation : this.continuations) {
                try {
                    continuation.then(this);
                }
                catch (RuntimeException ex) {
                    throw ex;
                }
                catch (Exception ex2) {
                    throw new RuntimeException(ex2);
                }
            }
        }
        finally {}
        this.continuations = null;
    }
    // monitorexit(lock)
    
    public static void setUnobservedExceptionHandler(final UnobservedExceptionHandler unobservedExceptionHandler) {
        Task.unobservedExceptionHandler = unobservedExceptionHandler;
    }
    
    public static Task<Void> whenAll(final Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return forResult((Void)null);
        }
        final bolts.TaskCompletionSource<Void> taskCompletionSource = new bolts.TaskCompletionSource<Void>();
        final ArrayList list = new ArrayList();
        final Object o = new Object();
        final AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final Iterator<? extends Task<?>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ((Task)iterator.next()).continueWith(new Continuation<Object, Void>() {
                @Override
                public Void then(final Task<Object> task) {
                    Label_0143: {
                        Label_0028: {
                            if (!task.isFaulted()) {
                                break Label_0028;
                            }
                            synchronized (o) {
                                list.add(task.getError());
                                // monitorexit(this.val$errorLock)
                                if (task.isCancelled()) {
                                    atomicBoolean.set(true);
                                }
                                if (atomicInteger.decrementAndGet() == 0) {
                                    if (list.size() == 0) {
                                        break Label_0143;
                                    }
                                    if (list.size() != 1) {
                                        break Label_0028;
                                    }
                                    taskCompletionSource.setError(list.get(0));
                                }
                                return null;
                            }
                        }
                        taskCompletionSource.setError(new AggregateException(String.format("There were %d exceptions.", list.size()), list));
                        return null;
                    }
                    if (atomicBoolean.get()) {
                        taskCompletionSource.setCancelled();
                        return null;
                    }
                    taskCompletionSource.setResult(null);
                    return null;
                }
            });
        }
        return taskCompletionSource.getTask();
    }
    
    public static <TResult> Task<List<TResult>> whenAllResult(final Collection<? extends Task<TResult>> collection) {
        return whenAll(collection).onSuccess((Continuation<Void, List<TResult>>)new Continuation<Void, List<TResult>>() {
            @Override
            public List<TResult> then(final Task<Void> task) throws Exception {
                List<TResult> emptyList;
                if (collection.size() == 0) {
                    emptyList = Collections.emptyList();
                }
                else {
                    final ArrayList<TResult> list = new ArrayList<TResult>();
                    final Iterator iterator = collection.iterator();
                    while (true) {
                        emptyList = list;
                        if (!iterator.hasNext()) {
                            break;
                        }
                        list.add(iterator.next().getResult());
                    }
                }
                return emptyList;
            }
        });
    }
    
    public static Task<Task<?>> whenAny(final Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return forResult((Task<?>)null);
        }
        final bolts.TaskCompletionSource<Task<?>> taskCompletionSource = new bolts.TaskCompletionSource<Task<?>>();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final Iterator<? extends Task<?>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ((Task)iterator.next()).continueWith(new Continuation<Object, Void>() {
                @Override
                public Void then(final Task<Object> result) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.setResult(result);
                    }
                    else {
                        result.getError();
                    }
                    return null;
                }
            });
        }
        return taskCompletionSource.getTask();
    }
    
    public static <TResult> Task<Task<TResult>> whenAnyResult(final Collection<? extends Task<TResult>> collection) {
        if (collection.size() == 0) {
            return forResult((Task<TResult>)null);
        }
        final bolts.TaskCompletionSource<Task<TResult>> taskCompletionSource = new bolts.TaskCompletionSource<Task<TResult>>();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final Iterator<? extends Task<TResult>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ((Task)iterator.next()).continueWith(new Continuation<TResult, Void>() {
                @Override
                public Void then(final Task<TResult> result) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.setResult(result);
                    }
                    else {
                        result.getError();
                    }
                    return null;
                }
            });
        }
        return taskCompletionSource.getTask();
    }
    
    public <TOut> Task<TOut> cast() {
        return (Task<TOut>)this;
    }
    
    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation) {
        return this.continueWhile(callable, continuation, Task.IMMEDIATE_EXECUTOR, null);
    }
    
    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation, final CancellationToken cancellationToken) {
        return this.continueWhile(callable, continuation, Task.IMMEDIATE_EXECUTOR, cancellationToken);
    }
    
    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation, final Executor executor) {
        return this.continueWhile(callable, continuation, executor, null);
    }
    
    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation, final Executor executor, final CancellationToken cancellationToken) {
        final Capture<Task$9> capture = new Capture<Task$9>();
        capture.set(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(final Task<Void> task) throws Exception {
                if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (callable.call()) {
                    return Task.forResult((Object)null).onSuccessTask((Continuation<Object, Task<Object>>)continuation, executor).onSuccessTask(capture.get(), executor);
                }
                return Task.forResult((Void)null);
            }
        });
        return this.makeVoid().continueWithTask((Continuation<Void, Task<Void>>)capture.get(), executor);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation) {
        return this.continueWith(continuation, Task.IMMEDIATE_EXECUTOR, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final CancellationToken cancellationToken) {
        return this.continueWith(continuation, Task.IMMEDIATE_EXECUTOR, cancellationToken);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final Executor executor) {
        return this.continueWith(continuation, executor, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final Executor executor, final CancellationToken cancellationToken) {
        final bolts.TaskCompletionSource<TContinuationResult> taskCompletionSource = new bolts.TaskCompletionSource<TContinuationResult>();
        synchronized (this.lock) {
            final boolean completed = this.isCompleted();
            if (!completed) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    @Override
                    public Void then(final Task<TResult> task) {
                        completeImmediately(taskCompletionSource, (Continuation<Object, Object>)continuation, task, executor, cancellationToken);
                        return null;
                    }
                });
            }
            // monitorexit(this.lock)
            if (completed) {
                completeImmediately(taskCompletionSource, continuation, this, executor, cancellationToken);
            }
            return taskCompletionSource.getTask();
        }
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation) {
        return this.continueWithTask(continuation, Task.IMMEDIATE_EXECUTOR, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final CancellationToken cancellationToken) {
        return this.continueWithTask(continuation, Task.IMMEDIATE_EXECUTOR, cancellationToken);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor) {
        return this.continueWithTask(continuation, executor, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor, final CancellationToken cancellationToken) {
        final bolts.TaskCompletionSource<TContinuationResult> taskCompletionSource = new bolts.TaskCompletionSource<TContinuationResult>();
        synchronized (this.lock) {
            final boolean completed = this.isCompleted();
            if (!completed) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    @Override
                    public Void then(final Task<TResult> task) {
                        completeAfterTask(taskCompletionSource, (Continuation<Object, Task<Object>>)continuation, task, executor, cancellationToken);
                        return null;
                    }
                });
            }
            // monitorexit(this.lock)
            if (completed) {
                completeAfterTask(taskCompletionSource, continuation, this, executor, cancellationToken);
            }
            return taskCompletionSource.getTask();
        }
    }
    
    public Exception getError() {
        synchronized (this.lock) {
            if (this.error != null) {
                this.errorHasBeenObserved = true;
                if (this.unobservedErrorNotifier != null) {
                    this.unobservedErrorNotifier.setObserved();
                    this.unobservedErrorNotifier = null;
                }
            }
            return this.error;
        }
    }
    
    public TResult getResult() {
        synchronized (this.lock) {
            return this.result;
        }
    }
    
    public boolean isCancelled() {
        synchronized (this.lock) {
            return this.cancelled;
        }
    }
    
    public boolean isCompleted() {
        synchronized (this.lock) {
            return this.complete;
        }
    }
    
    public boolean isFaulted() {
        while (true) {
            synchronized (this.lock) {
                if (this.getError() != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public Task<Void> makeVoid() {
        return this.continueWithTask((Continuation<TResult, Task<Void>>)new Continuation<TResult, Task<Void>>() {
            @Override
            public Task<Void> then(final Task<TResult> task) throws Exception {
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                return Task.forResult((Void)null);
            }
        });
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation) {
        return this.onSuccess(continuation, Task.IMMEDIATE_EXECUTOR, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, final CancellationToken cancellationToken) {
        return this.onSuccess(continuation, Task.IMMEDIATE_EXECUTOR, cancellationToken);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, final Executor executor) {
        return this.onSuccess(continuation, executor, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, final Executor executor, final CancellationToken cancellationToken) {
        return this.continueWithTask((Continuation<TResult, Task<TContinuationResult>>)new Continuation<TResult, Task<TContinuationResult>>() {
            @Override
            public Task<TContinuationResult> then(final Task<TResult> task) {
                if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWith(continuation);
            }
        }, executor);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation) {
        return this.onSuccessTask(continuation, Task.IMMEDIATE_EXECUTOR);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final CancellationToken cancellationToken) {
        return this.onSuccessTask(continuation, Task.IMMEDIATE_EXECUTOR, cancellationToken);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor) {
        return this.onSuccessTask(continuation, executor, null);
    }
    
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor, final CancellationToken cancellationToken) {
        return this.continueWithTask((Continuation<TResult, Task<TContinuationResult>>)new Continuation<TResult, Task<TContinuationResult>>() {
            @Override
            public Task<TContinuationResult> then(final Task<TResult> task) {
                if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWithTask(continuation);
            }
        }, executor);
    }
    
    boolean trySetCancelled() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            this.runContinuations();
            return true;
        }
    }
    
    boolean trySetError(final Exception error) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.error = error;
            this.errorHasBeenObserved = false;
            this.lock.notifyAll();
            this.runContinuations();
            if (!this.errorHasBeenObserved && getUnobservedExceptionHandler() != null) {
                this.unobservedErrorNotifier = new UnobservedErrorNotifier(this);
            }
            return true;
        }
    }
    
    boolean trySetResult(final TResult result) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = result;
            this.lock.notifyAll();
            this.runContinuations();
            return true;
        }
    }
    
    public void waitForCompletion() throws InterruptedException {
        synchronized (this.lock) {
            if (!this.isCompleted()) {
                this.lock.wait();
            }
        }
    }
    
    public boolean waitForCompletion(final long n, final TimeUnit timeUnit) throws InterruptedException {
        synchronized (this.lock) {
            if (!this.isCompleted()) {
                this.lock.wait(timeUnit.toMillis(n));
            }
            return this.isCompleted();
        }
    }
    
    public class TaskCompletionSource extends bolts.TaskCompletionSource<TResult>
    {
        TaskCompletionSource() {
        }
    }
    
    public interface UnobservedExceptionHandler
    {
        void unobservedException(final Task<?> p0, final UnobservedTaskException p1);
    }
}
