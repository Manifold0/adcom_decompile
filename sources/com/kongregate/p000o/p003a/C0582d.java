package com.kongregate.p000o.p003a;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.kongregate.android.internal.util.C0562j;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.a.d */
public class C0582d {

    /* renamed from: com.kongregate.o.a.d$b */
    public interface C0532b {
        /* renamed from: a */
        void mo1241a(String str, String str2);

        /* renamed from: b */
        void mo1242b(String str, String str2);
    }

    /* renamed from: com.kongregate.o.a.d$c */
    public interface C0534c {
        /* renamed from: a */
        void mo1243a(JSONObject jSONObject);
    }

    /* renamed from: com.kongregate.o.a.d$a */
    public class C0581a {
        /* renamed from: a */
        final /* synthetic */ C0582d f801a;

        public C0581a(C0582d c0582d) {
            this.f801a = c0582d;
        }

        /* renamed from: a */
        public void m845a(String str) {
        }
    }

    /* renamed from: a */
    public static String m846a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            C0562j.m761d("validateDeltaConfig: Package info unavaliable. Can not verify config.");
        } else if (packageInfo.activities == null) {
            C0562j.m753a("validateDeltaConfig: no activities specified.");
        }
        return null;
    }

    public C0582d(Context context) {
    }

    /* renamed from: a */
    public void m849a(Activity activity, Application application, Map<String, Object> map, String str) {
    }

    /* renamed from: a */
    public void m848a(Activity activity) {
    }

    /* renamed from: b */
    public void m857b(Activity activity) {
    }

    /* renamed from: a */
    public void m852a(String str) {
    }

    /* renamed from: a */
    public void m854a(String str, Map<String, Object> map) {
    }

    /* renamed from: a */
    public void m853a(String str, double d, Map<String, Object> map, String str2, String str3) {
    }

    /* renamed from: a */
    public Map<String, Object> m847a() {
        return new HashMap();
    }

    /* renamed from: b */
    public Object m856b(String str) {
        return new HashMap();
    }

    /* renamed from: a */
    public void m855a(Map<String, String> map) {
    }

    /* renamed from: a */
    public void m850a(C0532b c0532b) {
    }

    /* renamed from: a */
    public void m851a(C0534c c0534c) {
    }

    /* renamed from: b */
    public void m858b(Map<String, Object> map) {
    }

    /* renamed from: c */
    public void m859c(String str) {
    }
}
