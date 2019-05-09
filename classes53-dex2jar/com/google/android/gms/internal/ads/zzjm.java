// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.search.SearchAdRequest;
import android.os.Bundle;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzjm
{
    public static final zzjm zzara;
    
    static {
        zzara = new zzjm();
    }
    
    @VisibleForTesting
    protected zzjm() {
    }
    
    public static zzjj zza(final Context context, final zzlw zzlw) {
        final Date birthday = zzlw.getBirthday();
        long time;
        if (birthday != null) {
            time = birthday.getTime();
        }
        else {
            time = -1L;
        }
        final String contentUrl = zzlw.getContentUrl();
        final int gender = zzlw.getGender();
        final Set<String> keywords = zzlw.getKeywords();
        Object unmodifiableList;
        if (!keywords.isEmpty()) {
            unmodifiableList = Collections.unmodifiableList((List<?>)new ArrayList<Object>(keywords));
        }
        else {
            unmodifiableList = null;
        }
        final boolean testDevice = zzlw.isTestDevice(context);
        final int zzit = zzlw.zzit();
        final Location location = zzlw.getLocation();
        final Bundle networkExtrasBundle = zzlw.getNetworkExtrasBundle(AdMobAdapter.class);
        final boolean manualImpressionsEnabled = zzlw.getManualImpressionsEnabled();
        final String publisherProvidedId = zzlw.getPublisherProvidedId();
        final SearchAdRequest zziq = zzlw.zziq();
        zzmq zzmq;
        if (zziq != null) {
            zzmq = new zzmq(zziq);
        }
        else {
            zzmq = null;
        }
        final String s = null;
        final Context applicationContext = context.getApplicationContext();
        String zza = s;
        if (applicationContext != null) {
            final String packageName = applicationContext.getPackageName();
            zzkb.zzif();
            zza = zzamu.zza(Thread.currentThread().getStackTrace(), packageName);
        }
        return new zzjj(7, time, networkExtrasBundle, gender, (List<String>)unmodifiableList, testDevice, zzit, manualImpressionsEnabled, publisherProvidedId, zzmq, location, contentUrl, zzlw.zzis(), zzlw.getCustomTargeting(), Collections.unmodifiableList((List<? extends String>)new ArrayList<String>(zzlw.zziu())), zzlw.zzip(), zza, zzlw.isDesignedForFamilies());
    }
}
