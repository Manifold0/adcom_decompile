// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.et;
import com.tapjoy.internal.gc;
import java.io.Serializable;
import android.content.Intent;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.gh;
import com.tapjoy.internal.fy;
import java.util.Map;
import java.util.HashMap;
import android.os.SystemClock;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.eq;
import java.util.UUID;
import android.content.Context;

public class TJPlacement
{
    TJPlacementListener a;
    private TJCorePlacement b;
    private TJPlacementListener c;
    private TJPlacementVideoListener d;
    private String e;
    public String pushId;
    
    @Deprecated
    public TJPlacement(final Context context, final String s, final TJPlacementListener tjPlacementListener) {
        TJCorePlacement tjCorePlacement;
        if ((tjCorePlacement = TJPlacementManager.a(s)) == null) {
            tjCorePlacement = TJPlacementManager.a(s, "", "", false);
        }
        tjCorePlacement.setContext(context);
        this.a(tjCorePlacement, tjPlacementListener);
    }
    
    TJPlacement(final TJCorePlacement tjCorePlacement, final TJPlacementListener tjPlacementListener) {
        this.a(tjCorePlacement, tjPlacementListener);
    }
    
    private void a(final TJCorePlacement b, final TJPlacementListener c) {
        this.b = b;
        this.e = UUID.randomUUID().toString();
        this.c = c;
        TJPlacementListener a;
        if (c != null) {
            a = (TJPlacementListener)eq.a(c, TJPlacementListener.class);
        }
        else {
            a = null;
        }
        this.a = a;
        FiveRocksIntegration.addPlacementCallback(this.getName(), this);
    }
    
    private void a(final TJError tjError) {
        this.b.a(this, TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, tjError);
    }
    
    public static void dismissContent() {
        final String connectFlagValue = TapjoyConnectCore.getConnectFlagValue("TJC_OPTION_DISMISS_CONTENT_ALL");
        boolean b = false;
        if ("true".equals(connectFlagValue)) {
            b = true;
        }
        TJPlacementManager.dismissContentShowing(b);
    }
    
    public String getGUID() {
        return this.e;
    }
    
    public TJPlacementListener getListener() {
        return this.c;
    }
    
    public String getName() {
        if (this.b.getPlacementData() != null) {
            return this.b.getPlacementData().getPlacementName();
        }
        return "";
    }
    
    public TJPlacementVideoListener getVideoListener() {
        return this.d;
    }
    
    public boolean isContentAvailable() {
        this.b.f.a(1);
        return this.b.isContentAvailable();
    }
    
    public boolean isContentReady() {
        final boolean contentReady = this.b.isContentReady();
        final ez f = this.b.f;
        if (contentReady) {
            f.a(4);
            return contentReady;
        }
        f.a(2);
        return contentReady;
    }
    
    public void requestContent() {
        final String name = this.getName();
        TapjoyLog.i("TJPlacement", "requestContent() called for placement " + name);
        fi.a("TJPlacement.requestContent").a("placement", name).a("placement_type", this.b.c.getPlacementType());
        if (!TapjoyConnectCore.isConnected()) {
            fi.b("TJPlacement.requestContent").b("not connected").c();
            this.a(new TJError(0, "SDK not connected -- connect must be called first with a successful callback"));
            return;
        }
        if (this.b.getContext() == null) {
            fi.b("TJPlacement.requestContent").b("no context").c();
            this.a(new TJError(0, "Context is null -- TJPlacement requires a valid Context."));
            return;
        }
        if (ct.c(name)) {
            fi.b("TJPlacement.requestContent").b("invalid name").c();
            this.a(new TJError(0, "Invalid placement name -- TJPlacement requires a valid placement name."));
            return;
        }
        while (true) {
            final TJCorePlacement tjCorePlacement;
            Label_0333: {
                Label_0300: {
                    try {
                        final TJCorePlacement b = this.b;
                        if (this == null) {
                            b.a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "Cannot request content from a NULL placement"));
                        }
                        else {
                            b.a("REQUEST", this);
                            if (b.e - SystemClock.elapsedRealtime() <= 0L) {
                                break Label_0333;
                            }
                            TapjoyLog.d(TJCorePlacement.a, "Content has not expired yet for " + b.c.getPlacementName());
                            if (!b.l) {
                                break Label_0300;
                            }
                            fi.b("TJPlacement.requestContent").a("content_type", b.b()).a("from", "cache").c();
                            b.k = false;
                            b.a(this);
                            b.c();
                        }
                        return;
                    }
                    finally {
                        fi.d("TJPlacement.requestContent");
                    }
                }
                fi.b("TJPlacement.requestContent").a("content_type", "none").a("from", "cache").c();
                tjCorePlacement.a(this);
                return;
            }
            if (tjCorePlacement.l) {
                fi.c("TJPlacement.requestContent").a("was_available", true);
            }
            if (tjCorePlacement.m) {
                fi.c("TJPlacement.requestContent").a("was_ready", true);
            }
            if (ct.c(tjCorePlacement.p)) {
                tjCorePlacement.a();
                return;
            }
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("mediation_agent", tjCorePlacement.p);
            hashMap.put("mediation_id", tjCorePlacement.q);
            tjCorePlacement.a(tjCorePlacement.c.getMediationURL(), hashMap);
        }
    }
    
    public void setAdapterVersion(final String o) {
        this.b.o = o;
    }
    
    public void setMediationId(final String q) {
        this.b.q = q;
    }
    
    public void setMediationName(String string) {
        TapjoyLog.d("TJPlacement", "setMediationName=" + string);
        if (!ct.c(string)) {
            Context context;
            if (this.b != null) {
                context = this.b.getContext();
            }
            else {
                context = null;
            }
            this.b = TJPlacementManager.a(this.getName(), string, "", false);
            final TJCorePlacement b = this.b;
            b.p = string;
            b.n = string;
            b.c.setPlacementType(string);
            string = TapjoyConnectCore.getPlacementURL() + "v1/apps/" + TapjoyConnectCore.getAppID() + "/mediation_content?";
            b.c.setMediationURL(string);
            if (context != null) {
                this.b.setContext(context);
            }
        }
    }
    
    public void setVideoListener(final TJPlacementVideoListener d) {
        this.d = d;
    }
    
    public void showContent() {
        int n = 1;
        TapjoyLog.i("TJPlacement", "showContent() called for placement " + this.getName());
        final TJCorePlacement b = this.b;
        fi.a("TJPlacement.showContent").a("placement", b.c.getPlacementName()).a("placement_type", b.c.getPlacementType()).a("content_type", b.b());
        final ez f = b.f;
        f.a(8);
        final et a = f.a;
        if (a != null) {
            a.a();
        }
        if (!this.b.isContentAvailable()) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "No placement content available. Can not show content for non-200 placement."));
            fi.b("TJPlacement.showContent").b("no content").c();
            return;
        }
        while (true) {
            Label_0221: {
                try {
                    final TJCorePlacement b2 = this.b;
                    if (this == null) {
                        b2.a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "Cannot show content from a NULL placement"));
                    }
                    else {
                        if (!TapjoyConnectCore.isFullScreenViewOpen()) {
                            break Label_0221;
                        }
                        TapjoyLog.w(TJCorePlacement.a, "Only one view can be presented at a time.");
                        fi.b("TJPlacement.showContent").b("another content showing").c();
                    }
                    return;
                }
                finally {
                    fi.d("TJPlacement.showContent");
                }
            }
            if (TapjoyConnectCore.isViewOpen()) {
                TapjoyLog.w(TJCorePlacement.a, "Will close N2E content.");
                TJPlacementManager.dismissContentShowing(false);
            }
            final TJCorePlacement tjCorePlacement;
            tjCorePlacement.a("SHOW", this);
            final fi.a d = fi.d("TJPlacement.showContent");
            if (tjCorePlacement.g.isPrerendered()) {
                d.a("prerendered", true);
            }
            if (tjCorePlacement.isContentReady()) {
                d.a("content_ready", true);
            }
            tjCorePlacement.f.d = d;
            final String string = UUID.randomUUID().toString();
            if (tjCorePlacement.i != null) {
                tjCorePlacement.i.f = string;
                if (tjCorePlacement.i != null) {
                    if (tjCorePlacement.i instanceof fy) {
                        n = 3;
                    }
                    else if (tjCorePlacement.i instanceof gh) {
                        n = 2;
                    }
                    else {
                        n = 0;
                    }
                }
                TapjoyConnectCore.viewWillOpen(string, n);
                tjCorePlacement.i.e = new fv() {
                    @Override
                    public final void a(final Context context, final String s, final String httpResponse) {
                        if (httpResponse == null) {
                            TJCorePlacement.h(TJCorePlacement.this).setRedirectURL(s);
                        }
                        else {
                            TJCorePlacement.h(TJCorePlacement.this).setBaseURL(s);
                            TJCorePlacement.h(TJCorePlacement.this).setHttpResponse(httpResponse);
                        }
                        TJCorePlacement.h(TJCorePlacement.this).setHasProgressSpinner(true);
                        TJCorePlacement.h(TJCorePlacement.this).setContentViewId(string);
                        final Intent intent = new Intent(TJCorePlacement.g(TJCorePlacement.this), (Class)TJAdUnitActivity.class);
                        intent.putExtra("placement_data", (Serializable)TJCorePlacement.h(TJCorePlacement.this));
                        intent.setFlags(268435456);
                        context.startActivity(intent);
                    }
                };
                gc.a(new Runnable() {
                    @Override
                    public final void run() {
                        TJCorePlacement.m(TJCorePlacement.this).a(gc.a().p, TJCorePlacement.this.f);
                    }
                });
            }
            else {
                tjCorePlacement.c.setContentViewId(string);
                final Intent intent = new Intent(tjCorePlacement.b, (Class)TJAdUnitActivity.class);
                intent.putExtra("placement_data", (Serializable)tjCorePlacement.c);
                intent.setFlags(268435456);
                tjCorePlacement.b.startActivity(intent);
            }
            tjCorePlacement.e = 0L;
            tjCorePlacement.l = false;
            tjCorePlacement.m = false;
        }
    }
}
