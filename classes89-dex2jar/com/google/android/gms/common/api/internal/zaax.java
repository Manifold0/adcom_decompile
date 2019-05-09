// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.GmsClientEventManager;

final class zaax implements GmsClientEventState
{
    private final /* synthetic */ zaaw zahh;
    
    zaax(final zaaw zahh) {
        this.zahh = zahh;
    }
    
    @Override
    public final Bundle getConnectionHint() {
        return null;
    }
    
    @Override
    public final boolean isConnected() {
        return this.zahh.isConnected();
    }
}
