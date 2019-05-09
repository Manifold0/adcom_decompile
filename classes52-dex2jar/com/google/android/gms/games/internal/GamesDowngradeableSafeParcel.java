// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.common.internal.DowngradeableSafeParcel;

public abstract class GamesDowngradeableSafeParcel extends DowngradeableSafeParcel
{
    protected static boolean zzb(final Integer n) {
        return n != null && GmsVersion.isAtLeastFenacho((int)n);
    }
    
    public boolean prepareForClientVersion(final int n) {
        this.setShouldDowngrade(!zzb(n));
        return true;
    }
}
