// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzajh;
import android.view.View$OnClickListener;
import com.google.android.gms.internal.ads.zzpx;
import android.os.IBinder;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.ads.zzasd;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzoj;
import com.google.android.gms.internal.ads.zzon;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.view.View;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.internal.ads.zzxe;
import com.google.android.gms.internal.ads.zzoq;
import java.util.List;
import org.json.JSONArray;
import com.google.android.gms.internal.ads.zzoo;
import org.json.JSONException;
import java.util.Iterator;
import android.text.TextUtils;
import org.json.JSONObject;
import android.os.Bundle;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzpw;
import android.util.Base64;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import com.google.android.gms.internal.ads.zzakb;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.internal.ads.zzyc;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzxz;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@ParametersAreNonnullByDefault
public final class zzas
{
    @VisibleForTesting
    static zzv<zzaqw> zza(@Nullable final zzxz zzxz, @Nullable final zzyc zzyc, final zzac zzac) {
        return new zzax(zzxz, zzac, zzyc);
    }
    
    private static String zza(@Nullable final Bitmap bitmap) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzakb.zzdk("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(Bitmap$CompressFormat.PNG, 100, (OutputStream)byteArrayOutputStream);
        final String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        final String value = String.valueOf("data:image/png;base64,");
        final String value2 = String.valueOf(encodeToString);
        if (value2.length() != 0) {
            return value.concat(value2);
        }
        return new String(value);
    }
    
    @VisibleForTesting
    private static String zza(@Nullable final zzpw zzpw) {
        if (zzpw == null) {
            zzakb.zzdk("Image is null. Returning empty string");
            return "";
        }
        try {
            final Uri uri = zzpw.getUri();
            if (uri != null) {
                return uri.toString();
            }
        }
        catch (RemoteException ex) {
            zzakb.zzdk("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzpw);
    }
    
    private static JSONObject zza(@Nullable final Bundle bundle, final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty((CharSequence)s)) {
            return jsonObject;
        }
        final JSONObject jsonObject2 = new JSONObject(s);
        final Iterator keys = jsonObject2.keys();
        while (keys.hasNext()) {
            final String s2 = keys.next();
            if (bundle.containsKey(s2)) {
                if ("image".equals(jsonObject2.getString(s2))) {
                    final Object value = bundle.get(s2);
                    if (value instanceof Bitmap) {
                        jsonObject.put(s2, (Object)zza((Bitmap)value));
                    }
                    else {
                        zzakb.zzdk("Invalid type. An image type extra should return a bitmap");
                    }
                }
                else if (bundle.get(s2) instanceof Bitmap) {
                    zzakb.zzdk("Invalid asset type. Bitmap should be returned only for image type");
                }
                else {
                    jsonObject.put(s2, (Object)String.valueOf(bundle.get(s2)));
                }
            }
        }
        return jsonObject;
    }
    
    public static boolean zza(final zzaqw zzaqw, final zzxe zzxe, final CountDownLatch countDownLatch) {
        while (true) {
            while (true) {
                Label_0602: {
                    while (true) {
                        Label_0596: {
                            try {
                                final View view = zzaqw.getView();
                                boolean b;
                                if (view == null) {
                                    zzakb.zzdk("AdWebView is null");
                                    b = false;
                                }
                                else {
                                    view.setVisibility(4);
                                    final List<String> zzbsi = zzxe.zzbtw.zzbsi;
                                    if (zzbsi == null || zzbsi.isEmpty()) {
                                        zzakb.zzdk("No template ids present in mediation response");
                                        b = false;
                                    }
                                    else {
                                        zzaqw.zza("/nativeExpressAssetsLoaded", new zzav(countDownLatch));
                                        zzaqw.zza("/nativeExpressAssetsLoadingFailed", new zzaw(countDownLatch));
                                        final zzxz zzmo = zzxe.zzbtx.zzmo();
                                        final zzyc zzmp = zzxe.zzbtx.zzmp();
                                        if (zzbsi.contains("2") && zzmo != null) {
                                            final String headline = zzmo.getHeadline();
                                            final List images = zzmo.getImages();
                                            final String body = zzmo.getBody();
                                            final zzpw zzjz = zzmo.zzjz();
                                            final String callToAction = zzmo.getCallToAction();
                                            final double starRating = zzmo.getStarRating();
                                            final String store = zzmo.getStore();
                                            final String price = zzmo.getPrice();
                                            final Bundle extras = zzmo.getExtras();
                                            if (zzmo.zzmw() == null) {
                                                break Label_0602;
                                            }
                                            final View view2 = (View)ObjectWrapper.unwrap(zzmo.zzmw());
                                            zzaqw.zzuf().zza(new zzat(new zzoo(headline, images, body, zzjz, callToAction, starRating, store, price, null, extras, null, view2, zzmo.zzke(), null), zzxe.zzbtw.zzbsh, zzaqw));
                                        }
                                        else {
                                            if (!zzbsi.contains("1") || zzmp == null) {
                                                goto Label_0560;
                                            }
                                            final String headline2 = zzmp.getHeadline();
                                            final List images2 = zzmp.getImages();
                                            final String body2 = zzmp.getBody();
                                            final zzpw zzkg = zzmp.zzkg();
                                            final String callToAction2 = zzmp.getCallToAction();
                                            final String advertiser = zzmp.getAdvertiser();
                                            final Bundle extras2 = zzmp.getExtras();
                                            if (zzmp.zzmw() == null) {
                                                goto Label_0554;
                                            }
                                            zzaqw.zzuf().zza(new zzau(new zzoq(headline2, images2, body2, zzkg, callToAction2, advertiser, null, extras2, null, (View)ObjectWrapper.unwrap(zzmp.zzmw()), zzmp.zzke(), null), zzxe.zzbtw.zzbsh, zzaqw));
                                        }
                                        final String zzbsf = zzxe.zzbtw.zzbsf;
                                        final String zzbsg = zzxe.zzbtw.zzbsg;
                                        if (zzbsg != null) {
                                            zzaqw.loadDataWithBaseURL(zzbsg, zzbsf, "text/html", "UTF-8", null);
                                            break Label_0596;
                                        }
                                        goto Label_0572;
                                    }
                                }
                                if (!b) {
                                    countDownLatch.countDown();
                                }
                                return b;
                            }
                            catch (RemoteException ex) {
                                zzakb.zzc("Unable to invoke load assets", (Throwable)ex);
                                final boolean b = false;
                                continue;
                            }
                            catch (RuntimeException ex2) {
                                countDownLatch.countDown();
                                throw ex2;
                            }
                        }
                        boolean b = true;
                        continue;
                    }
                }
                final View view2 = null;
                continue;
            }
        }
    }
    
    private static String zzb(final zzpw zzpw) {
        Drawable drawable;
        try {
            final IObjectWrapper zzjy = zzpw.zzjy();
            if (zzjy == null) {
                zzakb.zzdk("Drawable is null. Returning empty string");
                return "";
            }
            drawable = (Drawable)ObjectWrapper.unwrap(zzjy);
            if (!(drawable instanceof BitmapDrawable)) {
                zzakb.zzdk("Drawable is not an instance of BitmapDrawable. Returning empty string");
                return "";
            }
        }
        catch (RemoteException ex) {
            zzakb.zzdk("Unable to get drawable. Returning empty string");
            return "";
        }
        return zza(((BitmapDrawable)drawable).getBitmap());
    }
    
    @Nullable
    private static zzpw zzd(final Object o) {
        if (o instanceof IBinder) {
            return zzpx.zzh((IBinder)o);
        }
        return null;
    }
    
    private static void zzd(final zzaqw zzaqw) {
        final View$OnClickListener onClickListener = zzaqw.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzaqw.getView());
        }
    }
    
    @Nullable
    public static View zze(@Nullable final zzajh zzajh) {
        if (zzajh == null) {
            zzakb.e("AdState is null");
            return null;
        }
        if (zzf(zzajh) && zzajh.zzbyo != null) {
            return zzajh.zzbyo.getView();
        }
        while (true) {
            while (true) {
                try {
                    if (zzajh.zzbtx != null) {
                        final IObjectWrapper view = zzajh.zzbtx.getView();
                        if (view == null) {
                            zzakb.zzdk("View in mediation adapter is null.");
                            return null;
                        }
                        return (View)ObjectWrapper.unwrap(view);
                    }
                }
                catch (RemoteException ex) {
                    zzakb.zzc("Could not get View from mediation adapter.", (Throwable)ex);
                    return null;
                }
                final IObjectWrapper view = null;
                continue;
            }
        }
    }
    
    public static boolean zzf(@Nullable final zzajh zzajh) {
        return zzajh != null && zzajh.zzceq && zzajh.zzbtw != null && zzajh.zzbtw.zzbsf != null;
    }
}
