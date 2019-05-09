// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Bundle;
import java.util.List;
import javax.annotation.Nullable;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.content.pm.ApplicationInfo;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@ParametersAreNonnullByDefault
@SafeParcelable$Class(creator = "NonagonRequestParcelCreator")
public final class zzaey extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaey> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final ApplicationInfo applicationInfo;
    @SafeParcelable$Field(id = 4)
    private final String packageName;
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final PackageInfo zzccw;
    @SafeParcelable$Field(id = 5)
    private final List<String> zzcdj;
    @SafeParcelable$Field(id = 7)
    private final String zzcds;
    @SafeParcelable$Field(id = 1)
    private final Bundle zzcfy;
    @SafeParcelable$Field(id = 2)
    private final zzang zzcfz;
    @SafeParcelable$Field(id = 8)
    private final boolean zzcga;
    @SafeParcelable$Field(id = 9)
    private final String zzcgb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaez();
    }
    
    @SafeParcelable$Constructor
    public zzaey(@SafeParcelable$Param(id = 1) final Bundle zzcfy, @SafeParcelable$Param(id = 2) final zzang zzcfz, @SafeParcelable$Param(id = 3) final ApplicationInfo applicationInfo, @SafeParcelable$Param(id = 4) final String packageName, @SafeParcelable$Param(id = 5) final List<String> zzcdj, @Nullable @SafeParcelable$Param(id = 6) final PackageInfo zzccw, @SafeParcelable$Param(id = 7) final String zzcds, @SafeParcelable$Param(id = 8) final boolean zzcga, @SafeParcelable$Param(id = 9) final String zzcgb) {
        this.zzcfy = zzcfy;
        this.zzcfz = zzcfz;
        this.packageName = packageName;
        this.applicationInfo = applicationInfo;
        this.zzcdj = zzcdj;
        this.zzccw = zzccw;
        this.zzcds = zzcds;
        this.zzcga = zzcga;
        this.zzcgb = zzcgb;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzcfy, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzcfz, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.applicationInfo, n, false);
        SafeParcelWriter.writeString(parcel, 4, this.packageName, false);
        SafeParcelWriter.writeStringList(parcel, 5, (List)this.zzcdj, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzccw, n, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzcds, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzcga);
        SafeParcelWriter.writeString(parcel, 9, this.zzcgb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
