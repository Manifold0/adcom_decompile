package com.google.android.gms.drive.metadata.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveFolder;
import java.util.Locale;

public final class zzk {
    private String zzis;

    private zzk(String str) {
        this.zzis = str.toLowerCase(Locale.US);
    }

    @Nullable
    public static zzk zze(@Nullable String str) {
        boolean z = str == null || !str.isEmpty();
        Preconditions.checkArgument(z);
        return str == null ? null : new zzk(str);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.zzis.equals(((zzk) obj).zzis);
    }

    public final int hashCode() {
        return this.zzis.hashCode();
    }

    public final boolean isFolder() {
        return this.zzis.equals(DriveFolder.MIME_TYPE);
    }

    public final String toString() {
        return this.zzis;
    }

    public final boolean zzaz() {
        return this.zzis.startsWith("application/vnd.google-apps");
    }
}
