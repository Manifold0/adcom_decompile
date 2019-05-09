// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.Bundle;

@zzadh
public final class zzamm
{
    public static boolean zzo(final zzjj zzjj) {
        Bundle zzaqg;
        if (zzjj.zzaqg != null) {
            zzaqg = zzjj.zzaqg;
        }
        else {
            zzaqg = new Bundle();
        }
        Bundle bundle;
        if (zzaqg.getBundle(AdMobAdapter.class.getName()) != null) {
            bundle = zzaqg.getBundle(AdMobAdapter.class.getName());
        }
        else {
            bundle = new Bundle();
        }
        return bundle.getBoolean("render_test_label", false);
    }
}
