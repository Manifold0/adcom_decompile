// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public class zzaal
{
    private final zzaqw zzbnd;
    private final String zzbxf;
    
    public zzaal(final zzaqw zzaqw) {
        this(zzaqw, "");
    }
    
    public zzaal(final zzaqw zzbnd, final String zzbxf) {
        this.zzbnd = zzbnd;
        this.zzbxf = zzbxf;
    }
    
    public final void zza(final int n, final int n2, final int n3, final int n4, final float n5, final int n6) {
        try {
            this.zzbnd.zza("onScreenInfoChanged", new JSONObject().put("width", n).put("height", n2).put("maxSizeWidth", n3).put("maxSizeHeight", n4).put("density", (double)n5).put("rotation", n6));
        }
        catch (JSONException ex) {
            zzakb.zzb("Error occured while obtaining screen information.", (Throwable)ex);
        }
    }
    
    public final void zzb(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzbnd.zza("onSizeChanged", new JSONObject().put("x", n).put("y", n2).put("width", n3).put("height", n4));
        }
        catch (JSONException ex) {
            zzakb.zzb("Error occured while dispatching size change.", (Throwable)ex);
        }
    }
    
    public final void zzbw(final String s) {
        try {
            this.zzbnd.zza("onError", new JSONObject().put("message", (Object)s).put("action", (Object)this.zzbxf));
        }
        catch (JSONException ex) {
            zzakb.zzb("Error occurred while dispatching error event.", (Throwable)ex);
        }
    }
    
    public final void zzbx(final String s) {
        try {
            this.zzbnd.zza("onReadyEventReceived", new JSONObject().put("js", (Object)s));
        }
        catch (JSONException ex) {
            zzakb.zzb("Error occured while dispatching ready Event.", (Throwable)ex);
        }
    }
    
    public final void zzby(final String s) {
        try {
            this.zzbnd.zza("onStateChanged", new JSONObject().put("state", (Object)s));
        }
        catch (JSONException ex) {
            zzakb.zzb("Error occured while dispatching state change.", (Throwable)ex);
        }
    }
    
    public final void zzc(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzbnd.zza("onDefaultPositionReceived", new JSONObject().put("x", n).put("y", n2).put("width", n3).put("height", n4));
        }
        catch (JSONException ex) {
            zzakb.zzb("Error occured while dispatching default position.", (Throwable)ex);
        }
    }
}
