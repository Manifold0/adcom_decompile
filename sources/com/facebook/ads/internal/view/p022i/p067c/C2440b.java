package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.view.i.c.b */
public class C2440b extends C2391c {
    /* renamed from: a */
    private WeakReference<OnAudioFocusChangeListener> f5894a = null;
    /* renamed from: b */
    private final C1818d f5895b = new C24351(this);
    /* renamed from: c */
    private final C1814j f5896c = new C24362(this);
    /* renamed from: d */
    private final C1812l f5897d = new C24393(this);

    /* renamed from: com.facebook.ads.internal.view.i.c.b$1 */
    class C24351 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2440b f5888a;

        C24351(C2440b c2440b) {
            this.f5888a = c2440b;
        }

        /* renamed from: a */
        public void m6255a(C2406c c2406c) {
            ((AudioManager) this.f5888a.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.f5888a.f5894a == null ? null : (OnAudioFocusChangeListener) this.f5888a.f5894a.get());
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.b$2 */
    class C24362 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2440b f5889a;

        C24362(C2440b c2440b) {
            this.f5889a = c2440b;
        }

        /* renamed from: a */
        public void m6257a(C2410i c2410i) {
            ((AudioManager) this.f5889a.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.f5889a.f5894a == null ? null : (OnAudioFocusChangeListener) this.f5889a.f5894a.get());
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.b$3 */
    class C24393 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2440b f5893a;

        /* renamed from: com.facebook.ads.internal.view.i.c.b$3$1 */
        class C24381 implements OnAudioFocusChangeListener {
            /* renamed from: a */
            final /* synthetic */ C24393 f5892a;

            C24381(C24393 c24393) {
                this.f5892a = c24393;
            }

            public void onAudioFocusChange(final int i) {
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C24381 f5891b;

                    public void run() {
                        if (this.f5891b.f5892a.f5893a.getVideoView() != null && i <= 0) {
                            this.f5891b.f5892a.f5893a.getVideoView().m6160a(false);
                        }
                    }
                });
            }
        }

        C24393(C2440b c2440b) {
            this.f5893a = c2440b;
        }

        /* renamed from: a */
        public void m6259a(C2411k c2411k) {
            if (this.f5893a.f5894a == null || this.f5893a.f5894a.get() == null) {
                this.f5893a.f5894a = new WeakReference(new C24381(this));
            }
            ((AudioManager) this.f5893a.getContext().getApplicationContext().getSystemService("audio")).requestAudioFocus((OnAudioFocusChangeListener) this.f5893a.f5894a.get(), 3, 1);
        }
    }

    public C2440b(Context context) {
        super(context);
    }

    /* renamed from: a */
    protected void mo5608a() {
        super.mo5608a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5029a(this.f5897d, this.f5895b, this.f5896c);
        }
    }

    /* renamed from: b */
    protected void mo5609b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5031b(this.f5896c, this.f5895b, this.f5897d);
        }
        super.mo5609b();
    }

    protected void onDetachedFromWindow() {
        ((AudioManager) getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.f5894a == null ? null : (OnAudioFocusChangeListener) this.f5894a.get());
        super.onDetachedFromWindow();
    }
}
