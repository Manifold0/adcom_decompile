package com.moat.analytics.mobile.cha;

import android.support.annotation.CallSuper;
import android.view.View;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.cha.i */
abstract class C1502i extends C1490b {
    /* renamed from: ˋॱ */
    private int f3502 = C1501c.f3500;
    /* renamed from: ˏॱ */
    private int f3503 = Integer.MIN_VALUE;
    /* renamed from: ͺ */
    private int f3504 = Integer.MIN_VALUE;
    /* renamed from: ॱˊ */
    private double f3505 = Double.NaN;
    /* renamed from: ॱˋ */
    private int f3506 = Integer.MIN_VALUE;
    /* renamed from: ॱˎ */
    private int f3507 = 0;

    /* renamed from: com.moat.analytics.mobile.cha.i$5 */
    class C15005 implements Runnable {
        /* renamed from: ˎ */
        private /* synthetic */ C1502i f3496;

        C15005(C1502i c1502i) {
            this.f3496 = c1502i;
        }

        public final void run() {
            try {
                if (!this.f3496.mo4375() || this.f3496.m3737()) {
                    this.f3496.m3740();
                } else if (Boolean.valueOf(this.f3496.m3777()).booleanValue()) {
                    this.f3496.ᐝ.postDelayed(this, 200);
                } else {
                    this.f3496.m3740();
                }
            } catch (Exception e) {
                this.f3496.m3740();
                C1518o.m3840(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.i$c */
    enum C1501c {
        ;
        
        /* renamed from: ˊ */
        public static final int f3497 = 0;
        /* renamed from: ˋ */
        public static final int f3498 = 0;
        /* renamed from: ˎ */
        public static final int f3499 = 0;
        /* renamed from: ˏ */
        public static final int f3500 = 0;
        /* renamed from: ॱ */
        public static final int f3501 = 0;

        static {
            f3500 = 1;
            f3497 = 2;
            f3499 = 3;
            f3498 = 4;
            f3501 = 5;
            int[] iArr = new int[]{1, 2, 3, 4, 5};
        }
    }

    /* renamed from: ͺ */
    abstract boolean mo4375();

    /* renamed from: ॱˋ */
    abstract Integer mo4376();

    /* renamed from: ॱˎ */
    abstract boolean mo4377();

    /* renamed from: ॱᐝ */
    abstract Integer mo4378();

    C1502i(String str) {
        super(str);
    }

    /* renamed from: ॱ */
    public final boolean mo4373(Map<String, String> map, View view) {
        try {
            boolean ॱ = super.mo4373(map, view);
            if (!ॱ) {
                return ॱ;
            }
            this.ᐝ.postDelayed(new C15005(this), 200);
            return ॱ;
        } catch (Exception e) {
            C1487a.m3715(3, "IntervalVideoTracker", this, "Problem with video loop");
            m3731("trackVideoAd", e);
            return false;
        }
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.f3505 = m3735().doubleValue();
    }

    /* renamed from: ˎ */
    final JSONObject mo4372(MoatAdEvent moatAdEvent) {
        Integer ॱˋ;
        if (moatAdEvent.f3417.equals(MoatAdEvent.f3413)) {
            try {
                ॱˋ = mo4376();
            } catch (Exception e) {
                ॱˋ = Integer.valueOf(this.f3503);
            }
            moatAdEvent.f3417 = ॱˋ;
        } else {
            ॱˋ = moatAdEvent.f3417;
        }
        if (moatAdEvent.f3417.intValue() < 0 || (moatAdEvent.f3417.intValue() == 0 && moatAdEvent.f3418 == MoatAdEventType.AD_EVT_COMPLETE && this.f3503 > 0)) {
            ॱˋ = Integer.valueOf(this.f3503);
            moatAdEvent.f3417 = ॱˋ;
        }
        if (moatAdEvent.f3418 == MoatAdEventType.AD_EVT_COMPLETE) {
            if (ॱˋ.intValue() == Integer.MIN_VALUE || this.f3504 == Integer.MIN_VALUE || !C1490b.m3733(ॱˋ, Integer.valueOf(this.f3504))) {
                this.f3502 = C1501c.f3498;
                moatAdEvent.f3418 = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f3502 = C1501c.f3501;
            }
        }
        return super.mo4372(moatAdEvent);
    }

    @CallSuper
    /* renamed from: ʻॱ */
    final boolean m3777() throws C1518o {
        if (!mo4375() || m3737()) {
            return false;
        }
        int intValue;
        try {
            int intValue2 = mo4376().intValue();
            if (this.f3503 >= 0 && intValue2 < 0) {
                return false;
            }
            this.f3503 = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            MoatAdEventType moatAdEventType;
            Object obj;
            intValue = mo4378().intValue();
            boolean ॱˎ = mo4377();
            double d = ((double) intValue) / 4.0d;
            double doubleValue = m3735().doubleValue();
            if (intValue2 > this.f3506) {
                this.f3506 = intValue2;
            }
            if (this.f3504 == Integer.MIN_VALUE) {
                this.f3504 = intValue;
            }
            if (!ॱˎ) {
                if (this.f3502 != C1501c.f3497) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                    this.f3502 = C1501c.f3497;
                }
                moatAdEventType = null;
            } else if (this.f3502 == C1501c.f3500) {
                moatAdEventType = MoatAdEventType.AD_EVT_START;
                this.f3502 = C1501c.f3499;
            } else if (this.f3502 == C1501c.f3497) {
                moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                this.f3502 = C1501c.f3499;
            } else {
                intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                if (intValue >= 0 && intValue < 3) {
                    moatAdEventType = ʻ[intValue];
                    if (!this.ʼ.containsKey(moatAdEventType)) {
                        this.ʼ.put(moatAdEventType, Integer.valueOf(1));
                    }
                }
                moatAdEventType = null;
            }
            if (moatAdEventType != null) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null && !Double.isNaN(this.f3505) && Math.abs(this.f3505 - doubleValue) > 0.05d) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                obj = 1;
            }
            if (obj != null) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), m3742()));
            }
            this.f3505 = doubleValue;
            this.f3507 = 0;
            return true;
        } catch (Exception e) {
            intValue = this.f3507;
            this.f3507 = intValue + 1;
            if (intValue < 5) {
                return true;
            }
            return false;
        }
    }
}
