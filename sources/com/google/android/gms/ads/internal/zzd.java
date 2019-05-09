package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzadk;
import com.google.android.gms.internal.ads.zzaeg;
import com.google.android.gms.internal.ads.zzafa;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzajl;
import com.google.android.gms.internal.ads.zzajx;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzaoe;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzgk;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import com.google.android.gms.internal.ads.zzhx;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzua;
import com.google.android.gms.internal.ads.zzwz;
import com.google.android.gms.internal.ads.zzxg;
import com.google.android.gms.internal.ads.zzxn;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity.purchasing.googleplay.Consts;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzd extends zza implements zzn, zzbo, zzwz {
    protected final zzxn zzwh;
    private transient boolean zzwi;

    public zzd(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        this(new zzbw(context, zzjn, str, zzang), zzxn, null, zzw);
    }

    @VisibleForTesting
    private zzd(zzbw zzbw, zzxn zzxn, @Nullable zzbl zzbl, zzw zzw) {
        super(zzbw, null, zzw);
        this.zzwh = zzxn;
        this.zzwi = false;
    }

    private final zzaeg zza(zzjj zzjj, Bundle bundle, zzajl zzajl, int i) {
        PackageInfo packageInfo;
        int i2;
        ApplicationInfo applicationInfo = this.zzvw.zzrt.getApplicationInfo();
        try {
            packageInfo = Wrappers.packageManager(this.zzvw.zzrt).getPackageInfo(applicationInfo.packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        DisplayMetrics displayMetrics = this.zzvw.zzrt.getResources().getDisplayMetrics();
        Bundle bundle2 = null;
        if (!(this.zzvw.zzacs == null || this.zzvw.zzacs.getParent() == null)) {
            int[] iArr = new int[2];
            this.zzvw.zzacs.getLocationOnScreen(iArr);
            int i3 = iArr[0];
            int i4 = iArr[1];
            int width = this.zzvw.zzacs.getWidth();
            int height = this.zzvw.zzacs.getHeight();
            i2 = 0;
            if (this.zzvw.zzacs.isShown() && i3 + width > 0 && i4 + height > 0 && i3 <= displayMetrics.widthPixels && i4 <= displayMetrics.heightPixels) {
                i2 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt("x", i3);
            bundle2.putInt("y", i4);
            bundle2.putInt("width", width);
            bundle2.putInt("height", height);
            bundle2.putInt(String.VISIBLE, i2);
        }
        String zzql = zzbv.zzeo().zzpx().zzql();
        this.zzvw.zzacy = new zzajj(zzql, this.zzvw.zzacp);
        this.zzvw.zzacy.zzn(zzjj);
        zzbv.zzek();
        String zza = zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacs, this.zzvw.zzacv);
        long j = 0;
        if (this.zzvw.zzadd != null) {
            try {
                j = this.zzvw.zzadd.getValue();
            } catch (RemoteException e2) {
                zzakb.zzdk("Cannot get correlation id, default to 0.");
            }
        }
        String uuid = UUID.randomUUID().toString();
        Bundle zza2 = zzbv.zzep().zza(this.zzvw.zzrt, this, zzql);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (i3 = 0; i3 < this.zzvw.zzadi.size(); i3++) {
            String str = (String) this.zzvw.zzadi.keyAt(i3);
            arrayList.add(str);
            if (this.zzvw.zzadh.containsKey(str) && this.zzvw.zzadh.get(str) != null) {
                arrayList2.add(str);
            }
        }
        Future zza3 = zzaki.zza(new zzg(this));
        Future zza4 = zzaki.zza(new zzh(this));
        String str2 = null;
        if (zzajl != null) {
            str2 = zzajl.zzpu();
        }
        String str3 = null;
        if (this.zzvw.zzads != null && this.zzvw.zzads.size() > 0) {
            i2 = 0;
            if (packageInfo != null) {
                i2 = packageInfo.versionCode;
            }
            if (i2 > zzbv.zzeo().zzqh().zzqz()) {
                zzbv.zzeo().zzqh().zzrf();
                zzbv.zzeo().zzqh().zzae(i2);
            } else {
                JSONObject zzre = zzbv.zzeo().zzqh().zzre();
                if (zzre != null) {
                    JSONArray optJSONArray = zzre.optJSONArray(this.zzvw.zzacp);
                    if (optJSONArray != null) {
                        str3 = optJSONArray.toString();
                    }
                }
            }
        }
        zzjn zzjn = this.zzvw.zzacv;
        String str4 = this.zzvw.zzacp;
        String zzih = zzkb.zzih();
        zzang zzang = this.zzvw.zzacr;
        List list = this.zzvw.zzads;
        boolean zzqt = zzbv.zzeo().zzqh().zzqt();
        int i5 = displayMetrics.widthPixels;
        int i6 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        List zzjb = zznk.zzjb();
        String str5 = this.zzvw.zzaco;
        zzpl zzpl = this.zzvw.zzadj;
        String zzfq = this.zzvw.zzfq();
        float zzdo = zzbv.zzfj().zzdo();
        boolean zzdp = zzbv.zzfj().zzdp();
        zzbv.zzek();
        int zzas = zzakk.zzas(this.zzvw.zzrt);
        zzbv.zzek();
        int zzx = zzakk.zzx(this.zzvw.zzacs);
        boolean z = this.zzvw.zzrt instanceof Activity;
        boolean zzqy = zzbv.zzeo().zzqh().zzqy();
        boolean zzqa = zzbv.zzeo().zzqa();
        int zztx = zzbv.zzff().zztx();
        zzbv.zzek();
        Bundle zzrk = zzakk.zzrk();
        String zzrw = zzbv.zzeu().zzrw();
        zzlu zzlu = this.zzvw.zzadl;
        boolean zzrx = zzbv.zzeu().zzrx();
        Bundle zzlt = zzua.zzlk().zzlt();
        boolean zzcr = zzbv.zzeo().zzqh().zzcr(this.zzvw.zzacp);
        List list2 = this.zzvw.zzadn;
        boolean isCallerInstantApp = Wrappers.packageManager(this.zzvw.zzrt).isCallerInstantApp();
        boolean zzqb = zzbv.zzeo().zzqb();
        zzbv.zzem();
        return new zzaeg(bundle2, zzjj, zzjn, str4, applicationInfo, packageInfo, zzql, zzih, zzang, zza2, list, arrayList, bundle, zzqt, i5, i6, f, zza, j, uuid, zzjb, str5, zzpl, zzfq, zzdo, zzdp, zzas, zzx, z, zzqy, zza3, str2, zzqa, zztx, zzrk, zzrw, zzlu, zzrx, zzlt, zzcr, zza4, list2, str3, arrayList2, i, isCallerInstantApp, zzqb, zzakq.zzrp(), (ArrayList) zzano.zza(zzbv.zzeo().zzqi(), null, 1000, TimeUnit.MILLISECONDS));
    }

    @Nullable
    static String zzc(zzajh zzajh) {
        if (zzajh == null) {
            return null;
        }
        String str = zzajh.zzbty;
        Object obj = ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) ? 1 : null;
        if (obj == null || zzajh.zzbtw == null) {
            return str;
        }
        try {
            return new JSONObject(zzajh.zzbtw.zzbsb).getString("class_name");
        } catch (JSONException e) {
            return str;
        } catch (NullPointerException e2) {
            return str;
        }
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        return this.zzvw.zzacw == null ? null : this.zzvw.zzacw.zzbty;
    }

    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzvw.zzacw.zzcod == null || this.zzvw.zzacw.zzcod.zzbsn == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, zzc(this.zzvw.zzacw.zzcod.zzbsn));
        }
        if (!(this.zzvw.zzacw.zzbtw == null || this.zzvw.zzacw.zzbtw.zzbrw == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, this.zzvw.zzacw.zzbtw.zzbrw);
        }
        super.onAdClicked();
    }

    public final void onPause() {
        this.zzvy.zzj(this.zzvw.zzacw);
    }

    public final void onResume() {
        this.zzvy.zzk(this.zzvw.zzacw);
    }

    public void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null || !this.zzvw.zzfo())) {
            zzbv.zzem();
            zzakq.zzi(this.zzvw.zzacw.zzbyo);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbtx == null)) {
            try {
                this.zzvw.zzacw.zzbtx.pause();
            } catch (RemoteException e) {
                zzakb.zzdk("Could not pause mediation adapter.");
            }
        }
        this.zzvy.zzj(this.zzvw.zzacw);
        this.zzvv.pause();
    }

    public final void recordImpression() {
        zza(this.zzvw.zzacw, false);
    }

    public void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        zzaqw zzaqw = null;
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null)) {
            zzaqw = this.zzvw.zzacw.zzbyo;
        }
        if (zzaqw != null && this.zzvw.zzfo()) {
            zzbv.zzem();
            zzakq.zzj(this.zzvw.zzacw.zzbyo);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbtx == null)) {
            try {
                this.zzvw.zzacw.zzbtx.resume();
            } catch (RemoteException e) {
                zzakb.zzdk("Could not resume mediation adapter.");
            }
        }
        if (zzaqw == null || !zzaqw.zzum()) {
            this.zzvv.resume();
        }
        this.zzvy.zzk(this.zzvw.zzacw);
    }

    public void showInterstitial() {
        zzakb.zzdk("showInterstitial is not supported for current ad type");
    }

    protected void zza(@Nullable zzajh zzajh, boolean z) {
        if (zzajh == null) {
            zzakb.zzdk("Ad state was null when trying to ping impression URLs.");
            return;
        }
        if (zzajh == null) {
            zzakb.zzdk("Ad state was null when trying to ping impression URLs.");
        } else {
            zzakb.zzck("Pinging Impression URLs.");
            if (this.zzvw.zzacy != null) {
                this.zzvw.zzacy.zzpm();
            }
            zzajh.zzcoq.zza(zzb.AD_IMPRESSION);
            if (!(zzajh.zzbso == null || zzajh.zzcok)) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzc(zzajh.zzbso));
                zzajh.zzcok = true;
            }
        }
        if (!zzajh.zzcom || z) {
            if (!(zzajh.zzcod == null || zzajh.zzcod.zzbso == null)) {
                zzbv.zzfd();
                zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzc(zzajh.zzcod.zzbso));
            }
            if (!(zzajh.zzbtw == null || zzajh.zzbtw.zzbrx == null)) {
                zzbv.zzfd();
                zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzajh.zzbtw.zzbrx);
            }
            zzajh.zzcom = true;
        }
    }

    public final void zza(zzqs zzqs, String str) {
        zzrc zzrc = null;
        if (zzqs != null) {
            try {
                Object customTemplateId = zzqs.getCustomTemplateId();
            } catch (Throwable e) {
                zzakb.zzc("Unable to call onCustomClick.", e);
                return;
            }
        }
        customTemplateId = null;
        if (!(this.zzvw.zzadh == null || customTemplateId == null)) {
            zzrc = (zzrc) this.zzvw.zzadh.get(customTemplateId);
        }
        if (zzrc == null) {
            zzakb.zzdk("Mediation adapter invoked onCustomClick but no listeners were set.");
        } else {
            zzrc.zzb(zzqs, str);
        }
    }

    public final boolean zza(zzaeg zzaeg, zznx zznx) {
        this.zzvr = zznx;
        zznx.zze("seq_num", zzaeg.zzccy);
        zznx.zze(Consts.INAPP_REQUEST_ID, zzaeg.zzcdi);
        zznx.zze(TapjoyConstants.TJC_SESSION_ID, zzaeg.zzccz);
        if (zzaeg.zzccw != null) {
            zznx.zze(TapjoyConstants.TJC_APP_VERSION_NAME, String.valueOf(zzaeg.zzccw.versionCode));
        }
        zzbw zzbw = this.zzvw;
        zzbv.zzeg();
        Context context = this.zzvw.zzrt;
        zzhx zzhx = this.zzwc.zzxb;
        zzajx zzafa = zzaeg.zzccv.extras.getBundle("sdk_less_server_data") != null ? new zzafa(context, zzaeg, this, zzhx) : new zzadk(context, zzaeg, this, zzhx);
        zzafa.zzqo();
        zzbw.zzact = zzafa;
        return true;
    }

    final boolean zza(zzajh zzajh) {
        zzjj zzjj;
        boolean z = false;
        if (this.zzvx != null) {
            zzjj = this.zzvx;
            this.zzvx = null;
        } else {
            zzjj = zzajh.zzccv;
            if (zzjj.extras != null) {
                z = zzjj.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzjj, zzajh, z);
    }

    protected boolean zza(@Nullable zzajh zzajh, zzajh zzajh2) {
        int i;
        int i2 = 0;
        if (!(zzajh == null || zzajh.zzbtz == null)) {
            zzajh.zzbtz.zza(null);
        }
        if (zzajh2.zzbtz != null) {
            zzajh2.zzbtz.zza((zzwz) this);
        }
        if (zzajh2.zzcod != null) {
            i = zzajh2.zzcod.zzbtc;
            i2 = zzajh2.zzcod.zzbtd;
        } else {
            i = 0;
        }
        this.zzvw.zzadt.zze(i, i2);
        return true;
    }

    protected boolean zza(zzjj zzjj, zzajh zzajh, boolean z) {
        if (!z && this.zzvw.zzfo()) {
            if (zzajh.zzbsu > 0) {
                this.zzvv.zza(zzjj, zzajh.zzbsu);
            } else if (zzajh.zzcod != null && zzajh.zzcod.zzbsu > 0) {
                this.zzvv.zza(zzjj, zzajh.zzcod.zzbsu);
            } else if (!zzajh.zzceq && zzajh.errorCode == 2) {
                this.zzvv.zzg(zzjj);
            }
        }
        return this.zzvv.zzdz();
    }

    public boolean zza(zzjj zzjj, zznx zznx) {
        return zza(zzjj, zznx, 1);
    }

    public final boolean zza(zzjj zzjj, zznx zznx, int i) {
        if (!zzca()) {
            return false;
        }
        zzajl zzra;
        zzbv.zzek();
        zzgk zzaf = zzbv.zzeo().zzaf(this.zzvw.zzrt);
        Bundle zza = zzaf == null ? null : zzakk.zza(zzaf);
        this.zzvv.cancel();
        this.zzvw.zzadv = 0;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcs)).booleanValue()) {
            zzra = zzbv.zzeo().zzqh().zzra();
            zzbv.zzes().zza(this.zzvw.zzrt, this.zzvw.zzacr, false, zzra, zzra != null ? zzra.zzpv() : null, this.zzvw.zzacp, null);
        } else {
            zzra = null;
        }
        return zza(zza(zzjj, zza, zzra, i), zznx);
    }

    public final void zzb(zzajh zzajh) {
        super.zzb(zzajh);
        if (zzajh.zzbtw != null) {
            zzakb.zzck("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzfu();
            }
            zzakb.zzck("Pinging network fill URLs.");
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, false, zzajh.zzbtw.zzbsa);
            if (!(zzajh.zzcod == null || zzajh.zzcod.zzbsr == null || zzajh.zzcod.zzbsr.size() <= 0)) {
                zzakb.zzck("Pinging urls remotely");
                zzbv.zzek().zza(this.zzvw.zzrt, zzajh.zzcod.zzbsr);
            }
        } else {
            zzakb.zzck("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzft();
            }
        }
        if (zzajh.errorCode == 3 && zzajh.zzcod != null && zzajh.zzcod.zzbsq != null) {
            zzakb.zzck("Pinging no fill URLs.");
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, false, zzajh.zzcod.zzbsq);
        }
    }

    protected final void zzb(@Nullable zzajh zzajh, boolean z) {
        if (zzajh != null) {
            if (!(zzajh == null || zzajh.zzbsp == null || zzajh.zzcol)) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzb(zzajh.zzbsp));
                zzajh.zzcol = true;
            }
            if (!zzajh.zzcon || z) {
                if (!(zzajh.zzcod == null || zzajh.zzcod.zzbsp == null)) {
                    zzbv.zzfd();
                    zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzb(zzajh.zzcod.zzbsp));
                }
                if (!(zzajh.zzbtw == null || zzajh.zzbtw.zzbry == null)) {
                    zzbv.zzfd();
                    zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh, this.zzvw.zzacp, z, zzajh.zzbtw.zzbry);
                }
                zzajh.zzcon = true;
            }
        }
    }

    public final void zzb(String str, String str2) {
        onAppEvent(str, str2);
    }

    protected final boolean zzc(zzjj zzjj) {
        return super.zzc(zzjj) && !this.zzwi;
    }

    protected boolean zzca() {
        zzbv.zzek();
        if (zzakk.zzl(this.zzvw.zzrt, "android.permission.INTERNET")) {
            zzbv.zzek();
            if (zzakk.zzaj(this.zzvw.zzrt)) {
                return true;
            }
        }
        return false;
    }

    public void zzcb() {
        this.zzwi = false;
        zzbn();
        this.zzvw.zzacy.zzpo();
    }

    public void zzcc() {
        this.zzwi = true;
        zzbp();
    }

    public void zzcd() {
        zzakb.zzdk("Mediated ad does not support onVideoEnd callback");
    }

    public void zzce() {
        onAdClicked();
    }

    public final void zzcf() {
        zzcb();
    }

    public final void zzcg() {
        zzbo();
    }

    public final void zzch() {
        zzcc();
    }

    public final void zzci() {
        if (this.zzvw.zzacw != null) {
            String str = this.zzvw.zzacw.zzbty;
            zzakb.zzdk(new StringBuilder(String.valueOf(str).length() + 74).append("Mediation adapter ").append(str).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        zza(this.zzvw.zzacw, true);
        zzb(this.zzvw.zzacw, true);
        zzbq();
    }

    public void zzcj() {
        recordImpression();
    }

    @Nullable
    public final String zzck() {
        return this.zzvw.zzacw == null ? null : zzc(this.zzvw.zzacw);
    }

    public final void zzcl() {
        Executor executor = zzaoe.zzcvy;
        zzbl zzbl = this.zzvv;
        zzbl.getClass();
        executor.execute(zze.zza(zzbl));
    }

    public final void zzcm() {
        Executor executor = zzaoe.zzcvy;
        zzbl zzbl = this.zzvv;
        zzbl.getClass();
        executor.execute(zzf.zza(zzbl));
    }
}
