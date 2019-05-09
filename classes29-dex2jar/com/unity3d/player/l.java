// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

public final class l extends View
{
    final int a;
    final int b;
    Bitmap c;
    Bitmap d;
    
    public l(final Context context, final int a) {
        super(context);
        this.a = a;
        this.b = this.getResources().getIdentifier("unity_static_splash", "drawable", this.getContext().getPackageName());
        if (this.b != 0) {
            this.forceLayout();
        }
    }
    
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
    }
    
    public final void onLayout(final boolean b, int width, int width2, int n, int height) {
        if (this.b != 0) {
            if (this.c == null) {
                final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                bitmapFactory$Options.inScaled = false;
                this.c = BitmapFactory.decodeResource(this.getResources(), this.b, bitmapFactory$Options);
            }
            width2 = this.c.getWidth();
            height = this.c.getHeight();
            width = this.getWidth();
            final int height2 = this.getHeight();
            if (width != 0 && height2 != 0) {
                final float n2 = width2 / (float)height;
                if (width / (float)height2 <= n2) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                while (true) {
                    Label_0321: {
                        switch (l$1.a[this.a - 1]) {
                            default: {
                                width = height;
                                break;
                            }
                            case 1: {
                                if (width < width2) {
                                    n = (int)(width / n2);
                                    width2 = width;
                                    width = n;
                                }
                                else {
                                    width = height;
                                }
                                if (height2 < width) {
                                    break Label_0321;
                                }
                                break;
                            }
                            case 2:
                            case 3: {
                                if (this.a == l.a.c) {
                                    width2 = 1;
                                }
                                else {
                                    width2 = 0;
                                }
                                if ((n ^ width2) != 0x0) {
                                    n = (int)(width / n2);
                                    width2 = width;
                                    width = n;
                                    break;
                                }
                                break Label_0321;
                            }
                        }
                        if (this.d != null) {
                            if (this.d.getWidth() == width2 && this.d.getHeight() == width) {
                                return;
                            }
                            if (this.d != this.c) {
                                this.d.recycle();
                                this.d = null;
                            }
                        }
                        (this.d = Bitmap.createScaledBitmap(this.c, width2, width, true)).setDensity(this.getResources().getDisplayMetrics().densityDpi);
                        final ColorDrawable colorDrawable = new ColorDrawable(-16777216);
                        final BitmapDrawable bitmapDrawable = new BitmapDrawable(this.getResources(), this.d);
                        bitmapDrawable.setGravity(17);
                        this.setBackground((Drawable)new LayerDrawable(new Drawable[] { (Drawable)colorDrawable, (Drawable)bitmapDrawable }));
                        return;
                    }
                    width = height2;
                    width2 = (int)(width * n2);
                    continue;
                }
            }
        }
    }
    
    enum a
    {
        public static final int a;
        public static final int b;
        public static final int c;
        private static final /* synthetic */ int[] d;
        
        static {
            a = 1;
            b = 2;
            c = 3;
            d = new int[] { l.a.a, l.a.b, l.a.c };
        }
        
        public static int[] a() {
            return l.a.d.clone();
        }
    }
}
