package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnFailureListener;

final class zzl implements OnFailureListener {
    private final /* synthetic */ RegisterListenerMethod zzap;
    private final /* synthetic */ zzk zzaq;

    zzl(zzk zzk, RegisterListenerMethod registerListenerMethod) {
        this.zzaq = zzk;
        this.zzap = registerListenerMethod;
    }

    public final void onFailure(Exception exception) {
        synchronized (this.zzaq) {
            this.zzaq.zzan.remove(this.zzap.getListenerKey());
        }
    }
}
