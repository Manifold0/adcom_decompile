// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.internal.drive.zzho;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.drive.zzdp;
import com.google.android.gms.internal.drive.zzbs;
import com.google.android.gms.internal.drive.zzbn;
import com.google.android.gms.internal.drive.zziw;
import com.google.android.gms.internal.drive.zzix;
import com.google.android.gms.internal.drive.zzhn;
import com.google.android.gms.common.util.VisibleForTesting;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DriveIdCreator")
@SafeParcelable$Reserved({ 1 })
public class DriveId extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<DriveId> CREATOR;
    public static final int RESOURCE_TYPE_FILE = 0;
    public static final int RESOURCE_TYPE_FOLDER = 1;
    public static final int RESOURCE_TYPE_UNKNOWN = -1;
    @SafeParcelable$Field(id = 2)
    private final String zzab;
    @SafeParcelable$Field(id = 3)
    private final long zzac;
    @SafeParcelable$Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveId.RESOURCE_TYPE_UNKNOWN", id = 5)
    private final int zzad;
    private volatile String zzae;
    @SafeParcelable$Field(id = 4)
    private final long zzf;
    private volatile String zzh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    @SafeParcelable$Constructor
    public DriveId(@SafeParcelable$Param(id = 2) final String zzab, @SafeParcelable$Param(id = 3) final long zzac, @SafeParcelable$Param(id = 4) final long zzf, @SafeParcelable$Param(id = 5) final int zzad) {
        final boolean b = false;
        this.zzh = null;
        this.zzae = null;
        this.zzab = zzab;
        Preconditions.checkArgument(!"".equals(zzab));
        boolean b2 = false;
        Label_0058: {
            if (zzab == null) {
                b2 = b;
                if (zzac == -1L) {
                    break Label_0058;
                }
            }
            b2 = true;
        }
        Preconditions.checkArgument(b2);
        this.zzac = zzac;
        this.zzf = zzf;
        this.zzad = zzad;
    }
    
    public static DriveId decodeFromString(final String s) {
        final boolean startsWith = s.startsWith("DriveId:");
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "Invalid DriveId: ".concat(value);
        }
        else {
            concat = new String("Invalid DriveId: ");
        }
        Preconditions.checkArgument(startsWith, (Object)concat);
        return zza(Base64.decode(s.substring(8), 10));
    }
    
    @VisibleForTesting
    public static DriveId zza(final String s) {
        Preconditions.checkNotNull((Object)s);
        return new DriveId(s, -1L, -1L, -1);
    }
    
    @VisibleForTesting
    private static DriveId zza(final byte[] array) {
        while (true) {
            while (true) {
                zzhn zzhn;
                try {
                    zzhn = zzix.zza(new zzhn(), array, 0, array.length);
                    if ("".equals(zzhn.zzab)) {
                        final String zzab = null;
                        return new DriveId(zzab, zzhn.zzac, zzhn.zzf, zzhn.zzad);
                    }
                }
                catch (zziw zziw) {
                    throw new IllegalArgumentException();
                }
                final String zzab = zzhn.zzab;
                continue;
            }
        }
    }
    
    public DriveFile asDriveFile() {
        if (this.zzad == 1) {
            throw new IllegalStateException("This DriveId corresponds to a folder. Call asDriveFolder instead.");
        }
        return new zzbn(this);
    }
    
    public DriveFolder asDriveFolder() {
        if (this.zzad == 0) {
            throw new IllegalStateException("This DriveId corresponds to a file. Call asDriveFile instead.");
        }
        return new zzbs(this);
    }
    
    public DriveResource asDriveResource() {
        if (this.zzad == 1) {
            return this.asDriveFolder();
        }
        if (this.zzad == 0) {
            return this.asDriveFile();
        }
        return new zzdp(this);
    }
    
    public final String encodeToString() {
        if (this.zzh == null) {
            final zzhn zzhn = new zzhn();
            zzhn.versionCode = 1;
            String zzab;
            if (this.zzab == null) {
                zzab = "";
            }
            else {
                zzab = this.zzab;
            }
            zzhn.zzab = zzab;
            zzhn.zzac = this.zzac;
            zzhn.zzf = this.zzf;
            zzhn.zzad = this.zzad;
            final String encodeToString = Base64.encodeToString(zzix.zza(zzhn), 10);
            final String value = String.valueOf("DriveId:");
            final String value2 = String.valueOf(encodeToString);
            String concat;
            if (value2.length() != 0) {
                concat = value.concat(value2);
            }
            else {
                concat = new String(value);
            }
            this.zzh = concat;
        }
        return this.zzh;
    }
    
    public boolean equals(final Object o) {
        if (o != null && o.getClass() == DriveId.class) {
            final DriveId driveId = (DriveId)o;
            if (driveId.zzf == this.zzf) {
                if (driveId.zzac == -1L && this.zzac == -1L) {
                    return driveId.zzab.equals(this.zzab);
                }
                if (this.zzab == null || driveId.zzab == null) {
                    if (driveId.zzac == this.zzac) {
                        return true;
                    }
                }
                else if (driveId.zzac == this.zzac && driveId.zzab.equals(this.zzab)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Nullable
    public String getResourceId() {
        return this.zzab;
    }
    
    public int getResourceType() {
        return this.zzad;
    }
    
    public int hashCode() {
        if (this.zzac == -1L) {
            return this.zzab.hashCode();
        }
        final String value = String.valueOf(String.valueOf(this.zzf));
        final String value2 = String.valueOf(String.valueOf(this.zzac));
        String concat;
        if (value2.length() != 0) {
            concat = value.concat(value2);
        }
        else {
            concat = new String(value);
        }
        return concat.hashCode();
    }
    
    public final String toInvariantString() {
        if (this.zzae == null) {
            final zzho zzho = new zzho();
            zzho.zzac = this.zzac;
            zzho.zzf = this.zzf;
            this.zzae = Base64.encodeToString(zzix.zza(zzho), 10);
        }
        return this.zzae;
    }
    
    public String toString() {
        return this.encodeToString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzab, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzac);
        SafeParcelWriter.writeLong(parcel, 4, this.zzf);
        SafeParcelWriter.writeInt(parcel, 5, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
