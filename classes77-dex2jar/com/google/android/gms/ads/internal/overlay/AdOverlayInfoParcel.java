// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzaq;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "AdOverlayInfoCreator")
@SafeParcelable$Reserved({ 1 })
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<AdOverlayInfoParcel> CREATOR;
    @SafeParcelable$Field(id = 11)
    public final int orientation;
    @SafeParcelable$Field(id = 13)
    public final String url;
    @SafeParcelable$Field(id = 14)
    public final zzang zzacr;
    @SafeParcelable$Field(id = 2)
    public final zzc zzbyl;
    @SafeParcelable$Field(getter = "getAdClickListenerAsBinder", id = 3, type = "android.os.IBinder")
    public final zzjd zzbym;
    @SafeParcelable$Field(getter = "getAdOverlayListenerAsBinder", id = 4, type = "android.os.IBinder")
    public final zzn zzbyn;
    @SafeParcelable$Field(getter = "getAdWebViewAsBinder", id = 5, type = "android.os.IBinder")
    public final zzaqw zzbyo;
    @SafeParcelable$Field(getter = "getAppEventGmsgListenerAsBinder", id = 6, type = "android.os.IBinder")
    public final zzd zzbyp;
    @SafeParcelable$Field(id = 7)
    public final String zzbyq;
    @SafeParcelable$Field(id = 8)
    public final boolean zzbyr;
    @SafeParcelable$Field(id = 9)
    public final String zzbys;
    @SafeParcelable$Field(getter = "getLeaveApplicationListenerAsBinder", id = 10, type = "android.os.IBinder")
    public final zzt zzbyt;
    @SafeParcelable$Field(id = 12)
    public final int zzbyu;
    @SafeParcelable$Field(id = 16)
    public final String zzbyv;
    @SafeParcelable$Field(id = 17)
    public final zzaq zzbyw;
    @SafeParcelable$Field(getter = "getAdMetadataGmsgListenerAsBinder", id = 18, type = "android.os.IBinder")
    public final zzb zzbyx;
    
    static {
        CREATOR = (Parcelable$Creator)new zzm();
    }
    
    @SafeParcelable$Constructor
    AdOverlayInfoParcel(@SafeParcelable$Param(id = 2) final zzc zzbyl, @SafeParcelable$Param(id = 3) final IBinder binder, @SafeParcelable$Param(id = 4) final IBinder binder2, @SafeParcelable$Param(id = 5) final IBinder binder3, @SafeParcelable$Param(id = 6) final IBinder binder4, @SafeParcelable$Param(id = 7) final String zzbyq, @SafeParcelable$Param(id = 8) final boolean zzbyr, @SafeParcelable$Param(id = 9) final String zzbys, @SafeParcelable$Param(id = 10) final IBinder binder5, @SafeParcelable$Param(id = 11) final int orientation, @SafeParcelable$Param(id = 12) final int zzbyu, @SafeParcelable$Param(id = 13) final String url, @SafeParcelable$Param(id = 14) final zzang zzacr, @SafeParcelable$Param(id = 16) final String zzbyv, @SafeParcelable$Param(id = 17) final zzaq zzbyw, @SafeParcelable$Param(id = 18) final IBinder binder6) {
        this.zzbyl = zzbyl;
        this.zzbym = (zzjd)ObjectWrapper.unwrap(IObjectWrapper$Stub.asInterface(binder));
        this.zzbyn = (zzn)ObjectWrapper.unwrap(IObjectWrapper$Stub.asInterface(binder2));
        this.zzbyo = (zzaqw)ObjectWrapper.unwrap(IObjectWrapper$Stub.asInterface(binder3));
        this.zzbyx = (zzb)ObjectWrapper.unwrap(IObjectWrapper$Stub.asInterface(binder6));
        this.zzbyp = (zzd)ObjectWrapper.unwrap(IObjectWrapper$Stub.asInterface(binder4));
        this.zzbyq = zzbyq;
        this.zzbyr = zzbyr;
        this.zzbys = zzbys;
        this.zzbyt = (zzt)ObjectWrapper.unwrap(IObjectWrapper$Stub.asInterface(binder5));
        this.orientation = orientation;
        this.zzbyu = zzbyu;
        this.url = url;
        this.zzacr = zzacr;
        this.zzbyv = zzbyv;
        this.zzbyw = zzbyw;
    }
    
    public AdOverlayInfoParcel(final zzc zzbyl, final zzjd zzbym, final zzn zzbyn, final zzt zzbyt, final zzang zzacr) {
        this.zzbyl = zzbyl;
        this.zzbym = zzbym;
        this.zzbyn = zzbyn;
        this.zzbyo = null;
        this.zzbyx = null;
        this.zzbyp = null;
        this.zzbyq = null;
        this.zzbyr = false;
        this.zzbys = null;
        this.zzbyt = zzbyt;
        this.orientation = -1;
        this.zzbyu = 4;
        this.url = null;
        this.zzacr = zzacr;
        this.zzbyv = null;
        this.zzbyw = null;
    }
    
    public AdOverlayInfoParcel(final zzjd zzbym, final zzn zzbyn, final zzb zzbyx, final zzd zzbyp, final zzt zzbyt, final zzaqw zzbyo, final boolean zzbyr, final int orientation, final String url, final zzang zzacr) {
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.zzbyn = zzbyn;
        this.zzbyo = zzbyo;
        this.zzbyx = zzbyx;
        this.zzbyp = zzbyp;
        this.zzbyq = null;
        this.zzbyr = zzbyr;
        this.zzbys = null;
        this.zzbyt = zzbyt;
        this.orientation = orientation;
        this.zzbyu = 3;
        this.url = url;
        this.zzacr = zzacr;
        this.zzbyv = null;
        this.zzbyw = null;
    }
    
    public AdOverlayInfoParcel(final zzjd zzbym, final zzn zzbyn, final zzb zzbyx, final zzd zzbyp, final zzt zzbyt, final zzaqw zzbyo, final boolean zzbyr, final int orientation, final String zzbys, final String zzbyq, final zzang zzacr) {
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.zzbyn = zzbyn;
        this.zzbyo = zzbyo;
        this.zzbyx = zzbyx;
        this.zzbyp = zzbyp;
        this.zzbyq = zzbyq;
        this.zzbyr = zzbyr;
        this.zzbys = zzbys;
        this.zzbyt = zzbyt;
        this.orientation = orientation;
        this.zzbyu = 3;
        this.url = null;
        this.zzacr = zzacr;
        this.zzbyv = null;
        this.zzbyw = null;
    }
    
    public AdOverlayInfoParcel(final zzjd zzbym, final zzn zzbyn, final zzt zzbyt, final zzaqw zzbyo, final int orientation, final zzang zzacr, final String zzbyv, final zzaq zzbyw) {
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.zzbyn = zzbyn;
        this.zzbyo = zzbyo;
        this.zzbyx = null;
        this.zzbyp = null;
        this.zzbyq = null;
        this.zzbyr = false;
        this.zzbys = null;
        this.zzbyt = zzbyt;
        this.orientation = orientation;
        this.zzbyu = 1;
        this.url = null;
        this.zzacr = zzacr;
        this.zzbyv = zzbyv;
        this.zzbyw = zzbyw;
    }
    
    public AdOverlayInfoParcel(final zzjd zzbym, final zzn zzbyn, final zzt zzbyt, final zzaqw zzbyo, final boolean zzbyr, final int orientation, final zzang zzacr) {
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.zzbyn = zzbyn;
        this.zzbyo = zzbyo;
        this.zzbyx = null;
        this.zzbyp = null;
        this.zzbyq = null;
        this.zzbyr = zzbyr;
        this.zzbys = null;
        this.zzbyt = zzbyt;
        this.orientation = orientation;
        this.zzbyu = 2;
        this.url = null;
        this.zzacr = zzacr;
        this.zzbyv = null;
        this.zzbyw = null;
    }
    
    public static void zza(final Intent intent, final AdOverlayInfoParcel adOverlayInfoParcel) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", (Parcelable)adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }
    
    public static AdOverlayInfoParcel zzc(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel)bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzbyl, n, false);
        SafeParcelWriter.writeIBinder(parcel, 3, ObjectWrapper.wrap((Object)this.zzbym).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap((Object)this.zzbyn).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 5, ObjectWrapper.wrap((Object)this.zzbyo).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 6, ObjectWrapper.wrap((Object)this.zzbyp).asBinder(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbyq, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbyr);
        SafeParcelWriter.writeString(parcel, 9, this.zzbys, false);
        SafeParcelWriter.writeIBinder(parcel, 10, ObjectWrapper.wrap((Object)this.zzbyt).asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 11, this.orientation);
        SafeParcelWriter.writeInt(parcel, 12, this.zzbyu);
        SafeParcelWriter.writeString(parcel, 13, this.url, false);
        SafeParcelWriter.writeParcelable(parcel, 14, (Parcelable)this.zzacr, n, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzbyv, false);
        SafeParcelWriter.writeParcelable(parcel, 17, (Parcelable)this.zzbyw, n, false);
        SafeParcelWriter.writeIBinder(parcel, 18, ObjectWrapper.wrap((Object)this.zzbyx).asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
