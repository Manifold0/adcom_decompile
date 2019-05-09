// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.util.Collections;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class zziu implements Cloneable
{
    private Object value;
    private zzis<?, ?> zznc;
    private List<zziz> zznd;
    
    zziu() {
        this.zznd = new ArrayList<zziz>();
    }
    
    private final byte[] toByteArray() throws IOException {
        final byte[] array = new byte[this.zzaq()];
        this.zza(zzip.zzb(array));
        return array;
    }
    
    private final zziu zzbj() {
        final zziu zziu = new zziu();
        try {
            zziu.zznc = this.zznc;
            if (this.zznd == null) {
                zziu.zznd = null;
            }
            else {
                zziu.zznd.addAll(this.zznd);
            }
            if (this.value == null) {
                return zziu;
            }
            if (this.value instanceof zzix) {
                zziu.value = ((zzix)this.value).clone();
                return zziu;
            }
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError((Object)ex);
        }
        if (this.value instanceof byte[]) {
            zziu.value = ((byte[])this.value).clone();
            return zziu;
        }
        if (this.value instanceof byte[][]) {
            final byte[][] array = (byte[][])this.value;
            final byte[][] value = new byte[array.length][];
            zziu.value = value;
            for (int i = 0; i < array.length; ++i) {
                value[i] = array[i].clone();
            }
        }
        else {
            if (this.value instanceof boolean[]) {
                zziu.value = ((boolean[])this.value).clone();
                return zziu;
            }
            if (this.value instanceof int[]) {
                zziu.value = ((int[])this.value).clone();
                return zziu;
            }
            if (this.value instanceof long[]) {
                zziu.value = ((long[])this.value).clone();
                return zziu;
            }
            if (this.value instanceof float[]) {
                zziu.value = ((float[])this.value).clone();
                return zziu;
            }
            if (this.value instanceof double[]) {
                zziu.value = ((double[])this.value).clone();
                return zziu;
            }
            if (this.value instanceof zzix[]) {
                final zzix[] array2 = (zzix[])this.value;
                final zzix[] value2 = new zzix[array2.length];
                zziu.value = value2;
                for (int j = 0; j < array2.length; ++j) {
                    value2[j] = (zzix)array2[j].clone();
                }
            }
        }
        return zziu;
    }
    
    @Override
    public final boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (o == this) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o instanceof zziu) {
                final zziu zziu = (zziu)o;
                if (this.value != null && zziu.value != null) {
                    b2 = b;
                    if (this.zznc == zziu.zznc) {
                        if (!this.zznc.zzmx.isArray()) {
                            return this.value.equals(zziu.value);
                        }
                        if (this.value instanceof byte[]) {
                            return Arrays.equals((byte[])this.value, (byte[])zziu.value);
                        }
                        if (this.value instanceof int[]) {
                            return Arrays.equals((int[])this.value, (int[])zziu.value);
                        }
                        if (this.value instanceof long[]) {
                            return Arrays.equals((long[])this.value, (long[])zziu.value);
                        }
                        if (this.value instanceof float[]) {
                            return Arrays.equals((float[])this.value, (float[])zziu.value);
                        }
                        if (this.value instanceof double[]) {
                            return Arrays.equals((double[])this.value, (double[])zziu.value);
                        }
                        if (this.value instanceof boolean[]) {
                            return Arrays.equals((boolean[])this.value, (boolean[])zziu.value);
                        }
                        return Arrays.deepEquals((Object[])this.value, (Object[])zziu.value);
                    }
                }
                else {
                    if (this.zznd != null && zziu.zznd != null) {
                        return this.zznd.equals(zziu.zznd);
                    }
                    try {
                        return Arrays.equals(this.toByteArray(), zziu.toByteArray());
                    }
                    catch (IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public final int hashCode() {
        try {
            return Arrays.hashCode(this.toByteArray()) + 527;
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    final void zza(final zzip zzip) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (final zziz zziz : this.zznd) {
            zzip.zzp(zziz.tag);
            zzip.zzc(zziz.zzng);
        }
    }
    
    final void zza(final zziz zziz) throws IOException {
        if (this.zznd != null) {
            this.zznd.add(zziz);
            return;
        }
        if (this.value instanceof zzix) {
            final byte[] zzng = zziz.zzng;
            final zzio zza = zzio.zza(zzng, 0, zzng.length);
            final int zzbe = zza.zzbe();
            if (zzbe != zzng.length - zzip.zzm(zzbe)) {
                throw zziw.zzbk();
            }
            final zzix zza2 = ((zzix)this.value).zza(zza);
            this.zznc = this.zznc;
            this.value = zza2;
            this.zznd = null;
        }
        else {
            if (this.value instanceof zzix[]) {
                Collections.singletonList(zziz);
                throw new NoSuchMethodError();
            }
            Collections.singletonList(zziz);
            throw new NoSuchMethodError();
        }
    }
    
    final int zzaq() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        final Iterator<zziz> iterator = this.zznd.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final zziz zziz = iterator.next();
            n += zziz.zzng.length + (zzip.zzq(zziz.tag) + 0);
        }
        return n;
    }
}
