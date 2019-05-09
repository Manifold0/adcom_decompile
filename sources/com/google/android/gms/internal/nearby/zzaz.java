package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.Objects;

final class zzaz {
    private final long zzaf;
    private final String zzca;

    zzaz(String str, long j) {
        this.zzca = str;
        this.zzaf = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaz)) {
            return false;
        }
        zzaz zzaz = (zzaz) obj;
        return Objects.equal(this.zzca, zzaz.zzca) && Objects.equal(Long.valueOf(this.zzaf), Long.valueOf(zzaz.zzaf));
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzca, Long.valueOf(this.zzaf)});
    }

    public final String zze() {
        return this.zzca;
    }
}
