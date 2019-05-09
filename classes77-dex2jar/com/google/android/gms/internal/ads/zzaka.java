// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;

final class zzaka extends zzajx
{
    private Context mContext;
    
    zzaka(final Context mContext) {
        this.mContext = mContext;
    }
    
    @Override
    public final void onStop() {
    }
    
    @Override
    public final void zzdn() {
        try {
            final boolean isAdIdFakeForDebugLogging = AdvertisingIdClient.getIsAdIdFakeForDebugLogging(this.mContext);
            zzamy.zzaf(isAdIdFakeForDebugLogging);
            zzakb.zzdk(new StringBuilder(43).append("Update ad debug logging enablement as ").append(isAdIdFakeForDebugLogging).toString());
        }
        catch (GooglePlayServicesRepairableException ex) {}
        catch (IllegalStateException o) {
            goto Label_0038;
        }
        catch (GooglePlayServicesNotAvailableException o) {
            goto Label_0038;
        }
        catch (IOException o) {
            goto Label_0038;
        }
    }
}
