// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.response;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@ShowFirstParty
@SafeParcelable$Class(creator = "FieldMappingDictionaryEntryCreator")
public final class zal extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zal> CREATOR;
    @SafeParcelable$Field(id = 2)
    final String className;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(id = 3)
    final ArrayList<zam> zaqy;
    
    static {
        CREATOR = (Parcelable$Creator)new zao();
    }
    
    @SafeParcelable$Constructor
    zal(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final String className, @SafeParcelable$Param(id = 3) final ArrayList<zam> zaqy) {
        this.versionCode = versionCode;
        this.className = className;
        this.zaqy = zaqy;
    }
    
    zal(final String className, final Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.versionCode = 1;
        this.className = className;
        ArrayList<zam> zaqy;
        if (map == null) {
            zaqy = null;
        }
        else {
            zaqy = new ArrayList<zam>();
            for (final String s : map.keySet()) {
                zaqy.add(new zam(s, map.get(s)));
            }
        }
        this.zaqy = zaqy;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.className, false);
        SafeParcelWriter.writeTypedList(parcel, 3, (List)this.zaqy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
