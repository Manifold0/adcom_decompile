package com.tapjoy.mraid.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.webkit.WebView;

public class BasicWebView extends WebView {

    /* renamed from: com.tapjoy.mraid.view.BasicWebView$a */
    class C3010a extends SimpleOnGestureListener {
        /* renamed from: a */
        final /* synthetic */ BasicWebView f8318a;

        C3010a(BasicWebView basicWebView) {
            this.f8318a = basicWebView;
        }

        public final boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;
        }
    }

    @SuppressLint({"NewApi"})
    public BasicWebView(Context context) {
        super(context);
        setScrollContainer(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        GestureDetector gestureDetector = new GestureDetector(new C3010a(this));
        if (getSettings() != null) {
            getSettings().setJavaScriptEnabled(true);
            if (VERSION.SDK_INT >= 17) {
                getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
        }
        setBackgroundColor(0);
    }

    public BasicWebView(Context context, AttributeSet set) {
        super(context, set);
    }
}
