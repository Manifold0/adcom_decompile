// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzls extends zzek implements zzlr
{
    public zzls() {
        super("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.onVideoStart();
                break;
            }
            case 2: {
                this.onVideoPlay();
                break;
            }
            case 3: {
                this.onVideoPause();
                break;
            }
            case 4: {
                this.onVideoEnd();
                break;
            }
            case 5: {
                this.onVideoMute(zzel.zza(parcel));
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
