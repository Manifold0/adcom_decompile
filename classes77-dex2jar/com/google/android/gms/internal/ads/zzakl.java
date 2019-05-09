// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import java.util.List;
import android.content.Context;

final class zzakl implements zzoi
{
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ List zzcrs;
    private final /* synthetic */ zzoh zzcrt;
    
    zzakl(final zzakk zzakk, final List zzcrs, final zzoh zzcrt, final Context val$context) {
        this.zzcrs = zzcrs;
        this.zzcrt = zzcrt;
        this.val$context = val$context;
    }
    
    @Override
    public final void zzjp() {
        for (final String s : this.zzcrs) {
            final String value = String.valueOf(s);
            String concat;
            if (value.length() != 0) {
                concat = "Pinging url: ".concat(value);
            }
            else {
                concat = new String("Pinging url: ");
            }
            zzakb.zzdj(concat);
            this.zzcrt.mayLaunchUrl(Uri.parse(s), null, null);
        }
        this.zzcrt.zzc((Activity)this.val$context);
    }
    
    @Override
    public final void zzjq() {
    }
}
