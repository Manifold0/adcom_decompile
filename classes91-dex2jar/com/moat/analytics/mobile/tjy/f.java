// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.view.View;
import android.util.Log;
import org.json.JSONObject;
import android.media.AudioManager;
import java.util.HashMap;
import java.lang.ref.WeakReference;
import android.os.Handler;
import java.util.Map;

abstract class f
{
    protected static final MoatAdEventType[] b;
    protected boolean a;
    protected final Map c;
    protected final Handler d;
    protected Map e;
    protected WeakReference f;
    protected WeakReference g;
    protected final a h;
    protected final ap i;
    private boolean j;
    private WeakReference k;
    private ad l;
    
    static {
        b = new MoatAdEventType[] { MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE };
    }
    
    public f(final String s, final a h, final ap i) {
        this.i = i;
        this.h = h;
        this.a("Initializing.");
        this.l = new ad(s, i, h);
        this.c = new HashMap();
        this.d = new Handler();
        this.k = new WeakReference((T)h.c());
        this.j = false;
        this.a = false;
    }
    
    private int a(final AudioManager audioManager) {
        return audioManager.getStreamVolume(3);
    }
    
    private MoatAdEvent a(final Map map) {
        final MoatAdEventType fromString = MoatAdEventType.fromString(map.get("type"));
        Integer time_UNAVAILABLE;
        if (map.containsKey("playHead")) {
            time_UNAVAILABLE = (Integer)map.get("playHead");
        }
        else {
            time_UNAVAILABLE = MoatAdEvent.TIME_UNAVAILABLE;
        }
        Double volume_UNAVAILABLE;
        if (map.containsKey("adVolume")) {
            volume_UNAVAILABLE = (Double)map.get("adVolume");
        }
        else {
            volume_UNAVAILABLE = MoatAdEvent.VOLUME_UNAVAILABLE;
        }
        return new MoatAdEvent(fromString, time_UNAVAILABLE, volume_UNAVAILABLE);
    }
    
    private void b(final MoatAdEvent moatAdEvent) {
        final JSONObject a = this.a(moatAdEvent);
        this.a(String.format("Received event: %s", a.toString()));
        this.l.a(a);
        final MoatAdEventType eventType = moatAdEvent.eventType;
        if (eventType == MoatAdEventType.AD_EVT_COMPLETE || eventType == MoatAdEventType.AD_EVT_STOPPED || eventType == MoatAdEventType.AD_EVT_SKIPPED) {
            this.c.put(eventType, 1);
            this.c();
        }
    }
    
    protected abstract Map a();
    
    protected JSONObject a(final MoatAdEvent moatAdEvent) {
        Label_0024: {
            if (!Double.isNaN(moatAdEvent.adVolume)) {
                break Label_0024;
            }
            try {
                moatAdEvent.adVolume = this.d();
                return new JSONObject(moatAdEvent.toMap());
            }
            catch (Exception ex) {
                moatAdEvent.adVolume = 1.0;
                return new JSONObject(moatAdEvent.toMap());
            }
        }
    }
    
    protected void a(final String s) {
        if (this.i.b() || this.a) {
            Log.d("MoatVideoTracker", s);
        }
    }
    
    protected boolean a(final Integer n, final Integer n2) {
        return n2 - n <= Math.min(750.0, n2 * 0.05);
    }
    
    public boolean a(final Map e, Object o, final View view) {
        boolean b = true;
        final boolean b2 = false;
        Label_0019: {
            if (e != null) {
                break Label_0019;
            }
        Label_0186_Outer:
            while (true) {
                while (true) {
                    while (true) {
                        try {
                            this.a("trackVideoAd received null adIds object. Not tracking.");
                            b = false;
                            if (view == null) {
                                this.a("trackVideoAd received null video view instance");
                            }
                            if (o == null) {
                                this.a("trackVideoAd received null ad instance. Not tracking.");
                                b = false;
                            }
                            if (b) {
                                final String string = new JSONObject(e).toString();
                                final String string2 = o.toString();
                                String string3;
                                if (view != null) {
                                    string3 = view.getClass().getSimpleName() + "@" + view.hashCode();
                                }
                                else {
                                    string3 = "null";
                                }
                                this.a(String.format("trackVideoAd tracking ids: %s | ad: %s | view: %s", string, string2, string3));
                                this.e = e;
                                this.f = new WeakReference((T)o);
                                this.g = new WeakReference((T)view);
                                this.b();
                            }
                            o = new StringBuilder("Attempt to start tracking ad was ");
                            if (b) {
                                final String s = "";
                                this.a(((StringBuilder)o).append(s).append("successful.").toString());
                                return b;
                            }
                        }
                        catch (Exception ex) {
                            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
                            b = b2;
                            continue Label_0186_Outer;
                        }
                        break;
                    }
                    final String s = "un";
                    continue;
                }
            }
        }
    }
    
    protected void b() {
        final Map a = this.a();
        final Integer n = a.get("width");
        final Integer n2 = a.get("height");
        final Integer n3 = a.get("duration");
        this.a(String.format("Player metadata: height = %d, width = %d, duration = %d", n2, n, n3));
        this.l.a((View)this.g.get(), this.e, n, n2, n3);
    }
    
    protected void c() {
        if (!this.j) {
            this.d.postDelayed((Runnable)new g(this), 500L);
            this.j = true;
        }
    }
    
    public void changeTargetView(final View view) {
        if (this.i.b()) {
            final StringBuilder sb = new StringBuilder("changing view to ");
            String string;
            if (view != null) {
                string = view.getClass().getSimpleName() + "@" + view.hashCode();
            }
            else {
                string = "null";
            }
            Log.d("MoatVideoTracker", sb.append(string).toString());
        }
        this.g = new WeakReference((T)view);
        this.l.a(view);
    }
    
    protected double d() {
        final AudioManager audioManager = (AudioManager)((Context)this.k.get()).getSystemService("audio");
        return this.a(audioManager) / (double)audioManager.getStreamMaxVolume(3);
    }
    
    public void dispatchEvent(final MoatAdEvent moatAdEvent) {
        try {
            this.b(moatAdEvent);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
    
    public void dispatchEvent(final Map map) {
        try {
            this.b(this.a(map));
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
    
    protected boolean e() {
        return this.c.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.c.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.c.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }
    
    public void setDebug(final boolean a) {
        this.a = a;
    }
}
