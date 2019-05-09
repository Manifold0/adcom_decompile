// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.content.ContextWrapper;
import android.app.Activity;
import android.view.View;
import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import com.facebook.common.R$color;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.common.R$style;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.internal.FragmentWrapper;
import android.view.View$OnClickListener;
import android.widget.Button;

public abstract class FacebookButtonBase extends Button
{
    private String analyticsButtonCreatedEventName;
    private String analyticsButtonTappedEventName;
    private View$OnClickListener externalOnClickListener;
    private View$OnClickListener internalOnClickListener;
    private boolean overrideCompoundPadding;
    private int overrideCompoundPaddingLeft;
    private int overrideCompoundPaddingRight;
    private FragmentWrapper parentFragment;
    
    protected FacebookButtonBase(final Context context, final AttributeSet set, final int n, int com_facebook_button, final String analyticsButtonCreatedEventName, final String analyticsButtonTappedEventName) {
        super(context, set, 0);
        int defaultStyleResource = com_facebook_button;
        if (com_facebook_button == 0) {
            defaultStyleResource = this.getDefaultStyleResource();
        }
        if ((com_facebook_button = defaultStyleResource) == 0) {
            com_facebook_button = R$style.com_facebook_button;
        }
        this.configureButton(context, set, n, com_facebook_button);
        this.analyticsButtonCreatedEventName = analyticsButtonCreatedEventName;
        this.analyticsButtonTappedEventName = analyticsButtonTappedEventName;
        this.setClickable(true);
        this.setFocusable(true);
    }
    
    private void logButtonCreated(final Context context) {
        AppEventsLogger.newLogger(context).logSdkEvent(this.analyticsButtonCreatedEventName, (Double)null, (Bundle)null);
    }
    
    private void logButtonTapped(final Context context) {
        AppEventsLogger.newLogger(context).logSdkEvent(this.analyticsButtonTappedEventName, (Double)null, (Bundle)null);
    }
    
    private void parseBackgroundAttributes(final Context context, AttributeSet obtainStyledAttributes, int resourceId, final int n) {
        if (this.isInEditMode()) {
            return;
        }
        while (true) {
            obtainStyledAttributes = (AttributeSet)context.getTheme().obtainStyledAttributes(obtainStyledAttributes, new int[] { 16842964 }, resourceId, n);
            try {
                if (((TypedArray)obtainStyledAttributes).hasValue(0)) {
                    resourceId = ((TypedArray)obtainStyledAttributes).getResourceId(0, 0);
                    if (resourceId != 0) {
                        this.setBackgroundResource(resourceId);
                    }
                    else {
                        this.setBackgroundColor(((TypedArray)obtainStyledAttributes).getColor(0, 0));
                    }
                    return;
                }
            }
            finally {
                ((TypedArray)obtainStyledAttributes).recycle();
            }
            final Context context2;
            this.setBackgroundColor(ContextCompat.getColor(context2, R$color.com_facebook_blue));
        }
    }
    
    @SuppressLint({ "ResourceType" })
    private void parseCompoundDrawableAttributes(Context obtainStyledAttributes, final AttributeSet set, final int n, final int n2) {
        obtainStyledAttributes = (Context)obtainStyledAttributes.getTheme().obtainStyledAttributes(set, new int[] { 16843119, 16843117, 16843120, 16843118, 16843121 }, n, n2);
        try {
            this.setCompoundDrawablesWithIntrinsicBounds(((TypedArray)obtainStyledAttributes).getResourceId(0, 0), ((TypedArray)obtainStyledAttributes).getResourceId(1, 0), ((TypedArray)obtainStyledAttributes).getResourceId(2, 0), ((TypedArray)obtainStyledAttributes).getResourceId(3, 0));
            this.setCompoundDrawablePadding(((TypedArray)obtainStyledAttributes).getDimensionPixelSize(4, 0));
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    private void parseContentAttributes(Context obtainStyledAttributes, final AttributeSet set, final int n, final int n2) {
        obtainStyledAttributes = (Context)obtainStyledAttributes.getTheme().obtainStyledAttributes(set, new int[] { 16842966, 16842967, 16842968, 16842969 }, n, n2);
        try {
            this.setPadding(((TypedArray)obtainStyledAttributes).getDimensionPixelSize(0, 0), ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(1, 0), ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(2, 0), ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(3, 0));
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    private void parseTextAttributes(final Context p0, final AttributeSet p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/content/Context.getTheme:()Landroid/content/res/Resources$Theme;
        //     4: aload_2        
        //     5: iconst_1       
        //     6: newarray        I
        //     8: dup            
        //     9: iconst_0       
        //    10: ldc             16842904
        //    12: iastore        
        //    13: iload_3        
        //    14: iload           4
        //    16: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //    19: astore          5
        //    21: aload_0        
        //    22: aload           5
        //    24: iconst_0       
        //    25: invokevirtual   android/content/res/TypedArray.getColorStateList:(I)Landroid/content/res/ColorStateList;
        //    28: invokevirtual   com/facebook/FacebookButtonBase.setTextColor:(Landroid/content/res/ColorStateList;)V
        //    31: aload           5
        //    33: invokevirtual   android/content/res/TypedArray.recycle:()V
        //    36: aload_1        
        //    37: invokevirtual   android/content/Context.getTheme:()Landroid/content/res/Resources$Theme;
        //    40: aload_2        
        //    41: iconst_1       
        //    42: newarray        I
        //    44: dup            
        //    45: iconst_0       
        //    46: ldc             16842927
        //    48: iastore        
        //    49: iload_3        
        //    50: iload           4
        //    52: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //    55: astore          5
        //    57: aload_0        
        //    58: aload           5
        //    60: iconst_0       
        //    61: bipush          17
        //    63: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //    66: invokevirtual   com/facebook/FacebookButtonBase.setGravity:(I)V
        //    69: aload           5
        //    71: invokevirtual   android/content/res/TypedArray.recycle:()V
        //    74: aload_1        
        //    75: invokevirtual   android/content/Context.getTheme:()Landroid/content/res/Resources$Theme;
        //    78: aload_2        
        //    79: iconst_3       
        //    80: newarray        I
        //    82: dup            
        //    83: iconst_0       
        //    84: ldc             16842901
        //    86: iastore        
        //    87: dup            
        //    88: iconst_1       
        //    89: ldc             16842903
        //    91: iastore        
        //    92: dup            
        //    93: iconst_2       
        //    94: ldc             16843087
        //    96: iastore        
        //    97: iload_3        
        //    98: iload           4
        //   100: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //   103: astore_1       
        //   104: aload_0        
        //   105: iconst_0       
        //   106: aload_1        
        //   107: iconst_0       
        //   108: iconst_0       
        //   109: invokevirtual   android/content/res/TypedArray.getDimensionPixelSize:(II)I
        //   112: i2f            
        //   113: invokevirtual   com/facebook/FacebookButtonBase.setTextSize:(IF)V
        //   116: aload_0        
        //   117: aload_1        
        //   118: iconst_1       
        //   119: iconst_1       
        //   120: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //   123: invokestatic    android/graphics/Typeface.defaultFromStyle:(I)Landroid/graphics/Typeface;
        //   126: invokevirtual   com/facebook/FacebookButtonBase.setTypeface:(Landroid/graphics/Typeface;)V
        //   129: aload_0        
        //   130: aload_1        
        //   131: iconst_2       
        //   132: invokevirtual   android/content/res/TypedArray.getString:(I)Ljava/lang/String;
        //   135: invokevirtual   com/facebook/FacebookButtonBase.setText:(Ljava/lang/CharSequence;)V
        //   138: aload_1        
        //   139: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   142: return         
        //   143: astore_1       
        //   144: aload           5
        //   146: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   149: aload_1        
        //   150: athrow         
        //   151: astore_1       
        //   152: aload           5
        //   154: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   157: aload_1        
        //   158: athrow         
        //   159: astore_2       
        //   160: aload_1        
        //   161: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   164: aload_2        
        //   165: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  21     31     143    151    Any
        //  57     69     151    159    Any
        //  104    138    159    166    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 100, Size: 100
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void setupOnClickListener() {
        super.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                FacebookButtonBase.this.logButtonTapped(FacebookButtonBase.this.getContext());
                if (FacebookButtonBase.this.internalOnClickListener != null) {
                    FacebookButtonBase.this.internalOnClickListener.onClick(view);
                }
                else if (FacebookButtonBase.this.externalOnClickListener != null) {
                    FacebookButtonBase.this.externalOnClickListener.onClick(view);
                }
            }
        });
    }
    
    protected void callExternalOnClickListener(final View view) {
        if (this.externalOnClickListener != null) {
            this.externalOnClickListener.onClick(view);
        }
    }
    
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        this.parseBackgroundAttributes(context, set, n, n2);
        this.parseCompoundDrawableAttributes(context, set, n, n2);
        this.parseContentAttributes(context, set, n, n2);
        this.parseTextAttributes(context, set, n, n2);
        this.setupOnClickListener();
    }
    
    protected Activity getActivity() {
        Context context;
        for (context = this.getContext(); !(context instanceof Activity) && context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {}
        if (context instanceof Activity) {
            return (Activity)context;
        }
        throw new FacebookException("Unable to get Activity.");
    }
    
    public int getCompoundPaddingLeft() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingLeft;
        }
        return super.getCompoundPaddingLeft();
    }
    
    public int getCompoundPaddingRight() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingRight;
        }
        return super.getCompoundPaddingRight();
    }
    
    protected abstract int getDefaultRequestCode();
    
    protected int getDefaultStyleResource() {
        return 0;
    }
    
    public Fragment getFragment() {
        if (this.parentFragment != null) {
            return this.parentFragment.getSupportFragment();
        }
        return null;
    }
    
    public android.app.Fragment getNativeFragment() {
        if (this.parentFragment != null) {
            return this.parentFragment.getNativeFragment();
        }
        return null;
    }
    
    public int getRequestCode() {
        return this.getDefaultRequestCode();
    }
    
    protected int measureTextWidth(final String s) {
        return (int)Math.ceil(this.getPaint().measureText(s));
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isInEditMode()) {
            this.logButtonCreated(this.getContext());
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        int n;
        if ((this.getGravity() & 0x1) != 0x0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            final int compoundPaddingLeft = this.getCompoundPaddingLeft();
            final int compoundPaddingRight = this.getCompoundPaddingRight();
            final int min = Math.min((this.getWidth() - (compoundPaddingLeft + this.getCompoundDrawablePadding()) - compoundPaddingRight - this.measureTextWidth(this.getText().toString())) / 2, (compoundPaddingLeft - this.getPaddingLeft()) / 2);
            this.overrideCompoundPaddingLeft = compoundPaddingLeft - min;
            this.overrideCompoundPaddingRight = compoundPaddingRight + min;
            this.overrideCompoundPadding = true;
        }
        super.onDraw(canvas);
        this.overrideCompoundPadding = false;
    }
    
    public void setFragment(final android.app.Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }
    
    public void setFragment(final Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }
    
    protected void setInternalOnClickListener(final View$OnClickListener internalOnClickListener) {
        this.internalOnClickListener = internalOnClickListener;
    }
    
    public void setOnClickListener(final View$OnClickListener externalOnClickListener) {
        this.externalOnClickListener = externalOnClickListener;
    }
}
