// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.view.animation.TranslateAnimation;
import android.view.animation.Animation;
import com.chartboost.sdk.Libraries.CBUtility;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import android.widget.RelativeLayout;

public abstract class z extends RelativeLayout
{
    protected v a;
    private aa b;
    private int c;
    
    public z(final Context context, final v a) {
        super(context);
        this.a = a;
        this.c = 1;
        this.a(context);
    }
    
    private void a(Context context) {
        context = this.getContext();
        this.setGravity(17);
        (this.b = new aa(context)).a(-1);
        this.b.setBackgroundColor(-855638017);
        this.addView((View)this.b, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.addView(this.a(), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
    }
    
    private void a(boolean b, final long duration) {
        this.a.C = b;
        if ((b && this.getVisibility() == 0) || (!b && this.getVisibility() == 8)) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!b) {
                    z.this.setVisibility(8);
                    z.this.clearAnimation();
                }
                synchronized (z.this.a.g) {
                    z.this.a.g.remove(z.this);
                }
            }
        };
        if (b) {
            this.setVisibility(0);
        }
        float a = CBUtility.a((float)this.b(), this.getContext());
        while (true) {
        Label_0308_Outer:
            while (true) {
                Object o = null;
            Label_0260_Outer:
                while (true) {
                Label_0221_Outer:
                    while (true) {
                        while (true) {
                            switch (this.c) {
                                default: {
                                    o = null;
                                    break;
                                }
                                case 0: {
                                    Label_0173: {
                                        break Label_0173;
                                        float n;
                                        float n2;
                                        float n3;
                                        float n4;
                                        float n5;
                                        float n6;
                                        Label_0182_Outer:Block_13_Outer:Label_0316_Outer:
                                        while (true) {
                                            ((Animation)o).setFillAfter(b);
                                            this.startAnimation((Animation)o);
                                            synchronized (this.a.g) {
                                                this.a.g.put((View)this, runnable);
                                                // monitorexit(this.a.g)
                                                this.a.a.postDelayed((Runnable)runnable, duration);
                                                return;
                                                Label_0207: {
                                                    n = 0.0f;
                                                }
                                                // iftrue(Label_0213:, b == false)
                                                // iftrue(Label_0341:, b == false)
                                            Label_0316:
                                                while (true) {
                                                Label_0323_Outer:
                                                    while (true) {
                                                        Label_0229: {
                                                            while (true) {
                                                                Block_14: {
                                                                    Label_0276:Block_12_Outer:
                                                                    while (true) {
                                                                        while (true) {
                                                                            while (true) {
                                                                                Label_0236: {
                                                                                Label_0269:
                                                                                    while (true) {
                                                                                        while (true) {
                                                                                            while (true) {
                                                                                                Label_0189: {
                                                                                                    while (true) {
                                                                                                    Block_8:
                                                                                                        while (true) {
                                                                                                            break Label_0182;
                                                                                                            n2 = a;
                                                                                                            break Label_0316;
                                                                                                            Label_0254:
                                                                                                            n3 = 0.0f;
                                                                                                            break Label_0229;
                                                                                                            o = new TranslateAnimation(n4, n5, 0.0f, 0.0f);
                                                                                                            break Label_0260_Outer;
                                                                                                            break Block_8;
                                                                                                            n = -a;
                                                                                                            continue Block_13_Outer;
                                                                                                        }
                                                                                                        n6 = 0.0f;
                                                                                                        break Label_0189;
                                                                                                        n5 = 0.0f;
                                                                                                        continue Label_0276;
                                                                                                        continue Label_0316_Outer;
                                                                                                    }
                                                                                                    Label_0213:
                                                                                                    n6 = -a;
                                                                                                    break Label_0189;
                                                                                                    a = 0.0f;
                                                                                                    break Label_0236;
                                                                                                }
                                                                                                o = new TranslateAnimation(0.0f, 0.0f, n, n6);
                                                                                                break Label_0260_Outer;
                                                                                                n3 = a;
                                                                                                break Label_0229;
                                                                                                o = new TranslateAnimation(n2, a, 0.0f, 0.0f);
                                                                                                break Label_0260_Outer;
                                                                                                n4 = -a;
                                                                                                break Label_0269;
                                                                                                break Block_14;
                                                                                                b = false;
                                                                                                continue Label_0182_Outer;
                                                                                                continue Block_12_Outer;
                                                                                            }
                                                                                            continue Label_0308_Outer;
                                                                                        }
                                                                                        Label_0294:
                                                                                        n4 = 0.0f;
                                                                                        continue Label_0269;
                                                                                    }
                                                                                }
                                                                                o = new TranslateAnimation(0.0f, 0.0f, n3, a);
                                                                                break Label_0260_Outer;
                                                                                continue Label_0260_Outer;
                                                                            }
                                                                            continue Label_0323_Outer;
                                                                        }
                                                                        Label_0300:
                                                                        n5 = -a;
                                                                        continue Label_0276;
                                                                    }
                                                                }
                                                                a = 0.0f;
                                                                continue Label_0260_Outer;
                                                            }
                                                        }
                                                        continue Label_0260_Outer;
                                                    }
                                                    Label_0341:
                                                    n2 = 0.0f;
                                                    continue Label_0316;
                                                }
                                            }
                                            // iftrue(Label_0323:, b == false)
                                            // iftrue(Label_0207:, b == false)
                                            // iftrue(Label_0300:, b == false)
                                            // iftrue(Label_0294:, b == false)
                                            // iftrue(Label_0254:, b == false)
                                            // iftrue(Label_0236:, b == false)
                                        }
                                    }
                                    break;
                                }
                                case 1: {
                                    continue;
                                }
                                case 2: {
                                    continue Label_0221_Outer;
                                }
                                case 3: {
                                    continue Label_0260_Outer;
                                }
                            }
                            break;
                        }
                        break;
                    }
                    break;
                }
                ((Animation)o).setDuration(duration);
                if (!b) {
                    b = true;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    protected abstract View a();
    
    public void a(int b) {
        this.c = b;
        Object layoutParams = null;
        this.setClickable(false);
        b = this.b();
        switch (this.c) {
            case 0: {
                layoutParams = new RelativeLayout$LayoutParams(-1, CBUtility.a(b, this.getContext()));
                ((RelativeLayout$LayoutParams)layoutParams).addRule(10);
                this.b.b(1);
                break;
            }
            case 1: {
                layoutParams = new RelativeLayout$LayoutParams(-1, CBUtility.a(b, this.getContext()));
                ((RelativeLayout$LayoutParams)layoutParams).addRule(12);
                this.b.b(4);
                break;
            }
            case 2: {
                layoutParams = new RelativeLayout$LayoutParams(CBUtility.a(b, this.getContext()), -1);
                ((RelativeLayout$LayoutParams)layoutParams).addRule(9);
                this.b.b(8);
                break;
            }
            case 3: {
                layoutParams = new RelativeLayout$LayoutParams(CBUtility.a(b, this.getContext()), -1);
                ((RelativeLayout$LayoutParams)layoutParams).addRule(11);
                this.b.b(2);
                break;
            }
        }
        this.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    public void a(final boolean b) {
        this.a(b, 500L);
    }
    
    protected abstract int b();
}
