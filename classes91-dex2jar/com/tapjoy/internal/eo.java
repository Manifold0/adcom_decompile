// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TapjoyUtil;
import com.tapjoy.TapjoyLog;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import android.os.Bundle;
import android.content.Context;
import android.os.Build$VERSION;
import android.app.Activity;
import java.util.HashSet;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;

public final class eo
{
    private static final eo a;
    private Application b;
    private Application$ActivityLifecycleCallbacks c;
    private final HashSet d;
    
    static {
        a = new eo();
    }
    
    public eo() {
        this.d = new HashSet();
    }
    
    public static void a() {
        if (Build$VERSION.SDK_INT >= 14) {
            final eo a = eo.a;
            if (a.b != null) {
                synchronized (a) {
                    if (a.c != null) {
                        a.b.unregisterActivityLifecycleCallbacks(a.c);
                        a.c = null;
                    }
                }
            }
        }
    }
    
    public static void a(Context applicationContext) {
        if (Build$VERSION.SDK_INT >= 14 && applicationContext != null) {
            final eo a = eo.a;
            applicationContext = applicationContext.getApplicationContext();
            Label_0051: {
                if (a.b != null) {
                    break Label_0051;
                }
                while (true) {
                    while (true) {
                        try {
                            Label_0118: {
                                if (!(applicationContext instanceof Application)) {
                                    break Label_0118;
                                }
                                a.b = (Application)applicationContext;
                                if (a.b == null) {
                                    break;
                                }
                                synchronized (a) {
                                    if (a.c == null) {
                                        final Activity c = d.c();
                                        if (c != null) {
                                            a.d.add(b(c));
                                        }
                                        a.c = (Application$ActivityLifecycleCallbacks)new Application$ActivityLifecycleCallbacks() {
                                            final /* synthetic */ HashSet a = a.d;
                                            
                                            public final void onActivityCreated(final Activity activity, final Bundle bundle) {
                                            }
                                            
                                            public final void onActivityDestroyed(final Activity activity) {
                                            }
                                            
                                            public final void onActivityPaused(final Activity activity) {
                                            }
                                            
                                            public final void onActivityResumed(final Activity activity) {
                                            }
                                            
                                            public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
                                            }
                                            
                                            public final void onActivityStarted(final Activity activity) {
                                                this.a.add(b(activity));
                                                if (this.a.size() == 1) {
                                                    fq.a();
                                                }
                                                com.tapjoy.internal.d.a(activity);
                                            }
                                            
                                            public final void onActivityStopped(final Activity activity) {
                                                this.a.remove(b(activity));
                                                if (this.a.size() <= 0) {
                                                    fq.b();
                                                }
                                            }
                                        };
                                        a.b.registerActivityLifecycleCallbacks(a.c);
                                        fq.a();
                                    }
                                    return;
                                }
                            }
                            final CountDownLatch countDownLatch = new CountDownLatch(1);
                            TapjoyUtil.runOnMainThread(new Runnable() {
                                @Override
                                public final void run() {
                                    try {
                                        eo.this.b = eo.b();
                                    }
                                    catch (Exception ex) {
                                        TapjoyLog.w("Tapjoy.ActivityTracker", Log.getStackTraceString((Throwable)ex));
                                    }
                                    finally {
                                        countDownLatch.countDown();
                                    }
                                }
                            });
                            countDownLatch.await();
                            continue;
                        }
                        catch (Exception ex) {
                            TapjoyLog.w("Tapjoy.ActivityTracker", Log.getStackTraceString((Throwable)ex));
                            continue;
                        }
                        continue;
                    }
                }
            }
        }
    }
    
    static /* synthetic */ Application b() {
        return (Application)Class.forName("android.app.ActivityThread").getMethod("currentApplication", (Class<?>[])new Class[0]).invoke(null, (Object[])null);
    }
    
    private static String b(final Activity activity) {
        return activity.getClass().getName() + "@" + System.identityHashCode(activity);
    }
}
