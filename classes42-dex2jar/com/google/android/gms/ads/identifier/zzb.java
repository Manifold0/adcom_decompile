// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.identifier;

import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import android.content.Context;
import android.content.SharedPreferences;

public final class zzb
{
    private SharedPreferences zzs;
    
    public zzb(Context remoteContext) {
        try {
            remoteContext = GooglePlayServicesUtilLight.getRemoteContext(remoteContext);
            SharedPreferences sharedPreferences;
            if (remoteContext == null) {
                sharedPreferences = null;
            }
            else {
                sharedPreferences = remoteContext.getSharedPreferences("google_ads_flags", 0);
            }
            this.zzs = sharedPreferences;
        }
        catch (Throwable t) {
            Log.w("GmscoreFlag", "Error while getting SharedPreferences ", t);
            this.zzs = null;
        }
    }
    
    public final boolean getBoolean(final String s, final boolean b) {
        try {
            return this.zzs != null && this.zzs.getBoolean(s, false);
        }
        catch (Throwable t) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", t);
            return false;
        }
    }
    
    final float getFloat(final String s, float float1) {
        try {
            if (this.zzs == null) {
                return 0.0f;
            }
            float1 = this.zzs.getFloat(s, 0.0f);
            return float1;
        }
        catch (Throwable t) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", t);
            return 0.0f;
        }
    }
    
    final String getString(String string, final String s) {
        try {
            if (this.zzs == null) {
                return s;
            }
            string = this.zzs.getString(string, s);
            return string;
        }
        catch (Throwable t) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", t);
            return s;
        }
    }
}
