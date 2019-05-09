// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import java.util.Iterator;
import java.util.EnumSet;

public enum SmartLoginOption
{
    public static final EnumSet<SmartLoginOption> ALL;
    
    Enabled(1L), 
    None(0L), 
    RequireConfirm(2L);
    
    private final long mValue;
    
    static {
        ALL = EnumSet.allOf(SmartLoginOption.class);
    }
    
    private SmartLoginOption(final long mValue) {
        this.mValue = mValue;
    }
    
    public static EnumSet<SmartLoginOption> parseOptions(final long n) {
        final EnumSet<SmartLoginOption> none = EnumSet.noneOf(SmartLoginOption.class);
        for (final SmartLoginOption smartLoginOption : SmartLoginOption.ALL) {
            if ((smartLoginOption.getValue() & n) != 0x0L) {
                none.add(smartLoginOption);
            }
        }
        return none;
    }
    
    public long getValue() {
        return this.mValue;
    }
}
