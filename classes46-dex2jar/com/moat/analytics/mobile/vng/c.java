// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.Locale;
import org.json.JSONObject;
import java.util.HashMap;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import android.os.Handler;
import java.util.Map;

abstract class c<PlayerOrIMAAd> extends b
{
    static final MoatAdEventType[] f;
    final Map<MoatAdEventType, Integer> g;
    final Handler h;
    Map<String, String> i;
    WeakReference<PlayerOrIMAAd> j;
    WeakReference<View> k;
    private boolean l;
    private Double m;
    private final g n;
    private final String o;
    
    static {
        f = new MoatAdEventType[] { MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE };
    }
    
    c(final String o) {
        super(null, false, true);
        p.a(3, "BaseVideoTracker", this, "Initializing.");
        this.o = o;
        (this.n = new g((Context)com.moat.analytics.mobile.vng.a.a(), com.moat.analytics.mobile.vng.g.a.b)).a(o);
        super.a(this.n.b);
        super.a(this.n.a);
        this.g = new HashMap<MoatAdEventType, Integer>();
        this.h = new Handler();
        this.l = false;
        this.m = 1.0;
    }
    
    private void b(final MoatAdEvent moatAdEvent) {
        final JSONObject a = this.a(moatAdEvent);
        p.a(3, "BaseVideoTracker", this, String.format("Received event: %s", a.toString()));
        p.a("[SUCCESS] ", this.a() + String.format(" Received event: %s", a.toString()));
        this.a.a(this.n.c, a);
        final MoatAdEventType d = moatAdEvent.d;
        if (d == MoatAdEventType.AD_EVT_COMPLETE || d == MoatAdEventType.AD_EVT_STOPPED || d == MoatAdEventType.AD_EVT_SKIPPED) {
            this.g.put(d, 1);
            this.h();
        }
    }
    
    private void j() {
        final Map<String, Object> g = this.g();
        final Integer n = g.get("width");
        final Integer n2 = g.get("height");
        final Integer n3 = g.get("duration");
        p.a(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", n2, n, n3));
        this.n.a(this.o, this.i, n, n2, n3);
        super.changeTargetView(this.k.get());
        super.b();
    }
    
    JSONObject a(final MoatAdEvent moatAdEvent) {
        while (true) {
            if (!Double.isNaN(moatAdEvent.c)) {
                break Label_0023;
            }
            try {
                moatAdEvent.c = s.a();
                p.a(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "adVolume before adjusting for player volume %f", moatAdEvent.c));
                moatAdEvent.c *= this.m;
                p.a(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "adVolume after adjusting for player volume %f", moatAdEvent.c));
                return new JSONObject((Map)moatAdEvent.a());
            }
            catch (Exception ex) {
                moatAdEvent.c = 1.0;
                continue;
            }
            break;
        }
    }
    
    boolean a(final Integer n, final Integer n2) {
        return Math.abs(n2 - n) <= Math.min(750.0, n2 * 0.05);
    }
    
    public boolean a(final Map<String, String> i, final PlayerOrIMAAd playerOrIMAAd, final View view) {
    Label_0429_Outer:
        while (true) {
            int n = 1;
            while (true) {
                while (true) {
                    try {
                        if (this.e) {
                            p.a(3, "BaseVideoTracker", this, "trackVideoAd already called");
                            p.a("[ERROR] ", this.a() + " trackVideoAd can't be called twice");
                            n = 0;
                        }
                        if (i == null) {
                            p.a(3, "BaseVideoTracker", this, "trackVideoAd received null adIds object. Not tracking.");
                            p.a("[ERROR] ", this.a() + " trackVideoAd failed, received null adIds object");
                            n = 0;
                        }
                        if (view == null) {
                            p.a(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
                        }
                        if (playerOrIMAAd == null) {
                            p.a(3, "BaseVideoTracker", this, "trackVideoAd received null ad instance. Not tracking.");
                            p.a("[ERROR] ", this.a() + " trackVideoAd failed, received null ad instance");
                            n = 0;
                        }
                        boolean b = n != 0;
                        if (n != 0) {
                            final String string = new JSONObject((Map)i).toString();
                            final String string2 = playerOrIMAAd.toString();
                            String string3;
                            if (view != null) {
                                string3 = view.getClass().getSimpleName() + "@" + view.hashCode();
                            }
                            else {
                                string3 = "null";
                            }
                            p.a(3, "BaseVideoTracker", this, String.format("trackVideoAd tracking ids: %s | ad: %s | view: %s", string, string2, string3));
                            final StringBuilder append = new StringBuilder().append(this.a());
                            final String string4 = new JSONObject((Map)i).toString();
                            final String string5 = playerOrIMAAd.toString();
                            String string6;
                            if (view != null) {
                                string6 = view.getClass().getSimpleName() + "@" + view.hashCode();
                            }
                            else {
                                string6 = "null";
                            }
                            p.a("[SUCCESS] ", append.append(String.format(" trackVideoAd succeeded with ids: %s | ad: %s | view: %s", string4, string5, string6)).toString());
                            this.i = i;
                            this.j = new WeakReference<PlayerOrIMAAd>(playerOrIMAAd);
                            this.k = new WeakReference<View>(view);
                            this.j();
                            b = (n != 0);
                        }
                        final StringBuilder append2 = new StringBuilder().append("Attempt to start tracking ad was ");
                        if (b) {
                            final String s = "";
                            p.a(3, "BaseVideoTracker", this, append2.append(s).append("successful.").toString());
                            return b;
                        }
                    }
                    catch (Exception ex) {
                        com.moat.analytics.mobile.vng.m.a(ex);
                        final boolean b = false;
                        continue Label_0429_Outer;
                    }
                    break;
                }
                final String s = "un";
                continue;
            }
        }
    }
    
    @Override
    public void changeTargetView(final View view) {
        Label_0083: {
            if (view == null) {
                break Label_0083;
            }
            String string = view.getClass().getSimpleName() + "@" + view.hashCode();
            while (true) {
                p.a(3, "BaseVideoTracker", this, "changing view to " + string);
                this.k = new WeakReference<View>(view);
                try {
                    super.changeTargetView(view);
                    return;
                    string = "null";
                }
                catch (Exception ex) {
                    com.moat.analytics.mobile.vng.m.a(ex);
                }
            }
        }
    }
    
    public void dispatchEvent(final MoatAdEvent moatAdEvent) {
        try {
            this.b(moatAdEvent);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
    
    protected abstract Map<String, Object> g();
    
    void h() {
        if (!this.l) {
            this.h.postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        p.a(3, "BaseVideoTracker", this, "Shutting down.");
                        c.this.n.a();
                    }
                    catch (Exception ex) {
                        com.moat.analytics.mobile.vng.m.a(ex);
                    }
                }
            }, 500L);
            this.l = true;
        }
    }
    
    boolean i() {
        return this.g.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.g.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.g.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }
    
    public void setPlayerVolume(final Double m) {
        if (!m.equals(this.m)) {
            p.a(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", m));
            this.m = m;
            this.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.a));
        }
    }
    
    @Override
    public void stopTracking() {
        while (true) {
            while (true) {
                try {
                    final boolean c = super.c();
                    if (c) {
                        final String s = "[SUCCESS] ";
                        final StringBuilder append = new StringBuilder().append(this.a()).append(" stopTracking ");
                        String s2;
                        if (c) {
                            s2 = "succeeded";
                        }
                        else {
                            s2 = "failed";
                        }
                        p.a(s, append.append(s2).append(" for ").append(this.e()).toString());
                        this.h();
                        return;
                    }
                }
                catch (Exception ex) {
                    com.moat.analytics.mobile.vng.m.a(ex);
                    return;
                }
                final String s = "[ERROR] ";
                continue;
            }
        }
    }
}
