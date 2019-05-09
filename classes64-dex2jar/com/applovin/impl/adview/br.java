// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.media.MediaPlayer$OnErrorListener;
import java.util.concurrent.TimeUnit;
import java.lang.ref.WeakReference;
import android.media.MediaPlayer;
import android.media.MediaPlayer$OnPreparedListener;

class br implements MediaPlayer$OnPreparedListener
{
    final /* synthetic */ az a;
    
    br(final az a) {
        this.a = a;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.a.E = (WeakReference<MediaPlayer>)new WeakReference((T)mediaPlayer);
        int n;
        if (this.a.i()) {
            n = 0;
        }
        else {
            n = 1;
        }
        mediaPlayer.setVolume((float)n, (float)n);
        final int videoWidth = mediaPlayer.getVideoWidth();
        final int videoHeight = mediaPlayer.getVideoHeight();
        this.a.computedLengthSeconds = (int)TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration());
        this.a.videoView.setVideoSize(videoWidth, videoHeight);
        mediaPlayer.setDisplay(this.a.videoView.getHolder());
        mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)new bs(this));
        if (this.a.s == 0L) {
            this.a.q();
            this.a.k();
            this.a.v();
            this.a.u();
            this.a.playVideo();
            this.a.F();
        }
    }
}
