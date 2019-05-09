// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzlj;
import com.google.android.gms.internal.ads.zzagq;
import com.google.android.gms.internal.ads.zzagz;
import com.google.android.gms.internal.ads.zzpp;
import java.util.HashMap;
import android.view.View;
import com.google.android.gms.internal.ads.zzqf;
import com.google.android.gms.internal.ads.zzpn;
import android.widget.FrameLayout;
import com.google.android.gms.internal.ads.zzqa;
import com.google.android.gms.internal.ads.zzub;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzaaz;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzks;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.ads.internal.overlay.zzx;
import com.google.android.gms.ads.internal.overlay.zzs;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import android.app.Activity;
import com.google.android.gms.internal.ads.zzaap;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.content.Context;
import com.google.android.gms.internal.ads.zzkn;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import android.support.annotation.Keep;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzle;

@zzadh
@ParametersAreNonnullByDefault
@Keep
@KeepForSdkWithMembers
@DynamiteApi
@RetainForClient
public class ClientApi extends zzle
{
    public zzkn createAdLoaderBuilder(final IObjectWrapper objectWrapper, final String s, final zzxn zzxn, final int n) {
        final Context context = (Context)ObjectWrapper.unwrap(objectWrapper);
        zzbv.zzek();
        return (zzkn)new zzak(context, s, zzxn, new zzang(12451000, n, true, zzakk.zzav(context)), zzw.zzc(context));
    }
    
    public zzaap createAdOverlay(final IObjectWrapper objectWrapper) {
        final Activity activity = (Activity)ObjectWrapper.unwrap(objectWrapper);
        final AdOverlayInfoParcel zzc = AdOverlayInfoParcel.zzc(activity.getIntent());
        if (zzc == null) {
            return (zzaap)new zzr(activity);
        }
        switch (zzc.zzbyu) {
            default: {
                return (zzaap)new zzr(activity);
            }
            case 1: {
                return (zzaap)new zzq(activity);
            }
            case 4: {
                return (zzaap)new zzs(activity, zzc);
            }
            case 2: {
                return (zzaap)new zzx(activity);
            }
            case 3: {
                return (zzaap)new zzy(activity);
            }
        }
    }
    
    public zzks createBannerAdManager(final IObjectWrapper objectWrapper, final zzjn zzjn, final String s, final zzxn zzxn, final int n) throws RemoteException {
        final Context context = (Context)ObjectWrapper.unwrap(objectWrapper);
        zzbv.zzek();
        return (zzks)new com.google.android.gms.ads.internal.zzy(context, zzjn, s, zzxn, new zzang(12451000, n, true, zzakk.zzav(context)), zzw.zzc(context));
    }
    
    public zzaaz createInAppPurchaseManager(final IObjectWrapper objectWrapper) {
        return null;
    }
    
    public zzks createInterstitialAdManager(final IObjectWrapper objectWrapper, final zzjn zzjn, final String s, final zzxn zzxn, int n) throws RemoteException {
        final Context context = (Context)ObjectWrapper.unwrap(objectWrapper);
        zznk.initialize(context);
        zzbv.zzek();
        final zzang zzang = new zzang(12451000, n, true, zzakk.zzav(context));
        final boolean equals = "reward_mb".equals(zzjn.zzarb);
        if ((equals && (boolean)zzkb.zzik().zzd(zznk.zzayy)) || (equals && (boolean)zzkb.zzik().zzd(zznk.zzayz))) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return (zzks)new zzub(context, s, zzxn, zzang, zzw.zzc(context));
        }
        return (zzks)new zzal(context, zzjn, s, zzxn, zzang, zzw.zzc(context));
    }
    
    public zzqa createNativeAdViewDelegate(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2) {
        return (zzqa)new zzpn((FrameLayout)ObjectWrapper.unwrap(objectWrapper), (FrameLayout)ObjectWrapper.unwrap(objectWrapper2));
    }
    
    public zzqf createNativeAdViewHolderDelegate(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2, final IObjectWrapper objectWrapper3) {
        return (zzqf)new zzpp((View)ObjectWrapper.unwrap(objectWrapper), (HashMap<String, View>)ObjectWrapper.unwrap(objectWrapper2), (HashMap<String, View>)ObjectWrapper.unwrap(objectWrapper3));
    }
    
    public zzagz createRewardedVideoAd(final IObjectWrapper objectWrapper, final zzxn zzxn, final int n) {
        final Context context = (Context)ObjectWrapper.unwrap(objectWrapper);
        zzbv.zzek();
        return (zzagz)new zzagq(context, zzw.zzc(context), zzxn, new zzang(12451000, n, true, zzakk.zzav(context)));
    }
    
    public zzks createSearchAdManager(final IObjectWrapper objectWrapper, final zzjn zzjn, final String s, final int n) throws RemoteException {
        final Context context = (Context)ObjectWrapper.unwrap(objectWrapper);
        zzbv.zzek();
        return (zzks)new zzbp(context, zzjn, s, new zzang(12451000, n, true, zzakk.zzav(context)));
    }
    
    @Nullable
    public zzlj getMobileAdsSettingsManager(final IObjectWrapper objectWrapper) {
        return null;
    }
    
    public zzlj getMobileAdsSettingsManagerWithClientJarVersion(final IObjectWrapper objectWrapper, final int n) {
        final Context context = (Context)ObjectWrapper.unwrap(objectWrapper);
        zzbv.zzek();
        return (zzlj)zzay.zza(context, new zzang(12451000, n, true, zzakk.zzav(context)));
    }
}
