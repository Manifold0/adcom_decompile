// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamite;

import android.content.Context;

final class zza implements VersionPolicy.zza
{
    @Override
    public final int getLocalVersion(final Context context, final String s) {
        return DynamiteModule.getLocalVersion(context, s);
    }
    
    @Override
    public final int zza(final Context context, final String s, final boolean b) throws LoadingException {
        return DynamiteModule.zza(context, s, b);
    }
}
