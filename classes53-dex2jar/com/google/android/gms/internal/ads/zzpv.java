// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import java.util.Iterator;
import android.os.RemoteException;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.ads.formats.NativeAd;

@zzadh
public final class zzpv extends AdChoicesInfo
{
    private final List<Image> zzbhf;
    private final zzps zzbkk;
    private String zzbkl;
    
    public zzpv(zzps zzbkk) {
        IBinder binder = null;
        IInterface queryLocalInterface;
        Label_0033_Outer:Label_0098_Outer:
        while (true) {
            this.zzbhf = new ArrayList<Image>();
            this.zzbkk = (zzps)zzbkk;
            while (true) {
                Label_0159: {
                Label_0147:
                    while (true) {
                        try {
                            this.zzbkl = this.zzbkk.getText();
                            try {
                                for (final zzpw next : ((zzps)zzbkk).zzjr()) {
                                    if (!(next instanceof IBinder)) {
                                        break Label_0159;
                                    }
                                    binder = (IBinder)next;
                                    if (binder == null) {
                                        break Label_0159;
                                    }
                                    queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                                    if (!(queryLocalInterface instanceof zzpw)) {
                                        break Label_0147;
                                    }
                                    zzbkk = (RemoteException)queryLocalInterface;
                                    if (zzbkk == null) {
                                        continue Label_0033_Outer;
                                    }
                                    this.zzbhf.add(new zzpz((zzpw)zzbkk));
                                }
                            }
                            catch (RemoteException zzbkk) {
                                zzane.zzb("", (Throwable)zzbkk);
                            }
                            return;
                        }
                        catch (RemoteException ex) {
                            zzane.zzb("", (Throwable)ex);
                            this.zzbkl = "";
                            continue Label_0098_Outer;
                        }
                        break;
                    }
                    zzbkk = (RemoteException)new zzpy(binder);
                    continue;
                }
                zzbkk = null;
                continue;
            }
        }
    }
    
    @Override
    public final List<Image> getImages() {
        return this.zzbhf;
    }
    
    @Override
    public final CharSequence getText() {
        return this.zzbkl;
    }
}
