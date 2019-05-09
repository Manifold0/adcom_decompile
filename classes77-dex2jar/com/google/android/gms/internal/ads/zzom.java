// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.widget.ImageView;
import android.graphics.Typeface;
import android.widget.TextView;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.gms.ads.internal.zzbv;
import android.view.ViewGroup$LayoutParams;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import com.google.android.gms.common.internal.Preconditions;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import android.support.annotation.Nullable;
import android.graphics.drawable.AnimationDrawable;
import javax.annotation.ParametersAreNonnullByDefault;
import android.widget.RelativeLayout;

@zzadh
@ParametersAreNonnullByDefault
public final class zzom extends RelativeLayout
{
    private static final float[] zzbhs;
    @Nullable
    private AnimationDrawable zzbht;
    
    static {
        zzbhs = new float[] { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f };
    }
    
    public zzom(Context context, final zzoj zzoj, RelativeLayout$LayoutParams iterator) {
        super(context);
        Preconditions.checkNotNull((Object)zzoj);
        final ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new RoundRectShape(zzom.zzbhs, (RectF)null, (float[])null));
        shapeDrawable.getPaint().setColor(zzoj.getBackgroundColor());
        this.setLayoutParams((ViewGroup$LayoutParams)iterator);
        zzbv.zzem().setBackground((View)this, (Drawable)shapeDrawable);
        iterator = new RelativeLayout$LayoutParams(-2, -2);
        if (!TextUtils.isEmpty((CharSequence)zzoj.getText())) {
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
            final TextView textView = new TextView(context);
            textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText((CharSequence)zzoj.getText());
            textView.setTextColor(zzoj.getTextColor());
            textView.setTextSize((float)zzoj.getTextSize());
            zzkb.zzif();
            final int zza = zzamu.zza(context, 4);
            zzkb.zzif();
            textView.setPadding(zza, 0, zzamu.zza(context, 4), 0);
            this.addView((View)textView);
            ((RelativeLayout$LayoutParams)iterator).addRule(1, textView.getId());
        }
        context = (Context)new ImageView(context);
        ((ImageView)context).setLayoutParams((ViewGroup$LayoutParams)iterator);
        ((ImageView)context).setId(1195835394);
        final List zzjs = zzoj.zzjs();
        if (zzjs != null && zzjs.size() > 1) {
            this.zzbht = new AnimationDrawable();
            iterator = zzjs.iterator();
            while (((Iterator)iterator).hasNext()) {
                final zzon zzon = ((Iterator<zzon>)iterator).next();
                try {
                    this.zzbht.addFrame((Drawable)ObjectWrapper.unwrap(zzon.zzjy()), zzoj.zzjt());
                }
                catch (Exception ex) {
                    zzakb.zzb("Error while getting drawable.", (Throwable)ex);
                }
            }
            zzbv.zzem().setBackground((View)context, (Drawable)this.zzbht);
        }
        else if (zzjs.size() == 1) {
            try {
                ((ImageView)context).setImageDrawable((Drawable)ObjectWrapper.unwrap(zzjs.get(0).zzjy()));
            }
            catch (Exception ex2) {
                zzakb.zzb("Error while getting drawable.", (Throwable)ex2);
            }
        }
        this.addView((View)context);
    }
    
    public final void onAttachedToWindow() {
        if (this.zzbht != null) {
            this.zzbht.start();
        }
        super.onAttachedToWindow();
    }
}
