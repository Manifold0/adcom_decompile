package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "LogicalFilterCreator")
@Reserved({1000})
public final class zzr extends zza {
    public static final Creator<zzr> CREATOR = new zzs();
    private List<Filter> zzlc;
    @Field(id = 1)
    private final zzx zzlj;
    @Field(id = 2)
    private final List<FilterHolder> zzly;

    public zzr(zzx zzx, Filter filter, Filter... filterArr) {
        this.zzlj = zzx;
        this.zzly = new ArrayList(filterArr.length + 1);
        this.zzly.add(new FilterHolder(filter));
        this.zzlc = new ArrayList(filterArr.length + 1);
        this.zzlc.add(filter);
        for (Filter filter2 : filterArr) {
            this.zzly.add(new FilterHolder(filter2));
            this.zzlc.add(filter2);
        }
    }

    public zzr(zzx zzx, Iterable<Filter> iterable) {
        this.zzlj = zzx;
        this.zzlc = new ArrayList();
        this.zzly = new ArrayList();
        for (Filter filter : iterable) {
            this.zzlc.add(filter);
            this.zzly.add(new FilterHolder(filter));
        }
    }

    @Constructor
    zzr(@Param(id = 1) zzx zzx, @Param(id = 2) List<FilterHolder> list) {
        this.zzlj = zzx;
        this.zzly = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlj, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzly, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final <T> T zza(zzj<T> zzj) {
        List arrayList = new ArrayList();
        for (FilterHolder filter : this.zzly) {
            arrayList.add(filter.getFilter().zza(zzj));
        }
        return zzj.zza(this.zzlj, arrayList);
    }
}
