// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import com.chartboost.sdk.impl.s;
import android.content.Context;

public class a
{
    public int a;
    public String b;
    
    public a(final Context context) {
        AdvertisingIdClient$Info a;
        while (true) {
            try {
                a = s.a().a(context);
                if (a == null) {
                    this.a = -1;
                    this.b = null;
                    return;
                }
            }
            catch (IllegalStateException ex) {
                CBLogging.b("ContentValues", "This should have been called off the main thread.");
                a = null;
                continue;
            }
            catch (IOException ex2) {
                CBLogging.b("ContentValues", "The connection to Google Play Services failed.");
                a = null;
                continue;
            }
            catch (GooglePlayServicesRepairableException ex3) {
                CBLogging.b("ContentValues", "There was a recoverable error connecting to Google Play Services.");
                a = null;
                continue;
            }
            catch (GooglePlayServicesNotAvailableException ex4) {
                a = null;
                continue;
            }
            break;
        }
        if (a.isLimitAdTrackingEnabled()) {
            this.a = 1;
            this.b = null;
            return;
        }
        this.a = 0;
        this.b = a.getId();
    }
}
