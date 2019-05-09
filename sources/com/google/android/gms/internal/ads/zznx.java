package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznx {
    private final Object mLock = new Object();
    @VisibleForTesting
    private boolean zzbgn;
    private final List<zznv> zzbgo = new LinkedList();
    private final Map<String, String> zzbgp = new LinkedHashMap();
    private String zzbgq;
    @Nullable
    private zznx zzbgr;

    public zznx(boolean z, String str, String str2) {
        this.zzbgn = z;
        this.zzbgp.put("action", str);
        this.zzbgp.put("ad_format", str2);
    }

    public final boolean zza(zznv zznv, long j, String... strArr) {
        synchronized (this.mLock) {
            for (String zznv2 : strArr) {
                this.zzbgo.add(new zznv(j, zznv2, zznv));
            }
        }
        return true;
    }

    public final boolean zza(@Nullable zznv zznv, String... strArr) {
        return (!this.zzbgn || zznv == null) ? false : zza(zznv, zzbv.zzer().elapsedRealtime(), strArr);
    }

    public final void zzan(String str) {
        if (this.zzbgn) {
            synchronized (this.mLock) {
                this.zzbgq = str;
            }
        }
    }

    public final void zzc(@Nullable zznx zznx) {
        synchronized (this.mLock) {
            this.zzbgr = zznx;
        }
    }

    @Nullable
    public final zznv zzd(long j) {
        return !this.zzbgn ? null : new zznv(j, null, null);
    }

    public final void zze(String str, String str2) {
        if (this.zzbgn && !TextUtils.isEmpty(str2)) {
            zznn zzpy = zzbv.zzeo().zzpy();
            if (zzpy != null) {
                synchronized (this.mLock) {
                    zznr zzal = zzpy.zzal(str);
                    Map map = this.zzbgp;
                    map.put(str, zzal.zzd((String) map.get(str), str2));
                }
            }
        }
    }

    public final zznv zzjj() {
        return zzd(zzbv.zzer().elapsedRealtime());
    }

    public final String zzjk() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        synchronized (this.mLock) {
            for (zznv zznv : this.zzbgo) {
                long time = zznv.getTime();
                String zzjg = zznv.zzjg();
                zznv zznv2 = zznv2.zzjh();
                if (zznv2 != null && time > 0) {
                    stringBuilder2.append(zzjg).append('.').append(time - zznv2.getTime()).append(',');
                }
            }
            this.zzbgo.clear();
            if (!TextUtils.isEmpty(this.zzbgq)) {
                stringBuilder2.append(this.zzbgq);
            } else if (stringBuilder2.length() > 0) {
                stringBuilder2.setLength(stringBuilder2.length() - 1);
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    @VisibleForTesting
    final Map<String, String> zzjl() {
        Map<String, String> map;
        synchronized (this.mLock) {
            zznn zzpy = zzbv.zzeo().zzpy();
            if (zzpy == null || this.zzbgr == null) {
                map = this.zzbgp;
            } else {
                map = zzpy.zza(this.zzbgp, this.zzbgr.zzjl());
            }
        }
        return map;
    }

    public final zznv zzjm() {
        synchronized (this.mLock) {
        }
        return null;
    }
}
