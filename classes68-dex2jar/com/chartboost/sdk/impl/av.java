// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.net.Uri;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.view.SurfaceView;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.chartboost.sdk.g;
import com.chartboost.sdk.Libraries.CBLogging;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class av extends FrameLayout
{
    private View a;
    private boolean b;
    
    public av(final Context context) {
        super(context);
        this.b();
    }
    
    private void b() {
        this.b = true;
        final StringBuilder append = new StringBuilder().append("Choosing ");
        String s;
        if (this.b) {
            s = "texture";
        }
        else {
            s = "surface";
        }
        CBLogging.e("VideoInit", append.append(s).append(" solution for video playback").toString());
        final g a = g.a();
        if (this.b) {
            this.a = a.a((View)new au(this.getContext()));
        }
        else {
            this.a = a.a((View)new at(this.getContext()));
        }
        this.a.setContentDescription((CharSequence)"CBVideo");
        this.addView(this.a, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        if (!this.b) {
            ((SurfaceView)this.a).setZOrderMediaOverlay(true);
        }
    }
    
    public a a() {
        return (a)this.a;
    }
    
    public void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.a().a(n, n2);
    }
    
    public interface a
    {
        void a();
        
        void a(final int p0);
        
        void a(final int p0, final int p1);
        
        void a(final MediaPlayer$OnCompletionListener p0);
        
        void a(final MediaPlayer$OnErrorListener p0);
        
        void a(final MediaPlayer$OnPreparedListener p0);
        
        void a(final Uri p0);
        
        void b();
        
        int c();
        
        int d();
        
        boolean e();
    }
}
