package com.moat.analytics.mobile.tjy;

import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.tjy.i */
abstract class C2744i extends C2743f {
    /* renamed from: j */
    protected C2757k f6642j = C2757k.UNINITIALIZED;
    /* renamed from: k */
    protected int f6643k = Integer.MIN_VALUE;
    /* renamed from: l */
    protected double f6644l = Double.NaN;
    /* renamed from: m */
    protected int f6645m = Integer.MIN_VALUE;
    /* renamed from: n */
    protected int f6646n = Integer.MIN_VALUE;
    /* renamed from: o */
    private int f6647o = 0;

    public C2744i(String str, C2742a c2742a, ap apVar) {
        super(str, c2742a, apVar);
    }

    /* renamed from: a */
    protected JSONObject mo6084a(MoatAdEvent moatAdEvent) {
        Integer f;
        if (moatAdEvent.adPlayhead.equals(MoatAdEvent.TIME_UNAVAILABLE)) {
            try {
                f = mo6089f();
            } catch (Exception e) {
                f = Integer.valueOf(this.f6643k);
            }
            moatAdEvent.adPlayhead = f;
        } else {
            f = moatAdEvent.adPlayhead;
        }
        if (moatAdEvent.adPlayhead.intValue() < 0) {
            f = Integer.valueOf(this.f6643k);
            moatAdEvent.adPlayhead = f;
        }
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE) {
            if (f.intValue() == Integer.MIN_VALUE || this.f6646n == Integer.MIN_VALUE || !m6820a(f, Integer.valueOf(this.f6646n))) {
                this.f6642j = C2757k.STOPPED;
                moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f6642j = C2757k.COMPLETED;
            }
        }
        return super.mo6084a(moatAdEvent);
    }

    /* renamed from: b */
    protected void mo6085b() {
        super.mo6085b();
        this.d.postDelayed(new C2756j(this), 200);
    }

    /* renamed from: f */
    protected abstract Integer mo6089f();

    /* renamed from: g */
    protected abstract boolean mo6090g();

    /* renamed from: h */
    protected abstract Integer mo6091h();

    /* renamed from: i */
    protected boolean m6831i() {
        if (this.f.get() == null || m6825e()) {
            return false;
        }
        int intValue;
        try {
            int intValue2 = mo6089f().intValue();
            if (this.f6643k >= 0 && intValue2 < 0) {
                return false;
            }
            this.f6643k = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            intValue = mo6091h().intValue();
            boolean g = mo6090g();
            double d = ((double) intValue) / 4.0d;
            double d2 = m6824d();
            MoatAdEventType moatAdEventType = null;
            if (intValue2 > this.f6645m) {
                this.f6645m = intValue2;
            }
            if (this.f6646n == Integer.MIN_VALUE) {
                this.f6646n = intValue;
            }
            if (g) {
                if (this.f6642j == C2757k.UNINITIALIZED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_START;
                    this.f6642j = C2757k.PLAYING;
                } else if (this.f6642j == C2757k.PAUSED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                    this.f6642j = C2757k.PLAYING;
                } else {
                    MoatAdEventType moatAdEventType2;
                    intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                    if (intValue >= 0 && intValue < 3) {
                        moatAdEventType2 = b[intValue];
                        if (!this.c.containsKey(moatAdEventType2)) {
                            this.c.put(moatAdEventType2, Integer.valueOf(1));
                            moatAdEventType = moatAdEventType2;
                        }
                    }
                    moatAdEventType2 = null;
                    moatAdEventType = moatAdEventType2;
                }
            } else if (this.f6642j != C2757k.PAUSED) {
                moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                this.f6642j = C2757k.PAUSED;
            }
            Object obj = moatAdEventType != null ? 1 : null;
            if (obj == null && !Double.isNaN(this.f6644l) && Math.abs(this.f6644l - d2) > 0.05d) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                obj = 1;
            }
            if (obj != null) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), Double.valueOf(d2)));
            }
            this.f6644l = d2;
            this.f6647o = 0;
            return true;
        } catch (Exception e) {
            intValue = this.f6647o;
            this.f6647o = intValue + 1;
            return intValue < 5;
        }
    }
}
