package com.facebook.ads.internal.view.p022i.p066d;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;

/* renamed from: com.facebook.ads.internal.view.i.d.c */
public interface C2495c {
    /* renamed from: a */
    void mo5610a();

    /* renamed from: a */
    void mo5611a(int i);

    /* renamed from: a */
    void mo5612a(C2389a c2389a);

    /* renamed from: a */
    void mo5613a(boolean z);

    /* renamed from: b */
    void mo5614b();

    /* renamed from: c */
    void mo5615c();

    /* renamed from: d */
    boolean mo5616d();

    /* renamed from: e */
    void mo5617e();

    int getCurrentPosition();

    int getDuration();

    long getInitialBufferTime();

    C2389a getStartReason();

    C2501d getState();

    int getVideoHeight();

    int getVideoWidth();

    View getView();

    float getVolume();

    void setBackgroundPlaybackEnabled(boolean z);

    void setControlsAnchorView(View view);

    void setFullScreen(boolean z);

    void setRequestedVolume(float f);

    void setVideoMPD(@Nullable String str);

    void setVideoStateChangeListener(C2393e c2393e);

    void setup(Uri uri);
}
