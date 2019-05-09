package com.facebook.ads.internal.p042l;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p026b.C2570d;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.l.b */
public class C2043b {
    /* renamed from: a */
    public static final String f4560a = VERSION.RELEASE;
    /* renamed from: b */
    private final Context f4561b;
    /* renamed from: c */
    private final AtomicBoolean f4562c = new AtomicBoolean();

    public C2043b(Context context) {
        this.f4561b = context.getApplicationContext();
    }

    /* renamed from: a */
    public String m4953a() {
        return (Build.MANUFACTURER == null || Build.MANUFACTURER.length() <= 0) ? "" : Build.MANUFACTURER;
    }

    /* renamed from: b */
    public String m4954b() {
        return (Build.MODEL == null || Build.MODEL.length() <= 0) ? "" : Build.MODEL;
    }

    /* renamed from: c */
    public String m4955c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f4561b.getSystemService(PlaceFields.PHONE);
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null && networkOperatorName.length() > 0) {
                return networkOperatorName;
            }
        }
        return "";
    }

    /* renamed from: d */
    public String m4956d() {
        try {
            CharSequence applicationLabel = this.f4561b.getPackageManager().getApplicationLabel(this.f4561b.getPackageManager().getApplicationInfo(m4958f(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                return applicationLabel.toString();
            }
        } catch (NameNotFoundException e) {
        }
        return "";
    }

    /* renamed from: e */
    public String m4957e() {
        try {
            String f = m4958f();
            if (f != null && f.length() >= 0) {
                f = this.f4561b.getPackageManager().getInstallerPackageName(f);
                if (f != null && f.length() > 0) {
                    return f;
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /* renamed from: f */
    public String m4958f() {
        PendingIntent activity = PendingIntent.getActivity(this.f4561b, 0, new Intent(), 0);
        if (activity != null) {
            return VERSION.SDK_INT >= 17 ? activity.getCreatorPackage() : activity.getTargetPackage();
        } else {
            if (!this.f4562c.getAndSet(true)) {
                C2625a.m6741b(this.f4561b, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C2626b.f6511B, new Exception("PI_NULL"));
            }
            return "";
        }
    }

    /* renamed from: g */
    public String m4959g() {
        Object obj = null;
        try {
            obj = this.f4561b.getPackageManager().getPackageInfo(m4958f(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return !TextUtils.isEmpty(obj) ? obj : "";
    }

    /* renamed from: h */
    public int m4960h() {
        int i = 0;
        try {
            return this.f4561b.getPackageManager().getPackageInfo(m4958f(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    /* renamed from: i */
    public boolean m4961i() {
        return this.f4561b.checkCallingOrSelfPermission("android.permission.BIND_ACCESSIBILITY_SERVICE") == 0;
    }

    /* renamed from: j */
    public int m4962j() {
        return C2570d.m6623a(this.f4561b);
    }
}
