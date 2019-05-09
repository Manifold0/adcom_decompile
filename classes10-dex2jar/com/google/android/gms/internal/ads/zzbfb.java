// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfb extends IOException
{
    zzbfb(final int n, final int n2) {
        super(new StringBuilder(108).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(n).append(" limit ").append(n2).append(").").toString());
    }
}
