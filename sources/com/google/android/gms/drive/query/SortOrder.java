package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.query.internal.zzf;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Class(creator = "SortOrderCreator")
@Reserved({1000})
public class SortOrder extends AbstractSafeParcelable {
    public static final Creator<SortOrder> CREATOR = new zzc();
    @Field(id = 1)
    private final List<zzf> zzlg;
    @Field(defaultValue = "false", id = 2)
    private final boolean zzlh;

    public static class Builder {
        private final List<zzf> zzlg = new ArrayList();
        private boolean zzlh = false;

        public Builder addSortAscending(SortableMetadataField sortableMetadataField) {
            this.zzlg.add(new zzf(sortableMetadataField.getName(), true));
            return this;
        }

        public Builder addSortDescending(SortableMetadataField sortableMetadataField) {
            this.zzlg.add(new zzf(sortableMetadataField.getName(), false));
            return this;
        }

        public SortOrder build() {
            return new SortOrder(this.zzlg, false);
        }
    }

    @Constructor
    SortOrder(@Param(id = 1) List<zzf> list, @Param(id = 2) boolean z) {
        this.zzlg = list;
        this.zzlh = z;
    }

    public String toString() {
        return String.format(Locale.US, "SortOrder[%s, %s]", new Object[]{TextUtils.join(",", this.zzlg), Boolean.valueOf(this.zzlh)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzlg, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzlh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
