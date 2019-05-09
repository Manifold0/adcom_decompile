package com.facebook.ads.internal.p028u;

import com.facebook.ads.internal.protocol.C2068c;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.facebook.ads.internal.u.a */
public class C2121a {
    /* renamed from: a */
    private static Map<String, Long> f4901a = new ConcurrentHashMap();
    /* renamed from: b */
    private static Map<String, Long> f4902b = new ConcurrentHashMap();
    /* renamed from: c */
    private static Map<String, String> f4903c = new ConcurrentHashMap();

    /* renamed from: a */
    public static void m5384a(long j, C2122b c2122b) {
        f4901a.put(C2121a.m5389d(c2122b), Long.valueOf(j));
    }

    /* renamed from: a */
    public static void m5385a(String str, C2122b c2122b) {
        f4903c.put(C2121a.m5389d(c2122b), str);
    }

    /* renamed from: a */
    public static boolean m5386a(C2122b c2122b) {
        String d = C2121a.m5389d(c2122b);
        if (!f4902b.containsKey(d)) {
            return false;
        }
        long j;
        long longValue = ((Long) f4902b.get(d)).longValue();
        C2068c c = c2122b.m5393c();
        if (!f4901a.containsKey(d)) {
            switch (c) {
                case BANNER:
                    j = 15000;
                    break;
                case INTERSTITIAL:
                case NATIVE:
                    j = -1000;
                    break;
                default:
                    j = -1000;
                    break;
            }
        }
        j = ((Long) f4901a.get(d)).longValue();
        return System.currentTimeMillis() - longValue < j;
    }

    /* renamed from: b */
    public static void m5387b(C2122b c2122b) {
        f4902b.put(C2121a.m5389d(c2122b), Long.valueOf(System.currentTimeMillis()));
    }

    /* renamed from: c */
    public static String m5388c(C2122b c2122b) {
        return (String) f4903c.get(C2121a.m5389d(c2122b));
    }

    /* renamed from: d */
    private static String m5389d(C2122b c2122b) {
        int i = 0;
        Locale locale = Locale.US;
        String str = "%s:%s:%s:%d:%d:%d";
        Object[] objArr = new Object[6];
        objArr[0] = c2122b.m5392b();
        objArr[1] = c2122b.m5393c();
        objArr[2] = c2122b.m5391a();
        objArr[3] = Integer.valueOf(c2122b.m5394d() == null ? 0 : c2122b.m5394d().m6648a());
        if (c2122b.m5394d() != null) {
            i = c2122b.m5394d().m6649b();
        }
        objArr[4] = Integer.valueOf(i);
        objArr[5] = Integer.valueOf(c2122b.m5395e());
        return String.format(locale, str, objArr);
    }
}
