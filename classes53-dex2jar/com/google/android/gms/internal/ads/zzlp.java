// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzlp extends zzek implements zzlo
{
    public zzlp() {
        super("com.google.android.gms.ads.internal.client.IVideoController");
    }
    
    public static zzlo zze(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        if (queryLocalInterface instanceof zzlo) {
            return (zzlo)queryLocalInterface;
        }
        return new zzlq(binder);
    }
    
    protected final boolean dispatchTransaction(int playbackState, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
        switch (playbackState) {
            default: {
                return false;
            }
            case 1: {
                this.play();
                parcel2.writeNoException();
                break;
            }
            case 2: {
                this.pause();
                parcel2.writeNoException();
                break;
            }
            case 3: {
                this.mute(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            }
            case 4: {
                final boolean muted = this.isMuted();
                parcel2.writeNoException();
                zzel.zza(parcel2, muted);
                break;
            }
            case 5: {
                playbackState = this.getPlaybackState();
                parcel2.writeNoException();
                parcel2.writeInt(playbackState);
                break;
            }
            case 6: {
                final float zzim = this.zzim();
                parcel2.writeNoException();
                parcel2.writeFloat(zzim);
                break;
            }
            case 7: {
                final float zzin = this.zzin();
                parcel2.writeNoException();
                parcel2.writeFloat(zzin);
                break;
            }
            case 8: {
                final IBinder strongBinder = parcel.readStrongBinder();
                zzlr zzlr;
                if (strongBinder == null) {
                    zzlr = null;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    if (queryLocalInterface instanceof zzlr) {
                        zzlr = (zzlr)queryLocalInterface;
                    }
                    else {
                        zzlr = new zzlt(strongBinder);
                    }
                }
                this.zza(zzlr);
                parcel2.writeNoException();
                break;
            }
            case 9: {
                final float aspectRatio = this.getAspectRatio();
                parcel2.writeNoException();
                parcel2.writeFloat(aspectRatio);
                break;
            }
            case 10: {
                final boolean customControlsEnabled = this.isCustomControlsEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, customControlsEnabled);
                break;
            }
            case 11: {
                final zzlr zzio = this.zzio();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzio);
                break;
            }
            case 12: {
                final boolean clickToExpandEnabled = this.isClickToExpandEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, clickToExpandEnabled);
                break;
            }
        }
        return true;
    }
}
