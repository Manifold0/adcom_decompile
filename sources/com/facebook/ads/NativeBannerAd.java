package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.NativeBannerAdView.Type;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.protocol.C2070e;
import java.util.List;

public class NativeBannerAd extends NativeAdBase {
    public NativeBannerAd(Context context, String str) {
        super(context, str);
        m4075a(C2070e.NATIVE_BANNER);
    }

    /* renamed from: a */
    Type m4124a() {
        return m4078f().m5348x() == null ? null : Type.m4126a(m4078f().m5348x());
    }

    /* renamed from: a */
    void m4125a(Type type) {
        m4078f().m5318a(type.m4127a());
    }

    public void registerViewForInteraction(View view, MediaView mediaView) {
        registerViewForInteraction(view, mediaView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable List<View> list) {
        if (mediaView != null) {
            mediaView.m3926a((NativeAdBase) this, true);
        }
        if (list != null) {
            m4078f().m5312a(view, (C1783f) mediaView, (List) list);
        } else {
            m4078f().m5311a(view, (C1783f) mediaView);
        }
    }
}
