// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.List;
import rx.subscriptions.SerialSubscription;
import java.util.Iterator;
import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import rx.Subscription;
import java.util.Map;
import rx.subscriptions.CompositeSubscription;
import java.util.HashMap;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func2;
import rx.functions.Func1;
import rx.Observable;

public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R> implements OnSubscribe<R>
{
    final Observable<TLeft> left;
    final Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector;
    final Func2<TLeft, TRight, R> resultSelector;
    final Observable<TRight> right;
    final Func1<TRight, Observable<TRightDuration>> rightDurationSelector;
    
    public OnSubscribeJoin(final Observable<TLeft> left, final Observable<TRight> right, final Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector, final Func1<TRight, Observable<TRightDuration>> rightDurationSelector, final Func2<TLeft, TRight, R> resultSelector) {
        this.left = left;
        this.right = right;
        this.leftDurationSelector = leftDurationSelector;
        this.rightDurationSelector = rightDurationSelector;
        this.resultSelector = resultSelector;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        new ResultSink(new SerializedSubscriber<Object>(subscriber)).run();
    }
    
    final class ResultSink extends HashMap<Integer, TLeft>
    {
        private static final long serialVersionUID = 3491669543549085380L;
        final CompositeSubscription group;
        boolean leftDone;
        int leftId;
        boolean rightDone;
        int rightId;
        final Map<Integer, TRight> rightMap;
        final Subscriber<? super R> subscriber;
        
        public ResultSink(final Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
            this.group = new CompositeSubscription();
            this.rightMap = new HashMap<Integer, TRight>();
        }
        
        HashMap<Integer, TLeft> leftMap() {
            return this;
        }
        
        public void run() {
            this.subscriber.add(this.group);
            final LeftSubscriber leftSubscriber = new LeftSubscriber();
            final RightSubscriber rightSubscriber = new RightSubscriber();
            this.group.add(leftSubscriber);
            this.group.add(rightSubscriber);
            OnSubscribeJoin.this.left.unsafeSubscribe(leftSubscriber);
            OnSubscribeJoin.this.right.unsafeSubscribe(rightSubscriber);
        }
        
        final class LeftSubscriber extends Subscriber<TLeft>
        {
            protected void expire(final int n, final Subscription subscription) {
                final boolean b = false;
                final ResultSink this$1 = ResultSink.this;
                // monitorenter(this$1)
                int n2 = b ? 1 : 0;
                try {
                    if (ResultSink.this.leftMap().remove(n) != null) {
                        n2 = (b ? 1 : 0);
                        if (ResultSink.this.leftMap().isEmpty()) {
                            n2 = (b ? 1 : 0);
                            if (ResultSink.this.leftDone) {
                                n2 = 1;
                            }
                        }
                    }
                    // monitorexit(this$1)
                    if (n2 != 0) {
                        ResultSink.this.subscriber.onCompleted();
                        ResultSink.this.subscriber.unsubscribe();
                        return;
                    }
                }
                finally {
                }
                // monitorexit(this$1)
                final Subscription subscription2;
                ResultSink.this.group.remove(subscription2);
            }
            
            @Override
            public void onCompleted() {
                while (true) {
                    int n = 0;
                    while (true) {
                        Label_0087: {
                            synchronized (ResultSink.this) {
                                ResultSink.this.leftDone = true;
                                if (ResultSink.this.rightDone || ResultSink.this.leftMap().isEmpty()) {
                                    break Label_0087;
                                }
                                // monitorexit(this.this$1)
                                if (n != 0) {
                                    ResultSink.this.subscriber.onCompleted();
                                    ResultSink.this.subscriber.unsubscribe();
                                    return;
                                }
                            }
                            break;
                        }
                        n = 1;
                        continue;
                    }
                }
                ResultSink.this.group.remove(this);
            }
            
            @Override
            public void onError(final Throwable t) {
                ResultSink.this.subscriber.onError(t);
                ResultSink.this.subscriber.unsubscribe();
            }
            
            @Override
            public void onNext(final TLeft tLeft) {
                while (true) {
                    Object o = ResultSink.this;
                    Object o2;
                    synchronized (o) {
                        o2 = ResultSink.this;
                        final int n = ((ResultSink)o2).leftId++;
                        ResultSink.this.leftMap().put(n, tLeft);
                        final int rightId = ResultSink.this.rightId;
                        // monitorexit(o)
                        try {
                            o = OnSubscribeJoin.this.leftDurationSelector.call(tLeft);
                            o2 = new LeftDurationSubscriber(n);
                            ResultSink.this.group.add((Subscription)o2);
                            ((Observable)o).unsafeSubscribe((Subscriber)o2);
                            o2 = new ArrayList<TRight>();
                            synchronized (ResultSink.this) {
                                for (final Map.Entry<Integer, TRight> entry : ResultSink.this.rightMap.entrySet()) {
                                    if (entry.getKey() < rightId) {
                                        ((List<TRight>)o2).add(entry.getValue());
                                    }
                                }
                            }
                        }
                        catch (Throwable t) {
                            Exceptions.throwOrReport(t, this);
                        }
                        return;
                    }
                    // monitorexit(o)
                    o = ((List<TRight>)o2).iterator();
                    while (((Iterator)o).hasNext()) {
                        final TLeft tLeft2;
                        o2 = OnSubscribeJoin.this.resultSelector.call(tLeft2, ((Iterator<TRight>)o).next());
                        ResultSink.this.subscriber.onNext((Object)o2);
                    }
                }
            }
            
            final class LeftDurationSubscriber extends Subscriber<TLeftDuration>
            {
                final int id;
                boolean once;
                
                public LeftDurationSubscriber(final int id) {
                    this.once = true;
                    this.id = id;
                }
                
                @Override
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        LeftSubscriber.this.expire(this.id, this);
                    }
                }
                
                @Override
                public void onError(final Throwable t) {
                    LeftSubscriber.this.onError(t);
                }
                
                @Override
                public void onNext(final TLeftDuration tLeftDuration) {
                    this.onCompleted();
                }
            }
        }
        
        final class RightSubscriber extends Subscriber<TRight>
        {
            void expire(final int n, final Subscription subscription) {
                final boolean b = false;
                final ResultSink this$1 = ResultSink.this;
                // monitorenter(this$1)
                int n2 = b ? 1 : 0;
                try {
                    if (ResultSink.this.rightMap.remove(n) != null) {
                        n2 = (b ? 1 : 0);
                        if (ResultSink.this.rightMap.isEmpty()) {
                            n2 = (b ? 1 : 0);
                            if (ResultSink.this.rightDone) {
                                n2 = 1;
                            }
                        }
                    }
                    // monitorexit(this$1)
                    if (n2 != 0) {
                        ResultSink.this.subscriber.onCompleted();
                        ResultSink.this.subscriber.unsubscribe();
                        return;
                    }
                }
                finally {
                }
                // monitorexit(this$1)
                final Subscription subscription2;
                ResultSink.this.group.remove(subscription2);
            }
            
            @Override
            public void onCompleted() {
                while (true) {
                    int n = 0;
                    while (true) {
                        Label_0089: {
                            synchronized (ResultSink.this) {
                                ResultSink.this.rightDone = true;
                                if (ResultSink.this.leftDone || ResultSink.this.rightMap.isEmpty()) {
                                    break Label_0089;
                                }
                                // monitorexit(this.this$1)
                                if (n != 0) {
                                    ResultSink.this.subscriber.onCompleted();
                                    ResultSink.this.subscriber.unsubscribe();
                                    return;
                                }
                            }
                            break;
                        }
                        n = 1;
                        continue;
                    }
                }
                ResultSink.this.group.remove(this);
            }
            
            @Override
            public void onError(final Throwable t) {
                ResultSink.this.subscriber.onError(t);
                ResultSink.this.subscriber.unsubscribe();
            }
            
            @Override
            public void onNext(final TRight tRight) {
                while (true) {
                    Object o = ResultSink.this;
                    Object o2;
                    synchronized (o) {
                        o2 = ResultSink.this;
                        final int n = ((ResultSink)o2).rightId++;
                        ResultSink.this.rightMap.put(n, tRight);
                        final int leftId = ResultSink.this.leftId;
                        // monitorexit(o)
                        o = new SerialSubscription();
                        ResultSink.this.group.add((Subscription)o);
                        try {
                            o = OnSubscribeJoin.this.rightDurationSelector.call(tRight);
                            o2 = new RightDurationSubscriber(n);
                            ResultSink.this.group.add((Subscription)o2);
                            ((Observable)o).unsafeSubscribe((Subscriber)o2);
                            o2 = new ArrayList();
                            synchronized (ResultSink.this) {
                                for (final Map.Entry<Integer, TLeft> entry : ResultSink.this.leftMap().entrySet()) {
                                    if (entry.getKey() < leftId) {
                                        ((List<TLeft>)o2).add(entry.getValue());
                                    }
                                }
                            }
                        }
                        catch (Throwable t) {
                            Exceptions.throwOrReport(t, this);
                        }
                        return;
                    }
                    // monitorexit(o)
                    o = ((List<TLeft>)o2).iterator();
                    while (((Iterator)o).hasNext()) {
                        final TRight tRight2;
                        o2 = OnSubscribeJoin.this.resultSelector.call(((Iterator<TLeft>)o).next(), tRight2);
                        ResultSink.this.subscriber.onNext((Object)o2);
                    }
                }
            }
            
            final class RightDurationSubscriber extends Subscriber<TRightDuration>
            {
                final int id;
                boolean once;
                
                public RightDurationSubscriber(final int id) {
                    this.once = true;
                    this.id = id;
                }
                
                @Override
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        RightSubscriber.this.expire(this.id, this);
                    }
                }
                
                @Override
                public void onError(final Throwable t) {
                    RightSubscriber.this.onError(t);
                }
                
                @Override
                public void onNext(final TRightDuration tRightDuration) {
                    this.onCompleted();
                }
            }
        }
    }
}
