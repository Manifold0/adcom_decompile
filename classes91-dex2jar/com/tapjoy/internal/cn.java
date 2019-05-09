// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.math.BigInteger;

public final class cn extends Number
{
    private final String a;
    
    public cn(final String a) {
        this.a = a;
    }
    
    @Override
    public final double doubleValue() {
        return Double.parseDouble(this.a);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Number)) {
                return false;
            }
            final Number n = (Number)o;
            if (n instanceof Integer) {
                if (this.intValue() != n.intValue()) {
                    return false;
                }
            }
            else if (n instanceof Long) {
                if (this.longValue() != n.longValue()) {
                    return false;
                }
            }
            else if (n instanceof Float) {
                if (this.floatValue() != n.floatValue()) {
                    return false;
                }
            }
            else {
                if (!(n instanceof Double)) {
                    return this.a.equals(n.toString());
                }
                if (this.doubleValue() != n.doubleValue()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public final float floatValue() {
        return Float.parseFloat(this.a);
    }
    
    @Override
    public final int intValue() {
        try {
            return Integer.parseInt(this.a);
        }
        catch (NumberFormatException ex) {
            try {
                return (int)Long.parseLong(this.a);
            }
            catch (NumberFormatException ex2) {
                return new BigInteger(this.a).intValue();
            }
        }
    }
    
    @Override
    public final long longValue() {
        try {
            return Long.parseLong(this.a);
        }
        catch (NumberFormatException ex) {
            return new BigInteger(this.a).longValue();
        }
    }
    
    @Override
    public final String toString() {
        return this.a;
    }
}
