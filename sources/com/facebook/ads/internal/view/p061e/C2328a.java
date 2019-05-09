package com.facebook.ads.internal.view.p061e;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C2405b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.e.a */
public class C2328a extends RelativeLayout {
    /* renamed from: a */
    private final C2598w f5582a;
    /* renamed from: b */
    private final String f5583b;
    /* renamed from: c */
    private C2394a f5584c;
    /* renamed from: d */
    private final Paint f5585d = new Paint();
    /* renamed from: e */
    private final RectF f5586e;

    public C2328a(Context context, C2598w c2598w, String str, String str2, int i, C2394a c2394a, final C2085c c2085c, final String str3) {
        super(context);
        this.f5582a = c2598w;
        this.f5583b = str;
        this.f5584c = c2394a;
        View textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setText(str2);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        addView(textView);
        this.f5585d.setStyle(Style.FILL);
        this.f5585d.setColor(i);
        this.f5586e = new RectF();
        C2600x.m6680a((View) this, 0);
        setOnClickListener(new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C2328a f5495c;

            public void onClick(View view) {
                try {
                    Uri parse = Uri.parse(this.f5495c.f5583b);
                    this.f5495c.f5584c.getEventBus().m5028a(new C2405b(parse));
                    Map hashMap = new HashMap();
                    hashMap.put("touch", C2581k.m6645a(this.f5495c.f5582a.m6676e()));
                    C1842b a = C1843c.m4142a(this.f5495c.getContext(), c2085c, str3, parse, hashMap);
                    if (a != null) {
                        a.mo5376a();
                    }
                } catch (Throwable e) {
                    Log.e(String.valueOf(C2328a.class), "Error while opening " + this.f5495c.f5583b, e);
                } catch (Throwable e2) {
                    Log.e(String.valueOf(C2328a.class), "Error executing action", e2);
                }
            }
        });
    }

    protected void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f5586e.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f5586e, 10.0f * f, f * 10.0f, this.f5585d);
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.f5582a.m6671a(motionEvent, getRootView(), this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
