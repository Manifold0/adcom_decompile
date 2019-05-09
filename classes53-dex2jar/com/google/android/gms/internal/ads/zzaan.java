// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.webkit.WebView;
import android.support.annotation.Nullable;
import android.os.RemoteException;
import com.google.android.gms.dynamite.DynamiteModule$LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzaan
{
    private static final Object sLock;
    @VisibleForTesting
    @GuardedBy("sLock")
    private static boolean zzbxg;
    @VisibleForTesting
    @GuardedBy("sLock")
    private static boolean zzsh;
    @VisibleForTesting
    private zzatn zzbxh;
    
    static {
        sLock = new Object();
        zzaan.zzsh = false;
        zzaan.zzbxg = false;
    }
    
    @VisibleForTesting
    private final void zzj(final Context context) {
        synchronized (zzaan.sLock) {
            if (!zzkb.zzik().zzd(zznk.zzbet) || zzaan.zzbxg) {
                return;
            }
            try {
                zzaan.zzbxg = true;
                this.zzbxh = zzato.zzab(DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite").instantiate("com.google.android.gms.ads.omid.DynamiteOmid"));
            }
            catch (DynamiteModule$LoadingException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    @Nullable
    public final String getVersion(Context value) {
        if (!zzkb.zzik().zzd(zznk.zzbet)) {
            return null;
        }
        try {
            this.zzj((Context)value);
            value = (NullPointerException)String.valueOf(this.zzbxh.getVersion());
            if (((String)value).length() != 0) {
                return "a.".concat((String)value);
            }
            value = (NullPointerException)new String("a.");
            return (String)value;
        }
        catch (RemoteException ex) {}
        catch (NullPointerException value) {
            goto Label_0067;
        }
    }
    
    @Nullable
    public final IObjectWrapper zza(String ex, final WebView webView, final String s, final String s2, final String s3) {
        final Object sLock = zzaan.sLock;
        // monitorenter(sLock)
        try {
            if (!zzkb.zzik().zzd(zznk.zzbet) || !zzaan.zzsh) {
                return null;
            }
            // monitorexit(sLock)
            final zzaan zzaan = this;
            final zzatn zzatn = zzaan.zzbxh;
            final RemoteException ex2 = ex;
            final WebView webView2 = webView;
            final IObjectWrapper objectWrapper = ObjectWrapper.wrap((Object)webView2);
            final String s4 = s;
            final String s5 = s2;
            final String s6 = s3;
            final IObjectWrapper objectWrapper2 = zzatn.zza((String)ex2, objectWrapper, s4, s5, s6);
            final IObjectWrapper objectWrapper3;
            ex = (RemoteException)(objectWrapper3 = objectWrapper2);
            return objectWrapper3;
        }
        finally {
            final RemoteException ex3;
            ex = ex3;
        }
        // monitorexit(sLock)
        try {
            final zzaan zzaan = this;
            final zzatn zzatn = zzaan.zzbxh;
            final RemoteException ex2 = ex;
            final WebView webView2 = webView;
            final IObjectWrapper objectWrapper = ObjectWrapper.wrap((Object)webView2);
            final String s4 = s;
            final String s5 = s2;
            final String s6 = s3;
            final IObjectWrapper objectWrapper2 = zzatn.zza((String)ex2, objectWrapper, s4, s5, s6);
            final IObjectWrapper objectWrapper3;
            ex = (RemoteException)(objectWrapper3 = objectWrapper2);
            return objectWrapper3;
        }
        catch (NullPointerException ex4) {}
        catch (RemoteException ex) {
            goto Label_0073;
        }
    }
    
    public final void zza(final IObjectWrapper ex, final View view) {
        final Object sLock = zzaan.sLock;
        // monitorenter(sLock)
        try {
            if (!zzkb.zzik().zzd(zznk.zzbet) || !zzaan.zzsh) {
                return;
            }
            // monitorexit(sLock)
            try {
                this.zzbxh.zza((IObjectWrapper)ex, ObjectWrapper.wrap((Object)view));
            }
            catch (RemoteException ex2) {}
            catch (NullPointerException ex) {}
        }
        finally {}
    }
    
    public final boolean zzi(final Context context) {
        synchronized (zzaan.sLock) {
            if (!zzkb.zzik().zzd(zznk.zzbet)) {
                return false;
            }
            if (zzaan.zzsh) {
                return true;
            }
        }
        while (true) {
            try {
                final Context context2;
                this.zzj(context2);
                // monitorexit(o)
                return zzaan.zzsh = this.zzbxh.zzy(ObjectWrapper.wrap((Object)context2));
                final RemoteException ex;
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                // monitorexit(o)
                return false;
            }
            catch (RemoteException ex) {
                continue;
            }
            catch (NullPointerException ex) {
                continue;
            }
            break;
        }
    }
    
    public final void zzm(final IObjectWrapper ex) {
        final Object sLock = zzaan.sLock;
        // monitorenter(sLock)
        try {
            if (!zzkb.zzik().zzd(zznk.zzbet) || !zzaan.zzsh) {
                return;
            }
            // monitorexit(sLock)
            try {
                this.zzbxh.zzm((IObjectWrapper)ex);
            }
            catch (RemoteException ex2) {}
            catch (NullPointerException ex) {}
        }
        finally {}
    }
    
    public final void zzn(final IObjectWrapper ex) {
        final Object sLock = zzaan.sLock;
        // monitorenter(sLock)
        try {
            if (!zzkb.zzik().zzd(zznk.zzbet) || !zzaan.zzsh) {
                return;
            }
            // monitorexit(sLock)
            try {
                this.zzbxh.zzn((IObjectWrapper)ex);
            }
            catch (RemoteException ex2) {}
            catch (NullPointerException ex) {}
        }
        finally {}
    }
}
