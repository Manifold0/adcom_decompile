// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.util.PlatformVersion;
import android.content.Context;

@zzadh
public final class zzapo extends zzaph
{
    @Nullable
    @Override
    public final zzapg zza(final Context context, final zzapw zzapw, int n, final boolean b, final zznx zznx, final zzapv zzapv) {
        final ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (PlatformVersion.isAtLeastIceCreamSandwich() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            return null;
        }
        return new zzaov(context, b, zzapw.zzud().zzvs(), zzapv, new zzapx(context, zzapw.zztq(), zzapw.zzol(), zznx, zzapw.zztn()));
    }
}
