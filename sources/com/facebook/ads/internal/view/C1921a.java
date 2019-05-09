package com.facebook.ads.internal.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p021o.C2061d;
import com.facebook.ads.internal.view.p055a.C2192b;

/* renamed from: com.facebook.ads.internal.view.a */
public interface C1921a {

    /* renamed from: com.facebook.ads.internal.view.a$a */
    public interface C1789a {
        /* renamed from: a */
        void mo5333a(View view);

        /* renamed from: a */
        void mo5334a(View view, int i);

        /* renamed from: a */
        void mo5335a(String str);

        /* renamed from: a */
        void mo5336a(String str, C2061d c2061d);

        /* renamed from: a */
        void mo5337a(String str, boolean z, @Nullable C2192b c2192b);
    }

    /* renamed from: a */
    void mo5403a(Intent intent, @Nullable Bundle bundle, AudienceNetworkActivity audienceNetworkActivity);

    /* renamed from: a */
    void mo5404a(Bundle bundle);

    void a_(boolean z);

    /* renamed from: b */
    void mo5406b(boolean z);

    void onDestroy();

    void setListener(C1789a c1789a);
}
