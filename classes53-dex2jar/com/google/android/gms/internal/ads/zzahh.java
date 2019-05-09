// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

@zzadh
public final class zzahh implements RewardItem
{
    private final zzagu zzcli;
    
    public zzahh(final zzagu zzcli) {
        this.zzcli = zzcli;
    }
    
    @Override
    public final int getAmount() {
        if (this.zzcli == null) {
            return 0;
        }
        try {
            return this.zzcli.getAmount();
        }
        catch (RemoteException ex) {
            zzane.zzc("Could not forward getAmount to RewardItem", (Throwable)ex);
            return 0;
        }
    }
    
    @Override
    public final String getType() {
        if (this.zzcli == null) {
            return null;
        }
        try {
            return this.zzcli.getType();
        }
        catch (RemoteException ex) {
            zzane.zzc("Could not forward getType to RewardItem", (Throwable)ex);
            return null;
        }
    }
}
