// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics;

import android.os.Bundle;
import android.app.Activity;
import java.util.UUID;
import net.hockeyapp.android.metrics.model.SessionStateData;
import net.hockeyapp.android.metrics.model.SessionState;
import java.util.concurrent.RejectedExecutionException;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.metrics.model.Base;
import net.hockeyapp.android.metrics.model.EventData;
import android.os.AsyncTask;
import android.util.Log;
import android.text.TextUtils;
import java.util.Map;
import net.hockeyapp.android.utils.HockeyLog;
import android.annotation.TargetApi;
import android.app.Application$ActivityLifecycleCallbacks;
import net.hockeyapp.android.utils.Util;
import java.util.Date;
import net.hockeyapp.android.metrics.model.Domain;
import net.hockeyapp.android.metrics.model.Data;
import net.hockeyapp.android.metrics.model.TelemetryData;
import android.content.Context;
import android.app.Application;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;

public class MetricsManager
{
    protected static final AtomicInteger ACTIVITY_COUNT;
    protected static final AtomicLong LAST_BACKGROUND;
    private static final Object LOCK;
    private static final Integer SESSION_RENEWAL_INTERVAL;
    private static final String TAG = "HA-MetricsManager";
    private static volatile MetricsManager instance;
    private static Channel sChannel;
    private static Sender sSender;
    private static TelemetryContext sTelemetryContext;
    private static boolean sUserMetricsEnabled;
    private static WeakReference<Application> sWeakApplication;
    private volatile boolean mSessionTrackingDisabled;
    private TelemetryLifecycleCallbacks mTelemetryLifecycleCallbacks;
    
    static {
        MetricsManager.sUserMetricsEnabled = true;
        ACTIVITY_COUNT = new AtomicInteger(0);
        LAST_BACKGROUND = new AtomicLong(getTime());
        SESSION_RENEWAL_INTERVAL = 20000;
        LOCK = new Object();
    }
    
    protected MetricsManager(final Context context, final TelemetryContext sTelemetryContext, final Sender sender, Persistence persistence, final Channel sChannel) {
        MetricsManager.sTelemetryContext = sTelemetryContext;
        Sender sender2 = sender;
        if (sender == null) {
            sender2 = new Sender();
        }
        MetricsManager.sSender = sender2;
        if (persistence == null) {
            persistence = new Persistence(context, sender2);
        }
        else {
            persistence.setSender(sender2);
        }
        MetricsManager.sSender.setPersistence(persistence);
        if (sChannel == null) {
            MetricsManager.sChannel = new Channel(MetricsManager.sTelemetryContext, persistence);
        }
        else {
            MetricsManager.sChannel = sChannel;
        }
        if (persistence.hasFilesAvailable()) {
            persistence.getSender().triggerSending();
        }
    }
    
    protected static Data<Domain> createData(final TelemetryData baseData) {
        final Data<TelemetryData> data = (Data<TelemetryData>)new Data<Domain>();
        data.setBaseData(baseData);
        data.setBaseType(baseData.getBaseType());
        data.QualifiedName = baseData.getEnvelopeName();
        return (Data<Domain>)data;
    }
    
    public static void disableUserMetrics() {
        setUserMetricsEnabled(false);
    }
    
    public static void enableUserMetrics() {
        setUserMetricsEnabled(true);
    }
    
    private static Application getApplication() {
        Application application = null;
        if (MetricsManager.sWeakApplication != null) {
            application = MetricsManager.sWeakApplication.get();
        }
        return application;
    }
    
    protected static Channel getChannel() {
        return MetricsManager.sChannel;
    }
    
    protected static MetricsManager getInstance() {
        return MetricsManager.instance;
    }
    
    protected static Sender getSender() {
        return MetricsManager.sSender;
    }
    
    private static long getTime() {
        return new Date().getTime();
    }
    
    public static boolean isUserMetricsEnabled() {
        return MetricsManager.sUserMetricsEnabled;
    }
    
    public static void register(final Application application) {
        final String appIdentifier = Util.getAppIdentifier(application.getApplicationContext());
        if (appIdentifier == null || appIdentifier.length() == 0) {
            throw new IllegalArgumentException("HockeyApp app identifier was not configured correctly in manifest or build configuration.");
        }
        register(application, appIdentifier);
    }
    
    public static void register(final Application application, final String s) {
        register(application, s, null, null, null);
    }
    
    protected static void register(final Application p0, final String p1, final Sender p2, final Persistence p3, final Channel p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifnonnull       118
        //     6: getstatic       net/hockeyapp/android/metrics/MetricsManager.LOCK:Ljava/lang/Object;
        //     9: astore          7
        //    11: aload           7
        //    13: monitorenter   
        //    14: getstatic       net/hockeyapp/android/metrics/MetricsManager.instance:Lnet/hockeyapp/android/metrics/MetricsManager;
        //    17: astore          6
        //    19: aload           6
        //    21: ifnonnull       135
        //    24: aload_0        
        //    25: invokevirtual   android/app/Application.getApplicationContext:()Landroid/content/Context;
        //    28: invokestatic    net/hockeyapp/android/Constants.loadFromContext:(Landroid/content/Context;)V
        //    31: new             Lnet/hockeyapp/android/metrics/MetricsManager;
        //    34: dup            
        //    35: aload_0        
        //    36: invokevirtual   android/app/Application.getApplicationContext:()Landroid/content/Context;
        //    39: new             Lnet/hockeyapp/android/metrics/TelemetryContext;
        //    42: dup            
        //    43: aload_0        
        //    44: invokevirtual   android/app/Application.getApplicationContext:()Landroid/content/Context;
        //    47: aload_1        
        //    48: invokespecial   net/hockeyapp/android/metrics/TelemetryContext.<init>:(Landroid/content/Context;Ljava/lang/String;)V
        //    51: aload_2        
        //    52: aload_3        
        //    53: aload           4
        //    55: invokespecial   net/hockeyapp/android/metrics/MetricsManager.<init>:(Landroid/content/Context;Lnet/hockeyapp/android/metrics/TelemetryContext;Lnet/hockeyapp/android/metrics/Sender;Lnet/hockeyapp/android/metrics/Persistence;Lnet/hockeyapp/android/metrics/Channel;)V
        //    58: astore_1       
        //    59: new             Ljava/lang/ref/WeakReference;
        //    62: dup            
        //    63: aload_0        
        //    64: invokespecial   java/lang/ref/WeakReference.<init>:(Ljava/lang/Object;)V
        //    67: putstatic       net/hockeyapp/android/metrics/MetricsManager.sWeakApplication:Ljava/lang/ref/WeakReference;
        //    70: aload_1        
        //    71: astore_0       
        //    72: invokestatic    net/hockeyapp/android/utils/Util.sessionTrackingSupported:()Z
        //    75: ifne            119
        //    78: iconst_1       
        //    79: istore          5
        //    81: aload_0        
        //    82: iload           5
        //    84: putfield        net/hockeyapp/android/metrics/MetricsManager.mSessionTrackingDisabled:Z
        //    87: aload_0        
        //    88: putstatic       net/hockeyapp/android/metrics/MetricsManager.instance:Lnet/hockeyapp/android/metrics/MetricsManager;
        //    91: aload_0        
        //    92: getfield        net/hockeyapp/android/metrics/MetricsManager.mSessionTrackingDisabled:Z
        //    95: ifne            105
        //    98: iconst_0       
        //    99: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   102: invokestatic    net/hockeyapp/android/metrics/MetricsManager.setSessionTrackingDisabled:(Ljava/lang/Boolean;)V
        //   105: aload           7
        //   107: monitorexit    
        //   108: new             Lnet/hockeyapp/android/metrics/MetricsManager$1;
        //   111: dup            
        //   112: invokespecial   net/hockeyapp/android/metrics/MetricsManager$1.<init>:()V
        //   115: invokestatic    net/hockeyapp/android/PrivateEventManager.addEventListener:(Lnet/hockeyapp/android/PrivateEventManager$HockeyEventListener;)V
        //   118: return         
        //   119: iconst_0       
        //   120: istore          5
        //   122: goto            81
        //   125: astore_0       
        //   126: aload           7
        //   128: monitorexit    
        //   129: aload_0        
        //   130: athrow         
        //   131: astore_0       
        //   132: goto            126
        //   135: aload           6
        //   137: astore_0       
        //   138: goto            72
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  14     19     125    126    Any
        //  24     59     131    135    Any
        //  59     70     125    126    Any
        //  72     78     125    126    Any
        //  81     105    125    126    Any
        //  105    108    125    126    Any
        //  126    129    125    126    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
    
    @Deprecated
    public static void register(final Context context, final Application application) {
        final String appIdentifier = Util.getAppIdentifier(context);
        if (appIdentifier == null || appIdentifier.length() == 0) {
            throw new IllegalArgumentException("HockeyApp app identifier was not configured correctly in manifest or build configuration.");
        }
        register(context, application, appIdentifier);
    }
    
    @Deprecated
    public static void register(final Context context, final Application application, final String s) {
        register(application, s, null, null, null);
    }
    
    @TargetApi(14)
    private void registerTelemetryLifecycleCallbacks() {
        if (this.mTelemetryLifecycleCallbacks == null) {
            this.mTelemetryLifecycleCallbacks = new TelemetryLifecycleCallbacks();
        }
        getApplication().registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.mTelemetryLifecycleCallbacks);
    }
    
    public static boolean sessionTrackingEnabled() {
        return isUserMetricsEnabled() && !MetricsManager.instance.mSessionTrackingDisabled;
    }
    
    public static void setCustomServerURL(final String customServerURL) {
        if (MetricsManager.sSender != null) {
            MetricsManager.sSender.setCustomServerURL(customServerURL);
            return;
        }
        HockeyLog.warn("HA-MetricsManager", "HockeyApp couldn't set the custom server url. Please register(...) the MetricsManager before setting the server URL.");
    }
    
    protected static void setSender(final Sender sSender) {
        MetricsManager.sSender = sSender;
    }
    
    public static void setSessionTrackingDisabled(final Boolean b) {
        if (MetricsManager.instance == null || !isUserMetricsEnabled()) {
            HockeyLog.warn("HA-MetricsManager", "MetricsManager hasn't been registered or User Metrics has been disabled. No User Metrics will be collected!");
            return;
        }
        while (true) {
            synchronized (MetricsManager.LOCK) {
                if (Util.sessionTrackingSupported()) {
                    MetricsManager.instance.mSessionTrackingDisabled = b;
                    if (!b) {
                        MetricsManager.instance.registerTelemetryLifecycleCallbacks();
                    }
                    return;
                }
            }
            MetricsManager.instance.mSessionTrackingDisabled = true;
            MetricsManager.instance.unregisterTelemetryLifecycleCallbacks();
        }
    }
    
    private static void setUserMetricsEnabled(final boolean sUserMetricsEnabled) {
        MetricsManager.sUserMetricsEnabled = sUserMetricsEnabled;
        if (MetricsManager.sUserMetricsEnabled) {
            MetricsManager.instance.registerTelemetryLifecycleCallbacks();
            return;
        }
        MetricsManager.instance.unregisterTelemetryLifecycleCallbacks();
    }
    
    public static void trackEvent(final String s) {
        trackEvent(s, null);
    }
    
    public static void trackEvent(final String s, final Map<String, String> map) {
        trackEvent(s, map, null);
    }
    
    public static void trackEvent(final String s, final Map<String, String> map, final Map<String, Double> map2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        if (MetricsManager.instance == null) {
            Log.w("HA-MetricsManager", "MetricsManager hasn't been registered or User Metrics has been disabled. No User Metrics will be collected!");
            return;
        }
        if (!isUserMetricsEnabled()) {
            HockeyLog.warn("User Metrics is disabled. Will not track event.");
            return;
        }
        try {
            AsyncTaskUtils.execute(new AsyncTask<Void, Void, Void>() {
                protected Void doInBackground(final Void... array) {
                    final EventData eventData = new EventData();
                    eventData.setName(s);
                    if (map != null) {
                        eventData.setProperties(map);
                    }
                    if (map2 != null) {
                        eventData.setMeasurements(map2);
                    }
                    MetricsManager.sChannel.enqueueData(MetricsManager.createData(eventData));
                    return null;
                }
            });
        }
        catch (RejectedExecutionException ex) {
            HockeyLog.error("Could not track custom event. Executor rejected async task.", ex);
        }
    }
    
    private void trackSessionState(final SessionState sessionState) {
        try {
            AsyncTaskUtils.execute(new AsyncTask<Void, Void, Void>() {
                protected Void doInBackground(final Void... array) {
                    final SessionStateData sessionStateData = new SessionStateData();
                    sessionStateData.setState(sessionState);
                    MetricsManager.sChannel.enqueueData(MetricsManager.createData(sessionStateData));
                    return null;
                }
            });
        }
        catch (RejectedExecutionException ex) {
            HockeyLog.error("Could not track session state. Executor rejected async task.", ex);
        }
    }
    
    @TargetApi(14)
    private void unregisterTelemetryLifecycleCallbacks() {
        if (this.mTelemetryLifecycleCallbacks == null) {
            return;
        }
        getApplication().unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.mTelemetryLifecycleCallbacks);
        this.mTelemetryLifecycleCallbacks = null;
    }
    
    private void updateSession() {
        if (MetricsManager.ACTIVITY_COUNT.getAndIncrement() == 0) {
            if (!sessionTrackingEnabled()) {
                HockeyLog.debug("HA-MetricsManager", "Session management disabled by the developer");
                return;
            }
            HockeyLog.debug("HA-MetricsManager", "Starting & tracking session");
            this.renewSession();
        }
        else {
            final long time = getTime();
            final long andSet = MetricsManager.LAST_BACKGROUND.getAndSet(getTime());
            boolean b;
            if (time - andSet >= MetricsManager.SESSION_RENEWAL_INTERVAL) {
                b = true;
            }
            else {
                b = false;
            }
            HockeyLog.debug("HA-MetricsManager", "Checking if we have to renew a session, time difference is: " + (time - andSet));
            if (b && sessionTrackingEnabled()) {
                HockeyLog.debug("HA-MetricsManager", "Renewing session");
                this.renewSession();
            }
        }
    }
    
    protected void renewSession() {
        MetricsManager.sTelemetryContext.renewSessionContext(UUID.randomUUID().toString());
        this.trackSessionState(SessionState.START);
    }
    
    protected void setChannel(final Channel sChannel) {
        MetricsManager.sChannel = sChannel;
    }
    
    @TargetApi(14)
    private class TelemetryLifecycleCallbacks implements Application$ActivityLifecycleCallbacks
    {
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityDestroyed(final Activity activity) {
        }
        
        public void onActivityPaused(final Activity activity) {
            MetricsManager.LAST_BACKGROUND.set(getTime());
        }
        
        public void onActivityResumed(final Activity activity) {
            MetricsManager.this.updateSession();
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
        }
        
        public void onActivityStopped(final Activity activity) {
        }
    }
}
