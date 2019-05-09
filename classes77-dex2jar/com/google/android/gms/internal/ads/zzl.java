// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.text.TextUtils;

public final class zzl
{
    private final String mName;
    private final String mValue;
    
    public zzl(final String mName, final String mValue) {
        this.mName = mName;
        this.mValue = mValue;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final zzl zzl = (zzl)o;
            if (!TextUtils.equals((CharSequence)this.mName, (CharSequence)zzl.mName) || !TextUtils.equals((CharSequence)this.mValue, (CharSequence)zzl.mValue)) {
                return false;
            }
        }
        return true;
    }
    
    public final String getName() {
        return this.mName;
    }
    
    public final String getValue() {
        return this.mValue;
    }
    
    @Override
    public final int hashCode() {
        return this.mName.hashCode() * 31 + this.mValue.hashCode();
    }
    
    @Override
    public final String toString() {
        final String mName = this.mName;
        final String mValue = this.mValue;
        return new StringBuilder(String.valueOf(mName).length() + 20 + String.valueOf(mValue).length()).append("Header[name=").append(mName).append(",value=").append(mValue).append("]").toString();
    }
}
