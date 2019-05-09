package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class bs implements OnErrorListener {
    /* renamed from: a */
    final /* synthetic */ br f1817a;

    bs(br brVar) {
        this.f1817a = brVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f1817a.f1816a.f1553w.post(new bt(this, i, i2));
        return true;
    }
}
