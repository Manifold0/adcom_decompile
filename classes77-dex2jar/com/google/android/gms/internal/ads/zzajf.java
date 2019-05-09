// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;

final class zzajf implements Runnable
{
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzaoj zzcoa;
    
    zzajf(final zzaje zzaje, final Context val$context, final zzaoj zzcoa) {
        this.val$context = val$context;
        this.zzcoa = zzcoa;
    }
    
    @Override
    public final void run() {
        try {
            final AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.val$context);
            this.zzcoa.set(advertisingIdInfo);
        }
        catch (GooglePlayServicesRepairableException ex) {}
        catch (IllegalStateException advertisingIdInfo) {
            goto Label_0018;
        }
        catch (GooglePlayServicesNotAvailableException advertisingIdInfo) {
            goto Label_0018;
        }
        catch (IOException advertisingIdInfo) {
            goto Label_0018;
        }
    }
}
