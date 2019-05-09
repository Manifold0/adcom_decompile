// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import com.google.android.gms.drive.UserMetadata;

public final class zzu extends zzm<UserMetadata>
{
    public zzu(final String s, final int n) {
        super(s, Arrays.asList(zza(s, "permissionId"), zza(s, "displayName"), zza(s, "picture"), zza(s, "isAuthenticatedUser"), zza(s, "emailAddress")), Collections.emptyList(), 6000000);
    }
    
    private static String zza(final String s, final String s2) {
        return new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s2).length()).append(s).append(".").append(s2).toString();
    }
    
    private final String zzf(final String s) {
        return zza(this.getName(), s);
    }
    
    @Override
    protected final boolean zzb(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.hasColumn(this.zzf("permissionId")) && !dataHolder.hasNull(this.zzf("permissionId"), n, n2);
    }
}
