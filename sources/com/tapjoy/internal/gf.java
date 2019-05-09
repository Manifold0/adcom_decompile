package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.facebook.places.model.PlaceFields;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.dx.C2877a;
import com.tapjoy.internal.ed.C2889a;
import com.tapjoy.internal.eh.C2897a;
import com.tapjoy.internal.ek.C2903a;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

public final class gf {
    /* renamed from: a */
    public static final String f7891a = UUID.randomUUID().toString();
    /* renamed from: d */
    private static gf f7892d;
    /* renamed from: b */
    public final C2903a f7893b = new C2903a();
    /* renamed from: c */
    public final gm f7894c;
    /* renamed from: e */
    private final C2889a f7895e = new C2889a();
    /* renamed from: f */
    private final C2877a f7896f = new C2877a();
    /* renamed from: g */
    private final Context f7897g;

    /* renamed from: a */
    public static synchronized gf m7923a(Context context) {
        gf gfVar;
        synchronized (gf.class) {
            if (f7892d == null) {
                f7892d = new gf(context, gm.m7971a(context));
            }
            gfVar = f7892d;
        }
        return gfVar;
    }

    private gf(Context context, gm gmVar) {
        String str;
        Integer valueOf;
        gq.m7983a();
        Context applicationContext = context.getApplicationContext();
        this.f7897g = applicationContext;
        C2889a c2889a = this.f7895e;
        if (fd.m7717b().m7709b(TapjoyConnectFlag.DISABLE_ANDROID_ID_AS_ANALYTICS_ID)) {
            str = null;
        } else {
            str = Secure.getString(applicationContext.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
            if ("9774d56d682e549c".equals(str)) {
                str = null;
            } else {
                str = ct.m7338b(str);
            }
        }
        if (str == null) {
            File file;
            File a = C2996w.m8226a(applicationContext);
            File file2 = new File(gc.m7836c(applicationContext), "deviceid");
            if (a != null) {
                file = new File(a, ".io.5rocks");
            } else {
                file = null;
            }
            str = aa.m7141a(file2, file);
        }
        c2889a.f7495d = str;
        str = ab.m7142a(applicationContext);
        if (str != null) {
            this.f7895e.f7494c = str.replace(":", "").toLowerCase(Locale.US);
        }
        PackageManager packageManager = applicationContext.getPackageManager();
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(PlaceFields.PHONE);
        if (telephonyManager != null) {
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (!ct.m7339c(simCountryIso)) {
                this.f7895e.f7508q = simCountryIso.toUpperCase(Locale.US);
            }
            str = telephonyManager.getNetworkCountryIso();
            if (!ct.m7339c(str)) {
                this.f7895e.f7509r = str.toUpperCase(Locale.US);
            }
        }
        String packageName = applicationContext.getPackageName();
        this.f7895e.f7505n = packageName;
        C2889a c2889a2 = this.f7895e;
        Signature[] e = ae.m7152e(packageManager, packageName);
        if (e == null || e.length <= 0) {
            str = null;
        } else {
            str = Base64.encodeToString(cm.m7332a(e[0].toByteArray()), 2);
        }
        c2889a2.f7506o = ct.m7337a(str);
        this.f7896f.f7401c = ae.m7148a(packageManager, packageName);
        this.f7896f.f7402d = Integer.valueOf(ae.m7149b(packageManager, packageName));
        str = packageManager.getInstallerPackageName(packageName);
        if (!ct.m7339c(str)) {
            this.f7896f.f7404f = str;
        }
        str = m7924a(packageManager, packageName);
        if (!ct.m7339c(str)) {
            this.f7896f.f7405g = str;
        }
        m7926a();
        this.f7894c = gmVar;
        str = this.f7894c.f7933c.m8219a();
        if (str != null && str.length() > 0) {
            this.f7895e.f7507p = str + " 11.12.2/Android";
        }
        str = this.f7894c.m7974b();
        if (str != null) {
            this.f7893b.f7597d = str;
        }
        C2903a c2903a = this.f7893b;
        gm gmVar2 = this.f7894c;
        long j = gmVar2.f7932b.getLong("it", 0);
        if (j == 0) {
            applicationContext = gmVar2.f7931a;
            j = ae.m7150c(applicationContext.getPackageManager(), applicationContext.getPackageName());
            if (j == 0) {
                j = gc.m7837d(gmVar2.f7931a).lastModified();
                if (j == 0) {
                    Context context2 = gmVar2.f7931a;
                    j = new File(ae.m7151d(context2.getPackageManager(), context2.getPackageName())).lastModified();
                    if (j == 0) {
                        j = System.currentTimeMillis();
                    }
                }
            }
            gmVar2.f7932b.edit().putLong("it", j).commit();
        }
        c2903a.f7596c = Long.valueOf(j);
        int b = this.f7894c.f7936f.m8210b();
        this.f7893b.f7598e = Integer.valueOf(m7922a(7, b));
        this.f7893b.f7599f = Integer.valueOf(m7922a(30, b));
        b = this.f7894c.f7938h.m8210b();
        if (b > 0) {
            this.f7893b.f7601h = Integer.valueOf(b);
        }
        j = this.f7894c.f7939i.m8211a();
        if (j > 0) {
            this.f7893b.f7602i = Long.valueOf(j);
        }
        j = this.f7894c.f7940j.m8211a();
        if (j > 0) {
            this.f7893b.f7603j = Long.valueOf(j);
        }
        j = this.f7894c.f7941k.m8211a();
        if (j > 0) {
            this.f7893b.f7604k = Long.valueOf(j);
        }
        str = this.f7894c.f7942l.m8219a();
        if (str != null) {
            this.f7893b.f7605l = str;
        }
        b = this.f7894c.f7943m.m8210b();
        if (b > 0) {
            this.f7893b.f7606m = Integer.valueOf(b);
        }
        double a2 = this.f7894c.f7944n.m8203a();
        if (a2 != 0.0d) {
            this.f7893b.f7607n = Double.valueOf(a2);
        }
        j = this.f7894c.f7945o.m8211a();
        if (j > 0) {
            this.f7893b.f7608o = Long.valueOf(j);
        }
        a2 = this.f7894c.f7946p.m8203a();
        if (a2 != 0.0d) {
            this.f7893b.f7609p = Double.valueOf(a2);
        }
        str = this.f7894c.f7937g.m8219a();
        if (str != null) {
            try {
                ei eiVar = (ei) ei.f7589c.m7393a(Base64.decode(str, 2));
                this.f7893b.f7600g.clear();
                this.f7893b.f7600g.addAll(eiVar.f7590d);
            } catch (IllegalArgumentException e2) {
                this.f7894c.f7937g.m8200c();
            } catch (IOException e3) {
                this.f7894c.f7937g.m8200c();
            }
        }
        this.f7896f.f7403e = this.f7894c.f7947q.m8219a();
        this.f7893b.f7612s = this.f7894c.f7948r.m8219a();
        b = this.f7894c.f7949s.m8207a().intValue();
        C2903a c2903a2 = this.f7893b;
        if (b != -1) {
            valueOf = Integer.valueOf(b);
        } else {
            valueOf = null;
        }
        c2903a2.f7613t = valueOf;
        b = this.f7894c.f7950t.m8207a().intValue();
        c2903a2 = this.f7893b;
        if (b != -1) {
            valueOf = Integer.valueOf(b);
        } else {
            valueOf = null;
        }
        c2903a2.f7614u = valueOf;
        this.f7893b.f7615v = this.f7894c.f7951u.m8219a();
        this.f7893b.f7616w = this.f7894c.f7952v.m8219a();
        this.f7893b.f7617x = this.f7894c.f7953w.m8219a();
        this.f7893b.f7618y = this.f7894c.f7954x.m8219a();
        this.f7893b.f7619z = this.f7894c.f7955y.m8219a();
        str = this.f7894c.f7956z.m8219a();
        if (str != null) {
            try {
                ej ejVar = (ej) ej.f7592c.m7393a(Base64.decode(str, 2));
                this.f7893b.f7594A.clear();
                this.f7893b.f7594A.addAll(ejVar.f7593d);
            } catch (IllegalArgumentException e4) {
                this.f7894c.f7956z.m8200c();
            } catch (IOException e5) {
                this.f7894c.f7956z.m8200c();
            }
        }
        str = this.f7894c.f7927A.m8219a();
        boolean booleanValue = this.f7894c.f7928B.m8201a().booleanValue();
        if (str != null) {
            this.f7893b.f7610q = str;
            this.f7893b.f7611r = Boolean.valueOf(booleanValue);
        } else {
            this.f7893b.f7610q = null;
            this.f7893b.f7611r = null;
        }
        this.f7893b.f7595B = this.f7894c.f7929C.m8201a();
    }

    /* renamed from: a */
    private static String m7924a(PackageManager packageManager, String str) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            if (bundle != null) {
                Object obj = bundle.get("com.tapjoy.appstore");
                if (obj != null) {
                    return obj.toString().trim();
                }
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }

    /* renamed from: a */
    final void m7926a() {
        synchronized (this) {
            try {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) this.f7897g.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                Activity a = fu.m7788a();
                if (a != null) {
                    Window window = a.getWindow();
                    if (window != null) {
                        int i = displayMetrics.heightPixels;
                        Rect rect = new Rect();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        displayMetrics.heightPixels = i - rect.top;
                    }
                }
                this.f7895e.f7500i = Integer.valueOf(displayMetrics.densityDpi);
                this.f7895e.f7501j = Integer.valueOf(displayMetrics.widthPixels);
                this.f7895e.f7502k = Integer.valueOf(displayMetrics.heightPixels);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    public final ee m7932b() {
        ee eeVar;
        synchronized (this) {
            this.f7895e.f7503l = Locale.getDefault().toString();
            this.f7895e.f7504m = TimeZone.getDefault().getID();
            Object obj = null;
            long currentTimeMillis = System.currentTimeMillis() - 259200000;
            Iterator it = this.f7893b.f7600g.iterator();
            while (it.hasNext()) {
                Object obj2;
                if (((eh) it.next()).f7586g.longValue() <= currentTimeMillis) {
                    it.remove();
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                m7925g();
            }
            eeVar = new ee(this.f7895e.m7616b(), this.f7896f.m7591b(), this.f7893b.m7645b());
        }
        return eeVar;
    }

    /* renamed from: c */
    final String m7935c() {
        String a;
        synchronized (this) {
            a = this.f7894c.f7934d.m8219a();
        }
        return a;
    }

    /* renamed from: d */
    public final ef m7937d() {
        ef efVar = null;
        int i = 1;
        synchronized (this) {
            Calendar instance = Calendar.getInstance();
            int i2 = instance.get(5) + (((instance.get(1) * 10000) + (instance.get(2) * 100)) + 100);
            int intValue = this.f7894c.f7935e.m8207a().intValue();
            if (intValue != i2) {
                if (intValue == 0) {
                    this.f7893b.f7598e = Integer.valueOf(1);
                    this.f7893b.f7599f = Integer.valueOf(1);
                    efVar = new ef("fq7_0_1", "fq30_0_1", null);
                } else {
                    long timeInMillis;
                    int i3;
                    int intValue2 = this.f7894c.f7936f.m8207a().intValue();
                    int a = m7922a(7, intValue2);
                    int a2 = m7922a(30, intValue2);
                    Calendar instance2 = Calendar.getInstance();
                    instance2.set(intValue / 10000, ((intValue / 100) % 100) - 1, intValue % 100);
                    int signum = Integer.signum(instance.get(1) - instance2.get(1));
                    Calendar calendar;
                    switch (signum) {
                        case -1:
                            calendar = (Calendar) instance2.clone();
                            calendar.set(instance.get(1), instance.get(2), instance.get(5));
                            instance = calendar;
                            timeInMillis = instance2.getTimeInMillis();
                            break;
                        case 1:
                            calendar = (Calendar) instance.clone();
                            calendar.set(instance2.get(1), instance2.get(2), instance2.get(5));
                            long timeInMillis2 = instance.getTimeInMillis();
                            instance = calendar;
                            timeInMillis = timeInMillis2;
                            break;
                        default:
                            i3 = instance.get(6) - instance2.get(6);
                            break;
                    }
                    intValue = 0;
                    while (instance.getTimeInMillis() < timeInMillis) {
                        instance.add(5, 1);
                        intValue++;
                    }
                    i3 = signum > 0 ? intValue : -intValue;
                    if (Math.abs(i3) >= 30) {
                        i3 = 0;
                    } else if (i3 >= 0) {
                        i3 = intValue2 << i3;
                    } else {
                        i3 = intValue2 >> (-i3);
                    }
                    i3 |= 1;
                    int a3 = m7922a(7, i3);
                    intValue = m7922a(30, i3);
                    this.f7893b.f7598e = Integer.valueOf(a3);
                    this.f7893b.f7599f = Integer.valueOf(intValue);
                    int i4 = i3;
                    efVar = new ef("fq7_" + a + "_" + a3, "fq30_" + a2 + "_" + intValue, null);
                    i = i4;
                }
                this.f7894c.f7935e.m8208a(i2);
                this.f7894c.f7936f.m8208a(i);
            }
        }
        return efVar;
    }

    /* renamed from: a */
    private static int m7922a(int i, int i2) {
        return Integer.bitCount(((1 << i) - 1) & i2);
    }

    /* renamed from: a */
    final boolean m7930a(String str, long j, boolean z) {
        synchronized (this) {
            int size = this.f7893b.f7600g.size();
            int i = 0;
            while (i < size) {
                eh ehVar = (eh) this.f7893b.f7600g.get(i);
                if (!ehVar.f7585f.equals(str)) {
                    i++;
                } else if (z) {
                    C2897a b = ehVar.m7636b();
                    b.f7580d = Long.valueOf(j);
                    this.f7893b.f7600g.set(i, b.m7632b());
                    return true;
                } else {
                    return false;
                }
            }
            this.f7893b.f7600g.add(new eh(str, Long.valueOf(j)));
            m7925g();
            return true;
        }
    }

    /* renamed from: g */
    private void m7925g() {
        this.f7894c.f7937g.m8220a(Base64.encodeToString(ei.f7589c.m7397b(new ei(this.f7893b.f7600g)), 2));
    }

    /* renamed from: a */
    public final boolean m7929a(String str) {
        boolean z = true;
        synchronized (this) {
            this.f7894c.f7947q.m8220a(str);
            if (str != null) {
                if (cr.m7334a(this.f7896f.f7403e, str)) {
                    z = false;
                }
                this.f7896f.f7403e = str;
            } else {
                if (this.f7896f.f7403e == null) {
                    z = false;
                }
                this.f7896f.f7403e = null;
            }
        }
        return z;
    }

    /* renamed from: b */
    public final boolean m7934b(String str) {
        boolean z;
        synchronized (this) {
            this.f7894c.f7948r.m8220a(str);
            z = !cr.m7334a(this.f7893b.f7612s, str);
            if (z) {
                this.f7893b.f7612s = str;
            }
        }
        return z;
    }

    /* renamed from: a */
    public final boolean m7928a(Integer num) {
        boolean z;
        synchronized (this) {
            this.f7894c.f7949s.m8209a(num);
            z = !cr.m7334a(this.f7893b.f7613t, num);
            if (z) {
                this.f7893b.f7613t = num;
            }
        }
        return z;
    }

    /* renamed from: b */
    public final boolean m7933b(Integer num) {
        boolean z;
        synchronized (this) {
            this.f7894c.f7950t.m8209a(num);
            z = !cr.m7334a(this.f7893b.f7614u, num);
            if (z) {
                this.f7893b.f7614u = num;
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final boolean m7927a(int r4, java.lang.String r5) {
        /*
        r3 = this;
        r1 = 1;
        r0 = 0;
        monitor-enter(r3);
        switch(r4) {
            case 1: goto L_0x0008;
            case 2: goto L_0x0024;
            case 3: goto L_0x003d;
            case 4: goto L_0x0056;
            case 5: goto L_0x006f;
            default: goto L_0x0006;
        };
    L_0x0006:
        monitor-exit(r3);	 Catch:{ all -> 0x0021 }
        return r0;
    L_0x0008:
        r2 = r3.f7894c;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7951u;	 Catch:{ all -> 0x0021 }
        r2.m8220a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7615v;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.m7334a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x001a;
    L_0x0019:
        r0 = r1;
    L_0x001a:
        if (r0 == 0) goto L_0x0006;
    L_0x001c:
        r1 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r1.f7615v = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x0021:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0021 }
        throw r0;
    L_0x0024:
        r2 = r3.f7894c;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7952v;	 Catch:{ all -> 0x0021 }
        r2.m8220a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7616w;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.m7334a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x0036;
    L_0x0035:
        r0 = r1;
    L_0x0036:
        if (r0 == 0) goto L_0x0006;
    L_0x0038:
        r1 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r1.f7616w = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x003d:
        r2 = r3.f7894c;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7953w;	 Catch:{ all -> 0x0021 }
        r2.m8220a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7617x;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.m7334a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x004f;
    L_0x004e:
        r0 = r1;
    L_0x004f:
        if (r0 == 0) goto L_0x0006;
    L_0x0051:
        r1 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r1.f7617x = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x0056:
        r2 = r3.f7894c;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7954x;	 Catch:{ all -> 0x0021 }
        r2.m8220a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7618y;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.m7334a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x0068;
    L_0x0067:
        r0 = r1;
    L_0x0068:
        if (r0 == 0) goto L_0x0006;
    L_0x006a:
        r1 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r1.f7618y = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
    L_0x006f:
        r2 = r3.f7894c;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7955y;	 Catch:{ all -> 0x0021 }
        r2.m8220a(r5);	 Catch:{ all -> 0x0021 }
        r2 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r2 = r2.f7619z;	 Catch:{ all -> 0x0021 }
        r2 = com.tapjoy.internal.cr.m7334a(r2, r5);	 Catch:{ all -> 0x0021 }
        if (r2 != 0) goto L_0x0081;
    L_0x0080:
        r0 = r1;
    L_0x0081:
        if (r0 == 0) goto L_0x0006;
    L_0x0083:
        r1 = r3.f7893b;	 Catch:{ all -> 0x0021 }
        r1.f7619z = r5;	 Catch:{ all -> 0x0021 }
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.gf.a(int, java.lang.String):boolean");
    }

    /* renamed from: e */
    public final Set m7938e() {
        Set hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.f7893b.f7594A);
        }
        return hashSet;
    }

    /* renamed from: c */
    public final boolean m7936c(String str) {
        synchronized (this) {
            for (int size = this.f7893b.f7600g.size() - 1; size >= 0; size--) {
                eh ehVar = (eh) this.f7893b.f7600g.get(size);
                if (ehVar.f7585f.equals(str)) {
                    C2897a b = ehVar.m7636b();
                    b.f7581e = Long.valueOf(System.currentTimeMillis());
                    this.f7893b.f7600g.set(size, b.m7632b());
                    m7925g();
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: f */
    public final boolean m7939f() {
        return ((Boolean) cr.m7335b(this.f7893b.f7595B, ek.f7635r)).booleanValue();
    }

    /* renamed from: a */
    public final boolean m7931a(boolean z) {
        boolean z2;
        synchronized (this) {
            this.f7894c.f7929C.m8202a(z);
            z2 = z != ((Boolean) cr.m7335b(this.f7893b.f7595B, ek.f7635r)).booleanValue();
            this.f7893b.f7595B = Boolean.valueOf(z);
        }
        return z2;
    }
}
