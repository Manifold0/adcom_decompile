// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.graphics.drawable.Drawable;
import com.google.android.gms.common.util.DeviceProperties;
import android.text.method.TransformationMethod;
import com.google.android.gms.base.R$string;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import com.google.android.gms.base.R$color;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.google.android.gms.base.R$drawable;
import android.graphics.Typeface;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Button;

public final class SignInButtonImpl extends Button
{
    public SignInButtonImpl(final Context context) {
        this(context, null);
    }
    
    public SignInButtonImpl(final Context context, final AttributeSet set) {
        super(context, set, 16842824);
    }
    
    private static int zaa(final int n, int n2, final int n3, final int n4) {
        switch (n) {
            default: {
                throw new IllegalStateException(new StringBuilder(33).append("Unknown color scheme: ").append(n).toString());
            }
            case 1: {
                n2 = n3;
            }
            case 0: {
                return n2;
            }
            case 2: {
                return n4;
            }
        }
    }
    
    public final void configure(final Resources resources, final int n, final int n2) {
        this.setTypeface(Typeface.DEFAULT_BOLD);
        this.setTextSize(14.0f);
        final float density = resources.getDisplayMetrics().density;
        this.setMinHeight((int)(density * 48.0f + 0.5f));
        this.setMinWidth((int)(density * 48.0f + 0.5f));
        final int zaa = zaa(n2, R$drawable.common_google_signin_btn_icon_dark, R$drawable.common_google_signin_btn_icon_light, R$drawable.common_google_signin_btn_icon_light);
        int zaa2 = zaa(n2, R$drawable.common_google_signin_btn_text_dark, R$drawable.common_google_signin_btn_text_light, R$drawable.common_google_signin_btn_text_light);
        switch (n) {
            default: {
                throw new IllegalStateException(new StringBuilder(32).append("Unknown button size: ").append(n).toString());
            }
            case 2: {
                zaa2 = zaa;
            }
            case 0:
            case 1: {
                final Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zaa2));
                DrawableCompat.setTintList(wrap, resources.getColorStateList(R$color.common_google_signin_btn_tint));
                DrawableCompat.setTintMode(wrap, PorterDuff$Mode.SRC_ATOP);
                this.setBackgroundDrawable(wrap);
                this.setTextColor((ColorStateList)Preconditions.checkNotNull((Object)resources.getColorStateList(zaa(n2, R$color.common_google_signin_btn_text_dark, R$color.common_google_signin_btn_text_light, R$color.common_google_signin_btn_text_light))));
                switch (n) {
                    default: {
                        throw new IllegalStateException(new StringBuilder(32).append("Unknown button size: ").append(n).toString());
                    }
                    case 0: {
                        this.setText((CharSequence)resources.getString(R$string.common_signin_button_text));
                        break;
                    }
                    case 1: {
                        this.setText((CharSequence)resources.getString(R$string.common_signin_button_text_long));
                        break;
                    }
                    case 2: {
                        this.setText((CharSequence)null);
                        break;
                    }
                }
                this.setTransformationMethod((TransformationMethod)null);
                if (DeviceProperties.isWearable(this.getContext())) {
                    this.setGravity(19);
                }
            }
        }
    }
    
    public final void configure(final Resources resources, final SignInButtonConfig signInButtonConfig) {
        this.configure(resources, signInButtonConfig.getButtonSize(), signInButtonConfig.getColorScheme());
    }
}
