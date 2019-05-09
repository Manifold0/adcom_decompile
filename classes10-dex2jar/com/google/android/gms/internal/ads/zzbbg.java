// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

final class zzbbg<FieldDescriptorType extends zzbbi<FieldDescriptorType>>
{
    private static final zzbbg zzdra;
    private final zzbdp<FieldDescriptorType, Object> zzdqx;
    private boolean zzdqy;
    private boolean zzdqz;
    
    static {
        zzdra = new zzbbg(true);
    }
    
    private zzbbg() {
        this.zzdqz = false;
        this.zzdqx = zzbdp.zzcx(16);
    }
    
    private zzbbg(final boolean b) {
        this.zzdqz = false;
        this.zzdqx = zzbdp.zzcx(0);
        this.zzaaz();
    }
    
    static int zza(final zzbes zzbes, int zzcd, final Object o) {
        zzcd = zzbav.zzcd(zzcd);
        if (zzbes == zzbes.zzeaj) {
            zzbbq.zzi((zzbcu)o);
            zzcd <<= 1;
        }
        return zzcd + zzb(zzbes, o);
    }
    
    private final Object zza(final FieldDescriptorType fieldDescriptorType) {
        Object o;
        if ((o = this.zzdqx.get(fieldDescriptorType)) instanceof zzbbx) {
            o = zzbbx.zzadu();
        }
        return o;
    }
    
    static void zza(final zzbav zzbav, final zzbes zzbes, final int n, final Object o) throws IOException {
        if (zzbes == zzbes.zzeaj) {
            zzbbq.zzi((zzbcu)o);
            final zzbcu zzbcu = (zzbcu)o;
            zzbav.zzl(n, 3);
            zzbcu.zzb(zzbav);
            zzbav.zzl(n, 4);
            return;
        }
        zzbav.zzl(n, zzbes.zzagm());
        switch (zzbbh.zzdql[zzbes.ordinal()]) {
            default: {}
            case 1: {
                zzbav.zzb((double)o);
            }
            case 2: {
                zzbav.zzb((float)o);
            }
            case 3: {
                zzbav.zzm((long)o);
            }
            case 4: {
                zzbav.zzm((long)o);
            }
            case 5: {
                zzbav.zzbz((int)o);
            }
            case 6: {
                zzbav.zzo((long)o);
            }
            case 7: {
                zzbav.zzcc((int)o);
            }
            case 8: {
                zzbav.zzap((boolean)o);
            }
            case 9: {
                ((zzbcu)o).zzb(zzbav);
            }
            case 10: {
                zzbav.zze((zzbcu)o);
            }
            case 11: {
                if (o instanceof zzbah) {
                    zzbav.zzan((zzbah)o);
                    return;
                }
                zzbav.zzen((String)o);
            }
            case 12: {
                if (o instanceof zzbah) {
                    zzbav.zzan((zzbah)o);
                    return;
                }
                final byte[] array = (byte[])o;
                zzbav.zze(array, 0, array.length);
            }
            case 13: {
                zzbav.zzca((int)o);
            }
            case 14: {
                zzbav.zzcc((int)o);
            }
            case 15: {
                zzbav.zzo((long)o);
            }
            case 16: {
                zzbav.zzcb((int)o);
            }
            case 17: {
                zzbav.zzn((long)o);
            }
            case 18: {
                if (o instanceof zzbbr) {
                    zzbav.zzbz(((zzbbr)o).zzhq());
                    return;
                }
                zzbav.zzbz((int)o);
            }
        }
    }
    
    private final void zza(final FieldDescriptorType fieldDescriptorType, Object value) {
        if (fieldDescriptorType.zzada()) {
            if (!(value instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            final ArrayList<Object> list = new ArrayList<Object>();
            list.addAll((Collection<?>)value);
            final ArrayList<Object> list2 = list;
            final int size = list2.size();
            int n = 0;
            while (true) {
                value = list;
                if (n >= size) {
                    break;
                }
                value = list2.get(n);
                ++n;
                zza(fieldDescriptorType.zzacy(), value);
            }
        }
        else {
            zza(fieldDescriptorType.zzacy(), value);
        }
        if (value instanceof zzbbx) {
            this.zzdqz = true;
        }
        this.zzdqx.zza(fieldDescriptorType, value);
    }
    
    private static void zza(final zzbes zzbes, final Object o) {
        int n = 0;
        zzbbq.checkNotNull(o);
        switch (zzbbh.zzdrb[zzbes.zzagl().ordinal()]) {
            case 1: {
                n = ((o instanceof Integer) ? 1 : 0);
                break;
            }
            case 2: {
                n = ((o instanceof Long) ? 1 : 0);
                break;
            }
            case 3: {
                n = ((o instanceof Float) ? 1 : 0);
                break;
            }
            case 4: {
                n = ((o instanceof Double) ? 1 : 0);
                break;
            }
            case 5: {
                n = ((o instanceof Boolean) ? 1 : 0);
                break;
            }
            case 6: {
                n = ((o instanceof String) ? 1 : 0);
                break;
            }
            case 7: {
                if (o instanceof zzbah || o instanceof byte[]) {
                    n = 1;
                    break;
                }
                break;
            }
            case 8: {
                if (o instanceof Integer || o instanceof zzbbr) {
                    n = 1;
                    break;
                }
                break;
            }
            case 9: {
                if (o instanceof zzbcu || o instanceof zzbbx) {
                    n = 1;
                    break;
                }
                break;
            }
        }
        if (n == 0) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }
    
    public static <T extends zzbbi<T>> zzbbg<T> zzacv() {
        return (zzbbg<T>)zzbbg.zzdra;
    }
    
    private static int zzb(final zzbbi<?> zzbbi, final Object o) {
        final int n = 0;
        int n2 = 0;
        final zzbes zzacy = zzbbi.zzacy();
        final int zzhq = zzbbi.zzhq();
        if (zzbbi.zzada()) {
            int n3;
            if (zzbbi.zzadb()) {
                final Iterator<Object> iterator = (Iterator<Object>)((List)o).iterator();
                while (iterator.hasNext()) {
                    n2 += zzb(zzacy, iterator.next());
                }
                n3 = zzbav.zzcl(n2) + (zzbav.zzcd(zzhq) + n2);
            }
            else {
                final Iterator iterator2 = ((List)o).iterator();
                int n4 = n;
                while (true) {
                    n3 = n4;
                    if (!iterator2.hasNext()) {
                        break;
                    }
                    n4 += zza(zzacy, zzhq, iterator2.next());
                }
            }
            return n3;
        }
        return zza(zzacy, zzhq, o);
    }
    
    private static int zzb(final zzbes zzbes, final Object o) {
        switch (zzbbh.zzdql[zzbes.ordinal()]) {
            default: {
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
            case 1: {
                return zzbav.zzc((double)o);
            }
            case 2: {
                return zzbav.zzc((float)o);
            }
            case 3: {
                return zzbav.zzp((long)o);
            }
            case 4: {
                return zzbav.zzq((long)o);
            }
            case 5: {
                return zzbav.zzce((int)o);
            }
            case 6: {
                return zzbav.zzs((long)o);
            }
            case 7: {
                return zzbav.zzch((int)o);
            }
            case 8: {
                return zzbav.zzaq((boolean)o);
            }
            case 9: {
                return zzbav.zzg((zzbcu)o);
            }
            case 12: {
                if (o instanceof zzbah) {
                    return zzbav.zzao((zzbah)o);
                }
                return zzbav.zzr((byte[])o);
            }
            case 11: {
                if (o instanceof zzbah) {
                    return zzbav.zzao((zzbah)o);
                }
                return zzbav.zzeo((String)o);
            }
            case 13: {
                return zzbav.zzcf((int)o);
            }
            case 14: {
                return zzbav.zzci((int)o);
            }
            case 15: {
                return zzbav.zzt((long)o);
            }
            case 16: {
                return zzbav.zzcg((int)o);
            }
            case 17: {
                return zzbav.zzr((long)o);
            }
            case 10: {
                if (o instanceof zzbbx) {
                    return zzbav.zza((zzbcb)o);
                }
                return zzbav.zzf((zzbcu)o);
            }
            case 18: {
                if (o instanceof zzbbr) {
                    return zzbav.zzcj(((zzbbr)o).zzhq());
                }
                return zzbav.zzcj((int)o);
            }
        }
    }
    
    private static boolean zzb(final Map.Entry<FieldDescriptorType, Object> entry) {
        final zzbbi<FieldDescriptorType> zzbbi = entry.getKey();
        if (zzbbi.zzacz() == zzbex.zzebd) {
            if (zzbbi.zzada()) {
                final Iterator<zzbcu> iterator = entry.getValue().iterator();
                while (iterator.hasNext()) {
                    if (!iterator.next().isInitialized()) {
                        return false;
                    }
                }
            }
            else {
                final List<zzbcu> value = entry.getValue();
                if (value instanceof zzbcu) {
                    if (!((zzbcu)value).isInitialized()) {
                        return false;
                    }
                }
                else {
                    if (value instanceof zzbbx) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }
    
    private final void zzc(final Map.Entry<FieldDescriptorType, Object> entry) {
        final zzbbi<FieldDescriptorType> zzbbi = entry.getKey();
        zzbcu zzbcu;
        if ((zzbcu = entry.getValue()) instanceof zzbbx) {
            zzbcu = zzbbx.zzadu();
        }
        if (zzbbi.zzada()) {
            Object zza;
            if ((zza = this.zza((FieldDescriptorType)zzbbi)) == null) {
                zza = new ArrayList<Object>();
            }
            final Iterator<Object> iterator = ((List<Object>)zzbcu).iterator();
            while (iterator.hasNext()) {
                ((List<Object>)zza).add(zzp(iterator.next()));
            }
            this.zzdqx.zza((FieldDescriptorType)zzbbi, zza);
            return;
        }
        if (zzbbi.zzacz() != zzbex.zzebd) {
            this.zzdqx.zza((FieldDescriptorType)zzbbi, zzp(zzbcu));
            return;
        }
        final Object zza2 = this.zza((FieldDescriptorType)zzbbi);
        if (zza2 == null) {
            this.zzdqx.zza((FieldDescriptorType)zzbbi, zzp(zzbcu));
            return;
        }
        zzbcu zzbcu2;
        if (zza2 instanceof zzbdb) {
            zzbcu2 = zzbbi.zza((zzbdb)zza2, (zzbdb)zzbcu);
        }
        else {
            zzbcu2 = zzbbi.zza(((zzbdb)zza2).zzade(), zzbcu).zzadk();
        }
        this.zzdqx.zza((FieldDescriptorType)zzbbi, zzbcu2);
    }
    
    private static int zzd(final Map.Entry<FieldDescriptorType, Object> entry) {
        final zzbbi<FieldDescriptorType> zzbbi = entry.getKey();
        final zzbbx value = entry.getValue();
        if (zzbbi.zzacz() != zzbex.zzebd || zzbbi.zzada() || zzbbi.zzadb()) {
            return zzb(zzbbi, value);
        }
        if (value instanceof zzbbx) {
            return zzbav.zzb(entry.getKey().zzhq(), value);
        }
        return zzbav.zzb(entry.getKey().zzhq(), (zzbcu)value);
    }
    
    private static Object zzp(final Object o) {
        Object zzaek;
        if (o instanceof zzbdb) {
            zzaek = ((zzbdb)o).zzaek();
        }
        else {
            zzaek = o;
            if (o instanceof byte[]) {
                final byte[] array = (byte[])o;
                final byte[] array2 = new byte[array.length];
                System.arraycopy(array, 0, array2, 0, array.length);
                return array2;
            }
        }
        return zzaek;
    }
    
    final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzdqz) {
            return new zzbca<FieldDescriptorType>(this.zzdqx.zzafu().iterator());
        }
        return this.zzdqx.zzafu().iterator();
    }
    
    @Override
    public final boolean equals(final Object o) {
        return this == o || (o instanceof zzbbg && this.zzdqx.equals(((zzbbg)o).zzdqx));
    }
    
    @Override
    public final int hashCode() {
        return this.zzdqx.hashCode();
    }
    
    final boolean isEmpty() {
        return this.zzdqx.isEmpty();
    }
    
    public final boolean isImmutable() {
        return this.zzdqy;
    }
    
    public final boolean isInitialized() {
        for (int i = 0; i < this.zzdqx.zzafs(); ++i) {
            if (!zzb(this.zzdqx.zzcy(i))) {
                return false;
            }
        }
        final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.zzdqx.zzaft().iterator();
        while (iterator.hasNext()) {
            if (!zzb((Map.Entry)iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzdqz) {
            return new zzbca<FieldDescriptorType>(this.zzdqx.entrySet().iterator());
        }
        return this.zzdqx.entrySet().iterator();
    }
    
    public final void zza(final zzbbg<FieldDescriptorType> zzbbg) {
        for (int i = 0; i < zzbbg.zzdqx.zzafs(); ++i) {
            this.zzc(zzbbg.zzdqx.zzcy(i));
        }
        final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = zzbbg.zzdqx.zzaft().iterator();
        while (iterator.hasNext()) {
            this.zzc((Map.Entry)iterator.next());
        }
    }
    
    public final void zzaaz() {
        if (this.zzdqy) {
            return;
        }
        this.zzdqx.zzaaz();
        this.zzdqy = true;
    }
    
    public final int zzacw() {
        int i = 0;
        int n = 0;
        while (i < this.zzdqx.zzafs()) {
            final Map.Entry<FieldDescriptorType, Object> zzcy = this.zzdqx.zzcy(i);
            n += zzb(zzcy.getKey(), zzcy.getValue());
            ++i;
        }
        for (final Map.Entry<zzbbi<?>, V> entry : this.zzdqx.zzaft()) {
            n += zzb(entry.getKey(), entry.getValue());
        }
        return n;
    }
    
    public final int zzacx() {
        int i = 0;
        int n = 0;
        while (i < this.zzdqx.zzafs()) {
            n += zzd(this.zzdqx.zzcy(i));
            ++i;
        }
        final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.zzdqx.zzaft().iterator();
        while (iterator.hasNext()) {
            n += zzd((Map.Entry)iterator.next());
        }
        return n;
    }
}
