// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzkb;
import javax.annotation.Nullable;
import android.content.Context;
import android.widget.ImageButton;
import com.google.android.gms.internal.ads.zzadh;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

@zzadh
public final class zzo extends FrameLayout implements View$OnClickListener
{
    private final ImageButton zzbyy;
    private final zzw zzbyz;
    
    public zzo(final Context context, final zzp zzp, @Nullable final zzw zzbyz) {
        super(context);
        this.zzbyz = zzbyz;
        this.setOnClickListener((View$OnClickListener)this);
        (this.zzbyy = new ImageButton(context)).setImageResource(17301527);
        this.zzbyy.setBackgroundColor(0);
        this.zzbyy.setOnClickListener((View$OnClickListener)this);
        final ImageButton zzbyy = this.zzbyy;
        zzkb.zzif();
        final int zza = zzamu.zza(context, zzp.paddingLeft);
        zzkb.zzif();
        final int zza2 = zzamu.zza(context, 0);
        zzkb.zzif();
        final int zza3 = zzamu.zza(context, zzp.paddingRight);
        zzkb.zzif();
        zzbyy.setPadding(zza, zza2, zza3, zzamu.zza(context, zzp.paddingBottom));
        this.zzbyy.setContentDescription((CharSequence)"Interstitial close button");
        zzkb.zzif();
        zzamu.zza(context, zzp.size);
        final ImageButton zzbyy2 = this.zzbyy;
        zzkb.zzif();
        final int zza4 = zzamu.zza(context, zzp.size + zzp.paddingLeft + zzp.paddingRight);
        zzkb.zzif();
        this.addView((View)zzbyy2, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(zza4, zzamu.zza(context, zzp.size + zzp.paddingBottom), 17));
    }
    
    public final void onClick(final View view) {
        if (this.zzbyz != null) {
            this.zzbyz.zzni();
        }
    }
    
    public final void zzu(final boolean b) {
        if (b) {
            this.zzbyy.setVisibility(8);
            return;
        }
        this.zzbyy.setVisibility(0);
    }
}
