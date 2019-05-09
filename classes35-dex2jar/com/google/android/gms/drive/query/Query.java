// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.zzx;
import com.google.android.gms.drive.query.internal.zzt;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "QueryCreator")
@SafeParcelable$Reserved({ 1000 })
public class Query extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<Query> CREATOR;
    @SafeParcelable$Field(id = 7)
    private final List<DriveSpace> zzbw;
    @SafeParcelable$Field(id = 1)
    private final zzr zzkw;
    @SafeParcelable$Field(id = 3)
    private final String zzkx;
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final SortOrder zzky;
    @SafeParcelable$Field(id = 5)
    final List<String> zzkz;
    @SafeParcelable$Field(id = 6)
    final boolean zzla;
    @SafeParcelable$Field(id = 8)
    final boolean zzlb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @SafeParcelable$Constructor
    Query(@SafeParcelable$Param(id = 1) final zzr zzkw, @SafeParcelable$Param(id = 3) final String zzkx, @Nullable @SafeParcelable$Param(id = 4) final SortOrder zzky, @NonNull @SafeParcelable$Param(id = 5) final List<String> zzkz, @SafeParcelable$Param(id = 6) final boolean zzla, @NonNull @SafeParcelable$Param(id = 7) final List<DriveSpace> zzbw, @SafeParcelable$Param(id = 8) final boolean zzlb) {
        this.zzkw = zzkw;
        this.zzkx = zzkx;
        this.zzky = zzky;
        this.zzkz = zzkz;
        this.zzla = zzla;
        this.zzbw = zzbw;
        this.zzlb = zzlb;
    }
    
    private Query(final zzr zzr, final String s, final SortOrder sortOrder, @NonNull final List<String> list, final boolean b, @NonNull final Set<DriveSpace> set, final boolean b2) {
        this(zzr, s, sortOrder, list, b, new ArrayList<DriveSpace>(set), b2);
    }
    
    public Filter getFilter() {
        return this.zzkw;
    }
    
    @Deprecated
    public String getPageToken() {
        return this.zzkx;
    }
    
    @Nullable
    public SortOrder getSortOrder() {
        return this.zzky;
    }
    
    public String toString() {
        return String.format(Locale.US, "Query[%s,%s,PageToken=%s,Spaces=%s]", this.zzkw, this.zzky, this.zzkx, this.zzbw);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzkw, n, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzkx, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzky, n, false);
        SafeParcelWriter.writeStringList(parcel, 5, (List)this.zzkz, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzla);
        SafeParcelWriter.writeTypedList(parcel, 7, (List)this.zzbw, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzlb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final Set<DriveSpace> zzba() {
        return new HashSet<DriveSpace>(this.zzbw);
    }
    
    @VisibleForTesting
    public static class Builder
    {
        private String zzkx;
        private SortOrder zzky;
        private List<String> zzkz;
        private boolean zzla;
        private boolean zzlb;
        private final List<Filter> zzlc;
        private Set<DriveSpace> zzld;
        
        public Builder() {
            this.zzlc = new ArrayList<Filter>();
            this.zzkz = Collections.emptyList();
            this.zzld = Collections.emptySet();
        }
        
        public Builder(final Query query) {
            this.zzlc = new ArrayList<Filter>();
            this.zzkz = Collections.emptyList();
            this.zzld = Collections.emptySet();
            this.zzlc.add(query.getFilter());
            this.zzkx = query.getPageToken();
            this.zzky = query.getSortOrder();
            this.zzkz = query.zzkz;
            this.zzla = query.zzla;
            query.zzba();
            this.zzld = query.zzba();
            this.zzlb = query.zzlb;
        }
        
        public Builder addFilter(@NonNull final Filter filter) {
            Preconditions.checkNotNull((Object)filter, (Object)"Filter may not be null.");
            if (!(filter instanceof zzt)) {
                this.zzlc.add(filter);
            }
            return this;
        }
        
        public Query build() {
            return new Query(new zzr(zzx.zzmf, this.zzlc), this.zzkx, this.zzky, this.zzkz, this.zzla, this.zzld, this.zzlb, null);
        }
        
        @Deprecated
        public Builder setPageToken(final String zzkx) {
            this.zzkx = zzkx;
            return this;
        }
        
        public Builder setSortOrder(final SortOrder zzky) {
            this.zzky = zzky;
            return this;
        }
    }
}
