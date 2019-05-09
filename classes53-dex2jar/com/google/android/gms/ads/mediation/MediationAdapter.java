// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public interface MediationAdapter
{
    void onDestroy();
    
    void onPause();
    
    void onResume();
    
    public static final class zza
    {
        private int zzdfl;
        
        public final zza zzaj(final int n) {
            this.zzdfl = 1;
            return this;
        }
        
        public final Bundle zzvx() {
            final Bundle bundle = new Bundle();
            bundle.putInt("capabilities", this.zzdfl);
            return bundle;
        }
    }
}
