package com.chartboost.sdk.impl;

import android.content.Context;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.C1405g;
import com.chartboost.sdk.Libraries.CBLogging;

public class av extends FrameLayout {
    /* renamed from: a */
    private View f3099a;
    /* renamed from: b */
    private boolean f3100b;

    /* renamed from: com.chartboost.sdk.impl.av$a */
    public interface C1421a {
        /* renamed from: a */
        void mo4285a();

        /* renamed from: a */
        void mo4286a(int i);

        /* renamed from: a */
        void mo4287a(int i, int i2);

        /* renamed from: a */
        void mo4288a(OnCompletionListener onCompletionListener);

        /* renamed from: a */
        void mo4289a(OnErrorListener onErrorListener);

        /* renamed from: a */
        void mo4290a(OnPreparedListener onPreparedListener);

        /* renamed from: a */
        void mo4291a(Uri uri);

        /* renamed from: b */
        void mo4292b();

        /* renamed from: c */
        int mo4293c();

        /* renamed from: d */
        int mo4294d();

        /* renamed from: e */
        boolean mo4295e();
    }

    public av(Context context) {
        super(context);
        m3460b();
    }

    /* renamed from: b */
    private void m3460b() {
        this.f3100b = true;
        CBLogging.m3104e("VideoInit", "Choosing " + (this.f3100b ? "texture" : "surface") + " solution for video playback");
        C1405g a = C1405g.m3317a();
        if (this.f3100b) {
            this.f3099a = (View) a.m3318a(new au(getContext()));
        } else {
            this.f3099a = (View) a.m3318a(new at(getContext()));
        }
        this.f3099a.setContentDescription("CBVideo");
        addView(this.f3099a, new LayoutParams(-1, -1));
        if (!this.f3100b) {
            ((SurfaceView) this.f3099a).setZOrderMediaOverlay(true);
        }
    }

    /* renamed from: a */
    public C1421a m3461a() {
        return (C1421a) this.f3099a;
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        m3461a().mo4287a(w, h);
    }
}
