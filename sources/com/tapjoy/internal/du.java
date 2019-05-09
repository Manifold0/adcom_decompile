package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import com.tapjoy.TJAwardCurrencyListener;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJSetUserIDListener;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJVideoListener;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public abstract class du {
    /* renamed from: b */
    private static final du f7381b;
    /* renamed from: c */
    private static du f7382c;
    /* renamed from: a */
    protected volatile boolean f7383a = false;

    /* renamed from: a */
    public abstract TJPlacement mo6218a(String str, TJPlacementListener tJPlacementListener);

    /* renamed from: a */
    public abstract void mo6219a(float f);

    /* renamed from: a */
    public abstract void mo6220a(int i);

    /* renamed from: a */
    public abstract void mo6221a(int i, TJAwardCurrencyListener tJAwardCurrencyListener);

    /* renamed from: a */
    public abstract void mo6222a(int i, TJSpendCurrencyListener tJSpendCurrencyListener);

    /* renamed from: a */
    public abstract void mo6223a(int i, String str);

    /* renamed from: a */
    public abstract void mo6224a(Activity activity);

    /* renamed from: a */
    public abstract void mo6225a(GLSurfaceView gLSurfaceView);

    /* renamed from: a */
    public abstract void mo6226a(TJEarnedCurrencyListener tJEarnedCurrencyListener);

    /* renamed from: a */
    public abstract void mo6227a(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener);

    /* renamed from: a */
    public abstract void mo6228a(TJVideoListener tJVideoListener);

    /* renamed from: a */
    public abstract void mo6229a(String str);

    /* renamed from: a */
    public abstract void mo6230a(String str, long j);

    /* renamed from: a */
    public abstract void mo6231a(String str, TJSetUserIDListener tJSetUserIDListener);

    /* renamed from: a */
    public abstract void mo6232a(String str, String str2);

    /* renamed from: a */
    public abstract void mo6233a(String str, String str2, double d, String str3);

    /* renamed from: a */
    public abstract void mo6234a(String str, String str2, long j);

    /* renamed from: a */
    public abstract void mo6235a(String str, String str2, String str3, String str4);

    /* renamed from: a */
    public abstract void mo6236a(String str, String str2, String str3, String str4, long j);

    /* renamed from: a */
    public abstract void mo6237a(String str, String str2, String str3, String str4, String str5, long j);

    /* renamed from: a */
    public abstract void mo6238a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2);

    /* renamed from: a */
    public abstract void mo6239a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3);

    /* renamed from: a */
    public abstract void mo6240a(String str, String str2, String str3, String str4, Map map);

    /* renamed from: a */
    public abstract void mo6241a(Set set);

    /* renamed from: a */
    public abstract void mo6242a(boolean z);

    /* renamed from: a */
    public abstract boolean mo6243a(Context context, String str);

    /* renamed from: a */
    public abstract boolean mo6244a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener);

    /* renamed from: b */
    public abstract String mo6245b();

    /* renamed from: b */
    public abstract void mo6246b(int i);

    /* renamed from: b */
    public abstract void mo6247b(Activity activity);

    /* renamed from: b */
    public abstract void mo6248b(String str);

    /* renamed from: b */
    public abstract void mo6249b(String str, String str2, String str3, String str4);

    /* renamed from: b */
    public abstract void mo6250b(boolean z);

    /* renamed from: c */
    public abstract float mo6251c();

    /* renamed from: c */
    public abstract void mo6252c(Activity activity);

    /* renamed from: c */
    public abstract void mo6253c(String str);

    /* renamed from: c */
    public abstract void mo6254c(boolean z);

    /* renamed from: d */
    public abstract void mo6255d();

    /* renamed from: d */
    public abstract void mo6256d(String str);

    /* renamed from: e */
    public abstract void mo6257e();

    /* renamed from: e */
    public abstract void mo6258e(String str);

    /* renamed from: f */
    public abstract Set mo6259f();

    /* renamed from: f */
    public abstract void mo6260f(String str);

    /* renamed from: g */
    public abstract String mo6261g(String str);

    /* renamed from: g */
    public abstract void mo6262g();

    /* renamed from: h */
    public abstract void mo6263h(String str);

    /* renamed from: h */
    public abstract boolean mo6264h();

    /* renamed from: i */
    public abstract boolean mo6265i();

    du() {
    }

    static {
        fa.m7703a();
        es.m7684a();
        du dwVar = new dw();
        f7381b = dwVar;
        f7382c = dwVar;
    }

    /* renamed from: a */
    public static du m7479a() {
        return f7382c;
    }
}
