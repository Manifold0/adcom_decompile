// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.app.Activity;
import android.view.View;
import android.content.Context;
import android.net.Uri;

public final class zzci
{
    private static final String[] zzrc;
    private String zzqy;
    private String zzqz;
    private String zzra;
    private String[] zzrb;
    private zzce zzrd;
    
    static {
        zzrc = new String[] { "/aclk", "/pcs/click" };
    }
    
    public zzci(final zzce zzrd) {
        this.zzqy = "googleads.g.doubleclick.net";
        this.zzqz = "/pagead/ads";
        this.zzra = "ad.doubleclick.net";
        this.zzrb = new String[] { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
        this.zzrd = zzrd;
    }
    
    private final Uri zza(Uri build, final Context context, String s, final boolean b, final View view, final Activity activity) throws zzcj {
        boolean zza = false;
        Label_0064: {
            try {
                zza = this.zza(build);
                if (zza) {
                    if (build.toString().contains("dc_ms=")) {
                        throw new zzcj("Parameter already exists: dc_ms");
                    }
                    break Label_0064;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw new zzcj("Provided Uri is not in a valid state");
            }
            if (build.getQueryParameter("ms") != null) {
                throw new zzcj("Query parameter already exists: ms");
            }
        }
        String s2;
        if (b) {
            s2 = this.zzrd.zza(context, s, view, activity);
        }
        else {
            s2 = this.zzrd.zza(context);
        }
        if (zza) {
            s = build.toString();
            final int index = s.indexOf(";adurl");
            if (index != -1) {
                return Uri.parse(s.substring(0, index + 1) + "dc_ms" + "=" + s2 + ";" + s.substring(index + 1));
            }
            final String encodedPath = build.getEncodedPath();
            final int index2 = s.indexOf(encodedPath);
            return Uri.parse(s.substring(0, encodedPath.length() + index2) + ";" + "dc_ms" + "=" + s2 + ";" + s.substring(encodedPath.length() + index2));
        }
        else {
            s = build.toString();
            int n;
            if ((n = s.indexOf("&adurl")) == -1) {
                n = s.indexOf("?adurl");
            }
            if (n != -1) {
                return Uri.parse(s.substring(0, n + 1) + "ms" + "=" + s2 + "&" + s.substring(n + 1));
            }
            build = build.buildUpon().appendQueryParameter("ms", s2).build();
            return build;
        }
    }
    
    private final boolean zza(final Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzra);
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public final Uri zza(final Uri uri, final Context context) throws zzcj {
        return this.zza(uri, context, null, false, null, null);
    }
    
    public final Uri zza(Uri zza, final Context context, final View view, final Activity activity) throws zzcj {
        try {
            zza = this.zza(zza, context, zza.getQueryParameter("ai"), true, view, activity);
            return zza;
        }
        catch (UnsupportedOperationException ex) {
            throw new zzcj("Provided Uri is not in a valid state");
        }
    }
    
    public final void zza(final MotionEvent motionEvent) {
        this.zzrd.zza(motionEvent);
    }
    
    public final zzce zzaa() {
        return this.zzrd;
    }
    
    public final boolean zzb(final Uri uri) {
        final boolean b = false;
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            final String host = uri.getHost();
            final String[] zzrb = this.zzrb;
            final int length = zzrb.length;
            int n = 0;
            boolean b2;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if (host.endsWith(zzrb[n])) {
                    b2 = true;
                    break;
                }
                ++n;
            }
            return b2;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public final boolean zzc(final Uri uri) {
        boolean b = false;
        if (this.zzb(uri)) {
            final String[] zzrc = zzci.zzrc;
            final int length = zzrc.length;
            int n = 0;
            while (true) {
                b = b;
                if (n >= length) {
                    break;
                }
                if (uri.getPath().endsWith(zzrc[n])) {
                    b = true;
                    break;
                }
                ++n;
            }
        }
        return b;
    }
}
