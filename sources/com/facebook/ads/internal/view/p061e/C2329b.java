package com.facebook.ads.internal.view.p061e;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.e.b */
public class C2329b extends RelativeLayout {
    /* renamed from: a */
    private final Paint f5587a = new Paint();
    /* renamed from: b */
    private final RectF f5588b;

    public C2329b(Context context, String str) {
        super(context);
        float f = context.getResources().getDisplayMetrics().density;
        View textView = new TextView(context);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setTextSize(16.0f);
        textView.setText(str);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        int i = (int) (f * 6.0f);
        textView.setPadding(i, i, i, i);
        addView(textView);
        this.f5587a.setStyle(Style.FILL);
        this.f5587a.setColor(-1);
        this.f5588b = new RectF();
        C2600x.m6680a((View) this, 0);
    }

    protected void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f5588b.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f5588b, 10.0f * f, f * 10.0f, this.f5587a);
        super.onDraw(canvas);
    }
}
