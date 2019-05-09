// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.internal.ads.zzlb;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.internal.ads.zzjp;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "PublisherAdViewOptionsCreator")
public final class PublisherAdViewOptions extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<PublisherAdViewOptions> CREATOR;
    @SafeParcelable$Field(getter = "getManualImpressionsEnabled", id = 1)
    private final boolean zzvm;
    @Nullable
    @SafeParcelable$Field(getter = "getAppEventListenerBinder", id = 2, type = "android.os.IBinder")
    private final zzla zzvn;
    @Nullable
    private AppEventListener zzvo;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    private PublisherAdViewOptions(final Builder builder) {
        this.zzvm = builder.zzvm;
        this.zzvo = builder.zzvo;
        zzjp zzvn;
        if (this.zzvo != null) {
            zzvn = new zzjp(this.zzvo);
        }
        else {
            zzvn = null;
        }
        this.zzvn = zzvn;
    }
    
    @SafeParcelable$Constructor
    PublisherAdViewOptions(@SafeParcelable$Param(id = 1) final boolean zzvm, @Nullable @SafeParcelable$Param(id = 2) final IBinder binder) {
        this.zzvm = zzvm;
        zzla zzd;
        if (binder != null) {
            zzd = zzlb.zzd(binder);
        }
        else {
            zzd = null;
        }
        this.zzvn = zzd;
    }
    
    @Nullable
    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }
    
    public final boolean getManualImpressionsEnabled() {
        return this.zzvm;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.getManualImpressionsEnabled());
        IBinder binder;
        if (this.zzvn == null) {
            binder = null;
        }
        else {
            binder = this.zzvn.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, binder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Nullable
    public final zzla zzbg() {
        return this.zzvn;
    }
    
    public static final class Builder
    {
        private boolean zzvm;
        private AppEventListener zzvo;
        
        public Builder() {
            this.zzvm = false;
        }
        
        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this, null);
        }
        
        public final Builder setAppEventListener(final AppEventListener zzvo) {
            this.zzvo = zzvo;
            return this;
        }
        
        public final Builder setManualImpressionsEnabled(final boolean zzvm) {
            this.zzvm = zzvm;
            return this;
        }
    }
}
