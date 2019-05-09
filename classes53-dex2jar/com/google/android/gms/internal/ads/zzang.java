// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "VersionInfoParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzang extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzang> CREATOR;
    @SafeParcelable$Field(id = 3)
    public int zzcve;
    @SafeParcelable$Field(id = 4)
    public int zzcvf;
    @SafeParcelable$Field(id = 5)
    public boolean zzcvg;
    @SafeParcelable$Field(id = 6)
    public boolean zzcvh;
    @SafeParcelable$Field(id = 2)
    public String zzcw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzanh();
    }
    
    public zzang(final int n, final int n2, final boolean b) {
        this(n, n2, b, false, false);
    }
    
    public zzang(final int n, final int n2, final boolean b, final boolean b2) {
        this(12451000, n2, true, false, b2);
    }
    
    private zzang(final int n, final int n2, final boolean b, final boolean b2, final boolean b3) {
        String s;
        if (b) {
            s = "0";
        }
        else {
            s = "1";
        }
        this(new StringBuilder(String.valueOf(s).length() + 36).append("afma-sdk-a-v").append(n).append(".").append(n2).append(".").append(s).toString(), n, n2, b, b3);
    }
    
    @SafeParcelable$Constructor
    zzang(@SafeParcelable$Param(id = 2) final String zzcw, @SafeParcelable$Param(id = 3) final int zzcve, @SafeParcelable$Param(id = 4) final int zzcvf, @SafeParcelable$Param(id = 5) final boolean zzcvg, @SafeParcelable$Param(id = 6) final boolean zzcvh) {
        this.zzcw = zzcw;
        this.zzcve = zzcve;
        this.zzcvf = zzcvf;
        this.zzcvg = zzcvg;
        this.zzcvh = zzcvh;
    }
    
    public static zzang zzsl() {
        return new zzang(12451009, 12451009, true);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcw, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcve);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcvf);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcvg);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcvh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
