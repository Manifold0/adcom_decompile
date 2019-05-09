// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import org.json.JSONObject;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Collections;
import android.support.annotation.Nullable;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@ParametersAreNonnullByDefault
@SafeParcelable$Class(creator = "AutoClickProtectionConfigurationParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzael extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzael> CREATOR;
    @SafeParcelable$Field(id = 2)
    public final boolean zzcfr;
    @Nullable
    @SafeParcelable$Field(id = 3)
    public final List<String> zzcfs;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaem();
    }
    
    public zzael() {
        this(false, Collections.emptyList());
    }
    
    @SafeParcelable$Constructor
    public zzael(@SafeParcelable$Param(id = 2) final boolean zzcfr, @SafeParcelable$Param(id = 3) final List<String> zzcfs) {
        this.zzcfr = zzcfr;
        this.zzcfs = zzcfs;
    }
    
    @Nullable
    public static zzael zzl(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return new zzael();
        }
        final JSONArray optJSONArray = jsonObject.optJSONArray("reporting_urls");
        final ArrayList<String> list = new ArrayList<String>();
        if (optJSONArray != null) {
            int i = 0;
        Label_0051_Outer:
            while (i < optJSONArray.length()) {
                while (true) {
                    try {
                        list.add(optJSONArray.getString(i));
                        ++i;
                        continue Label_0051_Outer;
                    }
                    catch (JSONException ex) {
                        zzakb.zzc("Error grabbing url from json.", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                break;
            }
        }
        return new zzael(jsonObject.optBoolean("enable_protection"), list);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzcfr);
        SafeParcelWriter.writeStringList(parcel, 3, (List)this.zzcfs, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
