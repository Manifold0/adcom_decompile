// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbdk
{
    private final String info;
    private int position;
    
    zzbdk(final String info) {
        this.info = info;
        this.position = 0;
    }
    
    final boolean hasNext() {
        return this.position < this.info.length();
    }
    
    final int next() {
        final char char1 = this.info.charAt(this.position++);
        if (char1 < '\ud800') {
            return char1;
        }
        int n = char1 & '\u1fff';
        int n2 = 13;
        char char2;
        while (true) {
            char2 = this.info.charAt(this.position++);
            if (char2 < '\ud800') {
                break;
            }
            n |= (char2 & '\u1fff') << n2;
            n2 += 13;
        }
        return char2 << n2 | n;
    }
}
