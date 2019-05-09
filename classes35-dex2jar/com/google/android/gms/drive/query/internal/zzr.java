// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.query.Filter;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "LogicalFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzr extends zza
{
    public static final Parcelable$Creator<zzr> CREATOR;
    private List<Filter> zzlc;
    @SafeParcelable$Field(id = 1)
    private final zzx zzlj;
    @SafeParcelable$Field(id = 2)
    private final List<FilterHolder> zzly;
    
    static {
        CREATOR = (Parcelable$Creator)new zzs();
    }
    
    public zzr(final zzx zzlj, final Filter filter, final Filter... array) {
        this.zzlj = zzlj;
        (this.zzly = new ArrayList<FilterHolder>(array.length + 1)).add(new FilterHolder(filter));
        (this.zzlc = new ArrayList<Filter>(array.length + 1)).add(filter);
        for (int length = array.length, i = 0; i < length; ++i) {
            final Filter filter2 = array[i];
            this.zzly.add(new FilterHolder(filter2));
            this.zzlc.add(filter2);
        }
    }
    
    public zzr(final zzx zzlj, final Iterable<Filter> iterable) {
        this.zzlj = zzlj;
        this.zzlc = new ArrayList<Filter>();
        this.zzly = new ArrayList<FilterHolder>();
        for (final Filter filter : iterable) {
            this.zzlc.add(filter);
            this.zzly.add(new FilterHolder(filter));
        }
    }
    
    @SafeParcelable$Constructor
    zzr(@SafeParcelable$Param(id = 1) final zzx zzlj, @SafeParcelable$Param(id = 2) final List<FilterHolder> zzly) {
        this.zzlj = zzlj;
        this.zzly = zzly;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzlj, n, false);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzly, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <T> T zza(final zzj<T> zzj) {
        final ArrayList<T> list = new ArrayList<T>();
        final Iterator<FilterHolder> iterator = this.zzly.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getFilter().zza(zzj));
        }
        return zzj.zza(this.zzlj, list);
    }
}
