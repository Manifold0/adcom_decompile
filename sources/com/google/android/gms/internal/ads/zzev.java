package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;

public final class zzev implements zzgd {
    private WeakReference<zzoz> zzafl;

    public zzev(zzoz zzoz) {
        this.zzafl = new WeakReference(zzoz);
    }

    @Nullable
    public final View zzgh() {
        zzoz zzoz = (zzoz) this.zzafl.get();
        return zzoz != null ? zzoz.zzkr() : null;
    }

    public final boolean zzgi() {
        return this.zzafl.get() == null;
    }

    public final zzgd zzgj() {
        return new zzex((zzoz) this.zzafl.get());
    }
}
