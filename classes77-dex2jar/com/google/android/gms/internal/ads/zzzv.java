// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import android.support.customtabs.CustomTabsIntent$Builder;
import android.text.TextUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import android.app.Activity;
import android.net.Uri;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;

@zzadh
public final class zzzv implements MediationInterstitialAdapter
{
    private Uri mUri;
    private Activity zzbvp;
    private MediationInterstitialListener zzbvq;
    
    public final void onDestroy() {
        zzane.zzck("Destroying AdMobCustomTabsAdapter adapter.");
    }
    
    public final void onPause() {
        zzane.zzck("Pausing AdMobCustomTabsAdapter adapter.");
    }
    
    public final void onResume() {
        zzane.zzck("Resuming AdMobCustomTabsAdapter adapter.");
    }
    
    public final void requestInterstitialAd(final Context context, final MediationInterstitialListener zzbvq, final Bundle bundle, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        this.zzbvq = zzbvq;
        if (this.zzbvq == null) {
            zzane.zzdk("Listener not set for mediation. Returning.");
            return;
        }
        if (!(context instanceof Activity)) {
            zzane.zzdk("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzbvq.onAdFailedToLoad((MediationInterstitialAdapter)this, 0);
            return;
        }
        int n;
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzoh.zzh(context)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            zzane.zzdk("Default browser does not support custom tabs. Bailing out.");
            this.zzbvq.onAdFailedToLoad((MediationInterstitialAdapter)this, 0);
            return;
        }
        final String string = bundle.getString("tab_url");
        if (TextUtils.isEmpty((CharSequence)string)) {
            zzane.zzdk("The tab_url retrieved from mediation metadata is empty. Bailing out.");
            this.zzbvq.onAdFailedToLoad((MediationInterstitialAdapter)this, 0);
            return;
        }
        this.zzbvp = (Activity)context;
        this.mUri = Uri.parse(string);
        this.zzbvq.onAdLoaded((MediationInterstitialAdapter)this);
    }
    
    public final void showInterstitial() {
        final CustomTabsIntent build = new CustomTabsIntent$Builder().build();
        build.intent.setData(this.mUri);
        zzakk.zzcrm.post((Runnable)new zzzx(this, new AdOverlayInfoParcel(new zzc(build.intent), null, new zzzw(this), null, new zzang(0, 0, false))));
        zzbv.zzeo().zzqc();
    }
}
