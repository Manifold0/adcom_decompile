// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.support.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "SafeBrowsingConfigParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzaiq extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaiq> CREATOR;
    @SafeParcelable$Field(id = 2)
    public final String zzcnd;
    @SafeParcelable$Field(id = 3)
    public final String zzcne;
    @SafeParcelable$Field(id = 4)
    public final boolean zzcnf;
    @SafeParcelable$Field(id = 5)
    public final boolean zzcng;
    @SafeParcelable$Field(id = 6)
    public final List<String> zzcnh;
    @SafeParcelable$Field(id = 7)
    public final boolean zzcni;
    @SafeParcelable$Field(id = 8)
    public final boolean zzcnj;
    @SafeParcelable$Field(id = 9)
    public final List<String> zzcnk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzair();
    }
    
    @SafeParcelable$Constructor
    public zzaiq(@SafeParcelable$Param(id = 2) final String zzcnd, @SafeParcelable$Param(id = 3) final String zzcne, @SafeParcelable$Param(id = 4) final boolean zzcnf, @SafeParcelable$Param(id = 5) final boolean zzcng, @SafeParcelable$Param(id = 6) final List<String> zzcnh, @SafeParcelable$Param(id = 7) final boolean zzcni, @SafeParcelable$Param(id = 8) final boolean zzcnj, @SafeParcelable$Param(id = 9) final List<String> list) {
        this.zzcnd = zzcnd;
        this.zzcne = zzcne;
        this.zzcnf = zzcnf;
        this.zzcng = zzcng;
        this.zzcnh = zzcnh;
        this.zzcni = zzcni;
        this.zzcnj = zzcnj;
        List<String> zzcnk = list;
        if (list == null) {
            zzcnk = new ArrayList<String>();
        }
        this.zzcnk = zzcnk;
    }
    
    @Nullable
    public static zzaiq zzo(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        return new zzaiq(jsonObject.optString("click_string", ""), jsonObject.optString("report_url", ""), jsonObject.optBoolean("rendered_ad_enabled", false), jsonObject.optBoolean("non_malicious_reporting_enabled", false), zzamd.zza(jsonObject.optJSONArray("allowed_headers"), null), jsonObject.optBoolean("protection_enabled", false), jsonObject.optBoolean("malicious_reporting_enabled", false), zzamd.zza(jsonObject.optJSONArray("webview_permissions"), null));
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcnd, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcne, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzcnf);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcng);
        SafeParcelWriter.writeStringList(parcel, 6, (List)this.zzcnh, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzcni);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzcnj);
        SafeParcelWriter.writeStringList(parcel, 9, (List)this.zzcnk, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
