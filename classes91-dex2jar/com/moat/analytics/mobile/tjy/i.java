// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import org.json.JSONObject;

abstract class i extends f
{
    protected k j;
    protected int k;
    protected double l;
    protected int m;
    protected int n;
    private int o;
    
    public i(final String s, final a a, final ap ap) {
        super(s, a, ap);
        this.m = Integer.MIN_VALUE;
        this.n = Integer.MIN_VALUE;
        this.k = Integer.MIN_VALUE;
        this.l = Double.NaN;
        this.o = 0;
        this.j = com.moat.analytics.mobile.tjy.k.a;
    }
    
    @Override
    protected JSONObject a(final MoatAdEvent moatAdEvent) {
        Label_0129: {
            Integer n = null;
            Label_0018: {
                if (moatAdEvent.adPlayhead.equals(MoatAdEvent.TIME_UNAVAILABLE)) {
                    while (true) {
                        try {
                            n = this.f();
                            moatAdEvent.adPlayhead = n;
                            break Label_0018;
                        }
                        catch (Exception ex) {
                            n = this.k;
                            continue;
                        }
                        break;
                    }
                    break Label_0129;
                }
                n = moatAdEvent.adPlayhead;
            }
            if (moatAdEvent.adPlayhead < 0) {
                n = this.k;
                moatAdEvent.adPlayhead = n;
            }
            if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE) {
                if (n != Integer.MIN_VALUE && this.n != Integer.MIN_VALUE && this.a(n, this.n)) {
                    break Label_0129;
                }
                this.j = com.moat.analytics.mobile.tjy.k.d;
                moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
            }
            return super.a(moatAdEvent);
        }
        this.j = com.moat.analytics.mobile.tjy.k.e;
        return super.a(moatAdEvent);
    }
    
    @Override
    protected void b() {
        super.b();
        this.d.postDelayed((Runnable)new j(this), 200L);
    }
    
    protected abstract Integer f();
    
    protected abstract boolean g();
    
    protected abstract Integer h();
    
    protected boolean i() {
        if (this.f.get() == null || this.e()) {
            return false;
        }
    Label_0146_Outer:
        while (true) {
            while (true) {
                MoatAdEventType moatAdEventType = null;
                Label_0403: {
                    Label_0364: {
                        int intValue = 0;
                        double n = 0.0;
                        Label_0297: {
                            try {
                                intValue = this.f();
                                if (this.k >= 0 && intValue < 0) {
                                    return false;
                                }
                                if ((this.k = intValue) == 0) {
                                    return true;
                                }
                                final int intValue2 = this.h();
                                final boolean g = this.g();
                                n = intValue2 / 4.0;
                                final double d = this.d();
                                moatAdEventType = null;
                                if (intValue > this.m) {
                                    this.m = intValue;
                                }
                                if (this.n == Integer.MIN_VALUE) {
                                    this.n = intValue2;
                                }
                                if (!g) {
                                    break Label_0364;
                                }
                                if (this.j == com.moat.analytics.mobile.tjy.k.a) {
                                    moatAdEventType = MoatAdEventType.AD_EVT_START;
                                    this.j = com.moat.analytics.mobile.tjy.k.c;
                                    break Label_0403;
                                }
                                if (this.j == com.moat.analytics.mobile.tjy.k.b) {
                                    moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                                    this.j = com.moat.analytics.mobile.tjy.k.c;
                                    break Label_0403;
                                }
                                break Label_0297;
                            Block_17_Outer:
                                while (true) {
                                    final int n3;
                                    int n2 = n3;
                                    MoatAdEventType ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                    while (true) {
                                        Block_16: {
                                            break Block_16;
                                            ad_EVT_VOLUME_CHANGE = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                                            n2 = 1;
                                            Label_0209: {
                                                this.dispatchEvent(new MoatAdEvent(ad_EVT_VOLUME_CHANGE, intValue, d));
                                            }
                                            Label_0236:
                                            this.l = d;
                                            this.o = 0;
                                            return true;
                                        }
                                        n2 = n3;
                                        ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                        continue Label_0146_Outer;
                                    }
                                    n2 = n3;
                                    ad_EVT_VOLUME_CHANGE = moatAdEventType;
                                    continue Block_17_Outer;
                                }
                            }
                            // iftrue(Label_0209:, Double.isNaN(this.l))
                            // iftrue(Label_0236:, n2 == 0)
                            // iftrue(Label_0209:, Math.abs(this.l - d) <= 0.05)
                            // iftrue(Label_0209:, n3 != 0)
                            catch (Exception ex) {
                                if (this.o++ < 5) {
                                    return true;
                                }
                                break;
                            }
                        }
                        final int n4 = (int)Math.floor(intValue / n) - 1;
                        Label_0414: {
                            if (n4 >= 0 && n4 < 3) {
                                moatAdEventType = i.b[n4];
                                if (!this.c.containsKey(moatAdEventType)) {
                                    this.c.put(moatAdEventType, 1);
                                    break Label_0414;
                                }
                            }
                            moatAdEventType = null;
                        }
                        break Label_0403;
                    }
                    if (this.j != com.moat.analytics.mobile.tjy.k.b) {
                        moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                        this.j = com.moat.analytics.mobile.tjy.k.b;
                    }
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
}
