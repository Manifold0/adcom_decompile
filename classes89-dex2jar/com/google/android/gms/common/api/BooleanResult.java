// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class BooleanResult implements Result
{
    private final Status mStatus;
    private final boolean zabg;
    
    @KeepForSdk
    @ShowFirstParty
    public BooleanResult(final Status status, final boolean zabg) {
        this.mStatus = (Status)Preconditions.checkNotNull((Object)status, (Object)"Status must not be null");
        this.zabg = zabg;
    }
    
    @KeepForSdk
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof BooleanResult)) {
                return false;
            }
            final BooleanResult booleanResult = (BooleanResult)o;
            if (!this.mStatus.equals((Object)booleanResult.mStatus) || this.zabg != booleanResult.zabg) {
                return false;
            }
        }
        return true;
    }
    
    @KeepForSdk
    public Status getStatus() {
        return this.mStatus;
    }
    
    @KeepForSdk
    public boolean getValue() {
        return this.zabg;
    }
    
    @KeepForSdk
    @Override
    public final int hashCode() {
        final int hashCode = this.mStatus.hashCode();
        int n;
        if (this.zabg) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n + (hashCode + 527) * 31;
    }
}
