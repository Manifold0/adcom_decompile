// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.events.DriveEvent;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import android.content.Context;
import android.os.Looper;
import java.util.List;
import com.google.android.gms.drive.events.zzi;
import com.google.android.gms.common.internal.GmsLogger;

public final class zzee extends zzet
{
    private static final GmsLogger zzbx;
    private final int zzcy;
    private final zzi zzgr;
    private final zzeg zzgs;
    private final List<Integer> zzgt;
    
    static {
        zzbx = new GmsLogger("EventCallback", "");
    }
    
    public zzee(final Looper looper, final Context context, final int n, final zzi zzgr) {
        this.zzgt = new ArrayList<Integer>();
        this.zzcy = 1;
        this.zzgr = zzgr;
        this.zzgs = new zzeg(looper, context, null);
    }
    
    @Override
    public final void zzc(final zzfj zzfj) throws RemoteException {
        final DriveEvent zzak = zzfj.zzak();
        Preconditions.checkState(this.zzcy == zzak.getType());
        Preconditions.checkState(this.zzgt.contains(zzak.getType()));
        final zzeg zzgs = this.zzgs;
        zzgs.sendMessage(zzgs.obtainMessage(1, (Object)new Pair((Object)this.zzgr, (Object)zzak)));
    }
    
    public final void zzf(final int n) {
        this.zzgt.add(1);
    }
    
    public final boolean zzg(final int n) {
        return this.zzgt.contains(1);
    }
}
