package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.File;
import java.io.IOException;

/* renamed from: com.chartboost.sdk.impl.s */
public class C1454s {
    /* renamed from: b */
    private static C1454s f3321b = new C1454s(new Handler(Looper.getMainLooper()));
    /* renamed from: a */
    public final Handler f3322a;

    public C1454s(Handler handler) {
        this.f3322a = handler;
    }

    /* renamed from: a */
    public static C1454s m3627a() {
        return f3321b;
    }

    /* renamed from: b */
    public File m3632b() {
        return Environment.getExternalStorageDirectory();
    }

    /* renamed from: c */
    public String m3633c() {
        return Environment.getExternalStorageState();
    }

    /* renamed from: a */
    public boolean m3630a(int i) {
        return VERSION.SDK_INT >= i;
    }

    /* renamed from: d */
    public String m3634d() {
        return VERSION.RELEASE;
    }

    /* renamed from: a */
    public Info m3629a(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException, IllegalStateException {
        return AdvertisingIdClient.getAdvertisingIdInfo(context);
    }

    /* renamed from: e */
    public boolean m3635e() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: a */
    public boolean m3631a(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence);
    }

    /* renamed from: a */
    public Bitmap m3628a(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, null);
    }
}
