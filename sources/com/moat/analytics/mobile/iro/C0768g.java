package com.moat.analytics.mobile.iro;

import android.support.annotation.CallSuper;
import android.view.View;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.iro.g */
abstract class C0768g extends C0759d {
    /* renamed from: ˋॱ */
    private int f1193 = Integer.MIN_VALUE;
    /* renamed from: ˏॱ */
    private double f1194 = Double.NaN;
    /* renamed from: ͺ */
    private int f1195 = Integer.MIN_VALUE;
    /* renamed from: ॱˊ */
    private int f1196 = C0767a.f1190;
    /* renamed from: ॱˋ */
    private int f1197 = Integer.MIN_VALUE;
    /* renamed from: ॱˎ */
    private int f1198 = 0;

    /* renamed from: com.moat.analytics.mobile.iro.g$1 */
    class C07661 implements Runnable {
        /* renamed from: ॱ */
        private /* synthetic */ C0768g f1187;

        C07661(C0768g c0768g) {
            this.f1187 = c0768g;
        }

        public final void run() {
            try {
                if (!this.f1187.mo1650() || this.f1187.m1259()) {
                    this.f1187.m1260();
                } else if (Boolean.valueOf(this.f1187.m1293()).booleanValue()) {
                    this.f1187.ˊॱ.postDelayed(this, 200);
                } else {
                    this.f1187.m1260();
                }
            } catch (Exception e) {
                this.f1187.m1260();
                C0785o.m1351(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.g$a */
    enum C0767a {
        ;
        
        /* renamed from: ˊ */
        public static final int f1188 = 0;
        /* renamed from: ˋ */
        public static final int f1189 = 0;
        /* renamed from: ˎ */
        public static final int f1190 = 0;
        /* renamed from: ˏ */
        public static final int f1191 = 0;
        /* renamed from: ॱ */
        public static final int f1192 = 0;

        static {
            f1190 = 1;
            f1192 = 2;
            f1188 = 3;
            f1189 = 4;
            f1191 = 5;
            int[] iArr = new int[]{1, 2, 3, 4, 5};
        }
    }

    /* renamed from: ʻॱ */
    abstract Integer mo1646();

    /* renamed from: ˋॱ */
    abstract Integer mo1649();

    /* renamed from: ॱˊ */
    abstract boolean mo1650();

    /* renamed from: ॱˎ */
    abstract boolean mo1651();

    C0768g(String str) {
        super(str);
    }

    /* renamed from: ˋ */
    public final boolean mo1644(Map<String, String> map, View view) {
        try {
            boolean ˋ = super.mo1644((Map) map, view);
            if (!ˋ) {
                return ˋ;
            }
            this.ˊॱ.postDelayed(new C07661(this), 200);
            return ˋ;
        } catch (Exception e) {
            C0756b.m1234(3, "IntervalVideoTracker", this, "Problem with video loop");
            m1248("trackVideoAd", e);
            return false;
        }
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.f1194 = m1261().doubleValue();
    }

    /* renamed from: ˋ */
    final JSONObject mo1643(MoatAdEvent moatAdEvent) {
        Integer ˋॱ;
        if (moatAdEvent.f1111.equals(MoatAdEvent.f1108)) {
            try {
                ˋॱ = mo1649();
            } catch (Exception e) {
                ˋॱ = Integer.valueOf(this.f1195);
            }
            moatAdEvent.f1111 = ˋॱ;
        } else {
            ˋॱ = moatAdEvent.f1111;
        }
        if (moatAdEvent.f1111.intValue() < 0 || (moatAdEvent.f1111.intValue() == 0 && moatAdEvent.f1113 == MoatAdEventType.AD_EVT_COMPLETE && this.f1195 > 0)) {
            ˋॱ = Integer.valueOf(this.f1195);
            moatAdEvent.f1111 = ˋॱ;
        }
        if (moatAdEvent.f1113 == MoatAdEventType.AD_EVT_COMPLETE) {
            if (ˋॱ.intValue() == Integer.MIN_VALUE || this.f1197 == Integer.MIN_VALUE || !C0759d.m1251(ˋॱ, Integer.valueOf(this.f1197))) {
                this.f1196 = C0767a.f1189;
                moatAdEvent.f1113 = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f1196 = C0767a.f1191;
            }
        }
        return super.mo1643(moatAdEvent);
    }

    @CallSuper
    /* renamed from: ʼॱ */
    final boolean m1293() throws C0785o {
        if (!mo1650() || m1259()) {
            return false;
        }
        int intValue;
        try {
            int intValue2 = mo1649().intValue();
            if (this.f1195 >= 0 && intValue2 < 0) {
                return false;
            }
            this.f1195 = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            MoatAdEventType moatAdEventType;
            Object obj;
            intValue = mo1646().intValue();
            boolean ॱˎ = mo1651();
            double d = ((double) intValue) / 4.0d;
            double doubleValue = m1261().doubleValue();
            if (intValue2 > this.f1193) {
                this.f1193 = intValue2;
            }
            if (this.f1197 == Integer.MIN_VALUE) {
                this.f1197 = intValue;
            }
            if (!ॱˎ) {
                if (this.f1196 != C0767a.f1192) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                    this.f1196 = C0767a.f1192;
                }
                moatAdEventType = null;
            } else if (this.f1196 == C0767a.f1190) {
                moatAdEventType = MoatAdEventType.AD_EVT_START;
                this.f1196 = C0767a.f1188;
            } else if (this.f1196 == C0767a.f1192) {
                moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                this.f1196 = C0767a.f1188;
            } else {
                intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                if (intValue >= 0 && intValue < 3) {
                    moatAdEventType = ʽ[intValue];
                    if (!this.ʻ.containsKey(moatAdEventType)) {
                        this.ʻ.put(moatAdEventType, Integer.valueOf(1));
                    }
                }
                moatAdEventType = null;
            }
            if (moatAdEventType != null) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null && !Double.isNaN(this.f1194) && Math.abs(this.f1194 - doubleValue) > 0.05d) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                obj = 1;
            }
            if (obj != null) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), m1258()));
            }
            this.f1194 = doubleValue;
            this.f1198 = 0;
            return true;
        } catch (Exception e) {
            intValue = this.f1198;
            this.f1198 = intValue + 1;
            if (intValue < 5) {
                return true;
            }
            return false;
        }
    }
}
