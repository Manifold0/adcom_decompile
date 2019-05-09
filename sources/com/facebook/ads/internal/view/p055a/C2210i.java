package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;

/* renamed from: com.facebook.ads.internal.view.a.i */
public class C2210i extends LinearLayout {
    /* renamed from: a */
    public static final LayoutParams f5123a = new LayoutParams(-1, -2);
    /* renamed from: b */
    private static final int f5124b = ((int) (8.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f5125c = ((int) (14.5d * ((double) C2600x.f6420b)));
    /* renamed from: d */
    private static final int f5126d = ((int) (20.0f * C2600x.f6420b));
    /* renamed from: e */
    private final LinearLayout f5127e;
    /* renamed from: f */
    private final ImageView f5128f;
    /* renamed from: g */
    private final TextView f5129g;

    public C2210i(Context context) {
        super(context);
        this.f5128f = new ImageView(context);
        this.f5128f.setColorFilter(-10459280);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(f5126d, f5126d);
        layoutParams.gravity = 16;
        this.f5128f.setLayoutParams(layoutParams);
        this.f5127e = new LinearLayout(context);
        this.f5127e.setOrientation(1);
        this.f5127e.setPadding(f5124b * 2, 0, 0, 0);
        this.f5127e.setLayoutParams(f5123a);
        this.f5129g = new TextView(context);
        C2600x.m6687a(this.f5129g, true, 16);
        this.f5129g.setTextColor(-14934495);
        this.f5127e.addView(this.f5129g, f5123a);
        setOrientation(0);
        addView(this.f5128f);
        addView(this.f5127e);
    }

    /* renamed from: a */
    public void m5689a(C2603b c2603b, String str, String str2) {
        this.f5128f.setImageBitmap(C2604c.m6697a(c2603b));
        this.f5129g.setText(str);
        if (TextUtils.isEmpty(str2)) {
            setPadding(0, f5125c, 0, f5125c);
            return;
        }
        TextView textView = new TextView(getContext());
        C2600x.m6687a(textView, false, 14);
        textView.setTextColor(-10459280);
        textView.setText(str2);
        this.f5127e.addView(textView, f5123a);
        setPadding(0, f5124b, 0, f5124b);
    }
}
