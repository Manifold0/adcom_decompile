package com.moat.analytics.mobile.iro;

import android.os.Build.VERSION;
import android.util.Base64;
import android.util.Log;
import com.moat.analytics.mobile.iro.C0779l.C07781;
import com.moat.analytics.mobile.iro.C0789p.C0788b;
import com.moat.analytics.mobile.iro.C0803t.C0800c;
import java.net.URLEncoder;
import java.util.Locale;

/* renamed from: com.moat.analytics.mobile.iro.o */
final class C0785o extends Exception {
    /* renamed from: ˋ */
    private static Exception f1246 = null;
    /* renamed from: ˎ */
    private static Long f1247;
    /* renamed from: ˏ */
    private static final Long f1248 = Long.valueOf(60000);

    C0785o(String str) {
        super(str);
    }

    /* renamed from: ॱ */
    static String m1350(String str, Exception exception) {
        if (exception instanceof C0785o) {
            return str + " failed: " + exception.getMessage();
        }
        return str + " failed unexpectedly";
    }

    /* renamed from: ॱ */
    static void m1351(Exception exception) {
        if (C0803t.m1393().f1300) {
            Log.e("MoatException", Log.getStackTraceString(exception));
        } else {
            C0785o.m1349(exception);
        }
    }

    /* renamed from: ˊ */
    private static void m1349(Exception exception) {
        int i = 1;
        try {
            if (C0803t.m1393().f1298 == C0800c.f1286) {
                int i2 = C0803t.m1393().f1296;
                if (i2 != 0) {
                    if (i2 >= 100 || ((double) i2) / 100.0d >= Math.random()) {
                        String str = "";
                        String str2 = "";
                        String str3 = "";
                        String str4 = "";
                        StringBuilder stringBuilder = new StringBuilder("https://px.moatads.com/pixel.gif?e=0&i=MOATSDK1&ac=1");
                        StringBuilder stringBuilder2 = new StringBuilder("&zt=");
                        if (!(exception instanceof C0785o)) {
                            i = 0;
                        }
                        stringBuilder.append(stringBuilder2.append(i).toString());
                        stringBuilder.append("&zr=" + i2);
                        try {
                            String str5;
                            StringBuilder stringBuilder3 = new StringBuilder("&zm=");
                            if (exception.getMessage() == null) {
                                str5 = "null";
                            } else {
                                str5 = URLEncoder.encode(Base64.encodeToString(exception.getMessage().getBytes("UTF-8"), 0), "UTF-8");
                            }
                            stringBuilder.append(stringBuilder3.append(str5).toString());
                            stringBuilder.append("&k=" + URLEncoder.encode(Base64.encodeToString(Log.getStackTraceString(exception).getBytes("UTF-8"), 0), "UTF-8"));
                        } catch (Exception e) {
                        }
                        try {
                            str = "IRO";
                            stringBuilder.append("&zMoatMMAKv=2bc3418b93f01686fcbd1ebebcc04694651821b2");
                            str3 = "2.4.0";
                            C0788b ˎ = C0789p.m1362();
                            stringBuilder.append("&zMoatMMAKan=" + ˎ.m1354());
                            str2 = ˎ.m1353();
                            str4 = str;
                            str = str2;
                            str2 = str3;
                            str3 = Integer.toString(VERSION.SDK_INT);
                        } catch (Exception e2) {
                            String str6 = str4;
                            str4 = str;
                            str = str2;
                            str2 = str3;
                            str3 = str6;
                        }
                        stringBuilder.append("&d=Android:" + str4 + ":" + str + ":-");
                        stringBuilder.append("&bo=" + str2);
                        stringBuilder.append("&bd=" + str3);
                        Long valueOf = Long.valueOf(System.currentTimeMillis());
                        stringBuilder.append("&t=" + valueOf);
                        stringBuilder.append("&de=" + String.format(Locale.ROOT, "%.0f", new Object[]{Double.valueOf(Math.floor(Math.random() * Math.pow(10.0d, 12.0d)))}));
                        stringBuilder.append("&cs=0");
                        if (f1247 == null || valueOf.longValue() - f1247.longValue() > f1248.longValue()) {
                            new C07781(stringBuilder.toString()).start();
                            f1247 = valueOf;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            f1246 = exception;
        } catch (Exception e3) {
        }
    }

    /* renamed from: ˊ */
    static void m1348() {
        if (f1246 != null) {
            C0785o.m1349(f1246);
            f1246 = null;
        }
    }
}
