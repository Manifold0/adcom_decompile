package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.h */
abstract class C0837h<PlayerOrIMAAd> extends C0827c<PlayerOrIMAAd> {
    /* renamed from: l */
    int f1386l = Integer.MIN_VALUE;
    /* renamed from: m */
    private C0836a f1387m = C0836a.UNINITIALIZED;
    /* renamed from: n */
    private int f1388n = Integer.MIN_VALUE;
    /* renamed from: o */
    private double f1389o = Double.NaN;
    /* renamed from: p */
    private int f1390p = Integer.MIN_VALUE;
    /* renamed from: q */
    private int f1391q = 0;

    /* renamed from: com.moat.analytics.mobile.vng.h$1 */
    class C08351 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0837h f1379a;

        C08351(C0837h c0837h) {
            this.f1379a = c0837h;
        }

        public void run() {
            try {
                if (this.f1379a.j.get() == null || this.f1379a.m1467i()) {
                    this.f1379a.m1466h();
                } else if (Boolean.valueOf(this.f1379a.m1485m()).booleanValue()) {
                    this.f1379a.h.postDelayed(this, 200);
                } else {
                    this.f1379a.m1466h();
                }
            } catch (Exception e) {
                this.f1379a.m1466h();
                C0849m.m1543a(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.h$a */
    protected enum C0836a {
        UNINITIALIZED,
        PAUSED,
        PLAYING,
        STOPPED,
        COMPLETED
    }

    C0837h(String str) {
        super(str);
    }

    /* renamed from: n */
    private void m1479n() {
        this.h.postDelayed(new C08351(this), 200);
    }

    /* renamed from: a */
    protected JSONObject mo1666a(MoatAdEvent moatAdEvent) {
        Integer j;
        if (moatAdEvent.f1328b.equals(MoatAdEvent.f1326a)) {
            try {
                j = mo1668j();
            } catch (Exception e) {
                j = Integer.valueOf(this.f1388n);
            }
            moatAdEvent.f1328b = j;
        } else {
            j = moatAdEvent.f1328b;
        }
        if (moatAdEvent.f1328b.intValue() < 0 || (moatAdEvent.f1328b.intValue() == 0 && moatAdEvent.f1330d == MoatAdEventType.AD_EVT_COMPLETE && this.f1388n > 0)) {
            j = Integer.valueOf(this.f1388n);
            moatAdEvent.f1328b = j;
        }
        if (moatAdEvent.f1330d == MoatAdEventType.AD_EVT_COMPLETE) {
            if (j.intValue() == Integer.MIN_VALUE || this.f1386l == Integer.MIN_VALUE || !m1463a(j, Integer.valueOf(this.f1386l))) {
                this.f1387m = C0836a.STOPPED;
                moatAdEvent.f1330d = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f1387m = C0836a.COMPLETED;
            }
        }
        return super.mo1666a(moatAdEvent);
    }

    /* renamed from: a */
    public boolean mo1667a(Map<String, String> map, PlayerOrIMAAd playerOrIMAAd, View view) {
        try {
            if (!this.e) {
                m1479n();
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
        return super.mo1667a(map, playerOrIMAAd, view);
    }

    /* renamed from: j */
    protected abstract Integer mo1668j();

    /* renamed from: k */
    protected abstract boolean mo1680k();

    /* renamed from: l */
    protected abstract Integer mo1681l();

    /* renamed from: m */
    boolean m1485m() {
        if (this.j.get() == null || m1467i()) {
            return false;
        }
        int intValue;
        try {
            int intValue2 = mo1668j().intValue();
            if (this.f1388n >= 0 && intValue2 < 0) {
                return false;
            }
            this.f1388n = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            intValue = mo1681l().intValue();
            boolean k = mo1680k();
            double d = ((double) intValue) / 4.0d;
            double a = C0862s.m1586a();
            MoatAdEventType moatAdEventType = null;
            if (intValue2 > this.f1390p) {
                this.f1390p = intValue2;
            }
            if (this.f1386l == Integer.MIN_VALUE) {
                this.f1386l = intValue;
            }
            if (k) {
                if (this.f1387m == C0836a.UNINITIALIZED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_START;
                    this.f1387m = C0836a.PLAYING;
                } else if (this.f1387m == C0836a.PAUSED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                    this.f1387m = C0836a.PLAYING;
                } else {
                    MoatAdEventType moatAdEventType2;
                    intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                    if (intValue > -1 && intValue < 3) {
                        moatAdEventType2 = f[intValue];
                        if (!this.g.containsKey(moatAdEventType2)) {
                            this.g.put(moatAdEventType2, Integer.valueOf(1));
                            moatAdEventType = moatAdEventType2;
                        }
                    }
                    moatAdEventType2 = null;
                    moatAdEventType = moatAdEventType2;
                }
            } else if (this.f1387m != C0836a.PAUSED) {
                moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                this.f1387m = C0836a.PAUSED;
            }
            boolean z = moatAdEventType != null;
            if (!(z || Double.isNaN(this.f1389o) || Math.abs(this.f1389o - a) <= 0.05d)) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                z = true;
            }
            if (z) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), Double.valueOf(a)));
            }
            this.f1389o = a;
            this.f1391q = 0;
            return true;
        } catch (Exception e) {
            intValue = this.f1391q;
            this.f1391q = intValue + 1;
            return intValue < 5;
        }
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            C0858p.m1579a("[SUCCESS] ", mo1662a() + " stopTracking succeeded for " + m1453e());
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }
}
