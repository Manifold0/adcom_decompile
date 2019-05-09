package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C1820x;
import com.facebook.ads.internal.view.p022i.p023b.C2419w;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;

/* renamed from: com.facebook.ads.internal.view.i.c.f */
public class C2456f extends ImageView implements C2390b {
    /* renamed from: a */
    private static final int f5933a = ((int) (4.0f * Resources.getSystem().getDisplayMetrics().density));
    /* renamed from: b */
    private final Paint f5934b = new Paint();
    @Nullable
    /* renamed from: c */
    private C2394a f5935c;
    /* renamed from: d */
    private final C1820x f5936d = new C24541(this);

    /* renamed from: com.facebook.ads.internal.view.i.c.f$1 */
    class C24541 extends C1820x {
        /* renamed from: a */
        final /* synthetic */ C2456f f5931a;

        C24541(C2456f c2456f) {
            this.f5931a = c2456f;
        }

        /* renamed from: a */
        public void m6310a(C2419w c2419w) {
            this.f5931a.m6315a();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.f$2 */
    class C24552 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2456f f5932a;

        C24552(C2456f c2456f) {
            this.f5932a = c2456f;
        }

        public void onClick(View view) {
            if (this.f5932a.f5935c != null) {
                if (this.f5932a.m6312b()) {
                    this.f5932a.f5935c.setVolume(1.0f);
                } else {
                    this.f5932a.f5935c.setVolume(0.0f);
                }
                this.f5932a.m6315a();
            }
        }
    }

    public C2456f(Context context) {
        super(context);
        this.f5934b.setColor(-1728053248);
        setColorFilter(-1);
        setPadding(f5933a, f5933a, f5933a, f5933a);
        m6314c();
        setOnClickListener(new C24552(this));
    }

    /* renamed from: b */
    private boolean m6312b() {
        return this.f5935c != null && this.f5935c.getVolume() == 0.0f;
    }

    /* renamed from: c */
    private void m6314c() {
        setImageBitmap(C2604c.m6697a(C2603b.SOUND_ON));
    }

    /* renamed from: a */
    public final void m6315a() {
        if (this.f5935c != null) {
            if (m6312b()) {
                setImageBitmap(C2604c.m6697a(C2603b.SOUND_OFF));
            } else {
                m6314c();
            }
        }
    }

    /* renamed from: a */
    public void mo5597a(C2394a c2394a) {
        this.f5935c = c2394a;
        if (this.f5935c != null) {
            this.f5935c.getEventBus().m5030a(this.f5936d);
        }
    }

    /* renamed from: b */
    public void mo5598b(C2394a c2394a) {
        if (this.f5935c != null) {
            this.f5935c.getEventBus().m5032b(this.f5936d);
        }
        this.f5935c = null;
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        canvas.drawCircle((float) width, (float) height, (float) Math.min(width, height), this.f5934b);
        super.onDraw(canvas);
    }
}
