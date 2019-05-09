// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import android.text.TextUtils;
import java.util.List;
import android.support.annotation.CallSuper;
import java.util.Locale;
import org.json.JSONObject;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import android.os.Handler;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;

abstract class d extends c
{
    static final MoatAdEventType[] \u02bd;
    final Map<MoatAdEventType, Integer> \u02bb;
    WeakReference<View> \u02bc;
    final Handler \u02ca\u0971;
    private Double \u02cb\u0971;
    private Map<String, String> \u02cf\u0971;
    private boolean \u037a;
    private VideoTrackerListener \u0971\u02ca;
    private final Set<MoatAdEventType> \u0971\u02cb;
    private final String \u0971\u02ce;
    private final b \u141d\u0971;
    
    static {
        \u02bd = new MoatAdEventType[] { MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE };
    }
    
    d(final String \u0971\u02ce) {
        super(null, false, true);
        b.\u02cf(3, "BaseVideoTracker", this, "Initializing.");
        this.\u0971\u02ce = \u0971\u02ce;
        this.\u141d\u0971 = new b(a.\u02ce(), b.a.\u02ce);
        super.\u02cf = this.\u141d\u0971.\u02cb;
        while (true) {
            try {
                super.\u02cb(this.\u141d\u0971.\u02cf);
                this.\u02bb = new HashMap();
                this.\u0971\u02cb = new HashSet();
                this.\u02ca\u0971 = new Handler();
                this.\u037a = false;
                this.\u02cb\u0971 = 1.0;
            }
            catch (o \u02cb) {
                this.\u02cb = \u02cb;
                continue;
            }
            break;
        }
    }
    
    static boolean \u02cb(final Integer n, final Integer n2) {
        return Math.abs(n2 - n) <= Math.min(750.0, n2 * 0.05);
    }
    
    @Override
    public void changeTargetView(final View view) {
        b.\u02cf(3, "BaseVideoTracker", this, "changing view to " + b.\u0971(view));
        this.\u02bc = new WeakReference<View>(view);
        try {
            super.changeTargetView(view);
        }
        catch (Exception ex) {
            o.\u0971(ex);
        }
    }
    
    public void dispatchEvent(final MoatAdEvent moatAdEvent) {
        while (true) {
            int n = 0;
            while (true) {
                try {
                    final JSONObject \u02cb = this.\u02cb(moatAdEvent);
                    b.\u02cf(3, "BaseVideoTracker", this, String.format("Received event: %s", \u02cb.toString()));
                    b.\u02ce("[SUCCESS] ", this.\u02ca() + String.format(" Received event: %s", \u02cb.toString()));
                    if (this.\u0971() && this.\u02cf != null) {
                        this.\u02cf.\u02ca(this.\u141d\u0971.\u02ca, \u02cb);
                        if (!this.\u0971\u02cb.contains(moatAdEvent.\u0971)) {
                            this.\u0971\u02cb.add(moatAdEvent.\u0971);
                            if (this.\u0971\u02ca != null) {
                                this.\u0971\u02ca.onVideoEventReported(moatAdEvent.\u0971);
                            }
                        }
                    }
                    final MoatAdEventType \u0971 = moatAdEvent.\u0971;
                    if (\u0971 != MoatAdEventType.AD_EVT_COMPLETE && \u0971 != MoatAdEventType.AD_EVT_STOPPED) {
                        if (\u0971 != MoatAdEventType.AD_EVT_SKIPPED) {
                            if (n != 0) {
                                this.\u02bb.put(\u0971, 1);
                                if (this.\u02cf != null) {
                                    this.\u02cf.\u02cf(this);
                                }
                                this.\u0971\u02cb();
                            }
                            return;
                        }
                    }
                }
                catch (Exception ex) {
                    o.\u0971(ex);
                    return;
                }
                n = 1;
                continue;
            }
        }
    }
    
    public void removeVideoListener() {
        this.\u0971\u02ca = null;
    }
    
    public void setPlayerVolume(final Double \u02cb\u0971) {
        final double doubleValue = this.\u02cb\u0971;
        final double \u0971 = p.\u0971();
        if (!\u02cb\u0971.equals(this.\u02cb\u0971)) {
            b.\u02cf(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", \u02cb\u0971));
            this.\u02cb\u0971 = \u02cb\u0971;
            if (!Double.valueOf(doubleValue * \u0971).equals(this.\u02cb\u0971 * p.\u0971())) {
                this.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.\u02cf, this.\u02cb\u0971));
            }
        }
    }
    
    public void setVideoListener(final VideoTrackerListener \u0971\u02ca) {
        this.\u0971\u02ca = \u0971\u02ca;
    }
    
    @Override
    public void stopTracking() {
        try {
            super.stopTracking();
            this.\u0971\u02cb();
            if (this.\u0971\u02ca != null) {
                this.\u0971\u02ca = null;
            }
        }
        catch (Exception ex) {
            o.\u0971(ex);
        }
    }
    
    abstract Map<String, Object> \u02ca\u0971() throws o;
    
    JSONObject \u02cb(final MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.\u02ce)) {
            moatAdEvent.\u02ce = this.\u02cb\u0971;
        }
        return new JSONObject((Map)moatAdEvent.\u0971());
    }
    
    @CallSuper
    public boolean \u02cb(final Map<String, String> \u02cf\u0971, final View view) {
        try {
            this.\u02cb();
            this.\u02cf();
            if (view == null) {
                b.\u02cf(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.\u02cf\u0971 = \u02cf\u0971;
            this.\u02bc = new WeakReference<View>(view);
            this.\u02ce();
            final String format = String.format("trackVideoAd tracking ids: %s | view: %s", new JSONObject((Map)\u02cf\u0971).toString(), b.\u0971(view));
            b.\u02cf(3, "BaseVideoTracker", this, format);
            b.\u02ce("[SUCCESS] ", this.\u02ca() + " " + format);
            if (this.\u02ca != null) {
                this.\u02ca.onTrackingStarted(this.\u02bd());
            }
            return true;
        }
        catch (Exception ex) {
            this.\u0971("trackVideoAd", ex);
            return false;
        }
    }
    
    @Override
    final void \u02ce() throws o {
        super.changeTargetView(this.\u02bc.get());
        super.\u02ce();
        final HashMap \u02ca\u0971 = this.\u02ca\u0971();
        final Integer n = \u02ca\u0971.get("width");
        final Integer n2 = \u02ca\u0971.get("height");
        final Integer n3 = \u02ca\u0971.get("duration");
        b.\u02cf(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", n2, n, n3));
        this.\u141d\u0971.\u02ce(this.\u0971\u02ce, this.\u02cf\u0971, n, n2, n3);
    }
    
    @Override
    void \u02cf(final List<String> list) throws o {
        if (this.\u02cf\u0971 == null) {
            list.add("Null adIds object");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join((CharSequence)" and ", (Iterable)list));
        }
        super.\u02cf(list);
    }
    
    final Double \u02cf\u0971() {
        return this.\u02cb\u0971;
    }
    
    final boolean \u037a() {
        return this.\u02bb.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.\u02bb.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.\u02bb.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }
    
    final void \u0971\u02cb() {
        if (!this.\u037a) {
            this.\u037a = true;
            this.\u02ca\u0971.postDelayed((Runnable)new Runnable() {
                @Override
                public final void run() {
                    try {
                        b.\u02cf(3, "BaseVideoTracker", this, "Shutting down.");
                        final b \u02ce = d.this.\u141d\u0971;
                        b.\u02cf(3, "GlobalWebView", \u02ce, "Cleaning up");
                        \u02ce.\u02cb.\u02ca();
                        \u02ce.\u02cb = null;
                        \u02ce.\u02cf.destroy();
                        \u02ce.\u02cf = null;
                        d.this.\u0971\u02ca = null;
                    }
                    catch (Exception ex) {
                        o.\u0971(ex);
                    }
                }
            }, 500L);
        }
    }
    
    final Double \u141d() {
        return this.\u02cb\u0971 * p.\u0971();
    }
}
