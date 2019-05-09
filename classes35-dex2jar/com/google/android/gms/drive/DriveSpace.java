// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.text.TextUtils;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.regex.Pattern;
import java.util.Set;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DriveSpaceCreator")
@SafeParcelable$Reserved({ 1 })
public class DriveSpace extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<DriveSpace> CREATOR;
    public static final DriveSpace zzaf;
    public static final DriveSpace zzag;
    public static final DriveSpace zzah;
    private static final Set<DriveSpace> zzai;
    private static final String zzaj;
    private static final Pattern zzak;
    @SafeParcelable$Field(getter = "getName", id = 2)
    private final String name;
    
    static {
        CREATOR = (Parcelable$Creator)new zzm();
        zzaf = new DriveSpace("DRIVE");
        zzag = new DriveSpace("APP_DATA_FOLDER");
        zzah = new DriveSpace("PHOTOS");
        zzai = CollectionUtils.setOf((Object)DriveSpace.zzaf, (Object)DriveSpace.zzag, (Object)DriveSpace.zzah);
        zzaj = TextUtils.join((CharSequence)",", DriveSpace.zzai.toArray());
        zzak = Pattern.compile("[A-Z0-9_]*");
    }
    
    @SafeParcelable$Constructor
    DriveSpace(@SafeParcelable$Param(id = 2) final String s) {
        this.name = (String)Preconditions.checkNotNull((Object)s);
    }
    
    public boolean equals(final Object o) {
        return o != null && o.getClass() == DriveSpace.class && this.name.equals(((DriveSpace)o).name);
    }
    
    public int hashCode() {
        return 0x4A54C0DE ^ this.name.hashCode();
    }
    
    public String toString() {
        return this.name;
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
