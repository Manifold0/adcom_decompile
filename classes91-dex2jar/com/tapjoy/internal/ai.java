// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.animation.Animation;

public class ai
{
    protected final Animation a;
    
    public ai(final Animation a) {
        (this.a = a).setDuration(400L);
    }
    
    public Animation a() {
        return this.a;
    }
    
    public final ai b() {
        this.a.setDuration(600L);
        return this;
    }
    
    public enum a
    {
        public static final int a;
        public static final int b;
        public static final int c;
        public static final int d;
        private static final /* synthetic */ int[] e;
        
        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = new int[] { ai.a.a, ai.a.b, ai.a.c, ai.a.d };
        }
        
        public static int[] a() {
            return ai.a.e.clone();
        }
    }
}
