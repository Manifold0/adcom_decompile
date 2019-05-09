// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.nio.charset.Charset;

public final class zzaum<P>
{
    private static final Charset UTF_8;
    private ConcurrentMap<String, List<zzaun<P>>> zzdhk;
    private zzaun<P> zzdhl;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
    }
    
    public zzaum() {
        this.zzdhk = new ConcurrentHashMap<String, List<zzaun<P>>>();
    }
    
    protected final zzaun<P> zza(final P p2, final zzaxr.zzb zzb) throws GeneralSecurityException {
        byte[] array = null;
        switch (zzaud.zzdhh[zzb.zzzs().ordinal()]) {
            default: {
                throw new GeneralSecurityException("unknown output prefix type");
            }
            case 1:
            case 2: {
                array = ByteBuffer.allocate(5).put((byte)0).putInt(zzb.zzzr()).array();
                break;
            }
            case 3: {
                array = ByteBuffer.allocate(5).put((byte)1).putInt(zzb.zzzr()).array();
                break;
            }
            case 4: {
                array = zzauc.zzdhg;
                break;
            }
        }
        final zzaun zzaun = new zzaun<P>(p2, array, zzb.zzzq(), zzb.zzzs());
        final ArrayList<zzaun<P>> list = new ArrayList<zzaun<P>>();
        list.add((zzaun<P>)zzaun);
        final String s = new String(zzaun.zzwj(), zzaum.UTF_8);
        final List<zzaun<P>> list2 = this.zzdhk.put(s, Collections.unmodifiableList((List<? extends zzaun<P>>)list));
        if (list2 != null) {
            final ArrayList<zzaun<P>> list3 = new ArrayList<zzaun<P>>();
            list3.addAll((Collection<?>)list2);
            list3.add((zzaun<P>)zzaun);
            this.zzdhk.put(s, (List<zzaun<P>>)Collections.unmodifiableList((List<?>)list3));
        }
        return (zzaun<P>)zzaun;
    }
    
    protected final void zza(final zzaun<P> zzdhl) {
        this.zzdhl = zzdhl;
    }
    
    public final zzaun<P> zzwh() {
        return this.zzdhl;
    }
}
