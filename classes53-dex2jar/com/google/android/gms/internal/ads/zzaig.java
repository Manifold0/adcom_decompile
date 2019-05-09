// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONArray;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "RewardItemParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzaig extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaig> CREATOR;
    @SafeParcelable$Field(id = 2)
    public final String type;
    @SafeParcelable$Field(id = 3)
    public final int zzcmk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaih();
    }
    
    public zzaig(final RewardItem rewardItem) {
        this(rewardItem.getType(), rewardItem.getAmount());
    }
    
    @SafeParcelable$Constructor
    public zzaig(@SafeParcelable$Param(id = 2) final String type, @SafeParcelable$Param(id = 3) final int zzcmk) {
        this.type = type;
        this.zzcmk = zzcmk;
    }
    
    @Nullable
    public static zzaig zza(final JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        return new zzaig(jsonArray.getJSONObject(0).optString("rb_type"), jsonArray.getJSONObject(0).optInt("rb_amount"));
    }
    
    @Nullable
    public static zzaig zzce(@Nullable final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        try {
            return zza(new JSONArray(s));
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public final boolean equals(final Object o) {
        if (o != null && o instanceof zzaig) {
            final zzaig zzaig = (zzaig)o;
            if (Objects.equal((Object)this.type, (Object)zzaig.type) && Objects.equal((Object)this.zzcmk, (Object)zzaig.zzcmk)) {
                return true;
            }
        }
        return false;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.type, this.zzcmk });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.type, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcmk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
