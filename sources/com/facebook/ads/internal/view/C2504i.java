package com.facebook.ads.internal.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1883m;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p057e.C2615g;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.CircularProgressView;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2373p;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.facebook.ads.internal.view.p061e.C2330c;

/* renamed from: com.facebook.ads.internal.view.i */
public class C2504i extends LinearLayout implements C2390b {
    /* renamed from: a */
    public static final int f6089a = ((int) (56.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final float f6090d = Resources.getSystem().getDisplayMetrics().density;
    /* renamed from: e */
    private static final int f6091e = ((int) (40.0f * f6090d));
    /* renamed from: f */
    private static final int f6092f = ((int) (44.0f * f6090d));
    /* renamed from: g */
    private static final int f6093g = ((int) (10.0f * f6090d));
    /* renamed from: h */
    private static final int f6094h = ((int) (16.0f * f6090d));
    /* renamed from: i */
    private static final int f6095i = (f6094h - f6093g);
    /* renamed from: j */
    private static final int f6096j = ((f6094h * 2) - f6093g);
    /* renamed from: b */
    private final C2373p f6097b = new C23741(this);
    /* renamed from: c */
    private final C1818d f6098c = new C23752(this);
    /* renamed from: k */
    private final C1789a f6099k;
    /* renamed from: l */
    private final ImageView f6100l;
    /* renamed from: m */
    private final FrameLayout f6101m;
    /* renamed from: n */
    private final ImageView f6102n;
    /* renamed from: o */
    private final CircularProgressView f6103o;
    /* renamed from: p */
    private final C2330c f6104p;
    /* renamed from: q */
    private final RelativeLayout f6105q;
    /* renamed from: r */
    private final PopupMenu f6106r;
    @Nullable
    /* renamed from: s */
    private ImageView f6107s;
    @Nullable
    /* renamed from: t */
    private C2282b f6108t;
    @Nullable
    /* renamed from: u */
    private C2394a f6109u;
    /* renamed from: v */
    private int f6110v = 0;
    /* renamed from: w */
    private boolean f6111w = false;
    /* renamed from: x */
    private boolean f6112x = false;
    /* renamed from: y */
    private OnDismissListener f6113y;

    /* renamed from: com.facebook.ads.internal.view.i$b */
    public interface C2282b {
        /* renamed from: a */
        void mo5562a();
    }

    /* renamed from: com.facebook.ads.internal.view.i$1 */
    class C23741 extends C2373p {
        /* renamed from: a */
        final /* synthetic */ C2504i f5747a;

        C23741(C2504i c2504i) {
            this.f5747a = c2504i;
        }

        /* renamed from: a */
        public void m6129a(C2413o c2413o) {
            if (this.f5747a.f6109u != null && this.f5747a.f6110v != 0 && this.f5747a.f6103o.isShown()) {
                float currentPositionInMillis = ((float) this.f5747a.f6109u.getCurrentPositionInMillis()) / Math.min(((float) this.f5747a.f6110v) * 1000.0f, (float) this.f5747a.f6109u.getDuration());
                this.f5747a.setProgress(100.0f * currentPositionInMillis);
                if (currentPositionInMillis >= 1.0f) {
                    this.f5747a.m6457a(true);
                    this.f5747a.f6109u.getEventBus().m5031b(this.f5747a.f6097b, this.f5747a.f6098c);
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i$2 */
    class C23752 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2504i f5748a;

        C23752(C2504i c2504i) {
            this.f5748a = c2504i;
        }

        /* renamed from: a */
        public void m6131a(C2406c c2406c) {
            if (this.f5748a.f6109u != null && this.f5748a.f6110v != 0 && this.f5748a.f6103o.isShown() && !this.f5748a.f6112x) {
                this.f5748a.m6457a(true);
                this.f5748a.f6109u.getEventBus().m5031b(this.f5748a.f6097b, this.f5748a.f6098c);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i$3 */
    class C23763 implements OnDismissListener {
        /* renamed from: a */
        final /* synthetic */ C2504i f5749a;

        C23763(C2504i c2504i) {
            this.f5749a = c2504i;
        }

        public void onDismiss(PopupMenu popupMenu) {
            this.f5749a.f6111w = false;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i$4 */
    class C23774 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2504i f5750a;

        C23774(C2504i c2504i) {
            this.f5750a = c2504i;
        }

        public void onClick(View view) {
            if (this.f5750a.f6108t != null && this.f5750a.f6112x) {
                this.f5750a.f6108t.mo5562a();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i$5 */
    class C23785 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2504i f5751a;

        C23785(C2504i c2504i) {
            this.f5751a = c2504i;
        }

        public void onClick(View view) {
            this.f5751a.f6106r.show();
            this.f5751a.f6111w = true;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i$a */
    public enum C2383a {
        CROSS,
        ARROWS,
        DOWN_ARROW
    }

    public C2504i(Context context, C1789a c1789a, C2383a c2383a) {
        super(context);
        this.f6099k = c1789a;
        setGravity(16);
        if (VERSION.SDK_INT >= 14) {
            this.f6113y = new C23763(this);
        }
        this.f6102n = new ImageView(context);
        this.f6102n.setPadding(f6093g, f6093g, f6093g, f6093g);
        this.f6102n.setScaleType(ScaleType.FIT_CENTER);
        this.f6102n.setOnClickListener(new C23774(this));
        setCloseButtonStyle(c2383a);
        this.f6103o = new CircularProgressView(context);
        this.f6103o.setPadding(f6093g, f6093g, f6093g, f6093g);
        this.f6103o.setProgress(0.0f);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(f6095i, f6095i, f6096j, f6095i);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(f6092f, f6092f);
        this.f6101m = new FrameLayout(context);
        this.f6101m.setLayoutTransition(new LayoutTransition());
        this.f6101m.addView(this.f6102n, layoutParams2);
        this.f6101m.addView(this.f6103o, layoutParams2);
        addView(this.f6101m, layoutParams);
        this.f6105q = new RelativeLayout(context);
        layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        this.f6104p = new C2330c(context);
        layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        this.f6104p.setLayoutParams(layoutParams2);
        this.f6105q.addView(this.f6104p);
        addView(this.f6105q, layoutParams);
        this.f6100l = new ImageView(context);
        this.f6100l.setPadding(f6093g, f6093g, f6093g, f6093g);
        this.f6100l.setScaleType(ScaleType.FIT_CENTER);
        this.f6100l.setImageBitmap(C2604c.m6697a(C2603b.AD_CHOICES_ICON));
        this.f6100l.setOnClickListener(new C23785(this));
        this.f6106r = new PopupMenu(context, this.f6100l);
        this.f6106r.getMenu().add("Ad Choices");
        layoutParams = new LinearLayout.LayoutParams(f6091e, f6091e);
        layoutParams.setMargins(0, f6094h / 2, f6094h / 2, f6094h / 2);
        addView(this.f6100l, layoutParams);
    }

    /* renamed from: a */
    public void m6453a(C1876h c1876h, boolean z) {
        int a = c1876h.m4286a(z);
        this.f6104p.m6013a(c1876h.m4293g(z), a);
        this.f6100l.setColorFilter(a);
        if (this.f6107s != null) {
            this.f6107s.setColorFilter(a);
        }
        this.f6102n.setColorFilter(a);
        this.f6103o.m5780a(ColorUtils.setAlphaComponent(a, 77), a);
        if (z) {
            Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1778384896, 0});
            gradientDrawable.setCornerRadius(0.0f);
            C2600x.m6681a((View) this, gradientDrawable);
            return;
        }
        C2600x.m6680a((View) this, 0);
    }

    /* renamed from: a */
    public void m6454a(final C1883m c1883m, final String str) {
        this.f6107s = new ImageView(getContext());
        this.f6107s.setPadding(f6093g, f6093g, f6093g, f6093g);
        this.f6107s.setScaleType(ScaleType.FIT_CENTER);
        this.f6107s.setImageBitmap(C2604c.m6697a(C2603b.INFO_ICON));
        this.f6107s.setColorFilter(-1);
        addView(this.f6107s, getChildCount() - 1, new LinearLayout.LayoutParams(f6091e, f6091e));
        this.f6107s.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2504i f5753b;

            public void onClick(View view) {
                this.f5753b.f6099k.mo5337a(str, true, null);
            }
        });
        this.f6100l.setOnClickListener(new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C2504i f5756c;

            public void onClick(View view) {
                Object m = !TextUtils.isEmpty(C1993a.m4800m(this.f5756c.getContext())) ? C1993a.m4800m(this.f5756c.getContext()) : c1883m.m4330c();
                if (!TextUtils.isEmpty(m)) {
                    C2615g.m6721a(new C2615g(), this.f5756c.getContext(), Uri.parse(m), str);
                }
            }
        });
    }

    /* renamed from: a */
    public void m6455a(final C1883m c1883m, final String str, int i) {
        this.f6110v = i;
        this.f6104p.setPageDetails(c1883m);
        this.f6106r.setOnMenuItemClickListener(new OnMenuItemClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C2504i f5759c;

            public boolean onMenuItemClick(MenuItem menuItem) {
                this.f5759c.f6111w = false;
                if (!TextUtils.isEmpty(c1883m.m4330c())) {
                    C2615g.m6721a(new C2615g(), this.f5759c.getContext(), Uri.parse(c1883m.m4330c()), str);
                }
                return true;
            }
        });
        if (VERSION.SDK_INT >= 14) {
            this.f6106r.setOnDismissListener(this.f6113y);
        }
        m6457a(i <= 0);
    }

    /* renamed from: a */
    public void mo5597a(C2394a c2394a) {
        this.f6109u = c2394a;
        this.f6109u.getEventBus().m5029a(this.f6097b, this.f6098c);
    }

    /* renamed from: a */
    public void m6457a(boolean z) {
        int i = 8;
        this.f6112x = z;
        this.f6101m.setVisibility(0);
        this.f6103o.setVisibility(z ? 8 : 0);
        ImageView imageView = this.f6102n;
        if (z) {
            i = 0;
        }
        imageView.setVisibility(i);
        ((LinearLayout.LayoutParams) this.f6105q.getLayoutParams()).leftMargin = 0;
    }

    /* renamed from: a */
    public boolean m6458a() {
        return this.f6112x;
    }

    /* renamed from: b */
    public void m6459b() {
        this.f6112x = false;
        this.f6101m.setVisibility(8);
        this.f6103o.setVisibility(8);
        this.f6102n.setVisibility(8);
        ((LinearLayout.LayoutParams) this.f6105q.getLayoutParams()).leftMargin = f6093g;
    }

    /* renamed from: b */
    public void mo5598b(C2394a c2394a) {
        if (this.f6109u != null) {
            this.f6109u.getEventBus().m5031b(this.f6097b, this.f6098c);
            this.f6109u = null;
        }
    }

    /* renamed from: b */
    public void m6461b(boolean z) {
        this.f6100l.setVisibility(z ? 0 : 8);
    }

    /* renamed from: c */
    public void m6462c() {
        C2600x.m6689b(this.f6104p);
    }

    /* renamed from: d */
    public void m6463d() {
        if (VERSION.SDK_INT >= 14) {
            this.f6106r.setOnDismissListener(null);
        }
        this.f6106r.dismiss();
        if (VERSION.SDK_INT >= 14) {
            this.f6106r.setOnDismissListener(this.f6113y);
        }
    }

    /* renamed from: e */
    public void m6464e() {
        if (this.f6111w && VERSION.SDK_INT >= 14) {
            this.f6106r.show();
        }
    }

    public void setCloseButtonStyle(C2383a c2383a) {
        if (this.f6102n != null) {
            C2603b c2603b;
            switch (c2383a) {
                case ARROWS:
                    c2603b = C2603b.SKIP_ARROW;
                    break;
                case DOWN_ARROW:
                    c2603b = C2603b.MINIMIZE_ARROW;
                    break;
                default:
                    c2603b = C2603b.CROSS;
                    break;
            }
            this.f6102n.setImageBitmap(C2604c.m6697a(c2603b));
        }
    }

    public void setPageDetailsVisibility(int i) {
        this.f6105q.setVisibility(i);
    }

    public void setProgress(float f) {
        this.f6103o.setProgressWithAnimation(f);
    }

    public void setShowPageDetails(boolean z) {
        this.f6105q.removeAllViews();
        if (z) {
            this.f6105q.addView(this.f6104p);
        }
    }

    public void setToolbarListener(C2282b c2282b) {
        this.f6108t = c2282b;
    }
}
