package com.chartboost.sdk.Libraries;

import android.content.Context;
import com.chartboost.sdk.impl.C1454s;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* renamed from: com.chartboost.sdk.Libraries.a */
public class C1371a {
    /* renamed from: a */
    public int f2676a;
    /* renamed from: b */
    public String f2677b;

    public C1371a(Context context) {
        Info a;
        try {
            a = C1454s.m3627a().m3629a(context);
        } catch (IllegalStateException e) {
            CBLogging.m3099b("ContentValues", "This should have been called off the main thread.");
            a = null;
        } catch (IOException e2) {
            CBLogging.m3099b("ContentValues", "The connection to Google Play Services failed.");
            a = null;
        } catch (GooglePlayServicesRepairableException e3) {
            CBLogging.m3099b("ContentValues", "There was a recoverable error connecting to Google Play Services.");
            a = null;
        } catch (GooglePlayServicesNotAvailableException e4) {
            a = null;
        }
        if (a == null) {
            this.f2676a = -1;
            this.f2677b = null;
        } else if (a.isLimitAdTrackingEnabled()) {
            this.f2676a = 1;
            this.f2677b = null;
        } else {
            this.f2676a = 0;
            this.f2677b = a.getId();
        }
    }
}
