package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;

@zzadh
public final class zzapo extends zzaph {
    @Nullable
    public final zzapg zza(Context context, zzapw zzapw, int i, boolean z, zznx zznx, zzapv zzapv) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        Object obj = (!PlatformVersion.isAtLeastIceCreamSandwich() || (applicationInfo != null && applicationInfo.targetSdkVersion < 11)) ? null : 1;
        if (obj == null) {
            return null;
        }
        return new zzaov(context, z, zzapw.zzud().zzvs(), zzapv, new zzapx(context, zzapw.zztq(), zzapw.zzol(), zznx, zzapw.zztn()));
    }
}
