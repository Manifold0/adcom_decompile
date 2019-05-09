// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.AbstractMap;

class zzbdp<K extends Comparable<K>, V> extends AbstractMap<K, V>
{
    private boolean zzdqy;
    private final int zzdyj;
    private List<zzbdw> zzdyk;
    private Map<K, V> zzdyl;
    private volatile zzbdy zzdym;
    private Map<K, V> zzdyn;
    private volatile zzbds zzdyo;
    
    private zzbdp(final int zzdyj) {
        this.zzdyj = zzdyj;
        this.zzdyk = Collections.emptyList();
        this.zzdyl = Collections.emptyMap();
        this.zzdyn = Collections.emptyMap();
    }
    
    private final int zza(final K k) {
        int n = this.zzdyk.size() - 1;
        if (n >= 0) {
            final int compareTo = k.compareTo((K)this.zzdyk.get(n).getKey());
            if (compareTo > 0) {
                return -(n + 2);
            }
            if (compareTo == 0) {
                return n;
            }
        }
        int i = 0;
        while (i <= n) {
            final int n2 = (i + n) / 2;
            final int compareTo2 = k.compareTo((K)this.zzdyk.get(n2).getKey());
            if (compareTo2 < 0) {
                n = n2 - 1;
            }
            else {
                if (compareTo2 <= 0) {
                    return n2;
                }
                i = n2 + 1;
            }
        }
        return -(i + 1);
    }
    
    private final void zzafv() {
        if (this.zzdqy) {
            throw new UnsupportedOperationException();
        }
    }
    
    private final SortedMap<K, V> zzafw() {
        this.zzafv();
        if (this.zzdyl.isEmpty() && !(this.zzdyl instanceof TreeMap)) {
            this.zzdyl = new TreeMap<K, V>();
            this.zzdyn = (Map<K, V>)((TreeMap)this.zzdyl).descendingMap();
        }
        return (SortedMap<K, V>)(SortedMap)this.zzdyl;
    }
    
    static <FieldDescriptorType extends zzbbi<FieldDescriptorType>> zzbdp<FieldDescriptorType, Object> zzcx(final int n) {
        return (zzbdp<FieldDescriptorType, Object>)new zzbdq(n);
    }
    
    private final V zzcz(final int n) {
        this.zzafv();
        final Object value = this.zzdyk.remove(n).getValue();
        if (!this.zzdyl.isEmpty()) {
            final Iterator<Map.Entry<K, V>> iterator = this.zzafw().entrySet().iterator();
            this.zzdyk.add(new zzbdw((Entry<Object, Object>)iterator.next()));
            iterator.remove();
        }
        return (V)value;
    }
    
    @Override
    public void clear() {
        this.zzafv();
        if (!this.zzdyk.isEmpty()) {
            this.zzdyk.clear();
        }
        if (!this.zzdyl.isEmpty()) {
            this.zzdyl.clear();
        }
    }
    
    @Override
    public boolean containsKey(final Object o) {
        final Comparable comparable = (Comparable)o;
        return this.zza((K)comparable) >= 0 || this.zzdyl.containsKey(comparable);
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        if (this.zzdym == null) {
            this.zzdym = new zzbdy(this, null);
        }
        return (Set<Entry<K, V>>)this.zzdym;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzbdp)) {
                return super.equals(o);
            }
            final zzbdp zzbdp = (zzbdp)o;
            final int size = this.size();
            if (size != zzbdp.size()) {
                return false;
            }
            final int zzafs = this.zzafs();
            if (zzafs != zzbdp.zzafs()) {
                return this.entrySet().equals(zzbdp.entrySet());
            }
            for (int i = 0; i < zzafs; ++i) {
                if (!this.zzcy(i).equals(zzbdp.zzcy(i))) {
                    return false;
                }
            }
            if (zzafs != size) {
                return this.zzdyl.equals(zzbdp.zzdyl);
            }
        }
        return true;
    }
    
    @Override
    public V get(final Object o) {
        final Comparable comparable = (Comparable)o;
        final int zza = this.zza((K)comparable);
        if (zza >= 0) {
            return (V)this.zzdyk.get(zza).getValue();
        }
        return this.zzdyl.get(comparable);
    }
    
    @Override
    public int hashCode() {
        final int zzafs = this.zzafs();
        int i = 0;
        int n = 0;
        while (i < zzafs) {
            n += this.zzdyk.get(i).hashCode();
            ++i;
        }
        if (this.zzdyl.size() > 0) {
            return this.zzdyl.hashCode() + n;
        }
        return n;
    }
    
    public final boolean isImmutable() {
        return this.zzdqy;
    }
    
    @Override
    public V remove(final Object o) {
        this.zzafv();
        final Comparable comparable = (Comparable)o;
        final int zza = this.zza((K)comparable);
        if (zza >= 0) {
            return this.zzcz(zza);
        }
        if (this.zzdyl.isEmpty()) {
            return null;
        }
        return this.zzdyl.remove(comparable);
    }
    
    @Override
    public int size() {
        return this.zzdyk.size() + this.zzdyl.size();
    }
    
    public final V zza(final K k, final V value) {
        this.zzafv();
        final int zza = this.zza(k);
        if (zza >= 0) {
            return (V)this.zzdyk.get(zza).setValue(value);
        }
        this.zzafv();
        if (this.zzdyk.isEmpty() && !(this.zzdyk instanceof ArrayList)) {
            this.zzdyk = new ArrayList<zzbdw>(this.zzdyj);
        }
        final int n = -(zza + 1);
        if (n >= this.zzdyj) {
            return this.zzafw().put(k, value);
        }
        if (this.zzdyk.size() == this.zzdyj) {
            final zzbdw zzbdw = this.zzdyk.remove(this.zzdyj - 1);
            this.zzafw().put((K)zzbdw.getKey(), (V)zzbdw.getValue());
        }
        this.zzdyk.add(n, new zzbdw(this, k, value));
        return null;
    }
    
    public void zzaaz() {
        if (!this.zzdqy) {
            Map<K, V> zzdyl;
            if (this.zzdyl.isEmpty()) {
                zzdyl = Collections.emptyMap();
            }
            else {
                zzdyl = Collections.unmodifiableMap((Map<? extends K, ? extends V>)this.zzdyl);
            }
            this.zzdyl = zzdyl;
            Map<K, V> zzdyn;
            if (this.zzdyn.isEmpty()) {
                zzdyn = Collections.emptyMap();
            }
            else {
                zzdyn = Collections.unmodifiableMap((Map<? extends K, ? extends V>)this.zzdyn);
            }
            this.zzdyn = zzdyn;
            this.zzdqy = true;
        }
    }
    
    public final int zzafs() {
        return this.zzdyk.size();
    }
    
    public final Iterable<Entry<K, V>> zzaft() {
        if (this.zzdyl.isEmpty()) {
            return zzbdt.zzafy();
        }
        return this.zzdyl.entrySet();
    }
    
    final Set<Entry<K, V>> zzafu() {
        if (this.zzdyo == null) {
            this.zzdyo = new zzbds(this, null);
        }
        return (Set<Entry<K, V>>)this.zzdyo;
    }
    
    public final Entry<K, V> zzcy(final int n) {
        return (Entry<K, V>)this.zzdyk.get(n);
    }
}
