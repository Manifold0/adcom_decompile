package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxr.zzb;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class zzaum<P> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private ConcurrentMap<String, List<zzaun<P>>> zzdhk = new ConcurrentHashMap();
    private zzaun<P> zzdhl;

    protected final zzaun<P> zza(P p, zzb zzb) throws GeneralSecurityException {
        byte[] array;
        switch (zzaud.zzdhh[zzb.zzzs().ordinal()]) {
            case 1:
            case 2:
                array = ByteBuffer.allocate(5).put((byte) 0).putInt(zzb.zzzr()).array();
                break;
            case 3:
                array = ByteBuffer.allocate(5).put((byte) 1).putInt(zzb.zzzr()).array();
                break;
            case 4:
                array = zzauc.zzdhg;
                break;
            default:
                throw new GeneralSecurityException("unknown output prefix type");
        }
        zzaun<P> zzaun = new zzaun(p, array, zzb.zzzq(), zzb.zzzs());
        List arrayList = new ArrayList();
        arrayList.add(zzaun);
        String str = new String(zzaun.zzwj(), UTF_8);
        arrayList = (List) this.zzdhk.put(str, Collections.unmodifiableList(arrayList));
        if (arrayList != null) {
            List arrayList2 = new ArrayList();
            arrayList2.addAll(arrayList);
            arrayList2.add(zzaun);
            this.zzdhk.put(str, Collections.unmodifiableList(arrayList2));
        }
        return zzaun;
    }

    protected final void zza(zzaun<P> zzaun) {
        this.zzdhl = zzaun;
    }

    public final zzaun<P> zzwh() {
        return this.zzdhl;
    }
}
