// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import java.util.List;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import java.util.Map;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;

@zzadh
public final class zzyo implements NativeMediationAdRequest
{
    private final int zzaqn;
    private final boolean zzaqz;
    private final int zzbur;
    private final Map<String, Boolean> zzbva;
    private final Date zzhl;
    private final Set<String> zzhn;
    private final boolean zzho;
    private final Location zzhp;
    private final zzpl zzyb;
    private final List<String> zzyc;
    
    public zzyo(@Nullable final Date zzhl, final int zzaqn, @Nullable final Set<String> zzhn, @Nullable final Location zzhp, final boolean zzho, final int zzbur, final zzpl zzyb, final List<String> list, final boolean zzaqz) {
        this.zzhl = zzhl;
        this.zzaqn = zzaqn;
        this.zzhn = zzhn;
        this.zzhp = zzhp;
        this.zzho = zzho;
        this.zzbur = zzbur;
        this.zzyb = zzyb;
        this.zzaqz = zzaqz;
        this.zzyc = new ArrayList<String>();
        this.zzbva = new HashMap<String, Boolean>();
        if (list != null) {
            for (final String s : list) {
                if (s.startsWith("custom:")) {
                    final String[] split = s.split(":", 3);
                    if (split.length != 3) {
                        continue;
                    }
                    if ("true".equals(split[2])) {
                        this.zzbva.put(split[1], true);
                    }
                    else {
                        if (!"false".equals(split[2])) {
                            continue;
                        }
                        this.zzbva.put(split[1], false);
                    }
                }
                else {
                    this.zzyc.add(s);
                }
            }
        }
    }
    
    @Override
    public final float getAdVolume() {
        return zzmb.zziv().zzdo();
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
    public final NativeAdOptions getNativeAdOptions() {
        if (this.zzyb == null) {
            return null;
        }
        final NativeAdOptions.Builder setRequestMultipleImages = new NativeAdOptions.Builder().setReturnUrlsForImageAssets(this.zzyb.zzbjn).setImageOrientation(this.zzyb.zzbjo).setRequestMultipleImages(this.zzyb.zzbjp);
        if (this.zzyb.versionCode >= 2) {
            setRequestMultipleImages.setAdChoicesPlacement(this.zzyb.zzbjq);
        }
        if (this.zzyb.versionCode >= 3 && this.zzyb.zzbjr != null) {
            setRequestMultipleImages.setVideoOptions(new VideoOptions(this.zzyb.zzbjr));
        }
        return setRequestMultipleImages.build();
    }
    
    @Override
    public final boolean isAdMuted() {
        return zzmb.zziv().zzdp();
    }
    
    @Override
    public final boolean isAppInstallAdRequested() {
        return this.zzyc != null && (this.zzyc.contains("2") || this.zzyc.contains("6"));
    }
    
    @Override
    public final boolean isContentAdRequested() {
        return this.zzyc != null && (this.zzyc.contains("1") || this.zzyc.contains("6"));
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
    public final boolean isUnifiedNativeAdRequested() {
        return this.zzyc != null && this.zzyc.contains("6");
    }
    
    @Override
    public final int taggedForChildDirectedTreatment() {
        return this.zzbur;
    }
    
    @Override
    public final boolean zzna() {
        return this.zzyc != null && this.zzyc.contains("3");
    }
    
    @Override
    public final Map<String, Boolean> zznb() {
        return this.zzbva;
    }
}
