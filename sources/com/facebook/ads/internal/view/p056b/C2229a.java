package com.facebook.ads.internal.view.p056b;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.google.android.gms.drive.DriveFile;
import java.util.List;

@TargetApi(19)
/* renamed from: com.facebook.ads.internal.view.b.a */
public class C2229a extends LinearLayout {
    /* renamed from: a */
    private static final int f5162a = Color.rgb(224, 224, 224);
    /* renamed from: b */
    private static final Uri f5163b = Uri.parse("http://www.facebook.com");
    /* renamed from: c */
    private static final OnTouchListener f5164c = new C22261();
    /* renamed from: d */
    private static final int f5165d = Color.argb(34, 0, 0, 0);
    /* renamed from: e */
    private ImageView f5166e;
    /* renamed from: f */
    private C2235e f5167f;
    /* renamed from: g */
    private ImageView f5168g;
    /* renamed from: h */
    private C2222a f5169h;
    /* renamed from: i */
    private String f5170i;

    /* renamed from: com.facebook.ads.internal.view.b.a$a */
    public interface C2222a {
        /* renamed from: a */
        void mo5547a();
    }

    /* renamed from: com.facebook.ads.internal.view.b.a$1 */
    static class C22261 implements OnTouchListener {
        C22261() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    C2600x.m6680a(view, C2229a.f5165d);
                    break;
                case 1:
                    C2600x.m6680a(view, 0);
                    break;
            }
            return false;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.b.a$2 */
    class C22272 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2229a f5160a;

        C22272(C2229a c2229a) {
            this.f5160a = c2229a;
        }

        public void onClick(View view) {
            if (this.f5160a.f5169h != null) {
                this.f5160a.f5169h.mo5547a();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.b.a$3 */
    class C22283 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2229a f5161a;

        C22283(C2229a c2229a) {
            this.f5161a = c2229a;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.f5161a.f5170i) && !"about:blank".equals(this.f5161a.f5170i)) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.f5161a.f5170i));
                intent.addFlags(DriveFile.MODE_READ_ONLY);
                this.f5161a.getContext().startActivity(intent);
            }
        }
    }

    public C2229a(Context context) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        int i = (int) (50.0f * f);
        int i2 = (int) (f * 4.0f);
        C2600x.m6680a((View) this, -1);
        setGravity(16);
        this.f5166e = new ImageView(context);
        this.f5166e.setContentDescription("Close");
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        this.f5166e.setScaleType(ScaleType.CENTER);
        this.f5166e.setImageBitmap(C2604c.m6697a(C2603b.BROWSER_CLOSE));
        this.f5166e.setOnTouchListener(f5164c);
        this.f5166e.setOnClickListener(new C22272(this));
        addView(this.f5166e, layoutParams);
        this.f5167f = new C2235e(context);
        layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        this.f5167f.setPadding(0, i2, 0, i2);
        addView(this.f5167f, layoutParams);
        this.f5168g = new ImageView(context);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i, i);
        this.f5168g.setContentDescription("Open native browser");
        this.f5168g.setScaleType(ScaleType.CENTER);
        this.f5168g.setOnTouchListener(f5164c);
        this.f5168g.setOnClickListener(new C22283(this));
        addView(this.f5168g, layoutParams2);
        setupDefaultNativeBrowser(context);
    }

    private void setupDefaultNativeBrowser(Context context) {
        Bitmap bitmap;
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", f5163b), 65536);
        if (queryIntentActivities.size() == 0) {
            this.f5168g.setVisibility(8);
            bitmap = null;
        } else {
            bitmap = (queryIntentActivities.size() == 1 && "com.android.chrome".equals(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName)) ? C2604c.m6697a(C2603b.BROWSER_LAUNCH_CHROME) : C2604c.m6697a(C2603b.BROWSER_LAUNCH_NATIVE);
        }
        this.f5168g.setImageBitmap(bitmap);
    }

    public void setListener(C2222a c2222a) {
        this.f5169h = c2222a;
    }

    public void setTitle(String str) {
        this.f5167f.setTitle(str);
    }

    public void setUrl(String str) {
        this.f5170i = str;
        if (TextUtils.isEmpty(str) || "about:blank".equals(str)) {
            this.f5167f.setSubtitle(null);
            this.f5168g.setEnabled(false);
            this.f5168g.setColorFilter(new PorterDuffColorFilter(f5162a, Mode.SRC_IN));
            return;
        }
        this.f5167f.setSubtitle(str);
        this.f5168g.setEnabled(true);
        this.f5168g.setColorFilter(null);
    }
}
