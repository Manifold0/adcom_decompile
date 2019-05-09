// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import java.io.IOException;
import java.util.Iterator;
import android.os.Parcel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.regex.Matcher;
import com.google.android.gms.ads.internal.zzbv;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import android.util.Base64;
import java.util.HashMap;
import android.support.annotation.Nullable;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zztw
{
    private final Map<zztx, zzty> zzbok;
    private final LinkedList<zztx> zzbol;
    @Nullable
    private zzss zzbom;
    
    public zztw() {
        this.zzbok = new HashMap<zztx, zzty>();
        this.zzbol = new LinkedList<zztx>();
    }
    
    private static void zza(final String s, final zztx zztx) {
        if (zzakb.isLoggable(2)) {
            zzakb.v(String.format(s, zztx));
        }
    }
    
    private static String[] zzax(final String s) {
        String[] array;
        try {
            final String[] split = s.split("\u0000");
            int n = 0;
            while (true) {
                array = split;
                if (n >= split.length) {
                    break;
                }
                split[n] = new String(Base64.decode(split[n], 0), "UTF-8");
                ++n;
            }
        }
        catch (UnsupportedEncodingException ex) {
            array = new String[0];
        }
        return array;
    }
    
    private static boolean zzay(final String s) {
        try {
            return Pattern.matches((String)zzkb.zzik().zzd(zznk.zzazf), s);
        }
        catch (RuntimeException ex) {
            zzbv.zzeo().zza(ex, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }
    
    @VisibleForTesting
    private static String zzaz(final String s) {
        try {
            final Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(s);
            String group = s;
            if (matcher.matches()) {
                group = matcher.group(1);
            }
            return group;
        }
        catch (RuntimeException ex) {
            return s;
        }
    }
    
    private static void zzb(Bundle bundle, String s) {
        String s2 = null;
        Block_0: {
            while (true) {
                final String[] split = s.split("/", 2);
                if (split.length == 0) {
                    break;
                }
                s2 = split[0];
                if (split.length == 1) {
                    break Block_0;
                }
                bundle = bundle.getBundle(s2);
                if (bundle == null) {
                    break;
                }
                s = split[1];
            }
            return;
        }
        bundle.remove(s2);
    }
    
    static Set<String> zzh(final zzjj zzjj) {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<String>();
        set.addAll(zzjj.extras.keySet());
        final Bundle bundle = zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            set.addAll(bundle.keySet());
        }
        return (Set<String>)set;
    }
    
    static zzjj zzi(zzjj zzk) {
        zzk = zzk(zzk);
        final Bundle bundle = zzk.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean("_skipMediation", true);
        }
        zzk.extras.putBoolean("_skipMediation", true);
        return zzk;
    }
    
    @VisibleForTesting
    private static zzjj zzj(zzjj zzk) {
        zzk = zzk(zzk);
        final String[] split = ((String)zzkb.zzik().zzd(zznk.zzazb)).split(",");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String s = split[i];
            zzb(zzk.zzaqg, s);
            if (s.startsWith("com.google.ads.mediation.admob.AdMobAdapter/")) {
                zzb(zzk.extras, s.replaceFirst("com.google.ads.mediation.admob.AdMobAdapter/", ""));
            }
        }
        return zzk;
    }
    
    @VisibleForTesting
    private static zzjj zzk(zzjj zzhv) {
        final Parcel obtain = Parcel.obtain();
        zzhv.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        final zzjj zzjj = (zzjj)com.google.android.gms.internal.ads.zzjj.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        final zzna zzayo = zznk.zzayo;
        zzhv = zzjj;
        if (zzkb.zzik().zzd(zzayo)) {
            zzhv = zzjj.zzhv();
        }
        return zzhv;
    }
    
    private final String zzle() {
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            final Iterator<zztx> iterator = (Iterator<zztx>)this.zzbol.iterator();
            while (iterator.hasNext()) {
                sb.append(Base64.encodeToString(iterator.next().toString().getBytes("UTF-8"), 0));
                if (iterator.hasNext()) {
                    sb.append("\u0000");
                }
            }
        }
        catch (UnsupportedEncodingException ex) {
            return "";
        }
        return sb.toString();
    }
    
    @Nullable
    final zztz zza(final zzjj zzjj, final String s) {
        if (zzay(s)) {
            return null;
        }
        final int zzcjx = new zzagb(this.zzbom.getApplicationContext()).zzoo().zzcjx;
        final zzjj zzj = zzj(zzjj);
        final String zzaz = zzaz(s);
        final zztx zztx = new zztx(zzj, zzaz, zzcjx);
        zzty zzty = this.zzbok.get(zztx);
        if (zzty == null) {
            zza("Interstitial pool created at %s.", zztx);
            zzty = new zzty(zzj, zzaz, zzcjx);
            this.zzbok.put(zztx, zzty);
        }
        this.zzbol.remove(zztx);
        this.zzbol.add(zztx);
        zzty.zzli();
        while (this.zzbol.size() > (int)zzkb.zzik().zzd(zznk.zzazc)) {
            final zztx zztx2 = this.zzbol.remove();
            final zzty zzty2 = this.zzbok.get(zztx2);
            zza("Evicting interstitial queue for %s.", zztx2);
            while (zzty2.size() > 0) {
                final zztz zzl = zzty2.zzl(null);
                if (zzl.zzwa) {
                    zzua.zzlk().zzlm();
                }
                zzl.zzbor.zzdj();
            }
            this.zzbok.remove(zztx2);
        }
        while (zzty.size() > 0) {
            final zztz zzl2 = zzty.zzl(zzj);
            if (!zzl2.zzwa || zzbv.zzer().currentTimeMillis() - zzl2.zzbou <= 1000L * (int)zzkb.zzik().zzd(zznk.zzaze)) {
                String s2;
                if (zzl2.zzbos != null) {
                    s2 = " (inline) ";
                }
                else {
                    s2 = " ";
                }
                zza(new StringBuilder(String.valueOf(s2).length() + 34).append("Pooled interstitial").append(s2).append("returned at %s.").toString(), zztx);
                return zzl2;
            }
            zza("Expired interstitial at %s.", zztx);
            zzua.zzlk().zzll();
        }
        return null;
    }
    
    final void zza(zzss sharedPreferences) {
        if (this.zzbom != null) {
            goto Label_0340;
        }
        this.zzbom = ((zzss)sharedPreferences).zzlc();
        if (this.zzbom == null) {
            goto Label_0340;
        }
        sharedPreferences = (IOException)this.zzbom.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
        while (this.zzbol.size() > 0) {
            final zztx zztx = this.zzbol.remove();
            final zzty zzty = this.zzbok.get(zztx);
            zza("Flushing interstitial queue for %s.", zztx);
            while (zzty.size() > 0) {
                zzty.zzl(null).zzbor.zzdj();
            }
            this.zzbok.remove(zztx);
        }
        try {
            final HashMap<String, zztx> hashMap = new HashMap<String, zztx>();
            for (final Map.Entry<String, V> entry : ((SharedPreferences)sharedPreferences).getAll().entrySet()) {
                if (!entry.getKey().equals("PoolKeys")) {
                    final zzuc zzba = zzuc.zzba((String)entry.getValue());
                    final zztx zztx2 = new zztx(zzba.zzaao, zzba.zzye, zzba.zzbop);
                    if (this.zzbok.containsKey(zztx2)) {
                        continue;
                    }
                    this.zzbok.put(zztx2, new zzty(zzba.zzaao, zzba.zzye, zzba.zzbop));
                    hashMap.put(zztx2.toString(), zztx2);
                    zza("Restored interstitial queue for %s.", zztx2);
                }
            }
            goto Label_0341;
        }
        catch (RuntimeException ex) {}
        catch (IOException sharedPreferences) {
            goto Label_0307;
        }
    }
    
    final void zzb(final zzjj zzjj, final String s) {
        if (this.zzbom == null) {
            return;
        }
        final int zzcjx = new zzagb(this.zzbom.getApplicationContext()).zzoo().zzcjx;
        final zzjj zzj = zzj(zzjj);
        final String zzaz = zzaz(s);
        final zztx zztx = new zztx(zzj, zzaz, zzcjx);
        zzty zzty;
        if ((zzty = this.zzbok.get(zztx)) == null) {
            zza("Interstitial pool created at %s.", zztx);
            zzty = new zzty(zzj, zzaz, zzcjx);
            this.zzbok.put(zztx, zzty);
        }
        zzty.zza(this.zzbom, zzjj);
        zzty.zzli();
        zza("Inline entry added to the queue at %s.", zztx);
    }
    
    final void zzld() {
        if (this.zzbom != null) {
            for (final Map.Entry<zztx, zzty> entry : this.zzbok.entrySet()) {
                final zztx zztx = entry.getKey();
                final zzty zzty = entry.getValue();
                if (zzakb.isLoggable(2)) {
                    final int size = zzty.size();
                    final int zzlg = zzty.zzlg();
                    if (zzlg < size) {
                        zzakb.v(String.format("Loading %s/%s pooled interstitials for %s.", size - zzlg, size, zztx));
                    }
                }
                int n = zzty.zzlh() + 0;
                while (zzty.size() < (int)zzkb.zzik().zzd(zznk.zzazd)) {
                    zza("Pooling and loading one new interstitial for %s.", zztx);
                    if (zzty.zzb(this.zzbom)) {
                        ++n;
                    }
                }
                zzua.zzlk().zzw(n);
            }
            if (this.zzbom != null) {
                final SharedPreferences$Editor edit = this.zzbom.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
                edit.clear();
                for (final Map.Entry<zztx, zzty> entry2 : this.zzbok.entrySet()) {
                    final zztx zztx2 = entry2.getKey();
                    final zzty zzty2 = entry2.getValue();
                    if (zzty2.zzlj()) {
                        edit.putString(zztx2.toString(), new zzuc(zzty2).zzlu());
                        zza("Saved interstitial queue for %s.", zztx2);
                    }
                }
                edit.putString("PoolKeys", this.zzle());
                edit.apply();
            }
        }
    }
}
