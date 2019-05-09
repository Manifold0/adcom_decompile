// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i;

import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import android.view.View;
import android.content.Context;
import java.lang.ref.WeakReference;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.d.c;
import android.widget.RelativeLayout;

public class d extends RelativeLayout
{
    private final c a;
    @Nullable
    private g b;
    private WeakReference<a> c;
    
    public d(final Context context, final c a) {
        super(context);
        this.a = a;
        x.b((View)this.a);
        this.addView(this.a.getView(), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
    }
    
    public void a(final com.facebook.ads.internal.view.i.a.c c) {
        this.addView((View)c, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.b = (g)c;
    }
    
    public void b(final com.facebook.ads.internal.view.i.a.c c) {
        x.b((View)c);
        this.b = null;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        ((View)this.a).layout(0, 0, this.getWidth(), this.getHeight());
        if (this.b != null) {
            this.b.layout(0, 0, this.getWidth(), this.getHeight());
        }
    }
    
    protected void onMeasure(int size, int n) {
        final int n2 = 0;
        final int videoWidth = this.a.getVideoWidth();
        final int videoHeight = this.a.getVideoHeight();
        final int defaultSize = getDefaultSize(videoWidth, size);
        int n4;
        final int n3 = n4 = getDefaultSize(videoHeight, n);
        int n5 = defaultSize;
        int n6 = n2;
        Label_0132: {
            if (videoWidth > 0) {
                n4 = n3;
                n5 = defaultSize;
                n6 = n2;
                if (videoHeight > 0) {
                    final int mode = View$MeasureSpec.getMode(size);
                    size = View$MeasureSpec.getSize(size);
                    final int mode2 = View$MeasureSpec.getMode(n);
                    n4 = View$MeasureSpec.getSize(n);
                    if (mode == 1073741824 && mode2 == 1073741824) {
                        if (videoWidth * n4 < size * videoHeight) {
                            n5 = n4 * videoWidth / videoHeight;
                            n6 = 1;
                        }
                        else if (videoWidth * n4 > size * videoHeight) {
                            n4 = size * videoHeight / videoWidth;
                            n6 = 1;
                            n5 = size;
                        }
                        else {
                            n6 = 1;
                            n5 = size;
                        }
                    }
                    else if (mode == 1073741824) {
                        n = size * videoHeight / videoWidth;
                        if (mode2 == Integer.MIN_VALUE && n > n4) {
                            n6 = 1;
                            n5 = size;
                        }
                        else {
                            n4 = n;
                            n6 = 1;
                            n5 = size;
                        }
                    }
                    else {
                        if (mode2 == 1073741824) {
                            final int n7 = n4 * videoWidth / videoHeight;
                            n = n4;
                            n5 = n7;
                            if (mode == Integer.MIN_VALUE) {
                                n = n4;
                                if ((n5 = n7) > size) {
                                    n6 = 1;
                                    n5 = size;
                                    break Label_0132;
                                }
                            }
                        }
                        else {
                            int n8;
                            if (mode2 == Integer.MIN_VALUE && videoHeight > n4) {
                                n8 = n4 * videoWidth / videoHeight;
                            }
                            else {
                                n4 = videoHeight;
                                n8 = videoWidth;
                            }
                            n = n4;
                            n5 = n8;
                            if (mode == Integer.MIN_VALUE) {
                                n = n4;
                                if ((n5 = n8) > size) {
                                    n4 = size * videoHeight / videoWidth;
                                    n6 = 1;
                                    n5 = size;
                                    break Label_0132;
                                }
                            }
                        }
                        n6 = 1;
                        n4 = n;
                    }
                }
            }
        }
        this.setMeasuredDimension(n5, n4);
        if (n6 != 0 && this.c != null && this.c.get() != null) {
            this.c.get().a();
        }
    }
    
    public void setViewImplInflationListener(final a a) {
        this.c = new WeakReference<a>(a);
    }
    
    public interface a
    {
        void a();
    }
}
