package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.p037f.C1996c;

/* renamed from: com.facebook.ads.internal.view.a.j */
public class C2213j extends LinearLayout {
    /* renamed from: a */
    private static final int f5134a = ((int) (40.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final int f5135b = ((int) (20.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f5136c = ((int) (10.0f * C2600x.f6420b));
    /* renamed from: d */
    private final C1996c f5137d;
    /* renamed from: e */
    private final C2193e f5138e;

    /* renamed from: com.facebook.ads.internal.view.a.j$1 */
    class C22111 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2213j f5130a;

        C22111(C2213j c2213j) {
            this.f5130a = c2213j;
        }

        public void onClick(View view) {
            this.f5130a.f5138e.mo5535a();
        }
    }

    C2213j(Context context, C1996c c1996c, C2193e c2193e, C2603b c2603b) {
        this(context, c1996c, c2193e, null, c2603b);
    }

    C2213j(Context context, C1996c c1996c, C2193e c2193e, @Nullable String str, C2603b c2603b) {
        View imageView;
        super(context);
        this.f5137d = c1996c;
        this.f5138e = c2193e;
        setOrientation(1);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        if (!TextUtils.isEmpty(str)) {
            imageView = new ImageView(getContext());
            imageView.setColorFilter(-10459280);
            imageView.setImageBitmap(C2604c.m6697a(C2603b.BACK_ARROW));
            imageView.setPadding(0, f5136c, f5136c * 2, f5136c);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(f5134a, f5134a);
            imageView.setOnClickListener(new C22111(this));
            TextView textView = new TextView(getContext());
            textView.setGravity(17);
            textView.setText(str);
            C2600x.m6687a(textView, true, 16);
            textView.setTextColor(-14934495);
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams3.setMargins(0, 0, f5134a, 0);
            layoutParams3.gravity = 17;
            View linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            linearLayout.addView(imageView, layoutParams2);
            linearLayout.addView(textView, layoutParams3);
            linearLayout.setPadding(0, 0, 0, 0);
            imageView = new View(getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
            C2600x.m6680a(imageView, -10459280);
            addView(linearLayout, layoutParams);
            addView(imageView);
        }
        if (!TextUtils.isEmpty(this.f5137d.m4816c())) {
            CharSequence c = this.f5137d.m4816c();
            View imageView2 = new ImageView(getContext());
            imageView2.setColorFilter(-10459280);
            LayoutParams layoutParams4 = new LinearLayout.LayoutParams(f5135b, f5135b);
            layoutParams4.gravity = 16;
            imageView2.setImageBitmap(C2604c.m6697a(c2603b));
            TextView textView2 = new TextView(getContext());
            C2600x.m6687a(textView2, true, 14);
            textView2.setTextColor(-10459280);
            LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
            textView2.setText(c);
            textView2.setPadding(f5136c, 0, 0, 0);
            imageView = new LinearLayout(getContext());
            imageView.setOrientation(0);
            imageView.addView(imageView2, layoutParams4);
            imageView.addView(textView2, layoutParams5);
            imageView.setPadding(0, f5136c, 0, f5136c);
            addView(imageView, layoutParams);
        }
        imageView = m5690a();
        imageView.setPadding(0, f5136c, 0, 0);
        addView(imageView, layoutParams);
    }

    /* renamed from: a */
    private View m5690a() {
        View c2220l = new C2220l(getContext());
        for (final C1996c c1996c : this.f5137d.m4817d()) {
            final View c2197f = new C2197f(getContext());
            c2197f.m5676a(c1996c.m4815b(), null);
            c2197f.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C2213j f5133c;

                public void onClick(View view) {
                    c2197f.m5675a();
                    this.f5133c.f5138e.mo5537a(c1996c);
                }
            });
            c2220l.addView(c2197f);
        }
        return c2220l;
    }
}
