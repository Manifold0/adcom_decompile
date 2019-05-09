// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import com.chartboost.sdk.impl.ac;
import java.util.concurrent.ScheduledExecutorService;
import com.chartboost.sdk.impl.o;
import com.chartboost.sdk.impl.s;
import android.text.TextUtils;
import android.content.Context;
import com.chartboost.sdk.impl.aq;
import com.chartboost.sdk.Tracking.a;
import android.app.Activity;
import com.chartboost.sdk.Libraries.CBLogging;

class f implements Runnable
{
    boolean a;
    Chartboost.CBFramework b;
    Chartboost.CBMediation c;
    String d;
    String e;
    CBLogging.Level f;
    ChartboostDelegate g;
    Activity h;
    String i;
    String j;
    private final int k;
    
    f(final int k) {
        this.a = false;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = k;
    }
    
    @Override
    public void run() {
        Label_0247: {
            Label_0230: {
                Label_0216: {
                    Label_0208: {
                        Label_0200: {
                            Label_0144: {
                                try {
                                    switch (this.k) {
                                        case 1: {
                                            com.chartboost.sdk.i.u = this.a;
                                            return;
                                        }
                                        case 3: {
                                            break;
                                        }
                                        case 4: {
                                            break Label_0144;
                                        }
                                        case 5: {
                                            break Label_0200;
                                        }
                                        case 6: {
                                            break Label_0208;
                                        }
                                        case 7: {
                                            break Label_0216;
                                        }
                                        case 8: {
                                            break Label_0230;
                                        }
                                        case 0: {
                                            break Label_0247;
                                        }
                                        default: {
                                            return;
                                        }
                                    }
                                }
                                catch (Exception ex) {
                                    com.chartboost.sdk.Tracking.a.a(f.class, "run (" + this.k + ")", ex);
                                    return;
                                }
                                com.chartboost.sdk.i.i = this.c;
                                com.chartboost.sdk.i.j = this.d;
                                com.chartboost.sdk.i.h = com.chartboost.sdk.i.i + " " + com.chartboost.sdk.i.j;
                                return;
                            }
                            if (this.b == null) {
                                CBLogging.b("ChartboostCommand", "Pass a valid CBFramework enum value");
                                return;
                            }
                            com.chartboost.sdk.i.d = this.b;
                            com.chartboost.sdk.i.e = this.d;
                            com.chartboost.sdk.i.f = String.format("%s %s", this.b, this.d);
                            return;
                        }
                        com.chartboost.sdk.b.a(this.d);
                        return;
                    }
                    com.chartboost.sdk.i.a = this.e;
                    return;
                }
                if (com.chartboost.sdk.b.b()) {
                    CBLogging.a = this.f;
                    return;
                }
                return;
            }
            com.chartboost.sdk.i.c = this.g;
            aq.a("SdkSettings.assignDelegate", this.g);
            return;
        }
        if (com.chartboost.sdk.h.a() != null) {
            return;
        }
        Label_0452: {
            synchronized (h.class) {
                if (com.chartboost.sdk.h.a() != null) {
                    break Label_0452;
                }
                if (this.h == null) {
                    CBLogging.b("ChartboostCommand", "Activity object is null. Please pass a valid activity object");
                    return;
                }
            }
            if (!com.chartboost.sdk.b.a((Context)this.h)) {
                CBLogging.b("ChartboostCommand", "Permissions not set correctly");
                // monitorexit(h.class)
                return;
            }
            if (!com.chartboost.sdk.b.b((Context)this.h)) {
                CBLogging.b("ChartboostCommand", "Please add CBImpressionActivity in AndroidManifest.xml following README.md instructions.");
            }
            if (TextUtils.isEmpty((CharSequence)this.i) || TextUtils.isEmpty((CharSequence)this.j)) {
                CBLogging.b("ChartboostCommand", "AppId or AppSignature is null. Please pass a valid id's");
                // monitorexit(h.class)
                return;
            }
            final s a = s.a();
            final g a2 = com.chartboost.sdk.g.a();
            final Handler a3 = a.a;
            o.a();
            while (true) {
                try {
                    final ScheduledExecutorService scheduledExecutorService = a2.a(ac.a());
                    try {
                        final h h = new h(this.h, this.i, this.j, a, scheduledExecutorService, a3, a2.a(ac.a(4)));
                        com.chartboost.sdk.h.a(h);
                        h.b.c();
                        h.getClass();
                        h.a(h.new a(3));
                        // monitorexit(h.class)
                        return;
                        Label_0478: {
                            final Throwable t;
                            CBLogging.a("ChartboostCommand", "Unable to start threads", t);
                        }
                        // monitorexit(h.class)
                        return;
                        // iftrue(Label_0466:, scheduledExecutorService == null)
                        // iftrue(Label_0478:, !false)
                        while (true) {
                            Block_18: {
                                break Block_18;
                                throw new NullPointerException();
                            }
                            scheduledExecutorService.shutdown();
                            continue;
                        }
                    }
                    catch (Throwable t) {
                        continue;
                    }
                }
                catch (Throwable t) {
                    final ScheduledExecutorService scheduledExecutorService = null;
                    continue;
                }
                break;
            }
        }
    }
}
