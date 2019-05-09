// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;

abstract class h<PlayerOrIMAAd> extends c<PlayerOrIMAAd>
{
    int l;
    private a m;
    private int n;
    private double o;
    private int p;
    private int q;
    
    h(final String s) {
        super(s);
        this.p = Integer.MIN_VALUE;
        this.l = Integer.MIN_VALUE;
        this.n = Integer.MIN_VALUE;
        this.o = Double.NaN;
        this.q = 0;
        this.m = a.a;
    }
    
    private void n() {
        this.h.postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (h.this.j.get() != null && !h.this.i()) {
                        if ((boolean)h.this.m()) {
                            h.this.h.postDelayed((Runnable)this, 200L);
                            return;
                        }
                        h.this.h();
                        return;
                    }
                }
                catch (Exception ex) {
                    h.this.h();
                    com.moat.analytics.mobile.vng.m.a(ex);
                    return;
                }
                h.this.h();
            }
        }, 200L);
    }
    
    protected JSONObject a(final MoatAdEvent moatAdEvent) {
        Label_0162: {
            Integer b = null;
            Label_0018: {
                if (moatAdEvent.b.equals(MoatAdEvent.a)) {
                    while (true) {
                        try {
                            b = this.j();
                            moatAdEvent.b = b;
                            break Label_0018;
                        }
                        catch (Exception ex) {
                            b = this.n;
                            continue;
                        }
                        break;
                    }
                    break Label_0162;
                }
                b = moatAdEvent.b;
            }
            Integer value = null;
            Label_0074: {
                if (moatAdEvent.b >= 0) {
                    value = b;
                    if (moatAdEvent.b != 0) {
                        break Label_0074;
                    }
                    value = b;
                    if (moatAdEvent.d != MoatAdEventType.AD_EVT_COMPLETE) {
                        break Label_0074;
                    }
                    value = b;
                    if (this.n <= 0) {
                        break Label_0074;
                    }
                }
                value = this.n;
                moatAdEvent.b = value;
            }
            if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE) {
                if (value != Integer.MIN_VALUE && this.l != Integer.MIN_VALUE && this.a(value, this.l)) {
                    break Label_0162;
                }
                this.m = a.d;
                moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
            }
            return super.a(moatAdEvent);
        }
        this.m = a.e;
        return super.a(moatAdEvent);
    }
    
    @Override
    public boolean a(final Map<String, String> map, final PlayerOrIMAAd playerOrIMAAd, final View view) {
        try {
            if (!this.e) {
                this.n();
            }
            return super.a(map, playerOrIMAAd, view);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
            return super.a(map, playerOrIMAAd, view);
        }
    }
    
    protected abstract Integer j();
    
    protected abstract boolean k();
    
    protected abstract Integer l();
    
    boolean m() {
        if (this.j.get() != null && !this.i()) {
            while (true) {
            Label_0233_Outer:
                while (true) {
                    MoatAdEventType moatAdEventType = null;
                    Label_0409: {
                        Label_0366: {
                            int intValue = 0;
                            double n = 0.0;
                            Label_0298: {
                                try {
                                    intValue = this.j();
                                    if (this.n >= 0 && intValue < 0) {
                                        break;
                                    }
                                    if ((this.n = intValue) == 0) {
                                        return true;
                                    }
                                    final int intValue2 = this.l();
                                    final boolean k = this.k();
                                    n = intValue2 / 4.0;
                                    final double a = s.a();
                                    moatAdEventType = null;
                                    if (intValue > this.p) {
                                        this.p = intValue;
                                    }
                                    if (this.l == Integer.MIN_VALUE) {
                                        this.l = intValue2;
                                    }
                                    if (!k) {
                                        break Label_0366;
                                    }
                                    if (this.m == h.a.a) {
                                        moatAdEventType = MoatAdEventType.AD_EVT_START;
                                        this.m = h.a.c;
                                        break Label_0409;
                                    }
                                    if (this.m == h.a.b) {
                                        moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                                        this.m = h.a.c;
                                        break Label_0409;
                                    }
                                    break Label_0298;
                                    final int n3;
                                    int n2 = n3;
                                    MoatAdEventType ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                    // iftrue(Label_0206:, n3 != 0)
                                    n2 = n3;
                                    ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                    // iftrue(Label_0206:, Double.isNaN(this.o))
                                    // iftrue(Label_0233:, n2 == 0)
                                    // iftrue(Label_0206:, Math.abs(this.o - a) <= 0.05)
                                    while (true) {
                                    Block_17:
                                        while (true) {
                                            Block_15: {
                                                break Block_15;
                                                break Block_17;
                                            }
                                            n2 = n3;
                                            ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                            Block_16: {
                                                break Block_16;
                                                this.o = a;
                                                this.q = 0;
                                                return true;
                                            }
                                            ad_EVT_VOLUME_CHANGE = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                                            n2 = 1;
                                            continue Label_0233_Outer;
                                        }
                                        this.dispatchEvent(new MoatAdEvent(ad_EVT_VOLUME_CHANGE, intValue, a));
                                        continue;
                                    }
                                }
                                catch (Exception ex) {
                                    if (this.q++ < 5) {
                                        return true;
                                    }
                                    return false;
                                }
                            }
                            final int n4 = (int)Math.floor(intValue / n) - 1;
                            Label_0420: {
                                if (n4 > -1 && n4 < 3) {
                                    moatAdEventType = h.f[n4];
                                    if (!this.g.containsKey(moatAdEventType)) {
                                        this.g.put(moatAdEventType, 1);
                                        break Label_0420;
                                    }
                                }
                                moatAdEventType = null;
                            }
                            break Label_0409;
                        }
                        if (this.m != a.b) {
                            moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                            this.m = a.b;
                        }
                        break Label_0409;
                        return false;
                    }
                    if (moatAdEventType != null) {
                        final int n3 = 1;
                        continue;
                    }
                    final int n3 = 0;
                    continue;
                }
            }
        }
        return false;
    }
    
    @Override
    public void stopTracking() {
        try {
            this.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            com.moat.analytics.mobile.vng.p.a("[SUCCESS] ", this.a() + " stopTracking succeeded for " + this.e());
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
    
    protected enum a
    {
        a, 
        b, 
        c, 
        d, 
        e;
    }
}
