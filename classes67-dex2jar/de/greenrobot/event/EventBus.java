// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event;

import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Looper;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.List;
import java.util.Map;

public class EventBus
{
    private static final String DEFAULT_METHOD_NAME = "onEvent";
    public static String TAG;
    private static volatile EventBus defaultInstance;
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache;
    static ExecutorService executorService;
    private final AsyncPoster asyncPoster;
    private final BackgroundPoster backgroundPoster;
    private final ThreadLocal<PostingThreadState> currentPostingThreadState;
    private boolean logSubscriberExceptions;
    private final HandlerPoster mainThreadPoster;
    private final Map<Class<?>, Object> stickyEvents;
    private boolean subscribed;
    private final SubscriberMethodFinder subscriberMethodFinder;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    private final Map<Object, List<Class<?>>> typesBySubscriber;
    
    static {
        EventBus.executorService = Executors.newCachedThreadPool();
        EventBus.TAG = "Event";
        eventTypesCache = new HashMap<Class<?>, List<Class<?>>>();
    }
    
    public EventBus() {
        this.currentPostingThreadState = new ThreadLocal<PostingThreadState>() {
            @Override
            protected PostingThreadState initialValue() {
                return new PostingThreadState();
            }
        };
        this.subscriptionsByEventType = new HashMap<Class<?>, CopyOnWriteArrayList<Subscription>>();
        this.typesBySubscriber = new HashMap<Object, List<Class<?>>>();
        this.stickyEvents = new ConcurrentHashMap<Class<?>, Object>();
        this.mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
        this.backgroundPoster = new BackgroundPoster(this);
        this.asyncPoster = new AsyncPoster(this);
        this.subscriberMethodFinder = new SubscriberMethodFinder();
        this.logSubscriberExceptions = true;
    }
    
    static void addInterfaces(final List<Class<?>> list, final Class<?>[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final Class<?> clazz = array[i];
            if (!list.contains(clazz)) {
                list.add(clazz);
                addInterfaces(list, clazz.getInterfaces());
            }
        }
    }
    
    public static void clearCaches() {
        SubscriberMethodFinder.clearCaches();
        EventBus.eventTypesCache.clear();
    }
    
    public static void clearSkipMethodNameVerifications() {
        SubscriberMethodFinder.clearSkipMethodVerifications();
    }
    
    private List<Class<?>> findEventTypes(final Class<?> clazz) {
        synchronized (EventBus.eventTypesCache) {
            List<Class<?>> list;
            if ((list = EventBus.eventTypesCache.get(clazz)) == null) {
                final ArrayList<Class<?>> list2 = new ArrayList<Class<?>>();
                for (Class<?> superclass = clazz; superclass != null; superclass = superclass.getSuperclass()) {
                    list2.add(superclass);
                    addInterfaces(list2, superclass.getInterfaces());
                }
                EventBus.eventTypesCache.put(clazz, list2);
                list = list2;
            }
            return list;
        }
    }
    
    public static EventBus getDefault() {
        Label_0028: {
            if (EventBus.defaultInstance != null) {
                break Label_0028;
            }
            synchronized (EventBus.class) {
                if (EventBus.defaultInstance == null) {
                    EventBus.defaultInstance = new EventBus();
                }
                return EventBus.defaultInstance;
            }
        }
    }
    
    private void postSingleEvent(final Object p0, final PostingThreadState p1) throws Error {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //     4: astore          8
        //     6: aload_0        
        //     7: aload           8
        //     9: invokespecial   de/greenrobot/event/EventBus.findEventTypes:(Ljava/lang/Class;)Ljava/util/List;
        //    12: astore          9
        //    14: iconst_0       
        //    15: istore          4
        //    17: aload           9
        //    19: invokeinterface java/util/List.size:()I
        //    24: istore          6
        //    26: iconst_0       
        //    27: istore_3       
        //    28: iload_3        
        //    29: iload           6
        //    31: if_icmpge       202
        //    34: aload           9
        //    36: iload_3        
        //    37: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    42: checkcast       Ljava/lang/Class;
        //    45: astore          10
        //    47: aload_0        
        //    48: monitorenter   
        //    49: aload_0        
        //    50: getfield        de/greenrobot/event/EventBus.subscriptionsByEventType:Ljava/util/Map;
        //    53: aload           10
        //    55: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    60: checkcast       Ljava/util/concurrent/CopyOnWriteArrayList;
        //    63: astore          10
        //    65: aload_0        
        //    66: monitorexit    
        //    67: iload           4
        //    69: istore          5
        //    71: aload           10
        //    73: ifnull          168
        //    76: iload           4
        //    78: istore          5
        //    80: aload           10
        //    82: invokevirtual   java/util/concurrent/CopyOnWriteArrayList.isEmpty:()Z
        //    85: ifne            168
        //    88: aload           10
        //    90: invokevirtual   java/util/concurrent/CopyOnWriteArrayList.iterator:()Ljava/util/Iterator;
        //    93: astore          10
        //    95: aload           10
        //    97: invokeinterface java/util/Iterator.hasNext:()Z
        //   102: ifeq            165
        //   105: aload           10
        //   107: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   112: checkcast       Lde/greenrobot/event/Subscription;
        //   115: astore          11
        //   117: aload_2        
        //   118: aload_1        
        //   119: putfield        de/greenrobot/event/EventBus$PostingThreadState.event:Ljava/lang/Object;
        //   122: aload_2        
        //   123: aload           11
        //   125: putfield        de/greenrobot/event/EventBus$PostingThreadState.subscription:Lde/greenrobot/event/Subscription;
        //   128: aload_0        
        //   129: aload           11
        //   131: aload_1        
        //   132: aload_2        
        //   133: getfield        de/greenrobot/event/EventBus$PostingThreadState.isMainThread:Z
        //   136: invokespecial   de/greenrobot/event/EventBus.postToSubscription:(Lde/greenrobot/event/Subscription;Ljava/lang/Object;Z)V
        //   139: aload_2        
        //   140: getfield        de/greenrobot/event/EventBus$PostingThreadState.canceled:Z
        //   143: istore          7
        //   145: aload_2        
        //   146: aconst_null    
        //   147: putfield        de/greenrobot/event/EventBus$PostingThreadState.event:Ljava/lang/Object;
        //   150: aload_2        
        //   151: aconst_null    
        //   152: putfield        de/greenrobot/event/EventBus$PostingThreadState.subscription:Lde/greenrobot/event/Subscription;
        //   155: aload_2        
        //   156: iconst_0       
        //   157: putfield        de/greenrobot/event/EventBus$PostingThreadState.canceled:Z
        //   160: iload           7
        //   162: ifeq            95
        //   165: iconst_1       
        //   166: istore          5
        //   168: iload_3        
        //   169: iconst_1       
        //   170: iadd           
        //   171: istore_3       
        //   172: iload           5
        //   174: istore          4
        //   176: goto            28
        //   179: astore_1       
        //   180: aload_0        
        //   181: monitorexit    
        //   182: aload_1        
        //   183: athrow         
        //   184: astore_1       
        //   185: aload_2        
        //   186: aconst_null    
        //   187: putfield        de/greenrobot/event/EventBus$PostingThreadState.event:Ljava/lang/Object;
        //   190: aload_2        
        //   191: aconst_null    
        //   192: putfield        de/greenrobot/event/EventBus$PostingThreadState.subscription:Lde/greenrobot/event/Subscription;
        //   195: aload_2        
        //   196: iconst_0       
        //   197: putfield        de/greenrobot/event/EventBus$PostingThreadState.canceled:Z
        //   200: aload_1        
        //   201: athrow         
        //   202: iload           4
        //   204: ifne            261
        //   207: getstatic       de/greenrobot/event/EventBus.TAG:Ljava/lang/String;
        //   210: new             Ljava/lang/StringBuilder;
        //   213: dup            
        //   214: invokespecial   java/lang/StringBuilder.<init>:()V
        //   217: ldc             "No subscribers registered for event "
        //   219: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   222: aload           8
        //   224: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   227: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   230: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   233: pop            
        //   234: aload           8
        //   236: ldc             Lde/greenrobot/event/NoSubscriberEvent;.class
        //   238: if_acmpeq       261
        //   241: aload           8
        //   243: ldc             Lde/greenrobot/event/SubscriberExceptionEvent;.class
        //   245: if_acmpeq       261
        //   248: aload_0        
        //   249: new             Lde/greenrobot/event/NoSubscriberEvent;
        //   252: dup            
        //   253: aload_0        
        //   254: aload_1        
        //   255: invokespecial   de/greenrobot/event/NoSubscriberEvent.<init>:(Lde/greenrobot/event/EventBus;Ljava/lang/Object;)V
        //   258: invokevirtual   de/greenrobot/event/EventBus.post:(Ljava/lang/Object;)V
        //   261: return         
        //    Exceptions:
        //  throws java.lang.Error
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  49     67     179    184    Any
        //  128    145    184    202    Any
        //  180    182    179    184    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0165:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void postToSubscription(final Subscription subscription, final Object o, final boolean b) {
        switch (subscription.subscriberMethod.threadMode) {
            default: {
                throw new IllegalStateException("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
            }
            case PostThread: {
                this.invokeSubscriber(subscription, o);
            }
            case MainThread: {
                if (b) {
                    this.invokeSubscriber(subscription, o);
                    return;
                }
                this.mainThreadPoster.enqueue(subscription, o);
            }
            case BackgroundThread: {
                if (b) {
                    this.backgroundPoster.enqueue(subscription, o);
                    return;
                }
                this.invokeSubscriber(subscription, o);
            }
            case Async: {
                this.asyncPoster.enqueue(subscription, o);
            }
        }
    }
    
    private void register(final Object o, final String s, final boolean b, final int n) {
        synchronized (this) {
            final Iterator<SubscriberMethod> iterator = this.subscriberMethodFinder.findSubscriberMethods(o.getClass(), s).iterator();
            while (iterator.hasNext()) {
                this.subscribe(o, iterator.next(), b, n);
            }
        }
    }
    // monitorexit(this)
    
    private void register(final Object o, String iterator, final boolean b, final Class<?> clazz, final Class<?>... array) {
        while (true) {
        Label_0024:
            while (true) {
                Object class1 = null;
                Label_0071: {
                    synchronized (this) {
                        class1 = o.getClass();
                        iterator = (String)this.subscriberMethodFinder.findSubscriberMethods((Class<?>)class1, iterator).iterator();
                        while (((Iterator)iterator).hasNext()) {
                            class1 = ((Iterator<SubscriberMethod>)iterator).next();
                            if (clazz != ((SubscriberMethod)class1).eventType) {
                                break Label_0071;
                            }
                            this.subscribe(o, (SubscriberMethod)class1, b, 0);
                        }
                        break;
                    }
                }
                if (array != null) {
                    for (int length = array.length, i = 0; i < length; ++i) {
                        if (array[i] == ((SubscriberMethod)class1).eventType) {
                            this.subscribe(o, (SubscriberMethod)class1, b, 0);
                            continue Label_0024;
                        }
                    }
                    continue Label_0024;
                }
                continue Label_0024;
            }
        }
    }
    // monitorexit(this)
    
    public static void skipMethodVerificationFor(final Class<?> clazz) {
        SubscriberMethodFinder.skipMethodVerificationFor(clazz);
    }
    
    private void subscribe(Object stickyEvents, final SubscriberMethod subscriberMethod, boolean b, int n) {
        this.subscribed = true;
        final Class<?> eventType = subscriberMethod.eventType;
        final CopyOnWriteArrayList<Subscription> list = this.subscriptionsByEventType.get(eventType);
        final Subscription subscription = new Subscription(stickyEvents, subscriberMethod, n);
    Label_0298_Outer:
        while (true) {
            while (true) {
                Object value = null;
                final int size;
                Label_0075: {
                    Label_0066: {
                        if (list == null) {
                            value = new CopyOnWriteArrayList<Subscription>();
                            this.subscriptionsByEventType.put(eventType, (CopyOnWriteArrayList<Subscription>)value);
                            break Label_0066;
                        }
                        Label_0218: {
                            break Label_0218;
                            while (true) {
                                stickyEvents = this.stickyEvents;
                                while (true) {
                                    Label_0312: {
                                        synchronized (stickyEvents) {
                                            value = this.stickyEvents.get(eventType);
                                            // monitorexit(stickyEvents)
                                            if (value != null) {
                                                if (Looper.getMainLooper() != Looper.myLooper()) {
                                                    break Label_0312;
                                                }
                                                b = true;
                                                this.postToSubscription(subscription, value, b);
                                            }
                                            return;
                                            ++n;
                                            break Label_0075;
                                            while (true) {
                                                throw new EventBusException("Subscriber " + stickyEvents.getClass() + " already registered to event " + eventType);
                                                final Iterator<Subscription> iterator = list.iterator();
                                                Label_0225: {
                                                    continue;
                                                }
                                            }
                                        }
                                        // iftrue(Label_0225:, !(Subscription)iterator.next().equals((Object)subscription))
                                        // iftrue(Label_0066:, !iterator.hasNext())
                                    }
                                    b = false;
                                    continue Label_0298_Outer;
                                }
                            }
                        }
                    }
                    size = ((CopyOnWriteArrayList)value).size();
                    n = 0;
                }
                if (n <= size) {
                    if (n != size && subscription.priority <= ((CopyOnWriteArrayList<Subscription>)value).get(n).priority) {
                        continue;
                    }
                    ((CopyOnWriteArrayList<Subscription>)value).add(n, subscription);
                }
                break;
            }
            List<Class<?>> list2;
            if ((list2 = this.typesBySubscriber.get(stickyEvents)) == null) {
                list2 = new ArrayList<Class<?>>();
                this.typesBySubscriber.put(stickyEvents, list2);
            }
            list2.add(eventType);
            if (b) {
                continue Label_0298_Outer;
            }
            break;
        }
    }
    
    private void unubscribeByEventType(final Object o, final Class<?> clazz) {
        final CopyOnWriteArrayList<Subscription> list = this.subscriptionsByEventType.get(clazz);
        if (list != null) {
            int n;
            int n2;
            for (int size = list.size(), i = 0; i < size; i = n + 1, size = n2) {
                final Subscription subscription = list.get(i);
                n = i;
                n2 = size;
                if (subscription.subscriber == o) {
                    subscription.active = false;
                    list.remove(i);
                    n = i - 1;
                    n2 = size - 1;
                }
            }
        }
    }
    
    public void cancelEventDelivery(final Object o) {
        final PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        if (!postingThreadState.isPosting) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        }
        if (o == null) {
            throw new EventBusException("Event may not be null");
        }
        if (postingThreadState.event != o) {
            throw new EventBusException("Only the currently handled event may be aborted");
        }
        if (postingThreadState.subscription.subscriberMethod.threadMode != ThreadMode.PostThread) {
            throw new EventBusException(" event handlers may only abort the incoming event");
        }
        postingThreadState.canceled = true;
    }
    
    public void configureLogSubscriberExceptions(final boolean logSubscriberExceptions) {
        if (this.subscribed) {
            throw new EventBusException("This method must be called before any registration");
        }
        this.logSubscriberExceptions = logSubscriberExceptions;
    }
    
    public <T> T getStickyEvent(final Class<T> clazz) {
        synchronized (this.stickyEvents) {
            return clazz.cast(this.stickyEvents.get(clazz));
        }
    }
    
    void invokeSubscriber(final PendingPost pendingPost) {
        final Object event = pendingPost.event;
        final Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            this.invokeSubscriber(subscription, event);
        }
    }
    
    void invokeSubscriber(final Subscription subscription, final Object o) throws Error {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, o);
        }
        catch (InvocationTargetException ex) {
            final Throwable cause = ex.getCause();
            if (o instanceof SubscriberExceptionEvent) {
                Log.e(EventBus.TAG, "SubscriberExceptionEvent subscriber " + subscription.subscriber.getClass() + " threw an exception", cause);
                final SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent)o;
                Log.e(EventBus.TAG, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
                return;
            }
            if (this.logSubscriberExceptions) {
                Log.e(EventBus.TAG, "Could not dispatch event: " + o.getClass() + " to subscribing class " + subscription.subscriber.getClass(), cause);
            }
            this.post(new SubscriberExceptionEvent(this, cause, o, subscription.subscriber));
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalStateException("Unexpected exception", ex2);
        }
    }
    
    public boolean isRegistered(final Object o) {
        synchronized (this) {
            return this.typesBySubscriber.containsKey(o);
        }
    }
    
    public void post(final Object o) {
        final PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        final List<Object> eventQueue = postingThreadState.eventQueue;
        eventQueue.add(o);
        if (postingThreadState.isPosting) {
            return;
        }
        postingThreadState.isMainThread = (Looper.getMainLooper() == Looper.myLooper());
        postingThreadState.isPosting = true;
        if (postingThreadState.canceled) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        try {
            while (!eventQueue.isEmpty()) {
                this.postSingleEvent(eventQueue.remove(0), postingThreadState);
            }
        }
        finally {
            postingThreadState.isPosting = false;
            postingThreadState.isMainThread = false;
        }
        postingThreadState.isPosting = false;
        postingThreadState.isMainThread = false;
    }
    
    public void postSticky(final Object o) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(o.getClass(), o);
            // monitorexit(this.stickyEvents)
            this.post(o);
        }
    }
    
    public void register(final Object o) {
        this.register(o, "onEvent", false, 0);
    }
    
    public void register(final Object o, final int n) {
        this.register(o, "onEvent", false, n);
    }
    
    @Deprecated
    public void register(final Object o, final Class<?> clazz, final Class<?>... array) {
        this.register(o, "onEvent", false, clazz, array);
    }
    
    @Deprecated
    public void register(final Object o, final String s) {
        this.register(o, s, false, 0);
    }
    
    @Deprecated
    public void register(final Object o, final String s, final Class<?> clazz, final Class<?>... array) {
        this.register(o, s, false, clazz, array);
    }
    
    public void registerSticky(final Object o) {
        this.register(o, "onEvent", true, 0);
    }
    
    public void registerSticky(final Object o, final int n) {
        this.register(o, "onEvent", true, n);
    }
    
    @Deprecated
    public void registerSticky(final Object o, final Class<?> clazz, final Class<?>... array) {
        this.register(o, "onEvent", true, clazz, array);
    }
    
    @Deprecated
    public void registerSticky(final Object o, final String s) {
        this.register(o, s, true, 0);
    }
    
    @Deprecated
    public void registerSticky(final Object o, final String s, final Class<?> clazz, final Class<?>... array) {
        this.register(o, s, true, clazz, array);
    }
    
    public void removeAllStickyEvents() {
        synchronized (this.stickyEvents) {
            this.stickyEvents.clear();
        }
    }
    
    public <T> T removeStickyEvent(final Class<T> clazz) {
        synchronized (this.stickyEvents) {
            return clazz.cast(this.stickyEvents.remove(clazz));
        }
    }
    
    public boolean removeStickyEvent(final Object o) {
        synchronized (this.stickyEvents) {
            final Class<?> class1 = o.getClass();
            if (o.equals(this.stickyEvents.get(class1))) {
                this.stickyEvents.remove(class1);
                return true;
            }
            return false;
        }
    }
    
    public void unregister(final Object o) {
        // monitorexit(this)
        while (true) {
            final Throwable t;
            Label_0072: {
                synchronized (this) {
                    final List<Class<?>> list = this.typesBySubscriber.get(o);
                    if (list == null) {
                        break Label_0072;
                    }
                    final Iterator<Class<?>> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        this.unubscribeByEventType(o, iterator.next());
                    }
                }
                this.typesBySubscriber.remove(t);
                return;
            }
            Log.w(EventBus.TAG, "Subscriber to unregister was not registered before: " + t.getClass());
            continue;
        }
    }
    
    @Deprecated
    public void unregister(final Object o, final Class<?>... array) {
        synchronized (this) {
            if (array.length == 0) {
                throw new IllegalArgumentException("Provide at least one event class");
            }
        }
        final Throwable t;
        final List<Class<?>> list = this.typesBySubscriber.get(t);
        if (list != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final Class<?> clazz = array[i];
                this.unubscribeByEventType(t, clazz);
                list.remove(clazz);
            }
            if (list.isEmpty()) {
                this.typesBySubscriber.remove(t);
            }
        }
        else {
            Log.w(EventBus.TAG, "Subscriber to unregister was not registered before: " + t.getClass());
        }
    }
    // monitorexit(this)
    
    interface PostCallback
    {
        void onPostCompleted(final List<SubscriberExceptionEvent> p0);
    }
    
    static final class PostingThreadState
    {
        boolean canceled;
        Object event;
        List<Object> eventQueue;
        boolean isMainThread;
        boolean isPosting;
        Subscription subscription;
        
        PostingThreadState() {
            this.eventQueue = new ArrayList<Object>();
        }
    }
}
