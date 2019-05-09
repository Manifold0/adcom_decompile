// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.List;
import java.io.IOException;

final class zzbat implements zzbdl
{
    private int tag;
    private final zzbaq zzdqi;
    private int zzdqj;
    private int zzdqk;
    
    private zzbat(final zzbaq zzbaq) {
        this.zzdqk = 0;
        this.zzdqi = zzbbq.zza(zzbaq, "input");
        this.zzdqi.zzdqa = this;
    }
    
    public static zzbat zza(final zzbaq zzbaq) {
        if (zzbaq.zzdqa != null) {
            return zzbaq.zzdqa;
        }
        return new zzbat(zzbaq);
    }
    
    private final Object zza(final zzbes zzbes, final Class<?> clazz, final zzbbb zzbbb) throws IOException {
        switch (zzbau.zzdql[zzbes.ordinal()]) {
            default: {
                throw new RuntimeException("unsupported field type.");
            }
            case 1: {
                return this.zzabq();
            }
            case 2: {
                return this.zzabs();
            }
            case 3: {
                return this.readDouble();
            }
            case 4: {
                return this.zzabu();
            }
            case 5: {
                return this.zzabp();
            }
            case 6: {
                return this.zzabo();
            }
            case 7: {
                return this.readFloat();
            }
            case 8: {
                return this.zzabn();
            }
            case 9: {
                return this.zzabm();
            }
            case 10: {
                this.zzbv(2);
                return this.zzc((zzbdm<Object>)zzbdg.zzaeo().zze(clazz), zzbbb);
            }
            case 11: {
                return this.zzabv();
            }
            case 12: {
                return this.zzabw();
            }
            case 13: {
                return this.zzabx();
            }
            case 14: {
                return this.zzaby();
            }
            case 15: {
                return this.zzabr();
            }
            case 16: {
                return this.zzabt();
            }
            case 17: {
                return this.zzabl();
            }
        }
    }
    
    private final void zza(final List<String> list, final boolean b) throws IOException {
        if ((this.tag & 0x7) != 0x2) {
            throw zzbbu.zzadq();
        }
        if (list instanceof zzbcd && !b) {
            final zzbcd zzbcd = (zzbcd)list;
            int i;
            do {
                zzbcd.zzap(this.zzabs());
                if (this.zzdqi.zzaca()) {
                    return;
                }
                i = this.zzdqi.zzabk();
            } while (i == this.tag);
            this.zzdqk = i;
            return;
        }
        int zzabk;
        do {
            String s;
            if (b) {
                s = this.zzabr();
            }
            else {
                s = this.readString();
            }
            list.add(s);
            if (this.zzdqi.zzaca()) {
                return;
            }
            zzabk = this.zzdqi.zzabk();
        } while (zzabk == this.tag);
        this.zzdqk = zzabk;
    }
    
    private final void zzbv(final int n) throws IOException {
        if ((this.tag & 0x7) != n) {
            throw zzbbu.zzadq();
        }
    }
    
    private static void zzbw(final int n) throws IOException {
        if ((n & 0x7) != 0x0) {
            throw zzbbu.zzadr();
        }
    }
    
    private static void zzbx(final int n) throws IOException {
        if ((n & 0x3) != 0x0) {
            throw zzbbu.zzadr();
        }
    }
    
    private final void zzby(final int n) throws IOException {
        if (this.zzdqi.zzacb() != n) {
            throw zzbbu.zzadl();
        }
    }
    
    private final <T> T zzc(final zzbdm<T> zzbdm, final zzbbb zzbbb) throws IOException {
        final int zzabt = this.zzdqi.zzabt();
        if (this.zzdqi.zzdpx >= this.zzdqi.zzdpy) {
            throw new zzbbu("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        final int zzbr = this.zzdqi.zzbr(zzabt);
        final T instance = zzbdm.newInstance();
        final zzbaq zzdqi = this.zzdqi;
        ++zzdqi.zzdpx;
        zzbdm.zza(instance, this, zzbbb);
        zzbdm.zzo(instance);
        this.zzdqi.zzbp(0);
        final zzbaq zzdqi2 = this.zzdqi;
        --zzdqi2.zzdpx;
        this.zzdqi.zzbs(zzbr);
        return instance;
    }
    
    private final <T> T zzd(final zzbdm<T> zzbdm, final zzbbb zzbbb) throws IOException {
        final int zzdqj = this.zzdqj;
        this.zzdqj = (this.tag >>> 3 << 3 | 0x4);
        T instance;
        try {
            instance = zzbdm.newInstance();
            zzbdm.zza(instance, this, zzbbb);
            zzbdm.zzo(instance);
            if (this.tag != this.zzdqj) {
                throw zzbbu.zzadr();
            }
        }
        finally {
            this.zzdqj = zzdqj;
        }
        this.zzdqj = zzdqj;
        return instance;
    }
    
    @Override
    public final int getTag() {
        return this.tag;
    }
    
    @Override
    public final double readDouble() throws IOException {
        this.zzbv(1);
        return this.zzdqi.readDouble();
    }
    
    @Override
    public final float readFloat() throws IOException {
        this.zzbv(5);
        return this.zzdqi.readFloat();
    }
    
    @Override
    public final String readString() throws IOException {
        this.zzbv(2);
        return this.zzdqi.readString();
    }
    
    @Override
    public final void readStringList(final List<String> list) throws IOException {
        this.zza(list, false);
    }
    
    @Override
    public final <T> T zza(final zzbdm<T> zzbdm, final zzbbb zzbbb) throws IOException {
        this.zzbv(2);
        return (T)this.zzc((zzbdm<Object>)zzbdm, zzbbb);
    }
    
    @Override
    public final <T> void zza(final List<T> list, final zzbdm<T> zzbdm, final zzbbb zzbbb) throws IOException {
        if ((this.tag & 0x7) != 0x2) {
            throw zzbbu.zzadq();
        }
        int i;
        do {
            list.add(this.zzc(zzbdm, zzbbb));
            if (this.zzdqi.zzaca() || this.zzdqk != 0) {
                return;
            }
            i = this.zzdqi.zzabk();
        } while (i == this.tag);
        this.zzdqk = i;
    }
    
    @Override
    public final <K, V> void zza(final Map<K, V> map, final zzbcn<K, V> zzbcn, final zzbbb zzbbb) throws IOException {
        this.zzbv(2);
        final int zzbr = this.zzdqi.zzbr(this.zzdqi.zzabt());
        Object o = zzbcn.zzdvz;
        Object o2 = zzbcn.zzdwb;
        zzbbv zzbbv;
        while (true) {
            zzbbv = (zzbbv)o;
            Label_0165: {
                try {
                    final int zzaci = this.zzaci();
                    if (zzaci == Integer.MAX_VALUE || this.zzdqi.zzaca()) {
                        break;
                    }
                    switch (zzaci) {
                        default: {
                            o = zzbbv;
                            try {
                                if (!this.zzacj()) {
                                    throw new zzbbu("Unable to parse map entry.");
                                }
                                continue;
                            }
                            catch (zzbbv o) {
                                o = zzbbv;
                                if (!this.zzacj()) {
                                    throw new zzbbu("Unable to parse map entry.");
                                }
                                continue;
                            }
                            break;
                        }
                        case 1: {
                            break;
                        }
                        case 2: {
                            break Label_0165;
                        }
                    }
                }
                finally {
                    this.zzdqi.zzbs(zzbr);
                }
                o = this.zza(zzbcn.zzdvy, null, null);
                continue;
            }
            o2 = this.zza(zzbcn.zzdwa, zzbcn.zzdwb.getClass(), zzbbb);
            o = zzbbv;
        }
        final Map<Object, Object> map2;
        map2.put(zzbbv, o2);
        this.zzdqi.zzbs(zzbr);
    }
    
    @Override
    public final void zzaa(final List<Integer> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbbp) {
                final zzbbp zzbbp = (zzbbp)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbbp.zzco(this.zzdqi.zzabu());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbbp.zzco(this.zzdqi.zzabu());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzabu());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabu());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzab(final List<Integer> list) throws IOException {
        Label_0096: {
            if (list instanceof zzbbp) {
                final zzbbp zzbbp = (zzbbp)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt = this.zzdqi.zzabt();
                        zzbx(zzabt);
                        do {
                            zzbbp.zzco(this.zzdqi.zzabv());
                        } while (this.zzdqi.zzacb() < zzabt + this.zzdqi.zzacb());
                        break;
                    }
                    case 5: {
                        int i;
                        do {
                            zzbbp.zzco(this.zzdqi.zzabv());
                            if (this.zzdqi.zzaca()) {
                                break Label_0096;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt2 = this.zzdqi.zzabt();
                        zzbx(zzabt2);
                        do {
                            list.add(this.zzdqi.zzabv());
                        } while (this.zzdqi.zzacb() < zzabt2 + this.zzdqi.zzacb());
                        return;
                    }
                    case 5: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabv());
                            if (this.zzdqi.zzaca()) {
                                break Label_0096;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final long zzabl() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzabl();
    }
    
    @Override
    public final long zzabm() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzabm();
    }
    
    @Override
    public final int zzabn() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzabn();
    }
    
    @Override
    public final long zzabo() throws IOException {
        this.zzbv(1);
        return this.zzdqi.zzabo();
    }
    
    @Override
    public final int zzabp() throws IOException {
        this.zzbv(5);
        return this.zzdqi.zzabp();
    }
    
    @Override
    public final boolean zzabq() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzabq();
    }
    
    @Override
    public final String zzabr() throws IOException {
        this.zzbv(2);
        return this.zzdqi.zzabr();
    }
    
    @Override
    public final zzbah zzabs() throws IOException {
        this.zzbv(2);
        return this.zzdqi.zzabs();
    }
    
    @Override
    public final int zzabt() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzabt();
    }
    
    @Override
    public final int zzabu() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzabu();
    }
    
    @Override
    public final int zzabv() throws IOException {
        this.zzbv(5);
        return this.zzdqi.zzabv();
    }
    
    @Override
    public final long zzabw() throws IOException {
        this.zzbv(1);
        return this.zzdqi.zzabw();
    }
    
    @Override
    public final int zzabx() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzabx();
    }
    
    @Override
    public final long zzaby() throws IOException {
        this.zzbv(0);
        return this.zzdqi.zzaby();
    }
    
    @Override
    public final void zzac(final List<Long> list) throws IOException {
        Label_0088: {
            if (list instanceof zzbci) {
                final zzbci zzbci = (zzbci)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt = this.zzdqi.zzabt();
                        zzbw(zzabt);
                        do {
                            zzbci.zzw(this.zzdqi.zzabw());
                        } while (this.zzdqi.zzacb() < zzabt + this.zzdqi.zzacb());
                        break;
                    }
                    case 1: {
                        int i;
                        do {
                            zzbci.zzw(this.zzdqi.zzabw());
                            if (this.zzdqi.zzaca()) {
                                break Label_0088;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt2 = this.zzdqi.zzabt();
                        zzbw(zzabt2);
                        do {
                            list.add(this.zzdqi.zzabw());
                        } while (this.zzdqi.zzacb() < zzabt2 + this.zzdqi.zzacb());
                        return;
                    }
                    case 1: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabw());
                            if (this.zzdqi.zzaca()) {
                                break Label_0088;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final int zzaci() throws IOException {
        if (this.zzdqk != 0) {
            this.tag = this.zzdqk;
            this.zzdqk = 0;
        }
        else {
            this.tag = this.zzdqi.zzabk();
        }
        if (this.tag == 0 || this.tag == this.zzdqj) {
            return Integer.MAX_VALUE;
        }
        return this.tag >>> 3;
    }
    
    @Override
    public final boolean zzacj() throws IOException {
        return !this.zzdqi.zzaca() && this.tag != this.zzdqj && this.zzdqi.zzbq(this.tag);
    }
    
    @Override
    public final void zzad(final List<Integer> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbbp) {
                final zzbbp zzbbp = (zzbbp)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbbp.zzco(this.zzdqi.zzabx());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbbp.zzco(this.zzdqi.zzabx());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzabx());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabx());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzae(final List<Long> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbci) {
                final zzbci zzbci = (zzbci)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbci.zzw(this.zzdqi.zzaby());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbci.zzw(this.zzdqi.zzaby());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzaby());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzaby());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final <T> T zzb(final zzbdm<T> zzbdm, final zzbbb zzbbb) throws IOException {
        this.zzbv(3);
        return (T)this.zzd((zzbdm<Object>)zzbdm, zzbbb);
    }
    
    @Override
    public final <T> void zzb(final List<T> list, final zzbdm<T> zzbdm, final zzbbb zzbbb) throws IOException {
        if ((this.tag & 0x7) != 0x3) {
            throw zzbbu.zzadq();
        }
        int i;
        do {
            list.add(this.zzd(zzbdm, zzbbb));
            if (this.zzdqi.zzaca() || this.zzdqk != 0) {
                return;
            }
            i = this.zzdqi.zzabk();
        } while (i == this.tag);
        this.zzdqk = i;
    }
    
    @Override
    public final void zzp(final List<Double> list) throws IOException {
        Label_0088: {
            if (list instanceof zzbay) {
                final zzbay zzbay = (zzbay)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt = this.zzdqi.zzabt();
                        zzbw(zzabt);
                        do {
                            zzbay.zzd(this.zzdqi.readDouble());
                        } while (this.zzdqi.zzacb() < zzabt + this.zzdqi.zzacb());
                        break;
                    }
                    case 1: {
                        int i;
                        do {
                            zzbay.zzd(this.zzdqi.readDouble());
                            if (this.zzdqi.zzaca()) {
                                break Label_0088;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt2 = this.zzdqi.zzabt();
                        zzbw(zzabt2);
                        do {
                            list.add(this.zzdqi.readDouble());
                        } while (this.zzdqi.zzacb() < zzabt2 + this.zzdqi.zzacb());
                        return;
                    }
                    case 1: {
                        int j;
                        do {
                            list.add(this.zzdqi.readDouble());
                            if (this.zzdqi.zzaca()) {
                                break Label_0088;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzq(final List<Float> list) throws IOException {
        Label_0096: {
            if (list instanceof zzbbm) {
                final zzbbm zzbbm = (zzbbm)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt = this.zzdqi.zzabt();
                        zzbx(zzabt);
                        do {
                            zzbbm.zzd(this.zzdqi.readFloat());
                        } while (this.zzdqi.zzacb() < zzabt + this.zzdqi.zzacb());
                        break;
                    }
                    case 5: {
                        int i;
                        do {
                            zzbbm.zzd(this.zzdqi.readFloat());
                            if (this.zzdqi.zzaca()) {
                                break Label_0096;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt2 = this.zzdqi.zzabt();
                        zzbx(zzabt2);
                        do {
                            list.add(this.zzdqi.readFloat());
                        } while (this.zzdqi.zzacb() < zzabt2 + this.zzdqi.zzacb());
                        return;
                    }
                    case 5: {
                        int j;
                        do {
                            list.add(this.zzdqi.readFloat());
                            if (this.zzdqi.zzaca()) {
                                break Label_0096;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzr(final List<Long> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbci) {
                final zzbci zzbci = (zzbci)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbci.zzw(this.zzdqi.zzabl());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbci.zzw(this.zzdqi.zzabl());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzabl());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabl());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzs(final List<Long> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbci) {
                final zzbci zzbci = (zzbci)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbci.zzw(this.zzdqi.zzabm());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbci.zzw(this.zzdqi.zzabm());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzabm());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabm());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzt(final List<Integer> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbbp) {
                final zzbbp zzbbp = (zzbbp)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbbp.zzco(this.zzdqi.zzabn());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbbp.zzco(this.zzdqi.zzabn());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzabn());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabn());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzu(final List<Long> list) throws IOException {
        Label_0088: {
            if (list instanceof zzbci) {
                final zzbci zzbci = (zzbci)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt = this.zzdqi.zzabt();
                        zzbw(zzabt);
                        do {
                            zzbci.zzw(this.zzdqi.zzabo());
                        } while (this.zzdqi.zzacb() < zzabt + this.zzdqi.zzacb());
                        break;
                    }
                    case 1: {
                        int i;
                        do {
                            zzbci.zzw(this.zzdqi.zzabo());
                            if (this.zzdqi.zzaca()) {
                                break Label_0088;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt2 = this.zzdqi.zzabt();
                        zzbw(zzabt2);
                        do {
                            list.add(this.zzdqi.zzabo());
                        } while (this.zzdqi.zzacb() < zzabt2 + this.zzdqi.zzacb());
                        return;
                    }
                    case 1: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabo());
                            if (this.zzdqi.zzaca()) {
                                break Label_0088;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzv(final List<Integer> list) throws IOException {
        Label_0096: {
            if (list instanceof zzbbp) {
                final zzbbp zzbbp = (zzbbp)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt = this.zzdqi.zzabt();
                        zzbx(zzabt);
                        do {
                            zzbbp.zzco(this.zzdqi.zzabp());
                        } while (this.zzdqi.zzacb() < zzabt + this.zzdqi.zzacb());
                        break;
                    }
                    case 5: {
                        int i;
                        do {
                            zzbbp.zzco(this.zzdqi.zzabp());
                            if (this.zzdqi.zzaca()) {
                                break Label_0096;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int zzabt2 = this.zzdqi.zzabt();
                        zzbx(zzabt2);
                        do {
                            list.add(this.zzdqi.zzabp());
                        } while (this.zzdqi.zzacb() < zzabt2 + this.zzdqi.zzacb());
                        return;
                    }
                    case 5: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabp());
                            if (this.zzdqi.zzaca()) {
                                break Label_0096;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzw(final List<Boolean> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbaf) {
                final zzbaf zzbaf = (zzbaf)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbaf.addBoolean(this.zzdqi.zzabq());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbaf.addBoolean(this.zzdqi.zzabq());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzabq());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabq());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zzx(final List<String> list) throws IOException {
        this.zza(list, true);
    }
    
    @Override
    public final void zzy(final List<zzbah> list) throws IOException {
        if ((this.tag & 0x7) != 0x2) {
            throw zzbbu.zzadq();
        }
        int i;
        do {
            list.add(this.zzabs());
            if (this.zzdqi.zzaca()) {
                return;
            }
            i = this.zzdqi.zzabk();
        } while (i == this.tag);
        this.zzdqk = i;
    }
    
    @Override
    public final void zzz(final List<Integer> list) throws IOException {
        Label_0091: {
            if (list instanceof zzbbp) {
                final zzbbp zzbbp = (zzbbp)list;
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            zzbbp.zzco(this.zzdqi.zzabt());
                        } while (this.zzdqi.zzacb() < n);
                        this.zzby(n);
                        break;
                    }
                    case 0: {
                        int i;
                        do {
                            zzbbp.zzco(this.zzdqi.zzabt());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            i = this.zzdqi.zzabk();
                        } while (i == this.tag);
                        this.zzdqk = i;
                        return;
                    }
                }
            }
            else {
                switch (this.tag & 0x7) {
                    default: {
                        throw zzbbu.zzadq();
                    }
                    case 2: {
                        final int n2 = this.zzdqi.zzabt() + this.zzdqi.zzacb();
                        do {
                            list.add(this.zzdqi.zzabt());
                        } while (this.zzdqi.zzacb() < n2);
                        this.zzby(n2);
                        return;
                    }
                    case 0: {
                        int j;
                        do {
                            list.add(this.zzdqi.zzabt());
                            if (this.zzdqi.zzaca()) {
                                break Label_0091;
                            }
                            j = this.zzdqi.zzabk();
                        } while (j == this.tag);
                        this.zzdqk = j;
                        return;
                    }
                }
            }
        }
    }
}
