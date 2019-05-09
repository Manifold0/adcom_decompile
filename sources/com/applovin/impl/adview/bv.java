package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class bv implements OnErrorListener {
    /* renamed from: a */
    final /* synthetic */ az f1822a;

    bv(az azVar) {
        this.f1822a = azVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f1822a.f1553w.post(new bw(this, i, i2));
        return true;
    }
}
