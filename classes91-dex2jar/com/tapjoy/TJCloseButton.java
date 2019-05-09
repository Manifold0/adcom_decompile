// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.widget.RelativeLayout$LayoutParams;
import android.annotation.TargetApi;
import android.annotation.SuppressLint;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.os.Handler;
import android.os.Build$VERSION;
import android.graphics.Bitmap;
import android.view.ViewGroup$LayoutParams;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.widget.ImageButton;

public class TJCloseButton extends ImageButton
{
    private static final String a;
    private ClosePosition b;
    private boolean c;
    private boolean d;
    
    static {
        a = TJCloseButton.class.getSimpleName();
    }
    
    public TJCloseButton(final Context context) {
        this(context, ClosePosition.TOP_RIGHT);
    }
    
    public TJCloseButton(final Context context, final ClosePosition b) {
        super(context);
        this.c = true;
        this.b = b;
        Bitmap imageBitmap;
        final Bitmap bitmap = imageBitmap = TapjoyUtil.loadBitmapFromJar("tj_close_button.png", context);
        while (true) {
            if (bitmap != null) {
                break Label_0056;
            }
            try {
                imageBitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("tj_close_button", "drawable", context.getPackageName()));
                this.setImageBitmap(imageBitmap);
                this.setBackgroundColor(16777215);
                this.setLayoutParams((ViewGroup$LayoutParams)this.b.a);
            }
            catch (Exception ex) {
                TapjoyLog.w(TJCloseButton.a, "Could not find close button asset");
                imageBitmap = bitmap;
                continue;
            }
            break;
        }
    }
    
    @TargetApi(11)
    protected void onAttachedToWindow() {
        if (Build$VERSION.SDK_INT >= 12) {
            this.setAlpha(0.0f);
            this.setVisibility(0);
            this.d = true;
            this.setClickable(false);
            new Handler().postDelayed((Runnable)new Runnable() {
                @SuppressLint({ "NewApi" })
                @Override
                public final void run() {
                    TJCloseButton.this.animate().alpha(1.0f).setDuration(500L).setListener((Animator$AnimatorListener)new Animator$AnimatorListener() {
                        public final void onAnimationCancel(final Animator animator) {
                            TJCloseButton.this.setClickable(TJCloseButton.this.c);
                            TJCloseButton.this.d = false;
                        }
                        
                        public final void onAnimationEnd(final Animator animator) {
                            TJCloseButton.this.setClickable(TJCloseButton.this.c);
                            TJCloseButton.this.d = false;
                        }
                        
                        public final void onAnimationRepeat(final Animator animator) {
                        }
                        
                        public final void onAnimationStart(final Animator animator) {
                        }
                    });
                }
            }, 2000L);
        }
    }
    
    void setClickableRequested(final boolean b) {
        this.c = b;
        if (!this.d) {
            this.setClickable(b);
        }
    }
    
    public enum ClosePosition
    {
        BOTTOM_CENTER("BOTTOM_CENTER", 5, new int[] { 12, 14 }), 
        BOTTOM_LEFT("BOTTOM_LEFT", 4, new int[] { 12, 9 }), 
        BOTTOM_RIGHT("BOTTOM_RIGHT", 6, new int[] { 12, 11 }), 
        CENTER("CENTER", 3, new int[] { 13 }), 
        TOP_CENTER("TOP_CENTER", 1, new int[] { 10, 14 }), 
        TOP_LEFT("TOP_LEFT", 0, new int[] { 10, 9 }), 
        TOP_RIGHT("TOP_RIGHT", 2, new int[] { 10, 11 });
        
        final RelativeLayout$LayoutParams a;
        
        private ClosePosition(final String s, int i, final int[] array) {
            this.a = new RelativeLayout$LayoutParams(-2, -2);
            int length;
            for (length = array.length, i = 0; i < length; ++i) {
                this.a.addRule(array[i]);
            }
            i = (int)(-10.0f * TapjoyConnectCore.getDeviceScreenDensityScale());
            this.a.setMargins(0, i, i, 0);
        }
    }
}
