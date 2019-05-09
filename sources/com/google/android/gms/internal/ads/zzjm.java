package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@zzadh
public final class zzjm {
    public static final zzjm zzara = new zzjm();

    @VisibleForTesting
    protected zzjm() {
    }

    public static zzjj zza(Context context, zzlw zzlw) {
        Date birthday = zzlw.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzlw.getContentUrl();
        int gender = zzlw.getGender();
        Collection keywords = zzlw.getKeywords();
        List unmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean isTestDevice = zzlw.isTestDevice(context);
        int zzit = zzlw.zzit();
        Location location = zzlw.getLocation();
        Bundle networkExtrasBundle = zzlw.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzlw.getManualImpressionsEnabled();
        String publisherProvidedId = zzlw.getPublisherProvidedId();
        SearchAdRequest zziq = zzlw.zziq();
        zzmq zzmq = zziq != null ? new zzmq(zziq) : null;
        String str = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzkb.zzif();
            str = zzamu.zza(Thread.currentThread().getStackTrace(), packageName);
        }
        return new zzjj(7, time, networkExtrasBundle, gender, unmodifiableList, isTestDevice, zzit, manualImpressionsEnabled, publisherProvidedId, zzmq, location, contentUrl, zzlw.zzis(), zzlw.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzlw.zziu())), zzlw.zzip(), str, zzlw.isDesignedForFamilies());
    }
}
