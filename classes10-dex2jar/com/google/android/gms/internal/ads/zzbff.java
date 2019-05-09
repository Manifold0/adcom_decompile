// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class zzbff implements Cloneable
{
    private Object value;
    private zzbfd<?, ?> zzebq;
    private List<zzbfk> zzebr;
    
    zzbff() {
        this.zzebr = new ArrayList<zzbfk>();
    }
    
    private final byte[] toByteArray() throws IOException {
        final byte[] array = new byte[this.zzr()];
        this.zza(zzbfa.zzu(array));
        return array;
    }
    
    private final zzbff zzagp() {
        final zzbff zzbff = new zzbff();
        try {
            zzbff.zzebq = this.zzebq;
            if (this.zzebr == null) {
                zzbff.zzebr = null;
            }
            else {
                zzbff.zzebr.addAll(this.zzebr);
            }
            if (this.value == null) {
                return zzbff;
            }
            if (this.value instanceof zzbfi) {
                zzbff.value = ((zzbfi)this.value).clone();
                return zzbff;
            }
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError((Object)ex);
        }
        if (this.value instanceof byte[]) {
            zzbff.value = ((byte[])this.value).clone();
            return zzbff;
        }
        if (this.value instanceof byte[][]) {
            final byte[][] array = (byte[][])this.value;
            final byte[][] value = new byte[array.length][];
            zzbff.value = value;
            for (int i = 0; i < array.length; ++i) {
                value[i] = array[i].clone();
            }
        }
        else {
            if (this.value instanceof boolean[]) {
                zzbff.value = ((boolean[])this.value).clone();
                return zzbff;
            }
            if (this.value instanceof int[]) {
                zzbff.value = ((int[])this.value).clone();
                return zzbff;
            }
            if (this.value instanceof long[]) {
                zzbff.value = ((long[])this.value).clone();
                return zzbff;
            }
            if (this.value instanceof float[]) {
                zzbff.value = ((float[])this.value).clone();
                return zzbff;
            }
            if (this.value instanceof double[]) {
                zzbff.value = ((double[])this.value).clone();
                return zzbff;
            }
            if (this.value instanceof zzbfi[]) {
                final zzbfi[] array2 = (zzbfi[])this.value;
                final zzbfi[] value2 = new zzbfi[array2.length];
                zzbff.value = value2;
                for (int j = 0; j < array2.length; ++j) {
                    value2[j] = (zzbfi)array2[j].clone();
                }
            }
        }
        return zzbff;
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
            if (o instanceof zzbff) {
                final zzbff zzbff = (zzbff)o;
                if (this.value != null && zzbff.value != null) {
                    b2 = b;
                    if (this.zzebq == zzbff.zzebq) {
                        if (!this.zzebq.zzebl.isArray()) {
                            return this.value.equals(zzbff.value);
                        }
                        if (this.value instanceof byte[]) {
                            return Arrays.equals((byte[])this.value, (byte[])zzbff.value);
                        }
                        if (this.value instanceof int[]) {
                            return Arrays.equals((int[])this.value, (int[])zzbff.value);
                        }
                        if (this.value instanceof long[]) {
                            return Arrays.equals((long[])this.value, (long[])zzbff.value);
                        }
                        if (this.value instanceof float[]) {
                            return Arrays.equals((float[])this.value, (float[])zzbff.value);
                        }
                        if (this.value instanceof double[]) {
                            return Arrays.equals((double[])this.value, (double[])zzbff.value);
                        }
                        if (this.value instanceof boolean[]) {
                            return Arrays.equals((boolean[])this.value, (boolean[])zzbff.value);
                        }
                        return Arrays.deepEquals((Object[])this.value, (Object[])zzbff.value);
                    }
                }
                else {
                    if (this.zzebr != null && zzbff.zzebr != null) {
                        return this.zzebr.equals(zzbff.zzebr);
                    }
                    try {
                        return Arrays.equals(this.toByteArray(), zzbff.toByteArray());
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
    
    final void zza(final zzbfa zzbfa) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (final zzbfk zzbfk : this.zzebr) {
            zzbfa.zzde(zzbfk.tag);
            zzbfa.zzw(zzbfk.zzdpw);
        }
    }
    
    final void zza(final zzbfk zzbfk) throws IOException {
        if (this.zzebr != null) {
            this.zzebr.add(zzbfk);
            return;
        }
        if (this.value instanceof zzbfi) {
            final byte[] zzdpw = zzbfk.zzdpw;
            final zzbez zzi = zzbez.zzi(zzdpw, 0, zzdpw.length);
            final int zzacc = zzi.zzacc();
            if (zzacc != zzdpw.length - zzbfa.zzce(zzacc)) {
                throw zzbfh.zzagq();
            }
            final zzbfi zza = ((zzbfi)this.value).zza(zzi);
            this.zzebq = this.zzebq;
            this.value = zza;
            this.zzebr = null;
        }
        else {
            if (this.value instanceof zzbfi[]) {
                Collections.singletonList(zzbfk);
                throw new NoSuchMethodError();
            }
            Collections.singletonList(zzbfk);
            throw new NoSuchMethodError();
        }
    }
    
    final int zzr() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        final Iterator<zzbfk> iterator = this.zzebr.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final zzbfk zzbfk = iterator.next();
            n += zzbfk.zzdpw.length + (zzbfa.zzcl(zzbfk.tag) + 0);
        }
        return n;
    }
}
