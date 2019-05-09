// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.identifier;

import java.util.Iterator;
import android.net.Uri$Builder;
import java.io.IOException;
import android.util.Log;
import java.net.URL;
import java.net.HttpURLConnection;
import android.net.Uri;
import java.util.Map;

final class zza extends Thread
{
    private final /* synthetic */ Map zzl;
    
    zza(final AdvertisingIdClient advertisingIdClient, final Map zzl) {
        this.zzl = zzl;
    }
    
    @Override
    public final void run() {
        new zzc();
        Object zzl = this.zzl;
        final Uri$Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for (final String s : ((Map<String, String>)zzl).keySet()) {
            buildUpon.appendQueryParameter(s, (String)((Map<String, String>)zzl).get(s));
        }
        final String string = buildUpon.build().toString();
        try {
            zzl = new URL(string).openConnection();
            try {
                final int responseCode = ((HttpURLConnection)zzl).getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    Log.w("HttpUrlPinger", new StringBuilder(String.valueOf(string).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(string).toString());
                }
            }
            finally {
                ((HttpURLConnection)zzl).disconnect();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            final String message = ex.getMessage();
            Log.w("HttpUrlPinger", new StringBuilder(String.valueOf(string).length() + 32 + String.valueOf(message).length()).append("Error while parsing ping URL: ").append(string).append(". ").append(message).toString(), (Throwable)ex);
        }
        catch (RuntimeException ex2) {}
        catch (IOException zzl) {
            goto Label_0242;
        }
    }
}
