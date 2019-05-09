// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.Future;
import rx.subscriptions.MultipleAssignmentSubscription;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionException;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;
import rx.Subscription;
import rx.functions.Action0;
import java.util.concurrent.atomic.AtomicInteger;
import rx.subscriptions.CompositeSubscription;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import rx.Scheduler;

public final class ExecutorScheduler extends Scheduler
{
    final Executor executor;
    
    public ExecutorScheduler(final Executor executor) {
        this.executor = executor;
    }
    
    @Override
    public Worker createWorker() {
        return new ExecutorSchedulerWorker(this.executor);
    }
    
    static final class ExecutorSchedulerWorker extends Worker implements Runnable
    {
        final Executor executor;
        final ConcurrentLinkedQueue<ScheduledAction> queue;
        final ScheduledExecutorService service;
        final CompositeSubscription tasks;
        final AtomicInteger wip;
        
        public ExecutorSchedulerWorker(final Executor executor) {
            this.executor = executor;
            this.queue = new ConcurrentLinkedQueue<ScheduledAction>();
            this.wip = new AtomicInteger();
            this.tasks = new CompositeSubscription();
            this.service = GenericScheduledExecutorService.getInstance();
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.tasks.isUnsubscribed();
        }
        
        @Override
        public void run() {
            while (!this.tasks.isUnsubscribed()) {
                final ScheduledAction scheduledAction = this.queue.poll();
                if (scheduledAction == null) {
                    return;
                }
                if (!scheduledAction.isUnsubscribed()) {
                    if (this.tasks.isUnsubscribed()) {
                        this.queue.clear();
                        return;
                    }
                    scheduledAction.run();
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
            this.queue.clear();
        }
        
        @Override
        public Subscription schedule(final Action0 action0) {
            Subscription unsubscribed;
            if (this.isUnsubscribed()) {
                unsubscribed = Subscriptions.unsubscribed();
            }
            else {
                final ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0), this.tasks);
                this.tasks.add(scheduledAction);
                this.queue.offer(scheduledAction);
                unsubscribed = scheduledAction;
                if (this.wip.getAndIncrement() == 0) {
                    try {
                        this.executor.execute(this);
                        return scheduledAction;
                    }
                    catch (RejectedExecutionException ex) {
                        this.tasks.remove(scheduledAction);
                        this.wip.decrementAndGet();
                        RxJavaHooks.onError(ex);
                        throw ex;
                    }
                }
            }
            return unsubscribed;
        }
        
        @Override
        public Subscription schedule(final Action0 action0, final long n, final TimeUnit timeUnit) {
            if (n <= 0L) {
                return this.schedule(action0);
            }
            if (this.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            final Action0 onScheduledAction = RxJavaHooks.onScheduledAction(action0);
            final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
            final MultipleAssignmentSubscription multipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
            multipleAssignmentSubscription2.set(multipleAssignmentSubscription);
            this.tasks.add(multipleAssignmentSubscription2);
            final Subscription create = Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    ExecutorSchedulerWorker.this.tasks.remove(multipleAssignmentSubscription2);
                }
            });
            final ScheduledAction scheduledAction = new ScheduledAction(new Action0() {
                @Override
                public void call() {
                    if (!multipleAssignmentSubscription2.isUnsubscribed()) {
                        final Subscription schedule = ExecutorSchedulerWorker.this.schedule(onScheduledAction);
                        multipleAssignmentSubscription2.set(schedule);
                        if (((ScheduledAction)schedule).getClass() == ScheduledAction.class) {
                            ((ScheduledAction)schedule).add(create);
                        }
                    }
                }
            });
            multipleAssignmentSubscription.set(scheduledAction);
            try {
                scheduledAction.add(this.service.schedule(scheduledAction, n, timeUnit));
                return create;
            }
            catch (RejectedExecutionException ex) {
                RxJavaHooks.onError(ex);
                throw ex;
            }
        }
        
        @Override
        public void unsubscribe() {
            this.tasks.unsubscribe();
            this.queue.clear();
        }
    }
}
