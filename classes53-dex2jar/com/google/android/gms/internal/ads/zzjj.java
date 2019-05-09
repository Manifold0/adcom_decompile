// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.location.Location;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "AdRequestParcelCreator")
public final class zzjj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzjj> CREATOR;
    @SafeParcelable$Field(id = 3)
    public final Bundle extras;
    @SafeParcelable$Field(id = 1)
    public final int versionCode;
    @SafeParcelable$Field(id = 2)
    public final long zzapw;
    @SafeParcelable$Field(id = 4)
    public final int zzapx;
    @SafeParcelable$Field(id = 5)
    public final List<String> zzapy;
    @SafeParcelable$Field(id = 6)
    public final boolean zzapz;
    @SafeParcelable$Field(id = 7)
    public final int zzaqa;
    @SafeParcelable$Field(id = 8)
    public final boolean zzaqb;
    @SafeParcelable$Field(id = 9)
    public final String zzaqc;
    @SafeParcelable$Field(id = 10)
    public final zzmq zzaqd;
    @SafeParcelable$Field(id = 11)
    public final Location zzaqe;
    @SafeParcelable$Field(id = 12)
    public final String zzaqf;
    @SafeParcelable$Field(id = 13)
    public final Bundle zzaqg;
    @SafeParcelable$Field(id = 14)
    public final Bundle zzaqh;
    @SafeParcelable$Field(id = 15)
    public final List<String> zzaqi;
    @SafeParcelable$Field(id = 16)
    public final String zzaqj;
    @SafeParcelable$Field(id = 17)
    public final String zzaqk;
    @SafeParcelable$Field(id = 18)
    public final boolean zzaql;
    
    static {
        CREATOR = (Parcelable$Creator)new zzjl();
    }
    
    @SafeParcelable$Constructor
    public zzjj(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final long zzapw, @SafeParcelable$Param(id = 3) Bundle zzaqg, @SafeParcelable$Param(id = 4) final int zzapx, @SafeParcelable$Param(id = 5) final List<String> zzapy, @SafeParcelable$Param(id = 6) final boolean zzapz, @SafeParcelable$Param(id = 7) final int zzaqa, @SafeParcelable$Param(id = 8) final boolean zzaqb, @SafeParcelable$Param(id = 9) final String zzaqc, @SafeParcelable$Param(id = 10) final zzmq zzaqd, @SafeParcelable$Param(id = 11) final Location zzaqe, @SafeParcelable$Param(id = 12) final String zzaqf, @SafeParcelable$Param(id = 13) final Bundle bundle, @SafeParcelable$Param(id = 14) final Bundle zzaqh, @SafeParcelable$Param(id = 15) final List<String> zzaqi, @SafeParcelable$Param(id = 16) final String zzaqj, @SafeParcelable$Param(id = 17) final String zzaqk, @SafeParcelable$Param(id = 18) final boolean zzaql) {
        this.versionCode = versionCode;
        this.zzapw = zzapw;
        Object extras = zzaqg;
        if (zzaqg == null) {
            extras = new Bundle();
        }
        this.extras = (Bundle)extras;
        this.zzapx = zzapx;
        this.zzapy = zzapy;
        this.zzapz = zzapz;
        this.zzaqa = zzaqa;
        this.zzaqb = zzaqb;
        this.zzaqc = zzaqc;
        this.zzaqd = zzaqd;
        this.zzaqe = zzaqe;
        this.zzaqf = zzaqf;
        if ((zzaqg = bundle) == null) {
            zzaqg = new Bundle();
        }
        this.zzaqg = (Bundle)zzaqg;
        this.zzaqh = zzaqh;
        this.zzaqi = zzaqi;
        this.zzaqj = zzaqj;
        this.zzaqk = zzaqk;
        this.zzaql = zzaql;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof zzjj) {
            final zzjj zzjj = (zzjj)o;
            if (this.versionCode == zzjj.versionCode && this.zzapw == zzjj.zzapw && Objects.equal((Object)this.extras, (Object)zzjj.extras) && this.zzapx == zzjj.zzapx && Objects.equal((Object)this.zzapy, (Object)zzjj.zzapy) && this.zzapz == zzjj.zzapz && this.zzaqa == zzjj.zzaqa && this.zzaqb == zzjj.zzaqb && Objects.equal((Object)this.zzaqc, (Object)zzjj.zzaqc) && Objects.equal((Object)this.zzaqd, (Object)zzjj.zzaqd) && Objects.equal((Object)this.zzaqe, (Object)zzjj.zzaqe) && Objects.equal((Object)this.zzaqf, (Object)zzjj.zzaqf) && Objects.equal((Object)this.zzaqg, (Object)zzjj.zzaqg) && Objects.equal((Object)this.zzaqh, (Object)zzjj.zzaqh) && Objects.equal((Object)this.zzaqi, (Object)zzjj.zzaqi) && Objects.equal((Object)this.zzaqj, (Object)zzjj.zzaqj) && Objects.equal((Object)this.zzaqk, (Object)zzjj.zzaqk) && this.zzaql == zzjj.zzaql) {
                return true;
            }
        }
        return false;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.versionCode, this.zzapw, this.extras, this.zzapx, this.zzapy, this.zzapz, this.zzaqa, this.zzaqb, this.zzaqc, this.zzaqd, this.zzaqe, this.zzaqf, this.zzaqg, this.zzaqh, this.zzaqi, this.zzaqj, this.zzaqk, this.zzaql });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, this.zzapw);
        SafeParcelWriter.writeBundle(parcel, 3, this.extras, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzapx);
        SafeParcelWriter.writeStringList(parcel, 5, (List)this.zzapy, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzapz);
        SafeParcelWriter.writeInt(parcel, 7, this.zzaqa);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzaqb);
        SafeParcelWriter.writeString(parcel, 9, this.zzaqc, false);
        SafeParcelWriter.writeParcelable(parcel, 10, (Parcelable)this.zzaqd, n, false);
        SafeParcelWriter.writeParcelable(parcel, 11, (Parcelable)this.zzaqe, n, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzaqf, false);
        SafeParcelWriter.writeBundle(parcel, 13, this.zzaqg, false);
        SafeParcelWriter.writeBundle(parcel, 14, this.zzaqh, false);
        SafeParcelWriter.writeStringList(parcel, 15, (List)this.zzaqi, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzaqj, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzaqk, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzaql);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final zzjj zzhv() {
        Bundle bundle;
        if ((bundle = this.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter")) == null) {
            bundle = this.extras;
            this.zzaqg.putBundle("com.google.ads.mediation.admob.AdMobAdapter", this.extras);
        }
        return new zzjj(this.versionCode, this.zzapw, bundle, this.zzapx, this.zzapy, this.zzapz, this.zzaqa, this.zzaqb, this.zzaqc, this.zzaqd, this.zzaqe, this.zzaqf, this.zzaqg, this.zzaqh, this.zzaqi, this.zzaqj, this.zzaqk, this.zzaql);
    }
}
