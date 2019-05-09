// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import javax.annotation.Nullable;
import com.google.android.gms.common.R$string;
import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class StringResourceValueReader
{
    private final Resources zzeu;
    private final String zzev;
    
    public StringResourceValueReader(final Context context) {
        Preconditions.checkNotNull(context);
        this.zzeu = context.getResources();
        this.zzev = this.zzeu.getResourcePackageName(R$string.common_google_play_services_unknown_issue);
    }
    
    @Nullable
    @KeepForSdk
    public String getString(final String s) {
        final int identifier = this.zzeu.getIdentifier(s, "string", this.zzev);
        if (identifier == 0) {
            return null;
        }
        return this.zzeu.getString(identifier);
    }
}
