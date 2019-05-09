// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.internal.zzbv;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzge
{
    private final Object mLock;
    private final int zzagx;
    private final int zzagy;
    private final int zzagz;
    private final zzgr zzaha;
    private final zzha zzahb;
    private ArrayList<String> zzahc;
    private ArrayList<String> zzahd;
    private ArrayList<zzgp> zzahe;
    private int zzahf;
    private int zzahg;
    private int zzahh;
    private int zzahi;
    private String zzahj;
    private String zzahk;
    private String zzahl;
    
    public zzge(final int zzagx, final int zzagy, final int zzagz, final int n, final int n2, final int n3, final int n4) {
        this.mLock = new Object();
        this.zzahc = new ArrayList<String>();
        this.zzahd = new ArrayList<String>();
        this.zzahe = new ArrayList<zzgp>();
        this.zzahf = 0;
        this.zzahg = 0;
        this.zzahh = 0;
        this.zzahj = "";
        this.zzahk = "";
        this.zzahl = "";
        this.zzagx = zzagx;
        this.zzagy = zzagy;
        this.zzagz = zzagz;
        this.zzaha = new zzgr(n);
        this.zzahb = new zzha(n2, n3, n4);
    }
    
    private static String zza(final ArrayList<String> list, int i) {
        String string;
        if (list.isEmpty()) {
            string = "";
        }
        else {
            final StringBuilder sb = new StringBuilder();
            final ArrayList<String> list2 = list;
            final int size = list2.size();
            i = 0;
            while (i < size) {
                final String value = list2.get(i);
                ++i;
                sb.append(value);
                sb.append(' ');
                if (sb.length() > 100) {
                    break;
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            final String s = string = sb.toString();
            if (s.length() >= 100) {
                return s.substring(0, 100);
            }
        }
        return string;
    }
    
    private final void zzc(@Nullable final String s, final boolean b, final float n, final float n2, final float n3, final float n4) {
        if (s == null || s.length() < this.zzagz) {
            return;
        }
        synchronized (this.mLock) {
            this.zzahc.add(s);
            this.zzahf += s.length();
            if (b) {
                this.zzahd.add(s);
                this.zzahe.add(new zzgp(n, n2, n3, n4, this.zzahd.size() - 1));
            }
        }
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof zzge) {
            if (o == this) {
                return true;
            }
            final zzge zzge = (zzge)o;
            if (zzge.zzahj != null && zzge.zzahj.equals(this.zzahj)) {
                return true;
            }
        }
        return false;
    }
    
    public final int getScore() {
        return this.zzahi;
    }
    
    public final String getSignature() {
        return this.zzahj;
    }
    
    @Override
    public final int hashCode() {
        return this.zzahj.hashCode();
    }
    
    @Override
    public final String toString() {
        final int zzahg = this.zzahg;
        final int zzahi = this.zzahi;
        final int zzahf = this.zzahf;
        final String zza = zza(this.zzahc, 100);
        final String zza2 = zza(this.zzahd, 100);
        final String zzahj = this.zzahj;
        final String zzahk = this.zzahk;
        final String zzahl = this.zzahl;
        return new StringBuilder(String.valueOf(zza).length() + 165 + String.valueOf(zza2).length() + String.valueOf(zzahj).length() + String.valueOf(zzahk).length() + String.valueOf(zzahl).length()).append("ActivityContent fetchId: ").append(zzahg).append(" score:").append(zzahi).append(" total_length:").append(zzahf).append("\n text: ").append(zza).append("\n viewableText").append(zza2).append("\n signture: ").append(zzahj).append("\n viewableSignture: ").append(zzahk).append("\n viewableSignatureForVertical: ").append(zzahl).toString();
    }
    
    public final void zza(final String s, final boolean b, final float n, final float n2, final float n3, final float n4) {
        this.zzc(s, b, n, n2, n3, n4);
        synchronized (this.mLock) {
            if (this.zzahh < 0) {
                zzakb.zzck("ActivityContent: negative number of WebViews.");
            }
            this.zzgt();
        }
    }
    
    public final void zzb(final String s, final boolean b, final float n, final float n2, final float n3, final float n4) {
        this.zzc(s, b, n, n2, n3, n4);
    }
    
    public final boolean zzgn() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzahh == 0) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public final String zzgo() {
        return this.zzahk;
    }
    
    public final String zzgp() {
        return this.zzahl;
    }
    
    public final void zzgq() {
        synchronized (this.mLock) {
            this.zzahi -= 100;
        }
    }
    
    public final void zzgr() {
        synchronized (this.mLock) {
            --this.zzahh;
        }
    }
    
    public final void zzgs() {
        synchronized (this.mLock) {
            ++this.zzahh;
        }
    }
    
    public final void zzgt() {
        synchronized (this.mLock) {
            final int zzahi = this.zzahf * this.zzagx + this.zzahg * this.zzagy;
            if (zzahi > this.zzahi) {
                this.zzahi = zzahi;
                if ((boolean)zzkb.zzik().zzd(zznk.zzawq) && !zzbv.zzeo().zzqh().zzqu()) {
                    this.zzahj = this.zzaha.zza(this.zzahc);
                    this.zzahk = this.zzaha.zza(this.zzahd);
                }
                if ((boolean)zzkb.zzik().zzd(zznk.zzaws) && !zzbv.zzeo().zzqh().zzqw()) {
                    this.zzahl = this.zzahb.zza(this.zzahd, this.zzahe);
                }
            }
        }
    }
    
    @VisibleForTesting
    final int zzgu() {
        return this.zzahf;
    }
    
    public final void zzo(final int zzahg) {
        this.zzahg = zzahg;
    }
}
