// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.List;

final class zzbdo
{
    private static final Class<?> zzdyf;
    private static final zzbee<?, ?> zzdyg;
    private static final zzbee<?, ?> zzdyh;
    private static final zzbee<?, ?> zzdyi;
    
    static {
        zzdyf = zzafq();
        zzdyg = zzas(false);
        zzdyh = zzas(true);
        zzdyi = new zzbeg();
    }
    
    static <UT, UB> UB zza(final int n, final int n2, final UB ub, final zzbee<UT, UB> zzbee) {
        UB zzagb = ub;
        if (ub == null) {
            zzagb = zzbee.zzagb();
        }
        zzbee.zza(zzagb, n, n2);
        return zzagb;
    }
    
    static <UT, UB> UB zza(final int n, final List<Integer> list, final zzbbs<?> zzbbs, UB ub, final zzbee<UT, UB> zzbee) {
        if (zzbbs == null) {
            return ub;
        }
        UB ub2;
        if (list instanceof RandomAccess) {
            final int size = list.size();
            int i = 0;
            int n2 = 0;
            while (i < size) {
                final int intValue = list.get(i);
                if (zzbbs.zzq(intValue) != null) {
                    if (i != n2) {
                        list.set(n2, intValue);
                    }
                    ++n2;
                }
                else {
                    ub = zza(n, intValue, ub, zzbee);
                }
                ++i;
            }
            ub2 = ub;
            if (n2 != size) {
                list.subList(n2, size).clear();
                ub2 = ub;
            }
        }
        else {
            final Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                final int intValue2 = iterator.next();
                if (zzbbs.zzq(intValue2) == null) {
                    ub = zza(n, intValue2, ub, zzbee);
                    iterator.remove();
                }
            }
            ub2 = ub;
        }
        return ub2;
    }
    
    public static void zza(final int n, final List<String> list, final zzbey zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(n, list);
        }
    }
    
    public static void zza(final int n, final List<?> list, final zzbey zzbey, final zzbdm zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(n, list, zzbdm);
        }
    }
    
    public static void zza(final int n, final List<Double> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzg(n, list, b);
        }
    }
    
    static <T, FT extends zzbbi<FT>> void zza(final zzbbd<FT> zzbbd, final T t, final T t2) {
        final zzbbg<FT> zzm = zzbbd.zzm(t2);
        if (!zzm.isEmpty()) {
            zzbbd.zzn(t).zza(zzm);
        }
    }
    
    static <T> void zza(final zzbcp zzbcp, final T t, final T t2, final long n) {
        zzbek.zza(t, n, zzbcp.zzb(zzbek.zzp(t, n), zzbek.zzp(t2, n)));
    }
    
    static <T, UT, UB> void zza(final zzbee<UT, UB> zzbee, final T t, final T t2) {
        zzbee.zze(t, zzbee.zzg(zzbee.zzac(t), zzbee.zzac(t2)));
    }
    
    static int zzaf(final List<Long> list) {
        int n = 0;
        int n2 = 0;
        final int size = list.size();
        if (size != 0) {
            if (!(list instanceof zzbci)) {
                int i = 0;
                int n3 = 0;
                while (i < size) {
                    n3 += zzbav.zzp(list.get(i));
                    ++i;
                }
                return n3;
            }
            final zzbci zzbci = (zzbci)list;
            int n4 = 0;
            while (true) {
                n2 = n;
                if (n4 >= size) {
                    break;
                }
                final int zzp = zzbav.zzp(zzbci.getLong(n4));
                ++n4;
                n += zzp;
            }
        }
        return n2;
    }
    
    public static zzbee<?, ?> zzafn() {
        return zzbdo.zzdyg;
    }
    
    public static zzbee<?, ?> zzafo() {
        return zzbdo.zzdyh;
    }
    
    public static zzbee<?, ?> zzafp() {
        return zzbdo.zzdyi;
    }
    
    private static Class<?> zzafq() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    private static Class<?> zzafr() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    static int zzag(final List<Long> list) {
        int n = 0;
        int n2 = 0;
        final int size = list.size();
        if (size != 0) {
            if (!(list instanceof zzbci)) {
                int i = 0;
                int n3 = 0;
                while (i < size) {
                    n3 += zzbav.zzq(list.get(i));
                    ++i;
                }
                return n3;
            }
            final zzbci zzbci = (zzbci)list;
            int n4 = 0;
            while (true) {
                n2 = n;
                if (n4 >= size) {
                    break;
                }
                final int zzq = zzbav.zzq(zzbci.getLong(n4));
                ++n4;
                n += zzq;
            }
        }
        return n2;
    }
    
    static int zzah(final List<Long> list) {
        int n = 0;
        int n2 = 0;
        final int size = list.size();
        if (size != 0) {
            if (!(list instanceof zzbci)) {
                int i = 0;
                int n3 = 0;
                while (i < size) {
                    n3 += zzbav.zzr(list.get(i));
                    ++i;
                }
                return n3;
            }
            final zzbci zzbci = (zzbci)list;
            int n4 = 0;
            while (true) {
                n2 = n;
                if (n4 >= size) {
                    break;
                }
                final int zzr = zzbav.zzr(zzbci.getLong(n4));
                ++n4;
                n += zzr;
            }
        }
        return n2;
    }
    
    static int zzai(final List<Integer> list) {
        int n = 0;
        int n2 = 0;
        final int size = list.size();
        if (size != 0) {
            if (!(list instanceof zzbbp)) {
                int i = 0;
                int n3 = 0;
                while (i < size) {
                    n3 += zzbav.zzcj(list.get(i));
                    ++i;
                }
                return n3;
            }
            final zzbbp zzbbp = (zzbbp)list;
            int n4 = 0;
            while (true) {
                n2 = n;
                if (n4 >= size) {
                    break;
                }
                final int zzcj = zzbav.zzcj(zzbbp.getInt(n4));
                ++n4;
                n += zzcj;
            }
        }
        return n2;
    }
    
    static int zzaj(final List<Integer> list) {
        int n = 0;
        int n2 = 0;
        final int size = list.size();
        if (size != 0) {
            if (!(list instanceof zzbbp)) {
                int i = 0;
                int n3 = 0;
                while (i < size) {
                    n3 += zzbav.zzce(list.get(i));
                    ++i;
                }
                return n3;
            }
            final zzbbp zzbbp = (zzbbp)list;
            int n4 = 0;
            while (true) {
                n2 = n;
                if (n4 >= size) {
                    break;
                }
                final int zzce = zzbav.zzce(zzbbp.getInt(n4));
                ++n4;
                n += zzce;
            }
        }
        return n2;
    }
    
    static int zzak(final List<Integer> list) {
        int n = 0;
        int n2 = 0;
        final int size = list.size();
        if (size != 0) {
            if (!(list instanceof zzbbp)) {
                int i = 0;
                int n3 = 0;
                while (i < size) {
                    n3 += zzbav.zzcf(list.get(i));
                    ++i;
                }
                return n3;
            }
            final zzbbp zzbbp = (zzbbp)list;
            int n4 = 0;
            while (true) {
                n2 = n;
                if (n4 >= size) {
                    break;
                }
                final int zzcf = zzbav.zzcf(zzbbp.getInt(n4));
                ++n4;
                n += zzcf;
            }
        }
        return n2;
    }
    
    static int zzal(final List<Integer> list) {
        int n = 0;
        int n2 = 0;
        final int size = list.size();
        if (size != 0) {
            if (!(list instanceof zzbbp)) {
                int i = 0;
                int n3 = 0;
                while (i < size) {
                    n3 += zzbav.zzcg(list.get(i));
                    ++i;
                }
                return n3;
            }
            final zzbbp zzbbp = (zzbbp)list;
            int n4 = 0;
            while (true) {
                n2 = n;
                if (n4 >= size) {
                    break;
                }
                final int zzcg = zzbav.zzcg(zzbbp.getInt(n4));
                ++n4;
                n += zzcg;
            }
        }
        return n2;
    }
    
    static int zzam(final List<?> list) {
        return list.size() << 2;
    }
    
    static int zzan(final List<?> list) {
        return list.size() << 3;
    }
    
    static int zzao(final List<?> list) {
        return list.size();
    }
    
    private static zzbee<?, ?> zzas(final boolean b) {
        try {
            final Class<?> zzafr = zzafr();
            if (zzafr == null) {
                return null;
            }
            return (zzbee<?, ?>)zzafr.getConstructor(Boolean.TYPE).newInstance(b);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static void zzb(final int n, final List<zzbah> list, final zzbey zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(n, list);
        }
    }
    
    public static void zzb(final int n, final List<?> list, final zzbey zzbey, final zzbdm zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(n, list, zzbdm);
        }
    }
    
    public static void zzb(final int n, final List<Float> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzf(n, list, b);
        }
    }
    
    static int zzc(final int n, final Object o, final zzbdm zzbdm) {
        if (o instanceof zzbcb) {
            return zzbav.zza(n, (zzbcb)o);
        }
        return zzbav.zzb(n, (zzbcu)o, zzbdm);
    }
    
    static int zzc(int n, final List<?> list) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        n = zzbav.zzcd(n) * size;
        if (list instanceof zzbcd) {
            final zzbcd zzbcd = (zzbcd)list;
            for (int i = 0; i < size; ++i) {
                final Object zzcp = zzbcd.zzcp(i);
                if (zzcp instanceof zzbah) {
                    n += zzbav.zzao((zzbah)zzcp);
                }
                else {
                    n += zzbav.zzeo((String)zzcp);
                }
            }
            return n;
        }
        for (int j = 0; j < size; ++j) {
            final zzbah value = list.get(j);
            if (value instanceof zzbah) {
                n += zzbav.zzao(value);
            }
            else {
                n += zzbav.zzeo((String)value);
            }
        }
        return n;
    }
    
    static int zzc(int n, final List<?> list, final zzbdm zzbdm) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        n = zzbav.zzcd(n) * size;
        for (int i = 0; i < size; ++i) {
            final Object value = list.get(i);
            if (value instanceof zzbcb) {
                n += zzbav.zza((zzbcb)value);
            }
            else {
                n += zzbav.zza((zzbcu)value, zzbdm);
            }
        }
        return n;
    }
    
    public static void zzc(final int n, final List<Long> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzc(n, list, b);
        }
    }
    
    static int zzd(int i, final List<zzbah> list) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        int n = size * zzbav.zzcd(i);
        for (i = 0; i < list.size(); ++i) {
            n += zzbav.zzao(list.get(i));
        }
        return n;
    }
    
    static int zzd(final int n, final List<zzbcu> list, final zzbdm zzbdm) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        int n2 = 0;
        while (i < size) {
            n2 += zzbav.zzc(n, list.get(i), zzbdm);
            ++i;
        }
        return n2;
    }
    
    public static void zzd(final int n, final List<Long> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzd(n, list, b);
        }
    }
    
    static boolean zzd(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static void zze(final int n, final List<Long> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzn(n, list, b);
        }
    }
    
    public static boolean zze(final int n, final int n2, final int n3) {
        return n2 < 40 || n2 - (long)n + 1L + 9L <= 2L * n3 + 3L + (n3 + 3L) * 3L;
    }
    
    public static void zzf(final int n, final List<Long> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zze(n, list, b);
        }
    }
    
    public static void zzf(final Class<?> clazz) {
        if (!zzbbo.class.isAssignableFrom(clazz) && zzbdo.zzdyf != null && !zzbdo.zzdyf.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }
    
    public static void zzg(final int n, final List<Long> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzl(n, list, b);
        }
    }
    
    public static void zzh(final int n, final List<Integer> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(n, list, b);
        }
    }
    
    public static void zzi(final int n, final List<Integer> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzj(n, list, b);
        }
    }
    
    public static void zzj(final int n, final List<Integer> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzm(n, list, b);
        }
    }
    
    public static void zzk(final int n, final List<Integer> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(n, list, b);
        }
    }
    
    public static void zzl(final int n, final List<Integer> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzk(n, list, b);
        }
    }
    
    public static void zzm(final int n, final List<Integer> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzh(n, list, b);
        }
    }
    
    public static void zzn(final int n, final List<Boolean> list, final zzbey zzbey, final boolean b) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzi(n, list, b);
        }
    }
    
    static int zzo(final int n, final List<Long> list, final boolean b) {
        if (list.size() == 0) {
            return 0;
        }
        return zzaf(list) + list.size() * zzbav.zzcd(n);
    }
    
    static int zzp(final int n, final List<Long> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzcd(n) + zzag(list);
    }
    
    static int zzq(final int n, final List<Long> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzcd(n) + zzah(list);
    }
    
    static int zzr(final int n, final List<Integer> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzcd(n) + zzai(list);
    }
    
    static int zzs(final int n, final List<Integer> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzcd(n) + zzaj(list);
    }
    
    static int zzt(final int n, final List<Integer> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzcd(n) + zzak(list);
    }
    
    static int zzu(final int n, final List<Integer> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzcd(n) + zzal(list);
    }
    
    static int zzv(final int n, final List<?> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzbav.zzt(n, 0) * size;
    }
    
    static int zzw(final int n, final List<?> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzg(n, 0L);
    }
    
    static int zzx(final int n, final List<?> list, final boolean b) {
        final int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzg(n, true);
    }
}
