// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbep extends IllegalArgumentException
{
    zzbep(final int n, final int n2) {
        super(new StringBuilder(54).append("Unpaired surrogate at index ").append(n).append(" of ").append(n2).toString());
    }
}
