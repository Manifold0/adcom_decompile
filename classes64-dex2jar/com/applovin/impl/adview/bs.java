// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer$OnErrorListener;

class bs implements MediaPlayer$OnErrorListener
{
    final /* synthetic */ br a;
    
    bs(final br a) {
        this.a = a;
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.a.a.w.post((Runnable)new bt(this, n, n2));
        return true;
    }
}
