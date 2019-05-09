// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import java.util.EnumSet;

public enum CacheFlag
{
    public static final EnumSet<CacheFlag> ALL;
    
    ICON, 
    IMAGE, 
    NONE, 
    VIDEO;
    
    static {
        ALL = EnumSet.allOf(CacheFlag.class);
    }
}
