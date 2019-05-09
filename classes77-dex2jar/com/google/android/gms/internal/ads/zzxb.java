// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.os.SystemClock;
import android.os.Bundle;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.text.TextUtils;
import com.google.android.gms.ads.formats.NativeAdOptions$Builder;
import com.google.android.gms.ads.formats.NativeAdOptions;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.mediation.MediationAdapter;
import javax.annotation.concurrent.GuardedBy;
import java.util.List;
import android.content.Context;

@zzadh
public final class zzxb implements zzxf
{
    private final Context mContext;
    private final Object mLock;
    private zzjj zzaao;
    private final zzjn zzaau;
    private final boolean zzael;
    private final String zzbth;
    private final long zzbti;
    private final zzwy zzbtj;
    private final zzwx zzbtk;
    private final List<String> zzbtl;
    private final List<String> zzbtm;
    private final boolean zzbtn;
    private final boolean zzbto;
    @GuardedBy("mLock")
    private zzxq zzbtp;
    @GuardedBy("mLock")
    private int zzbtq;
    private zzxw zzbtr;
    private final zzxn zzwh;
    private final zzpl zzyb;
    private final List<String> zzyc;
    private final zzang zzyf;
    
    public zzxb(final Context mContext, final String zzbth, final zzxn zzwh, final zzwy zzbtj, final zzwx zzbtk, final zzjj zzaao, final zzjn zzaau, final zzang zzyf, final boolean zzael, final boolean zzbtn, final zzpl zzyb, final List<String> zzyc, final List<String> zzbtl, final List<String> zzbtm, final boolean zzbto) {
        this.mLock = new Object();
        this.zzbtq = -2;
        this.mContext = mContext;
        this.zzwh = zzwh;
        this.zzbtk = zzbtk;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(zzbth)) {
            this.zzbth = this.zzmh();
        }
        else {
            this.zzbth = zzbth;
        }
        this.zzbtj = zzbtj;
        if (zzbtk.zzbsl != -1L) {
            this.zzbti = zzbtk.zzbsl;
        }
        else if (zzbtj.zzbsl != -1L) {
            this.zzbti = zzbtj.zzbsl;
        }
        else {
            this.zzbti = 10000L;
        }
        this.zzaao = zzaao;
        this.zzaau = zzaau;
        this.zzyf = zzyf;
        this.zzael = zzael;
        this.zzbtn = zzbtn;
        this.zzyb = zzyb;
        this.zzyc = zzyc;
        this.zzbtl = zzbtl;
        this.zzbtm = zzbtm;
        this.zzbto = zzbto;
    }
    
    @VisibleForTesting
    private static zzxq zza(final MediationAdapter mediationAdapter) {
        return (zzxq)new zzyk(mediationAdapter);
    }
    
    @GuardedBy("mLock")
    private final void zza(final zzxa zzxa) {
        final String zzbk = this.zzbk(this.zzbtk.zzbsb);
        try {
            if (this.zzyf.zzcvf < 4100000) {
                if (this.zzaau.zzarc) {
                    this.zzbtp.zza(ObjectWrapper.wrap((Object)this.mContext), this.zzaao, zzbk, (zzxt)zzxa);
                    return;
                }
                this.zzbtp.zza(ObjectWrapper.wrap((Object)this.mContext), this.zzaau, this.zzaao, zzbk, (zzxt)zzxa);
                return;
            }
        }
        catch (RemoteException ex) {
            zzakb.zzc("Could not request ad from mediation adapter.", (Throwable)ex);
            this.zzx(5);
            return;
        }
        if (this.zzael || this.zzbtk.zzmg()) {
            final ArrayList<String> list = new ArrayList<String>(this.zzyc);
            if (this.zzbtl != null) {
                for (final String s : this.zzbtl) {
                    String s3;
                    final String s2 = s3 = ":false";
                    if (this.zzbtm != null) {
                        s3 = s2;
                        if (this.zzbtm.contains(s)) {
                            s3 = ":true";
                        }
                    }
                    list.add(new StringBuilder(String.valueOf(s).length() + 7 + String.valueOf(s3).length()).append("custom:").append(s).append(s3).toString());
                }
            }
            this.zzbtp.zza(ObjectWrapper.wrap((Object)this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, (zzxt)zzxa, this.zzyb, (List)list);
            return;
        }
        if (this.zzaau.zzarc) {
            this.zzbtp.zza(ObjectWrapper.wrap((Object)this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, (zzxt)zzxa);
            return;
        }
        if (!this.zzbtn) {
            this.zzbtp.zza(ObjectWrapper.wrap((Object)this.mContext), this.zzaau, this.zzaao, zzbk, this.zzbtk.zzbrr, (zzxt)zzxa);
            return;
        }
        if (this.zzbtk.zzbsf != null) {
            this.zzbtp.zza(ObjectWrapper.wrap((Object)this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, (zzxt)zzxa, new zzpl(zzbl(this.zzbtk.zzbsj)), (List)this.zzbtk.zzbsi);
            return;
        }
        this.zzbtp.zza(ObjectWrapper.wrap((Object)this.mContext), this.zzaau, this.zzaao, zzbk, this.zzbtk.zzbrr, (zzxt)zzxa);
    }
    
    @GuardedBy("mLock")
    private final String zzbk(final String s) {
        if (s == null || !this.zzmk() || this.zzy(2)) {
            return s;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            jsonObject.remove("cpm_floor_cents");
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            zzakb.zzdk("Could not remove field. Returning the original value");
            return s;
        }
    }
    
    private static NativeAdOptions zzbl(String optString) {
        int imageOrientation = 0;
        final NativeAdOptions$Builder nativeAdOptions$Builder = new NativeAdOptions$Builder();
        if (optString == null) {
            return nativeAdOptions$Builder.build();
        }
        try {
            final JSONObject jsonObject = new JSONObject(optString);
            nativeAdOptions$Builder.setRequestMultipleImages(jsonObject.optBoolean("multiple_images", false));
            nativeAdOptions$Builder.setReturnUrlsForImageAssets(jsonObject.optBoolean("only_urls", false));
            optString = jsonObject.optString("native_image_orientation", "any");
            if ("landscape".equals(optString)) {
                imageOrientation = 2;
            }
            else if ("portrait".equals(optString)) {
                imageOrientation = 1;
            }
            else if (!"any".equals(optString)) {
                imageOrientation = -1;
            }
            nativeAdOptions$Builder.setImageOrientation(imageOrientation);
            return nativeAdOptions$Builder.build();
        }
        catch (JSONException ex) {
            zzakb.zzc("Exception occurred when creating native ad options", (Throwable)ex);
            return nativeAdOptions$Builder.build();
        }
    }
    
    private final String zzmh() {
        try {
            if (!TextUtils.isEmpty((CharSequence)this.zzbtk.zzbrv)) {
                if (this.zzwh.zzbn(this.zzbtk.zzbrv)) {
                    return "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
                }
                return "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        }
        catch (RemoteException ex) {
            zzakb.zzdk("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }
    
    @GuardedBy("mLock")
    private final zzxw zzmi() {
        if (this.zzbtq != 0 || !this.zzmk()) {
            return null;
        }
        try {
            if (this.zzy(4) && this.zzbtr != null && this.zzbtr.zzmm() != 0) {
                return this.zzbtr;
            }
        }
        catch (RemoteException ex) {
            zzakb.zzdk("Could not get cpm value from MediationResponseMetadata");
        }
        return (zzxw)new zzxd(this.zzml());
    }
    
    @GuardedBy("mLock")
    private final zzxq zzmj() {
        final String value = String.valueOf(this.zzbth);
        String concat;
        if (value.length() != 0) {
            concat = "Instantiating mediation adapter: ".concat(value);
        }
        else {
            concat = new String("Instantiating mediation adapter: ");
        }
        zzakb.zzdj(concat);
        if (!this.zzael && !this.zzbtk.zzmg()) {
            if ((boolean)zzkb.zzik().zzd(zznk.zzbai) && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                return zza((MediationAdapter)new AdMobAdapter());
            }
            if ((boolean)zzkb.zzik().zzd(zznk.zzbaj) && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzbth)) {
                return zza((MediationAdapter)new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzbth)) {
                return (zzxq)new zzyk((MediationAdapter)new zzzv());
            }
        }
        try {
            return this.zzwh.zzbm(this.zzbth);
        }
        catch (RemoteException ex) {
            final String value2 = String.valueOf(this.zzbth);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Could not instantiate mediation adapter: ".concat(value2);
            }
            else {
                concat2 = new String("Could not instantiate mediation adapter: ");
            }
            zzakb.zza(concat2, (Throwable)ex);
            return null;
        }
    }
    
    private final boolean zzmk() {
        return this.zzbtj.zzbsx != -1;
    }
    
    @GuardedBy("mLock")
    private final int zzml() {
        int n;
        if (this.zzbtk.zzbsb == null) {
            n = 0;
        }
        else {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(this.zzbtk.zzbsb);
                if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                    return jsonObject.optInt("cpm_cents", 0);
                }
            }
            catch (JSONException ex) {
                zzakb.zzdk("Could not convert to json. Returning 0");
                return 0;
            }
            int optInt;
            if (this.zzy(2)) {
                optInt = jsonObject.optInt("cpm_floor_cents", 0);
            }
            else {
                optInt = 0;
            }
            n = optInt;
            if (optInt == 0) {
                return jsonObject.optInt("penalized_average_cpm_cents", 0);
            }
        }
        return n;
    }
    
    @GuardedBy("mLock")
    private final boolean zzy(final int n) {
        final boolean b = false;
        try {
            Bundle bundle;
            if (this.zzael) {
                bundle = this.zzbtp.zzmr();
            }
            else if (this.zzaau.zzarc) {
                bundle = this.zzbtp.getInterstitialAdapterInfo();
            }
            else {
                bundle = this.zzbtp.zzmq();
            }
            boolean b2 = b;
            if (bundle != null) {
                b2 = b;
                if ((bundle.getInt("capabilities", 0) & n) == n) {
                    b2 = true;
                }
            }
            return b2;
        }
        catch (RemoteException ex) {
            zzakb.zzdk("Could not get adapter info. Returning false");
            return false;
        }
    }
    
    public final void cancel() {
        synchronized (this.mLock) {
            while (true) {
                try {
                    if (this.zzbtp != null) {
                        this.zzbtp.destroy();
                    }
                    this.zzbtq = -1;
                    this.mLock.notify();
                }
                catch (RemoteException ex) {
                    zzakb.zzc("Could not destroy mediation adapter.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
    
    public final zzxe zza(long elapsedRealtime, final long n) {
        while (true) {
            while (true) {
                Label_0164: {
                    long elapsedRealtime3;
                    long n2;
                    synchronized (this.mLock) {
                        final long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        final zzxa zzxa = new zzxa();
                        zzakk.zzcrm.post((Runnable)new zzxc(this, zzxa));
                        final long zzbti = this.zzbti;
                        while (this.zzbtq == -2) {
                            elapsedRealtime3 = SystemClock.elapsedRealtime();
                            n2 = zzbti - (elapsedRealtime3 - elapsedRealtime2);
                            elapsedRealtime3 = n - (elapsedRealtime3 - elapsedRealtime);
                            if (n2 > 0L && elapsedRealtime3 > 0L) {
                                break Label_0164;
                            }
                            zzakb.zzdj("Timed out waiting for adapter.");
                            this.zzbtq = 3;
                        }
                        elapsedRealtime = zzbv.zzer().elapsedRealtime();
                        return new zzxe(this.zzbtk, this.zzbtp, this.zzbth, zzxa, this.zzbtq, this.zzmi(), elapsedRealtime - elapsedRealtime2);
                    }
                    try {
                        this.mLock.wait(Math.min(n2, elapsedRealtime3));
                        continue;
                    }
                    catch (InterruptedException ex) {
                        this.zzbtq = 5;
                        continue;
                    }
                }
                continue;
            }
        }
    }
    
    @Override
    public final void zza(final int n, final zzxw zzbtr) {
        synchronized (this.mLock) {
            this.zzbtq = 0;
            this.zzbtr = zzbtr;
            this.mLock.notify();
        }
    }
    
    @Override
    public final void zzx(final int zzbtq) {
        synchronized (this.mLock) {
            this.zzbtq = zzbtq;
            this.mLock.notify();
        }
    }
}
