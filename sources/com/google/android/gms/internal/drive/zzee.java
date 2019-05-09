package com.google.android.gms.internal.drive;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzi;
import java.util.ArrayList;
import java.util.List;

public final class zzee extends zzet {
    private static final GmsLogger zzbx = new GmsLogger("EventCallback", "");
    private final int zzcy = 1;
    private final zzi zzgr;
    private final zzeg zzgs;
    private final List<Integer> zzgt = new ArrayList();

    public zzee(Looper looper, Context context, int i, zzi zzi) {
        this.zzgr = zzi;
        this.zzgs = new zzeg(looper, context);
    }

    public final void zzc(zzfj zzfj) throws RemoteException {
        DriveEvent zzak = zzfj.zzak();
        Preconditions.checkState(this.zzcy == zzak.getType());
        Preconditions.checkState(this.zzgt.contains(Integer.valueOf(zzak.getType())));
        zzeg zzeg = this.zzgs;
        zzeg.sendMessage(zzeg.obtainMessage(1, new Pair(this.zzgr, zzak)));
    }

    public final void zzf(int i) {
        this.zzgt.add(Integer.valueOf(1));
    }

    public final boolean zzg(int i) {
        return this.zzgt.contains(Integer.valueOf(1));
    }
}
