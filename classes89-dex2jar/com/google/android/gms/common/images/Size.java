// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.images;

public final class Size
{
    private final int zane;
    private final int zanf;
    
    public Size(final int zane, final int zanf) {
        this.zane = zane;
        this.zanf = zanf;
    }
    
    public static Size parseSize(final String s) throws NumberFormatException {
        if (s == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int n;
        if ((n = s.indexOf(42)) < 0) {
            n = s.indexOf(120);
        }
        if (n < 0) {
            throw zah(s);
        }
        try {
            return new Size(Integer.parseInt(s.substring(0, n)), Integer.parseInt(s.substring(n + 1)));
        }
        catch (NumberFormatException ex) {
            throw zah(s);
        }
    }
    
    private static NumberFormatException zah(final String s) {
        throw new NumberFormatException(new StringBuilder(String.valueOf(s).length() + 16).append("Invalid Size: \"").append(s).append("\"").toString());
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != null) {
            if (this == o) {
                return true;
            }
            if (o instanceof Size) {
                final Size size = (Size)o;
                if (this.zane == size.zane && this.zanf == size.zanf) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public final int getHeight() {
        return this.zanf;
    }
    
    public final int getWidth() {
        return this.zane;
    }
    
    @Override
    public final int hashCode() {
        return this.zanf ^ (this.zane << 16 | this.zane >>> 16);
    }
    
    @Override
    public final String toString() {
        return new StringBuilder(23).append(this.zane).append("x").append(this.zanf).toString();
    }
}
