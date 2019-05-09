// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.ads.zzakb;
import android.os.Bundle;
import android.app.Activity;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzr extends zzd
{
    public zzr(final Activity activity) {
        super(activity);
    }
    
    @Override
    public final void onCreate(final Bundle bundle) {
        zzakb.v("AdOverlayParcel is null or does not contain valid overlay type.");
        this.zzbxx = 3;
        this.mActivity.finish();
    }
}
