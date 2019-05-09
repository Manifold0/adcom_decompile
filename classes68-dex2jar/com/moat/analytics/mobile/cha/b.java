// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.support.annotation.CallSuper;
import android.text.TextUtils;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;
import java.util.HashSet;
import java.util.HashMap;
import android.os.Handler;
import java.util.Set;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;

abstract class b extends d
{
    static final MoatAdEventType[] \u02bb;
    final Map<MoatAdEventType, Integer> \u02bc;
    private final String \u02bc\u0971;
    WeakReference<View> \u02ca\u0971;
    private boolean \u02cb\u0971;
    private Map<String, String> \u02cf\u0971;
    private Double \u037a;
    private VideoTrackerListener \u0971\u02ca;
    private final Set<MoatAdEventType> \u0971\u02cb;
    private final a \u0971\u02ce;
    final Handler \u141d;
    
    static {
        \u02bb = new MoatAdEventType[] { MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE };
    }
    
    b(final String \u02bc\u0971) {
        super(null, false, true);
        a.\u02cf(3, "BaseVideoTracker", this, "Initializing.");
        this.\u02bc\u0971 = \u02bc\u0971;
        this.\u0971\u02ce = new a(c.\u02cf(), a.d.\u02cb);
        super.\u02ce = this.\u0971\u02ce.\u02cf;
        while (true) {
            try {
                super.\u0971(this.\u0971\u02ce.\u02cb);
                this.\u02bc = new HashMap();
                this.\u0971\u02cb = new HashSet();
                this.\u141d = new Handler();
                this.\u02cb\u0971 = false;
                this.\u037a = 1.0;
            }
            catch (o \u0971) {
                this.\u0971 = \u0971;
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
        a.\u02cf(3, "BaseVideoTracker", this, "changing view to " + a.\u02cf(view));
        this.\u02ca\u0971 = new WeakReference<View>(view);
        try {
            super.changeTargetView(view);
        }
        catch (Exception ex) {
            o.\u02ce(ex);
        }
    }
    
    public void dispatchEvent(final MoatAdEvent moatAdEvent) {
        while (true) {
            int n = 0;
            while (true) {
                try {
                    final JSONObject \u02ce = this.\u02ce(moatAdEvent);
                    a.\u02cf(3, "BaseVideoTracker", this, String.format("Received event: %s", \u02ce.toString()));
                    a.\u02ca("[SUCCESS] ", this.\u02cb() + String.format(" Received event: %s", \u02ce.toString()));
                    if (this.\u02ca() && this.\u02ce != null) {
                        this.\u02ce.\u0971(this.\u0971\u02ce.\u02ca, \u02ce);
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
                                this.\u02bc.put(\u0971, 1);
                                if (this.\u02ce != null) {
                                    this.\u02ce.\u02cb(this);
                                }
                                this.\u02cf\u0971();
                            }
                            return;
                        }
                    }
                }
                catch (Exception ex) {
                    o.\u02ce(ex);
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
    
    public void setPlayerVolume(final Double \u037a) {
        final double doubleValue = this.\u037a;
        final double \u0971 = r.\u0971();
        if (!\u037a.equals(this.\u037a)) {
            a.\u02cf(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", \u037a));
            this.\u037a = \u037a;
            if (!Double.valueOf(doubleValue * \u0971).equals(this.\u037a * r.\u0971())) {
                this.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.\u02cb, this.\u037a));
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
            this.\u02cf\u0971();
            if (this.\u0971\u02ca != null) {
                this.\u0971\u02ca = null;
            }
        }
        catch (Exception ex) {
            o.\u02ce(ex);
        }
    }
    
    final Double \u02ca\u0971() {
        return this.\u037a * r.\u0971();
    }
    
    @Override
    void \u02cb(final List<String> list) throws o {
        if (this.\u02cf\u0971 == null) {
            list.add("Null adIds object");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join((CharSequence)" and ", (Iterable)list));
        }
        super.\u02cb(list);
    }
    
    final boolean \u02cb\u0971() {
        return this.\u02bc.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.\u02bc.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.\u02bc.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }
    
    JSONObject \u02ce(final MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.\u02ca)) {
            moatAdEvent.\u02ca = this.\u037a;
        }
        return new JSONObject((Map)moatAdEvent.\u02cf());
    }
    
    @Override
    final void \u02cf() throws o {
        super.changeTargetView(this.\u02ca\u0971.get());
        super.\u02cf();
        final HashMap \u141d = this.\u141d();
        final Integer n = \u141d.get("width");
        final Integer n2 = \u141d.get("height");
        final Integer n3 = \u141d.get("duration");
        a.\u02cf(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", n2, n, n3));
        this.\u0971\u02ce.\u02ca(this.\u02bc\u0971, this.\u02cf\u0971, n, n2, n3);
    }
    
    final void \u02cf\u0971() {
        if (!this.\u02cb\u0971) {
            this.\u02cb\u0971 = true;
            this.\u141d.postDelayed((Runnable)new Runnable() {
                @Override
                public final void run() {
                    try {
                        a.\u02cf(3, "BaseVideoTracker", this, "Shutting down.");
                        final a \u02ca = b.this.\u0971\u02ce;
                        a.\u02cf(3, "GlobalWebView", \u02ca, "Cleaning up");
                        \u02ca.\u02cf.\u02ca();
                        \u02ca.\u02cf = null;
                        \u02ca.\u02cb.destroy();
                        \u02ca.\u02cb = null;
                        b.this.\u0971\u02ca = null;
                    }
                    catch (Exception ex) {
                        o.\u02ce(ex);
                    }
                }
            }, 500L);
        }
    }
    
    @CallSuper
    public boolean \u0971(final Map<String, String> \u02cf\u0971, final View view) {
        try {
            this.\u02ce();
            this.\u0971();
            if (view == null) {
                a.\u02cf(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.\u02cf\u0971 = \u02cf\u0971;
            this.\u02ca\u0971 = new WeakReference<View>(view);
            this.\u02cf();
            final String format = String.format("trackVideoAd tracking ids: %s | view: %s", new JSONObject((Map)\u02cf\u0971).toString(), a.\u02cf(view));
            a.\u02cf(3, "BaseVideoTracker", this, format);
            a.\u02ca("[SUCCESS] ", this.\u02cb() + " " + format);
            if (this.\u02ca != null) {
                this.\u02ca.onTrackingStarted(this.\u02bb());
            }
            return true;
        }
        catch (Exception ex) {
            this.\u0971("trackVideoAd", ex);
            return false;
        }
    }
    
    final Double \u0971\u02ca() {
        return this.\u037a;
    }
    
    abstract Map<String, Object> \u141d() throws o;
}
