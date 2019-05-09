// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamite.DynamiteModule$LoadingException;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzhx
{
    @VisibleForTesting
    zzen zzaln;
    @VisibleForTesting
    boolean zzalo;
    
    public zzhx() {
    }
    
    public zzhx(final Context context) {
        zznk.initialize(context);
        if (!zzkb.zzik().zzd(zznk.zzbeo)) {
            return;
        }
        try {
            this.zzaln = zzeo.zza(DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite").instantiate("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
            ObjectWrapper.wrap((Object)context);
            this.zzaln.zza(ObjectWrapper.wrap((Object)context), "GMA_SDK");
            this.zzalo = true;
        }
        catch (NullPointerException ex) {}
        catch (RemoteException ex2) {
            goto Label_0076;
        }
        catch (DynamiteModule$LoadingException ex3) {
            goto Label_0076;
        }
    }
    
    public zzhx(final Context context, final String s, final String s2) {
        zznk.initialize(context);
        try {
            this.zzaln = zzeo.zza(DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite").instantiate("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
            ObjectWrapper.wrap((Object)context);
            this.zzaln.zza(ObjectWrapper.wrap((Object)context), s, null);
            this.zzalo = true;
        }
        catch (NullPointerException ex) {}
        catch (RemoteException ex2) {
            goto Label_0056;
        }
        catch (DynamiteModule$LoadingException ex3) {
            goto Label_0056;
        }
    }
    
    public final zzhz zzd(final byte[] array) {
        return new zzhz(this, array, null);
    }
}
