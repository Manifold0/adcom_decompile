package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

class br implements OnPreparedListener {
    /* renamed from: a */
    final /* synthetic */ az f1816a;

    br(az azVar) {
        this.f1816a = azVar;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.f1816a.f1525E = new WeakReference(mediaPlayer);
        int i = this.f1816a.m1727i() ? 0 : 1;
        mediaPlayer.setVolume((float) i, (float) i);
        i = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.f1816a.computedLengthSeconds = (int) TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getDuration());
        this.f1816a.videoView.setVideoSize(i, videoHeight);
        mediaPlayer.setDisplay(this.f1816a.videoView.getHolder());
        mediaPlayer.setOnErrorListener(new bs(this));
        if (this.f1816a.f1549s == 0) {
            this.f1816a.m1742q();
            this.f1816a.m1730k();
            this.f1816a.m1752v();
            this.f1816a.m1750u();
            this.f1816a.playVideo();
            this.f1816a.m1680F();
        }
    }
}
