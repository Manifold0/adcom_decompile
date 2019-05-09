// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.regex.Pattern;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CustomPropertyKeyCreator")
@SafeParcelable$Reserved({ 1 })
public class CustomPropertyKey extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<CustomPropertyKey> CREATOR;
    public static final int PRIVATE = 1;
    public static final int PUBLIC = 0;
    private static final Pattern zzik;
    @SafeParcelable$Field(id = 3)
    private final int visibility;
    @SafeParcelable$Field(id = 2)
    private final String zzij;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
        zzik = Pattern.compile("[\\w.!@$%^&*()/-]+");
    }
    
    @SafeParcelable$Constructor
    public CustomPropertyKey(@SafeParcelable$Param(id = 2) final String zzij, @SafeParcelable$Param(id = 3) final int visibility) {
        final boolean b = true;
        Preconditions.checkNotNull((Object)zzij, (Object)"key");
        Preconditions.checkArgument(CustomPropertyKey.zzik.matcher(zzij).matches(), (Object)"key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        boolean b2 = b;
        if (visibility != 0) {
            b2 = (visibility == 1 && b);
        }
        Preconditions.checkArgument(b2, (Object)"visibility must be either PUBLIC or PRIVATE");
        this.zzij = zzij;
        this.visibility = visibility;
    }
    
    public static CustomPropertyKey fromJson(final JSONObject jsonObject) throws JSONException {
        return new CustomPropertyKey(jsonObject.getString("key"), jsonObject.getInt("visibility"));
    }
    
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            final CustomPropertyKey customPropertyKey = (CustomPropertyKey)o;
            if (!customPropertyKey.getKey().equals(this.zzij) || customPropertyKey.getVisibility() != this.visibility) {
                return false;
            }
        }
        return true;
    }
    
    public String getKey() {
        return this.zzij;
    }
    
    public int getVisibility() {
        return this.visibility;
    }
    
    public int hashCode() {
        final String zzij = this.zzij;
        return new StringBuilder(String.valueOf(zzij).length() + 11).append(zzij).append(this.visibility).toString().hashCode();
    }
    
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", (Object)this.getKey());
        jsonObject.put("visibility", this.getVisibility());
        return jsonObject;
    }
    
    public String toString() {
        final String zzij = this.zzij;
        return new StringBuilder(String.valueOf(zzij).length() + 31).append("CustomPropertyKey(").append(zzij).append(",").append(this.visibility).append(")").toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzij, false);
        SafeParcelWriter.writeInt(parcel, 3, this.visibility);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
