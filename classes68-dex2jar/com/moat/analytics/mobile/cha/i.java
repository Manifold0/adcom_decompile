// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;
import android.support.annotation.CallSuper;

abstract class i extends b
{
    private int \u02cb\u0971;
    private int \u02cf\u0971;
    private int \u037a;
    private double \u0971\u02ca;
    private int \u0971\u02cb;
    private int \u0971\u02ce;
    
    i(final String s) {
        super(s);
        this.\u0971\u02cb = Integer.MIN_VALUE;
        this.\u037a = Integer.MIN_VALUE;
        this.\u02cf\u0971 = Integer.MIN_VALUE;
        this.\u0971\u02ce = 0;
        this.\u02cb\u0971 = c.\u02cf;
        this.\u0971\u02ca = Double.NaN;
    }
    
    @Override
    public void setPlayerVolume(final Double playerVolume) {
        super.setPlayerVolume(playerVolume);
        this.\u0971\u02ca = this.\u02ca\u0971();
    }
    
    @Override
    public void stopTracking() {
        try {
            this.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        }
        catch (Exception ex) {
            o.\u02ce(ex);
        }
    }
    
    @CallSuper
    final boolean \u02bb\u0971() throws o {
        if (!this.\u037a() || this.\u02cb\u0971()) {
            return false;
        }
    Label_0143_Outer:
        while (true) {
            while (true) {
                MoatAdEventType moatAdEventType = null;
                Label_0397: {
                    Label_0394: {
                        Label_0361: {
                            int intValue = 0;
                            double n = 0.0;
                            Label_0294: {
                                try {
                                    intValue = this.\u0971\u02cb();
                                    if (this.\u02cf\u0971 >= 0 && intValue < 0) {
                                        return false;
                                    }
                                    if ((this.\u02cf\u0971 = intValue) == 0) {
                                        return true;
                                    }
                                    final int intValue2 = this.\u0971\u141d();
                                    final boolean \u0971\u02ce = this.\u0971\u02ce();
                                    n = intValue2 / 4.0;
                                    final double doubleValue = this.\u02ca\u0971();
                                    if (intValue > this.\u0971\u02cb) {
                                        this.\u0971\u02cb = intValue;
                                    }
                                    if (this.\u037a == Integer.MIN_VALUE) {
                                        this.\u037a = intValue2;
                                    }
                                    if (!\u0971\u02ce) {
                                        break Label_0361;
                                    }
                                    if (this.\u02cb\u0971 == c.\u02cf) {
                                        moatAdEventType = MoatAdEventType.AD_EVT_START;
                                        this.\u02cb\u0971 = c.\u02ce;
                                        break Label_0397;
                                    }
                                    if (this.\u02cb\u0971 == c.\u02ca) {
                                        moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                                        this.\u02cb\u0971 = c.\u02ce;
                                        break Label_0397;
                                    }
                                    break Label_0294;
                                    // iftrue(Label_0233:, n2 == 0)
                                    // iftrue(Label_0206:, n3 != 0)
                                    while (true) {
                                        final int n3;
                                        Block_16: {
                                            Label_0233: {
                                                Block_15: {
                                                    MoatAdEventType ad_EVT_VOLUME_CHANGE = null;
                                                Block_18:
                                                    while (true) {
                                                        break Block_18;
                                                        ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                                        int n2 = n3;
                                                        break Block_15;
                                                        ad_EVT_VOLUME_CHANGE = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                                                        n2 = 1;
                                                        continue Label_0143_Outer;
                                                    }
                                                    this.dispatchEvent(new MoatAdEvent(ad_EVT_VOLUME_CHANGE, intValue, this.\u0971\u02ca()));
                                                    break Label_0233;
                                                }
                                                MoatAdEventType ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                                int n2 = n3;
                                                break Block_16;
                                            }
                                            this.\u0971\u02ca = doubleValue;
                                            this.\u0971\u02ce = 0;
                                            return true;
                                        }
                                        MoatAdEventType ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                        int n2 = n3;
                                        continue;
                                    }
                                }
                                // iftrue(Label_0206:, Double.isNaN(this.\u0971\u02ca))
                                // iftrue(Label_0206:, Math.abs(this.\u0971\u02ca - doubleValue) <= 0.05)
                                catch (Exception ex) {
                                    if (this.\u0971\u02ce++ < 5) {
                                        return true;
                                    }
                                    break;
                                }
                            }
                            final int n4 = (int)Math.floor(intValue / n) - 1;
                            if (n4 < 0 || n4 >= 3) {
                                break Label_0394;
                            }
                            moatAdEventType = i.\u02bb[n4];
                            if (!this.\u02bc.containsKey(moatAdEventType)) {
                                this.\u02bc.put(moatAdEventType, 1);
                                break Label_0397;
                            }
                            break Label_0394;
                        }
                        if (this.\u02cb\u0971 != c.\u02ca) {
                            moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                            this.\u02cb\u0971 = c.\u02ca;
                            break Label_0397;
                        }
                    }
                    moatAdEventType = null;
                }
                if (moatAdEventType != null) {
                    final int n3 = 1;
                    continue;
                }
                final int n3 = 0;
                continue;
            }
        }
        return false;
    }
    
    @Override
    final JSONObject \u02ce(final MoatAdEvent moatAdEvent) {
        Label_0161: {
            Integer \u02cf = null;
            Label_0018: {
                if (moatAdEvent.\u02cf.equals(MoatAdEvent.\u02cb)) {
                    while (true) {
                        try {
                            \u02cf = this.\u0971\u02cb();
                            moatAdEvent.\u02cf = \u02cf;
                            break Label_0018;
                        }
                        catch (Exception ex) {
                            \u02cf = this.\u02cf\u0971;
                            continue;
                        }
                        break;
                    }
                    break Label_0161;
                }
                \u02cf = moatAdEvent.\u02cf;
            }
            Integer value = null;
            Label_0074: {
                if (moatAdEvent.\u02cf >= 0) {
                    value = \u02cf;
                    if (moatAdEvent.\u02cf != 0) {
                        break Label_0074;
                    }
                    value = \u02cf;
                    if (moatAdEvent.\u0971 != MoatAdEventType.AD_EVT_COMPLETE) {
                        break Label_0074;
                    }
                    value = \u02cf;
                    if (this.\u02cf\u0971 <= 0) {
                        break Label_0074;
                    }
                }
                value = this.\u02cf\u0971;
                moatAdEvent.\u02cf = value;
            }
            if (moatAdEvent.\u0971 == MoatAdEventType.AD_EVT_COMPLETE) {
                if (value != Integer.MIN_VALUE && this.\u037a != Integer.MIN_VALUE && b.\u02cb(value, this.\u037a)) {
                    break Label_0161;
                }
                this.\u02cb\u0971 = c.\u02cb;
                moatAdEvent.\u0971 = MoatAdEventType.AD_EVT_STOPPED;
            }
            return super.\u02ce(moatAdEvent);
        }
        this.\u02cb\u0971 = c.\u0971;
        return super.\u02ce(moatAdEvent);
    }
    
    abstract boolean \u037a();
    
    @Override
    public final boolean \u0971(final Map<String, String> map, final View view) {
        try {
            final boolean \u0971 = super.\u0971(map, view);
            if (\u0971) {
                this.\u141d.postDelayed((Runnable)new Runnable() {
                    @Override
                    public final void run() {
                        try {
                            if (i.this.\u037a() && !i.this.\u02cb\u0971()) {
                                if ((boolean)i.this.\u02bb\u0971()) {
                                    i.this.\u141d.postDelayed((Runnable)this, 200L);
                                    return;
                                }
                                i.this.\u02cf\u0971();
                                return;
                            }
                        }
                        catch (Exception ex) {
                            i.this.\u02cf\u0971();
                            o.\u02ce(ex);
                            return;
                        }
                        i.this.\u02cf\u0971();
                    }
                }, 200L);
            }
            return \u0971;
        }
        catch (Exception ex) {
            a.\u02cf(3, "IntervalVideoTracker", this, "Problem with video loop");
            this.\u0971("trackVideoAd", ex);
            return false;
        }
    }
    
    abstract Integer \u0971\u02cb();
    
    abstract boolean \u0971\u02ce();
    
    abstract Integer \u0971\u141d();
    
    enum c
    {
        public static final int \u02ca;
        public static final int \u02cb;
        public static final int \u02ce;
        public static final int \u02cf;
        public static final int \u0971;
        
        static {
            \u02cf = 1;
            \u02ca = 2;
            \u02ce = 3;
            \u02cb = 4;
            \u0971 = 5;
        }
    }
}
