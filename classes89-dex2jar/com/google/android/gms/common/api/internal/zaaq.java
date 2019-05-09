// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zaaq extends zaau
{
    private final /* synthetic */ zaak zagj;
    private final ArrayList<Api.Client> zagp;
    
    public zaaq(final zaak zagj, final ArrayList<Api.Client> zagp) {
        this.zagj = zagj;
        super(zagj, null);
        this.zagp = zagp;
    }
    
    @WorkerThread
    public final void zaan() {
        this.zagj.zaft.zaee.zaha = this.zagj.zaat();
        final ArrayList<Api.Client> list = this.zagp;
        final int size = list.size();
        int i = 0;
        while (i < size) {
            final Api.Client value = list.get(i);
            ++i;
            value.getRemoteService(this.zagj.zagf, this.zagj.zaft.zaee.zaha);
        }
    }
}
