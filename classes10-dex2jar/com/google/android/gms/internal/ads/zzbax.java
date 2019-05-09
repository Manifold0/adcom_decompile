// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.io.IOException;

final class zzbax implements zzbey
{
    private final zzbav zzdpv;
    
    private zzbax(final zzbav zzbav) {
        this.zzdpv = zzbbq.zza(zzbav, "output");
        this.zzdpv.zzdqn = this;
    }
    
    public static zzbax zza(final zzbav zzbav) {
        if (zzbav.zzdqn != null) {
            return zzbav.zzdqn;
        }
        return new zzbax(zzbav);
    }
    
    @Override
    public final void zza(final int n, final double n2) throws IOException {
        this.zzdpv.zza(n, n2);
    }
    
    @Override
    public final void zza(final int n, final float n2) throws IOException {
        this.zzdpv.zza(n, n2);
    }
    
    @Override
    public final void zza(final int n, final long n2) throws IOException {
        this.zzdpv.zza(n, n2);
    }
    
    @Override
    public final void zza(final int n, final zzbah zzbah) throws IOException {
        this.zzdpv.zza(n, zzbah);
    }
    
    @Override
    public final <K, V> void zza(final int n, final zzbcn<K, V> zzbcn, final Map<K, V> map) throws IOException {
        for (final Map.Entry<K, V> entry : map.entrySet()) {
            this.zzdpv.zzl(n, 2);
            this.zzdpv.zzca(zzbcm.zza(zzbcn, entry.getKey(), entry.getValue()));
            zzbcm.zza(this.zzdpv, (zzbcn<K, V>)zzbcn, (K)entry.getKey(), (V)entry.getValue());
        }
    }
    
    @Override
    public final void zza(final int n, final Object o) throws IOException {
        if (o instanceof zzbah) {
            this.zzdpv.zzb(n, (zzbah)o);
            return;
        }
        this.zzdpv.zza(n, (zzbcu)o);
    }
    
    @Override
    public final void zza(final int n, final Object o, final zzbdm zzbdm) throws IOException {
        this.zzdpv.zza(n, (zzbcu)o, zzbdm);
    }
    
    @Override
    public final void zza(final int n, final List<String> list) throws IOException {
        int i = 0;
        if (list instanceof zzbcd) {
            final zzbcd zzbcd = (zzbcd)list;
            for (int j = 0; j < list.size(); ++j) {
                final Object zzcp = zzbcd.zzcp(j);
                if (zzcp instanceof String) {
                    this.zzdpv.zzf(n, (String)zzcp);
                }
                else {
                    this.zzdpv.zza(n, (zzbah)zzcp);
                }
            }
        }
        else {
            while (i < list.size()) {
                this.zzdpv.zzf(n, list.get(i));
                ++i;
            }
        }
    }
    
    @Override
    public final void zza(final int n, final List<?> list, final zzbdm zzbdm) throws IOException {
        for (int i = 0; i < list.size(); ++i) {
            this.zza(n, list.get(i), zzbdm);
        }
    }
    
    @Override
    public final void zza(int i, final List<Integer> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzce(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzbz(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzm(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final int zzacn() {
        return zzbbo.zze.zzdul;
    }
    
    @Override
    public final void zzb(final int n, final long n2) throws IOException {
        this.zzdpv.zzb(n, n2);
    }
    
    @Override
    public final void zzb(final int n, final Object o, final zzbdm zzbdm) throws IOException {
        final zzbav zzdpv = this.zzdpv;
        final zzbcu zzbcu = (zzbcu)o;
        zzdpv.zzl(n, 3);
        zzbdm.zza(zzbcu, zzdpv.zzdqn);
        zzdpv.zzl(n, 4);
    }
    
    @Override
    public final void zzb(final int n, final List<zzbah> list) throws IOException {
        for (int i = 0; i < list.size(); ++i) {
            this.zzdpv.zza(n, list.get(i));
        }
    }
    
    @Override
    public final void zzb(final int n, final List<?> list, final zzbdm zzbdm) throws IOException {
        for (int i = 0; i < list.size(); ++i) {
            this.zzb(n, list.get(i), zzbdm);
        }
    }
    
    @Override
    public final void zzb(int i, final List<Integer> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzch(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzcc(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzp(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzc(final int n, final long n2) throws IOException {
        this.zzdpv.zzc(n, n2);
    }
    
    @Override
    public final void zzc(int i, final List<Long> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzp(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzm(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zza(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzcm(final int n) throws IOException {
        this.zzdpv.zzl(n, 3);
    }
    
    @Override
    public final void zzcn(final int n) throws IOException {
        this.zzdpv.zzl(n, 4);
    }
    
    @Override
    public final void zzd(int i, final List<Long> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzq(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzm(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zza(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zze(int i, final List<Long> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzs(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzo(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzc(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzf(final int n, final String s) throws IOException {
        this.zzdpv.zzf(n, s);
    }
    
    @Override
    public final void zzf(int i, final List<Float> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzc(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzb(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zza(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzf(final int n, final boolean b) throws IOException {
        this.zzdpv.zzf(n, b);
    }
    
    @Override
    public final void zzg(int i, final List<Double> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzc(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzb(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zza(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzh(int i, final List<Integer> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzcj(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzbz(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzm(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzi(final int n, final long n2) throws IOException {
        this.zzdpv.zza(n, n2);
    }
    
    @Override
    public final void zzi(int i, final List<Boolean> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzaq(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzap(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzf(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzj(final int n, final long n2) throws IOException {
        this.zzdpv.zzc(n, n2);
    }
    
    @Override
    public final void zzj(int i, final List<Integer> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzcf(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzca(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzn(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzk(int i, final List<Integer> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzci(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzcc(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzp(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzl(int i, final List<Long> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzt(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzo(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzc(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzm(final int n, final int n2) throws IOException {
        this.zzdpv.zzm(n, n2);
    }
    
    @Override
    public final void zzm(int i, final List<Integer> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzcg(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzcb(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzo(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzn(final int n, final int n2) throws IOException {
        this.zzdpv.zzn(n, n2);
    }
    
    @Override
    public final void zzn(int i, final List<Long> list, final boolean b) throws IOException {
        int j = 0;
        final int n = 0;
        if (b) {
            this.zzdpv.zzl(i, 2);
            i = 0;
            int n2 = 0;
            while (i < list.size()) {
                n2 += zzbav.zzr(list.get(i));
                ++i;
            }
            this.zzdpv.zzca(n2);
            for (i = n; i < list.size(); ++i) {
                this.zzdpv.zzn(list.get(i));
            }
        }
        else {
            while (j < list.size()) {
                this.zzdpv.zzb(i, list.get(j));
                ++j;
            }
        }
    }
    
    @Override
    public final void zzo(final int n, final int n2) throws IOException {
        this.zzdpv.zzo(n, n2);
    }
    
    @Override
    public final void zzp(final int n, final int n2) throws IOException {
        this.zzdpv.zzp(n, n2);
    }
    
    @Override
    public final void zzw(final int n, final int n2) throws IOException {
        this.zzdpv.zzp(n, n2);
    }
    
    @Override
    public final void zzx(final int n, final int n2) throws IOException {
        this.zzdpv.zzm(n, n2);
    }
}
