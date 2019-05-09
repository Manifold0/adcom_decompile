// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public enum eb implements dq
{
    public static final dn ADAPTER;
    
    APP("APP", 0, 0), 
    CAMPAIGN("CAMPAIGN", 1, 1), 
    CUSTOM("CUSTOM", 2, 2), 
    USAGES("USAGES", 3, 3);
    
    private final int a;
    
    static {
        ADAPTER = new a();
    }
    
    private eb(final String s, final int n, final int a) {
        this.a = a;
    }
    
    public static eb a(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return eb.APP;
            }
            case 1: {
                return eb.CAMPAIGN;
            }
            case 2: {
                return eb.CUSTOM;
            }
            case 3: {
                return eb.USAGES;
            }
        }
    }
    
    @Override
    public final int a() {
        return this.a;
    }
    
    static final class a extends dj
    {
        a() {
            super(eb.class);
        }
    }
}
