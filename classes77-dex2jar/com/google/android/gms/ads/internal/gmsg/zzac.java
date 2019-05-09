// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzaad;
import com.google.android.gms.internal.ads.zzaae;
import com.google.android.gms.internal.ads.zzzy;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.internal.ads.zzaam;
import com.google.android.gms.internal.ads.zzaab;
import com.google.android.gms.ads.internal.zzx;
import java.util.Map;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaqw;

@zzadh
public final class zzac implements zzv<zzaqw>
{
    private static final Map<String, Integer> zzbmz;
    private final zzx zzbmw;
    private final zzaab zzbmx;
    private final zzaam zzbmy;
    
    static {
        zzbmz = CollectionUtils.mapOfKeyValueArrays((Object[])new String[] { "resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload" }, (Object[])new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
    }
    
    public zzac(final zzx zzbmw, final zzaab zzbmx, final zzaam zzbmy) {
        this.zzbmw = zzbmw;
        this.zzbmx = zzbmx;
        this.zzbmy = zzbmy;
    }
}
