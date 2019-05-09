// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.concurrent.Callable;
import android.net.Uri;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;

@zzadh
public final class zzmw
{
    private final Context mContext;
    
    public zzmw(final Context mContext) {
        Preconditions.checkNotNull((Object)mContext, (Object)"Context can not be null");
        this.mContext = mContext;
    }
    
    private final boolean zza(final Intent intent) {
        boolean b = false;
        Preconditions.checkNotNull((Object)intent, (Object)"Intent can not be null");
        if (!this.mContext.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            b = true;
        }
        return b;
    }
    
    public final boolean zziw() {
        final Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return this.zza(intent);
    }
    
    public final boolean zzix() {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return this.zza(intent);
    }
    
    public final boolean zziy() {
        return (boolean)zzaml.zza(this.mContext, (Callable)new zzmx()) && Wrappers.packageManager(this.mContext).checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
    
    @TargetApi(14)
    public final boolean zziz() {
        return this.zza(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}
