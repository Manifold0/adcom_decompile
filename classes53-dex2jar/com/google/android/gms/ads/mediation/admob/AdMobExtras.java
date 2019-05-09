// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
public final class AdMobExtras implements NetworkExtras
{
    private final Bundle mExtras;
    
    public AdMobExtras(Bundle mExtras) {
        if (mExtras != null) {
            mExtras = new Bundle(mExtras);
        }
        else {
            mExtras = null;
        }
        this.mExtras = mExtras;
    }
    
    public final Bundle getExtras() {
        return this.mExtras;
    }
}
