// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer$OnCompletionListener;

class bu implements MediaPlayer$OnCompletionListener
{
    final /* synthetic */ az a;
    
    bu(final az a) {
        this.a = a;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        this.a.h();
    }
}
