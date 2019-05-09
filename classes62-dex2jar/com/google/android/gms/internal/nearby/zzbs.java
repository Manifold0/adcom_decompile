// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;

final class zzbs implements OnFailureListener
{
    private final /* synthetic */ String zzbm;
    private final /* synthetic */ zzbd zzcq;
    
    zzbs(final zzbd zzcq, final String zzbm) {
        this.zzcq = zzcq;
        this.zzbm = zzbm;
    }
    
    public final void onFailure(final Exception ex) {
        if (ex instanceof ApiException && ((ApiException)ex).getStatusCode() == 8003) {
            return;
        }
        this.zzcq.zzc(this.zzbm);
    }
}
