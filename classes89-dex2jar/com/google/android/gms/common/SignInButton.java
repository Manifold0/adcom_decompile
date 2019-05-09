// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.content.res.TypedArray;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import com.google.android.gms.common.internal.SignInButtonImpl;
import android.util.Log;
import com.google.android.gms.common.internal.SignInButtonCreator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.base.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public final class SignInButton extends FrameLayout implements View$OnClickListener
{
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zaas;
    private View$OnClickListener zaat;
    
    public SignInButton(final Context context) {
        this(context, null);
    }
    
    public SignInButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SignInButton(Context obtainStyledAttributes, final AttributeSet set, final int n) {
        super(obtainStyledAttributes, set, n);
        this.zaat = null;
        obtainStyledAttributes = (Context)obtainStyledAttributes.getTheme().obtainStyledAttributes(set, R$styleable.SignInButton, 0, 0);
        try {
            this.mSize = ((TypedArray)obtainStyledAttributes).getInt(R$styleable.SignInButton_buttonSize, 0);
            this.mColor = ((TypedArray)obtainStyledAttributes).getInt(R$styleable.SignInButton_colorScheme, 2);
            ((TypedArray)obtainStyledAttributes).recycle();
            this.setStyle(this.mSize, this.mColor);
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    public final void onClick(final View view) {
        if (this.zaat != null && view == this.zaas) {
            this.zaat.onClick((View)this);
        }
    }
    
    public final void setColorScheme(final int n) {
        this.setStyle(this.mSize, n);
    }
    
    public final void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.zaas.setEnabled(b);
    }
    
    public final void setOnClickListener(final View$OnClickListener zaat) {
        this.zaat = zaat;
        if (this.zaas != null) {
            this.zaas.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    @Deprecated
    public final void setScopes(final Scope[] array) {
        this.setStyle(this.mSize, this.mColor);
    }
    
    public final void setSize(final int n) {
        this.setStyle(n, this.mColor);
    }
    
    public final void setStyle(int mSize, int mColor) {
        this.mSize = mSize;
        this.mColor = mColor;
        final Context context = this.getContext();
        if (this.zaas != null) {
            this.removeView(this.zaas);
        }
        while (true) {
            try {
                this.zaas = SignInButtonCreator.createView(context, this.mSize, this.mColor);
                this.addView(this.zaas);
                this.zaas.setEnabled(this.isEnabled());
                this.zaas.setOnClickListener((View$OnClickListener)this);
            }
            catch (RemoteCreator$RemoteCreatorException ex) {
                Log.w("SignInButton", "Sign in button not found, using placeholder instead");
                mSize = this.mSize;
                mColor = this.mColor;
                final SignInButtonImpl zaas = new SignInButtonImpl(context);
                zaas.configure(context.getResources(), mSize, mColor);
                this.zaas = (View)zaas;
                continue;
            }
            break;
        }
    }
    
    @Deprecated
    public final void setStyle(final int n, final int n2, final Scope[] array) {
        this.setStyle(n, n2);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }
}
