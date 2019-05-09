// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.view;

import android.view.MotionEvent;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.util.AttributeSet;
import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import android.content.Context;
import android.webkit.WebView;

public class BasicWebView extends WebView
{
    @SuppressLint({ "NewApi" })
    public BasicWebView(final Context context) {
        super(context);
        this.setScrollContainer(false);
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        new GestureDetector((GestureDetector$OnGestureListener)new a());
        if (this.getSettings() != null) {
            this.getSettings().setJavaScriptEnabled(true);
            if (Build$VERSION.SDK_INT >= 17) {
                this.getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
        }
        this.setBackgroundColor(0);
    }
    
    public BasicWebView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    final class a extends GestureDetector$SimpleOnGestureListener
    {
        public final boolean onScroll(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
            return true;
        }
    }
}
