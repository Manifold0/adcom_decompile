// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdRequest;

@zzadh
public final class zzyj implements MediationAdRequest
{
    private final int zzaqn;
    private final boolean zzaqz;
    private final int zzbur;
    private final Date zzhl;
    private final Set<String> zzhn;
    private final boolean zzho;
    private final Location zzhp;
    
    public zzyj(@Nullable final Date zzhl, final int zzaqn, @Nullable final Set<String> zzhn, @Nullable final Location zzhp, final boolean zzho, final int zzbur, final boolean zzaqz) {
        this.zzhl = zzhl;
        this.zzaqn = zzaqn;
        this.zzhn = zzhn;
        this.zzhp = zzhp;
        this.zzho = zzho;
        this.zzbur = zzbur;
        this.zzaqz = zzaqz;
    }
    
    @Override
    public final Date getBirthday() {
        return this.zzhl;
    }
    
    @Override
    public final int getGender() {
        return this.zzaqn;
    }
    
    @Override
    public final Set<String> getKeywords() {
        return this.zzhn;
    }
    
    @Override
    public final Location getLocation() {
        return this.zzhp;
    }
    
    @Override
    public final boolean isDesignedForFamilies() {
        return this.zzaqz;
    }
    
    @Override
    public final boolean isTesting() {
        return this.zzho;
    }
    
    @Override
    public final int taggedForChildDirectedTreatment() {
        return this.zzbur;
    }
}
