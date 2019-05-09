// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.text.TextUtils;
import java.util.Locale;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.query.internal.zzf;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SortOrderCreator")
@SafeParcelable$Reserved({ 1000 })
public class SortOrder extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<SortOrder> CREATOR;
    @SafeParcelable$Field(id = 1)
    private final List<zzf> zzlg;
    @SafeParcelable$Field(defaultValue = "false", id = 2)
    private final boolean zzlh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    SortOrder(@SafeParcelable$Param(id = 1) final List<zzf> zzlg, @SafeParcelable$Param(id = 2) final boolean zzlh) {
        this.zzlg = zzlg;
        this.zzlh = zzlh;
    }
    
    public String toString() {
        return String.format(Locale.US, "SortOrder[%s, %s]", TextUtils.join((CharSequence)",", (Iterable)this.zzlg), this.zzlh);
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.zzlg, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzlh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static class Builder
    {
        private final List<zzf> zzlg;
        private boolean zzlh;
        
        public Builder() {
            this.zzlg = new ArrayList<zzf>();
            this.zzlh = false;
        }
        
        public Builder addSortAscending(final SortableMetadataField sortableMetadataField) {
            this.zzlg.add(new zzf(sortableMetadataField.getName(), true));
            return this;
        }
        
        public Builder addSortDescending(final SortableMetadataField sortableMetadataField) {
            this.zzlg.add(new zzf(sortableMetadataField.getName(), false));
            return this;
        }
        
        public SortOrder build() {
            return new SortOrder(this.zzlg, false);
        }
    }
}
