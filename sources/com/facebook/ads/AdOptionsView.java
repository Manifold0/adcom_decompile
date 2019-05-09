package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;

public class AdOptionsView extends LinearLayout {
    /* renamed from: a */
    private static final int f3676a = ((int) (C2600x.f6420b * 23.0f));
    /* renamed from: b */
    private static final int f3677b = ((int) (C2600x.f6420b * 4.0f));
    /* renamed from: c */
    private final ImageView f3678c;
    /* renamed from: d */
    private final ImageView f3679d;

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public AdOptionsView(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout) {
        this(context, nativeAdBase, nativeAdLayout, Orientation.HORIZONTAL, 23);
    }

    public AdOptionsView(Context context, final NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout, Orientation orientation, int i) {
        super(context);
        this.f3678c = new ImageView(context);
        this.f3678c.setScaleType(ScaleType.FIT_CENTER);
        this.f3678c.setPadding(f3677b, f3677b, f3677b, f3677b);
        this.f3678c.setImageBitmap(C2604c.m6697a(C2603b.INFO_ICON));
        this.f3679d = new ImageView(context);
        this.f3679d.setScaleType(ScaleType.FIT_CENTER);
        this.f3679d.setPadding(f3677b, f3677b, f3677b, f3677b);
        this.f3679d.setImageBitmap(C2604c.m6697a(C2603b.AD_CHOICES_ICON));
        setOrientation(orientation == Orientation.HORIZONTAL ? 0 : 1);
        setIconColor(-10459280);
        int max = Math.max(f3676a, (int) (C2600x.f6420b * ((float) i)));
        LayoutParams layoutParams = new LinearLayout.LayoutParams(max, max);
        addView(this.f3678c, layoutParams);
        addView(this.f3679d, layoutParams);
        nativeAdBase.m4078f().m5313a(nativeAdLayout);
        if (!nativeAdBase.isAdLoaded() || nativeAdBase.m4079g().m4507g()) {
            setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ AdOptionsView f3674b;

                public void onClick(View view) {
                    nativeAdBase.m4078f().m5349y();
                }
            });
            C2580j.m6643a(this, C2580j.INTERNAL_AD_OPTIONS_VIEW);
            return;
        }
        setVisibility(8);
    }

    public void setIconColor(int i) {
        this.f3678c.setColorFilter(i);
        this.f3679d.setColorFilter(i);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        layoutParams.width = -2;
        layoutParams.height = -2;
        super.setLayoutParams(layoutParams);
    }
}
