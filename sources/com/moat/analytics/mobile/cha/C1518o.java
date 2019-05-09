package com.moat.analytics.mobile.cha;

import android.os.Build.VERSION;
import android.util.Base64;
import android.util.Log;
import com.moat.analytics.mobile.cha.C1514m.C15132;
import com.moat.analytics.mobile.cha.C1526r.C1525e;
import com.moat.analytics.mobile.cha.C1536t.C1532a;
import java.net.URLEncoder;
import java.util.Locale;

/* renamed from: com.moat.analytics.mobile.cha.o */
final class C1518o extends Exception {
    /* renamed from: ˋ */
    private static Exception f3554 = null;
    /* renamed from: ˏ */
    private static final Long f3555 = Long.valueOf(60000);
    /* renamed from: ॱ */
    private static Long f3556;

    C1518o(String str) {
        super(str);
    }

    /* renamed from: ˎ */
    static String m3839(String str, Exception exception) {
        if (exception instanceof C1518o) {
            return str + " failed: " + exception.getMessage();
        }
        return str + " failed unexpectedly";
    }

    /* renamed from: ˎ */
    static void m3840(Exception exception) {
        if (C1536t.m3887().f3611) {
            Log.e("MoatException", Log.getStackTraceString(exception));
        } else {
            C1518o.m3838(exception);
        }
    }

    /* renamed from: ˋ */
    private static void m3838(Exception exception) {
        int i = 1;
        try {
            if (C1536t.m3887().f3610 == C1532a.f3592) {
                int i2 = C1536t.m3887().f3613;
                if (i2 != 0) {
                    if (i2 >= 100 || ((double) i2) / 100.0d >= Math.random()) {
                        String str = "";
                        String str2 = "";
                        String str3 = "";
                        String str4 = "";
                        StringBuilder stringBuilder = new StringBuilder("https://px.moatads.com/pixel.gif?e=0&i=MOATSDK1&ac=1");
                        StringBuilder stringBuilder2 = new StringBuilder("&zt=");
                        if (!(exception instanceof C1518o)) {
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
                            str = "CHA";
                            stringBuilder.append("&zMoatMMAKv=35d482907bc2811c2e46b96f16eb5f9fe00185f3");
                            str3 = "2.4.1";
                            C1525e ˊ = C1526r.m3860();
                            stringBuilder.append("&zMoatMMAKan=" + ˊ.m3855());
                            str2 = ˊ.m3854();
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
                        if (f3556 == null || valueOf.longValue() - f3556.longValue() > f3555.longValue()) {
                            new C15132(stringBuilder.toString()).start();
                            f3556 = valueOf;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            f3554 = exception;
        } catch (Exception e3) {
        }
    }

    /* renamed from: ॱ */
    static void m3841() {
        if (f3554 != null) {
            C1518o.m3838(f3554);
            f3554 = null;
        }
    }
}
