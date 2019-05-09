package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzpx;
import com.google.android.gms.internal.ads.zzxe;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzas {
    @VisibleForTesting
    static zzv<zzaqw> zza(@Nullable zzxz zzxz, @Nullable zzyc zzyc, zzac zzac) {
        return new zzax(zzxz, zzac, zzyc);
    }

    private static String zza(@Nullable Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzakb.zzdk("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    @VisibleForTesting
    private static String zza(@Nullable zzpw zzpw) {
        if (zzpw == null) {
            zzakb.zzdk("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzpw.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzakb.zzdk("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzpw);
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if (MessengerShareContentUtility.MEDIA_IMAGE.equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzakb.zzdk("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzakb.zzdk("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void zza(zzoo zzoo, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoo.getHeadline());
            jSONObject.put("body", zzoo.getBody());
            jSONObject.put("call_to_action", zzoo.getCallToAction());
            jSONObject.put("price", zzoo.getPrice());
            jSONObject.put("star_rating", String.valueOf(zzoo.getStarRating()));
            jSONObject.put("store", zzoo.getStore());
            jSONObject.put("icon", zza(zzoo.zzjz()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoo.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoo.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "2");
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzakb.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzoq zzoq, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoq.getHeadline());
            jSONObject.put("body", zzoq.getBody());
            jSONObject.put("call_to_action", zzoq.getCallToAction());
            jSONObject.put("advertiser", zzoq.getAdvertiser());
            jSONObject.put("logo", zza(zzoq.zzkg()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoq.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoq.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "1");
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzakb.zzc("Exception occurred when loading assets", e);
        }
    }

    public static boolean zza(zzaqw zzaqw, zzxe zzxe, CountDownLatch countDownLatch) {
        boolean z;
        try {
            View view = zzaqw.getView();
            if (view == null) {
                zzakb.zzdk("AdWebView is null");
                z = false;
            } else {
                view.setVisibility(4);
                List list = zzxe.zzbtw.zzbsi;
                if (list == null || list.isEmpty()) {
                    zzakb.zzdk("No template ids present in mediation response");
                    z = false;
                } else {
                    zzaqw.zza("/nativeExpressAssetsLoaded", new zzav(countDownLatch));
                    zzaqw.zza("/nativeExpressAssetsLoadingFailed", new zzaw(countDownLatch));
                    zzxz zzmo = zzxe.zzbtx.zzmo();
                    zzyc zzmp = zzxe.zzbtx.zzmp();
                    if (list.contains("2") && zzmo != null) {
                        zzaqw.zzuf().zza(new zzat(new zzoo(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz(), zzmo.getCallToAction(), zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), null, zzmo.getExtras(), null, zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), null), zzxe.zzbtw.zzbsh, zzaqw));
                    } else if (!list.contains("1") || zzmp == null) {
                        zzakb.zzdk("No matching template id and mapper");
                        z = false;
                    } else {
                        zzaqw.zzuf().zza(new zzau(new zzoq(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg(), zzmp.getCallToAction(), zzmp.getAdvertiser(), null, zzmp.getExtras(), null, zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), null), zzxe.zzbtw.zzbsh, zzaqw));
                    }
                    String str = zzxe.zzbtw.zzbsf;
                    String str2 = zzxe.zzbtw.zzbsg;
                    if (str2 != null) {
                        zzaqw.loadDataWithBaseURL(str2, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
                    } else {
                        zzaqw.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
                    }
                    z = true;
                }
            }
        } catch (Throwable e) {
            zzakb.zzc("Unable to invoke load assets", e);
            z = false;
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    private static String zzb(zzpw zzpw) {
        try {
            IObjectWrapper zzjy = zzpw.zzjy();
            if (zzjy == null) {
                zzakb.zzdk("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzakb.zzdk("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzakb.zzdk("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    @Nullable
    private static zzpw zzd(Object obj) {
        return obj instanceof IBinder ? zzpx.zzh((IBinder) obj) : null;
    }

    private static void zzd(zzaqw zzaqw) {
        OnClickListener onClickListener = zzaqw.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzaqw.getView());
        }
    }

    @Nullable
    public static View zze(@Nullable zzajh zzajh) {
        if (zzajh == null) {
            zzakb.e("AdState is null");
            return null;
        } else if (zzf(zzajh) && zzajh.zzbyo != null) {
            return zzajh.zzbyo.getView();
        } else {
            try {
                IObjectWrapper view = zzajh.zzbtx != null ? zzajh.zzbtx.getView() : null;
                if (view != null) {
                    return (View) ObjectWrapper.unwrap(view);
                }
                zzakb.zzdk("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                zzakb.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzf(@Nullable zzajh zzajh) {
        return (zzajh == null || !zzajh.zzceq || zzajh.zzbtw == null || zzajh.zzbtw.zzbsf == null) ? false : true;
    }
}
