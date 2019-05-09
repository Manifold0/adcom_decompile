// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.os.Environment;
import java.io.File;
import android.text.TextUtils;
import android.os.Build$VERSION;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.Context;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.Looper;
import android.os.Handler;

public class s
{
    private static s b;
    public final Handler a;
    
    static {
        s.b = new s(new Handler(Looper.getMainLooper()));
    }
    
    public s(final Handler a) {
        this.a = a;
    }
    
    public static s a() {
        return s.b;
    }
    
    public Bitmap a(final byte[] array) {
        return BitmapFactory.decodeByteArray(array, 0, array.length, (BitmapFactory$Options)null);
    }
    
    public AdvertisingIdClient$Info a(final Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException, IllegalStateException {
        return AdvertisingIdClient.getAdvertisingIdInfo(context);
    }
    
    public boolean a(final int n) {
        return Build$VERSION.SDK_INT >= n;
    }
    
    public boolean a(final CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence);
    }
    
    public File b() {
        return Environment.getExternalStorageDirectory();
    }
    
    public String c() {
        return Environment.getExternalStorageState();
    }
    
    public String d() {
        return Build$VERSION.RELEASE;
    }
    
    public boolean e() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
