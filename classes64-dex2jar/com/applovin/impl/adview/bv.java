// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer$OnErrorListener;

class bv implements MediaPlayer$OnErrorListener
{
    final /* synthetic */ az a;
    
    bv(final az a) {
        this.a = a;
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.a.w.post((Runnable)new bw(this, n, n2));
        return true;
    }
}
