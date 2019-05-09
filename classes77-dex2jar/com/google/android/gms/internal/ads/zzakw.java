// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.SharedPreferences$Editor;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import android.webkit.WebSettings;
import android.renderscript.Allocation;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import java.util.concurrent.Callable;
import com.google.android.gms.common.util.ClientLibraryUtils;
import android.text.TextUtils;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(17)
public class zzakw extends zzaky
{
    @Override
    public final String getDefaultUserAgent(final Context context) {
        final zzamn zzsb = zzamn.zzsb();
        if (TextUtils.isEmpty((CharSequence)zzsb.zzcpq)) {
            String zzcpq;
            if (ClientLibraryUtils.isPackageSide()) {
                zzcpq = (String)zzaml.zza(context, (Callable)new zzamo(zzsb, context));
            }
            else {
                zzcpq = (String)zzaml.zza(context, (Callable)new zzamp(zzsb, GooglePlayServicesUtilLight.getRemoteContext(context), context));
            }
            zzsb.zzcpq = zzcpq;
        }
        return zzsb.zzcpq;
    }
    
    @Override
    public final Drawable zza(final Context context, final Bitmap bitmap, final boolean b, final float radius) {
        if (!b || radius <= 0.0f || radius > 25.0f) {
            return (Drawable)new BitmapDrawable(context.getResources(), bitmap);
        }
        try {
            final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
            final Bitmap bitmap2 = Bitmap.createBitmap(scaledBitmap);
            final RenderScript create = RenderScript.create(context);
            final ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
            final Allocation fromBitmap = Allocation.createFromBitmap(create, scaledBitmap);
            final Allocation fromBitmap2 = Allocation.createFromBitmap(create, bitmap2);
            create2.setRadius(radius);
            create2.setInput(fromBitmap);
            create2.forEach(fromBitmap2);
            fromBitmap2.copyTo(bitmap2);
            return (Drawable)new BitmapDrawable(context.getResources(), bitmap2);
        }
        catch (RuntimeException ex) {
            return (Drawable)new BitmapDrawable(context.getResources(), bitmap);
        }
    }
    
    @Override
    public final boolean zza(final Context context, final WebSettings webSettings) {
        super.zza(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        return true;
    }
    
    @Override
    public final void zzaw(final Context context) {
        final zzamn zzsb = zzamn.zzsb();
        zzakb.v("Updating user agent.");
        final String defaultUserAgent = WebSettings.getDefaultUserAgent(context);
        if (!defaultUserAgent.equals(zzsb.zzcpq)) {
            final Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (ClientLibraryUtils.isPackageSide() || remoteContext == null) {
                final SharedPreferences$Editor putString = context.getSharedPreferences("admob_user_agent", 0).edit().putString("user_agent", WebSettings.getDefaultUserAgent(context));
                if (remoteContext == null) {
                    putString.apply();
                }
                else {
                    SharedPreferencesUtils.publishWorldReadableSharedPreferences(context, putString, "admob_user_agent");
                }
            }
            zzsb.zzcpq = defaultUserAgent;
        }
        zzakb.v("User agent is updated.");
    }
}
