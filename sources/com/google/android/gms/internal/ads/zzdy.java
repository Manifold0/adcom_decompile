package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class zzdy extends zzei {
    private List<Long> zztt = null;

    public zzdy(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 31);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdy = Long.valueOf(-1);
        this.zztq.zzdz = Long.valueOf(-1);
        if (this.zztt == null) {
            this.zztt = (List) this.zztz.invoke(null, new Object[]{this.zzps.getContext()});
        }
        if (this.zztt != null && this.zztt.size() == 2) {
            synchronized (this.zztq) {
                this.zztq.zzdy = Long.valueOf(((Long) this.zztt.get(0)).longValue());
                this.zztq.zzdz = Long.valueOf(((Long) this.zztt.get(1)).longValue());
            }
        }
    }
}
