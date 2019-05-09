package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "CustomPropertyKeyCreator")
@Reserved({1})
public class CustomPropertyKey extends AbstractSafeParcelable {
    public static final Creator<CustomPropertyKey> CREATOR = new zzc();
    public static final int PRIVATE = 1;
    public static final int PUBLIC = 0;
    private static final Pattern zzik = Pattern.compile("[\\w.!@$%^&*()/-]+");
    @Field(id = 3)
    private final int visibility;
    @Field(id = 2)
    private final String zzij;

    @Constructor
    public CustomPropertyKey(@Param(id = 2) String str, @Param(id = 3) int i) {
        boolean z = true;
        Preconditions.checkNotNull(str, ParametersKeys.KEY);
        Preconditions.checkArgument(zzik.matcher(str).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        if (!(i == 0 || i == 1)) {
            z = false;
        }
        Preconditions.checkArgument(z, "visibility must be either PUBLIC or PRIVATE");
        this.zzij = str;
        this.visibility = i;
    }

    public static CustomPropertyKey fromJson(JSONObject jSONObject) throws JSONException {
        return new CustomPropertyKey(jSONObject.getString(ParametersKeys.KEY), jSONObject.getInt("visibility"));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        CustomPropertyKey customPropertyKey = (CustomPropertyKey) obj;
        return customPropertyKey.getKey().equals(this.zzij) && customPropertyKey.getVisibility() == this.visibility;
    }

    public String getKey() {
        return this.zzij;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        String str = this.zzij;
        return new StringBuilder(String.valueOf(str).length() + 11).append(str).append(this.visibility).toString().hashCode();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ParametersKeys.KEY, getKey());
        jSONObject.put("visibility", getVisibility());
        return jSONObject;
    }

    public String toString() {
        String str = this.zzij;
        return new StringBuilder(String.valueOf(str).length() + 31).append("CustomPropertyKey(").append(str).append(",").append(this.visibility).append(")").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzij, false);
        SafeParcelWriter.writeInt(parcel, 3, this.visibility);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
