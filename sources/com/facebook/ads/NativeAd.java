package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.adapters.C1924i;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.protocol.C2070e;
import java.util.ArrayList;
import java.util.List;

public class NativeAd extends NativeAdBase {

    public enum AdCreativeType {
        IMAGE,
        VIDEO,
        CAROUSEL,
        UNKNOWN
    }

    protected NativeAd(Context context, C1924i c1924i, C2047d c2047d) {
        super(context, c1924i, c2047d);
        m4075a(C2070e.NATIVE_UNKNOWN);
    }

    public NativeAd(Context context, String str) {
        super(context, str);
        m4075a(C2070e.NATIVE_UNKNOWN);
    }

    NativeAd(NativeAdBase nativeAdBase) {
        super(nativeAdBase);
    }

    NativeAd(C2114e c2114e) {
        super(c2114e);
    }

    /* renamed from: a */
    String m4081a() {
        return m4078f().m5342r();
    }

    /* renamed from: a */
    void m4082a(Type type) {
        m4078f().m5318a(type.m4108a());
    }

    /* renamed from: b */
    String m4083b() {
        return m4078f().m5343s();
    }

    /* renamed from: c */
    VideoAutoplayBehavior m4084c() {
        return VideoAutoplayBehavior.fromInternalAutoplayBehavior(m4078f().m5344t());
    }

    /* renamed from: d */
    List<NativeAd> m4085d() {
        if (m4078f().m5345u() == null) {
            return null;
        }
        List<NativeAd> arrayList = new ArrayList();
        for (C2114e nativeAd : m4078f().m5345u()) {
            arrayList.add(new NativeAd(nativeAd));
        }
        return arrayList;
    }

    /* renamed from: e */
    Type m4086e() {
        return m4078f().m5348x() == null ? null : Type.m4107a(m4078f().m5348x());
    }

    public AdCreativeType getAdCreativeType() {
        return !TextUtils.isEmpty(m4078f().m5342r()) ? AdCreativeType.VIDEO : (m4078f().m5345u() == null || m4078f().m5345u().isEmpty()) ? (m4078f().m5334j() == null || TextUtils.isEmpty(m4078f().m5334j().m5352a())) ? AdCreativeType.UNKNOWN : AdCreativeType.IMAGE : AdCreativeType.CAROUSEL;
    }

    public void registerViewForInteraction(View view, MediaView mediaView) {
        registerViewForInteraction(view, mediaView, (AdIconView) null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView) {
        registerViewForInteraction(view, mediaView, imageView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView, @Nullable List<View> list) {
        if (imageView != null) {
            C2114e.m5284a(m4078f().m5333i(), imageView);
        }
        registerViewForInteraction(view, mediaView, (AdIconView) null, (List) list);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable AdIconView adIconView) {
        registerViewForInteraction(view, mediaView, (MediaView) adIconView, null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable MediaView mediaView2, @Nullable List<View> list) {
        if (mediaView != null) {
            mediaView.setNativeAd(this);
        }
        if (mediaView2 != null) {
            mediaView2.m3926a((NativeAdBase) this, false);
        }
        if (list != null) {
            m4078f().m5312a(view, (C1783f) mediaView, (List) list);
        } else {
            m4078f().m5311a(view, (C1783f) mediaView);
        }
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable List<View> list) {
        registerViewForInteraction(view, mediaView, (AdIconView) null, (List) list);
    }
}
