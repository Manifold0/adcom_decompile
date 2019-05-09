// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

public final class PageDirection
{
    public static final int NEXT = 0;
    public static final int NONE = -1;
    public static final int PREV = 1;
    
    private PageDirection() {
        throw new AssertionError((Object)"Uninstantiable");
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }
}
