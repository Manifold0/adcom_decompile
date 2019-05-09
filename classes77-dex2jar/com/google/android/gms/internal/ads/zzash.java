// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;

@zzadh
public final class zzash extends MutableContextWrapper
{
    private Context zzaeo;
    private Activity zzcuj;
    private Context zzdeq;
    
    public zzash(final Context baseContext) {
        super(baseContext);
        this.setBaseContext(baseContext);
    }
    
    public final Object getSystemService(final String s) {
        return this.zzdeq.getSystemService(s);
    }
    
    public final void setBaseContext(final Context zzdeq) {
        this.zzaeo = zzdeq.getApplicationContext();
        Activity zzcuj;
        if (zzdeq instanceof Activity) {
            zzcuj = (Activity)zzdeq;
        }
        else {
            zzcuj = null;
        }
        this.zzcuj = zzcuj;
        this.zzdeq = zzdeq;
        super.setBaseContext(this.zzaeo);
    }
    
    public final void startActivity(final Intent intent) {
        if (this.zzcuj != null) {
            this.zzcuj.startActivity(intent);
            return;
        }
        intent.setFlags(268435456);
        this.zzaeo.startActivity(intent);
    }
    
    public final Activity zzto() {
        return this.zzcuj;
    }
    
    public final Context zzua() {
        return this.zzdeq;
    }
}
