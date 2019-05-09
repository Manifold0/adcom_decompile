package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.widget.TextView;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* renamed from: com.facebook.ads.internal.view.i.c.c */
public class C2442c extends C2391c {
    /* renamed from: a */
    private final TextView f5899a;
    /* renamed from: b */
    private final String f5900b;
    /* renamed from: c */
    private final C1809f<C2413o> f5901c = new C24411(this);

    /* renamed from: com.facebook.ads.internal.view.i.c.c$1 */
    class C24411 extends C1809f<C2413o> {
        /* renamed from: a */
        final /* synthetic */ C2442c f5898a;

        C24411(C2442c c2442c) {
            this.f5898a = c2442c;
        }

        /* renamed from: a */
        public Class<C2413o> mo5359a() {
            return C2413o.class;
        }

        /* renamed from: a */
        public void m6268a(C2413o c2413o) {
            if (this.f5898a.getVideoView() != null) {
                this.f5898a.f5899a.setText(C2442c.m6270a(this.f5898a, (long) (this.f5898a.getVideoView().getDuration() - this.f5898a.getVideoView().getCurrentPositionInMillis())));
            }
        }
    }

    public C2442c(Context context, String str) {
        super(context);
        this.f5899a = new TextView(context);
        this.f5900b = str;
        addView(this.f5899a);
    }

    /* renamed from: a */
    static /* synthetic */ String m6270a(C2442c c2442c, long j) {
        if (j <= 0) {
            return "00:00";
        }
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(j);
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(j % 60000);
        if (c2442c.f5900b.isEmpty()) {
            return String.format(Locale.US, "%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(toSeconds)});
        }
        return c2442c.f5900b.replace("{{REMAINING_TIME}}", String.format(Locale.US, "%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(toSeconds)}));
    }

    /* renamed from: a */
    protected void mo5608a() {
        super.mo5608a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5030a(this.f5901c);
        }
    }

    /* renamed from: b */
    protected void mo5609b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5032b(this.f5901c);
        }
        super.mo5609b();
    }

    public void setCountdownTextColor(int i) {
        this.f5899a.setTextColor(i);
    }
}
