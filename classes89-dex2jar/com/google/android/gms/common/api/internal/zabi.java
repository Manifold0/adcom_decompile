// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

final class zabi implements BackgroundDetector$BackgroundStateChangeListener
{
    private final /* synthetic */ GoogleApiManager zaim;
    
    zabi(final GoogleApiManager zaim) {
        this.zaim = zaim;
    }
    
    public final void onBackgroundStateChanged(final boolean b) {
        this.zaim.handler.sendMessage(this.zaim.handler.obtainMessage(1, (Object)b));
    }
}
