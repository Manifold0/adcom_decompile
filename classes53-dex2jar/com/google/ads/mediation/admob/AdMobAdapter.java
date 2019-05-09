// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation.admob;

import android.text.TextUtils;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.ads.mediation.AbstractAdViewAdapter;

@Keep
public final class AdMobAdapter extends AbstractAdViewAdapter
{
    public static final String NEW_BUNDLE = "_newBundle";
    
    @Override
    protected final Bundle zza(Bundle bundle, final Bundle bundle2) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle3 = bundle;
        if (bundle.getBoolean("_newBundle")) {
            bundle3 = new Bundle(bundle);
        }
        bundle3.putInt("gw", 1);
        bundle3.putString("mad_hac", bundle2.getString("mad_hac"));
        if (!TextUtils.isEmpty((CharSequence)bundle2.getString("adJson"))) {
            bundle3.putString("_ad", bundle2.getString("adJson"));
        }
        bundle3.putBoolean("_noRefresh", true);
        return bundle3;
    }
}
