// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import java.util.UUID;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.Collections;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.internal.nearby.zzgp;
import com.google.android.gms.internal.nearby.zzgu;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.nearby.messages.internal.zzad;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "MessageFilterCreator")
public class MessageFilter extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<MessageFilter> CREATOR;
    public static final MessageFilter INCLUDE_ALL_MY_TYPES;
    @SafeParcelable$VersionField(id = 1000)
    private final int zzex;
    @SafeParcelable$Field(getter = "getMessageTypes", id = 1)
    private final List<zzad> zzey;
    @SafeParcelable$Field(getter = "getDeviceFilters", id = 2)
    private final List<zzgu> zzez;
    @SafeParcelable$Field(getter = "getIncludeAllMyTypes", id = 3)
    private final boolean zzfa;
    @SafeParcelable$Field(getter = "getBleFilters", id = 4)
    private final List<zzgp> zzfb;
    @SafeParcelable$Field(getter = "getNumRawAudioBytes", id = 5)
    private final int zzfc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
        INCLUDE_ALL_MY_TYPES = new Builder().includeAllMyTypes().build();
    }
    
    @SafeParcelable$Constructor
    MessageFilter(@SafeParcelable$Param(id = 1000) final int zzex, @SafeParcelable$Param(id = 1) final List<zzad> list, @SafeParcelable$Param(id = 2) final List<zzgu> list2, @SafeParcelable$Param(id = 3) final boolean zzfa, @SafeParcelable$Param(id = 4) final List<zzgp> list3, @SafeParcelable$Param(id = 5) final int zzfc) {
        this.zzex = zzex;
        this.zzey = Collections.unmodifiableList((List<? extends zzad>)Preconditions.checkNotNull((Object)list));
        this.zzfa = zzfa;
        List<? extends zzgu> emptyList = list2;
        if (list2 == null) {
            emptyList = Collections.emptyList();
        }
        this.zzez = (List<zzgu>)Collections.unmodifiableList((List<?>)emptyList);
        List<? extends zzgp> emptyList2;
        if ((emptyList2 = list3) == null) {
            emptyList2 = Collections.emptyList();
        }
        this.zzfb = (List<zzgp>)Collections.unmodifiableList((List<?>)emptyList2);
        this.zzfc = zzfc;
    }
    
    private MessageFilter(final List<zzad> list, final List<zzgu> list2, final boolean b, final List<zzgp> list3, final int n) {
        this(2, list, list2, b, list3, n);
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof MessageFilter)) {
                return false;
            }
            final MessageFilter messageFilter = (MessageFilter)o;
            if (this.zzfa != messageFilter.zzfa || !Objects.equal((Object)this.zzey, (Object)messageFilter.zzey) || !Objects.equal((Object)this.zzez, (Object)messageFilter.zzez) || !Objects.equal((Object)this.zzfb, (Object)messageFilter.zzfb)) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzey, this.zzez, this.zzfa, this.zzfb });
    }
    
    public String toString() {
        final boolean zzfa = this.zzfa;
        final String value = String.valueOf(this.zzey);
        return new StringBuilder(String.valueOf(value).length() + 53).append("MessageFilter{includeAllMyTypes=").append(zzfa).append(", messageTypes=").append(value).append("}").toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.zzey, false);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzez, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzfa);
        SafeParcelWriter.writeTypedList(parcel, 4, (List)this.zzfb, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzfc);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzex);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final List<zzad> zzaa() {
        return this.zzey;
    }
    
    public final boolean zzab() {
        return this.zzfa;
    }
    
    final List<zzgu> zzac() {
        return this.zzez;
    }
    
    public final List<zzgp> zzad() {
        return this.zzfb;
    }
    
    public static final class Builder
    {
        private final List<zzgu> zzez;
        private boolean zzfa;
        private int zzfc;
        private final Set<zzad> zzfd;
        private final Set<zzgp> zzfe;
        
        public Builder() {
            this.zzfd = new HashSet<zzad>();
            this.zzez = new ArrayList<zzgu>();
            this.zzfe = new HashSet<zzgp>();
            this.zzfc = 0;
        }
        
        private final Builder zza(final String s, final String s2) {
            this.zzfd.add(new zzad(s, s2));
            return this;
        }
        
        public final MessageFilter build() {
            Preconditions.checkState(this.zzfa || !this.zzfd.isEmpty(), (Object)"At least one of the include methods must be called.");
            return new MessageFilter(new ArrayList(this.zzfd), this.zzez, this.zzfa, new ArrayList(this.zzfe), this.zzfc, null);
        }
        
        public final Builder includeAllMyTypes() {
            this.zzfa = true;
            return this;
        }
        
        public final Builder includeAudioBytes(final int zzfc) {
            final boolean b = true;
            Preconditions.checkArgument(this.zzfc == 0, (Object)"includeAudioBytes() can only be called once per MessageFilter instance.");
            Preconditions.checkArgument(zzfc > 0, (Object)new StringBuilder(44).append("Invalid value for numAudioBytes: ").append(zzfc).toString());
            Preconditions.checkArgument(zzfc <= 10 && b, (Object)"numAudioBytes is capped by AudioBytes.MAX_SIZE = 10");
            this.zza("__reserved_namespace", "__audio_bytes");
            this.zzfc = zzfc;
            return this;
        }
        
        public final Builder includeEddystoneUids(final String s, @Nullable final String s2) {
            this.zza("__reserved_namespace", "__eddystone_uid");
            this.zzez.add(zzgu.zzb(s, s2));
            return this;
        }
        
        public final Builder includeFilter(final MessageFilter messageFilter) {
            this.zzfd.addAll(messageFilter.zzaa());
            this.zzez.addAll(messageFilter.zzac());
            this.zzfe.addAll(messageFilter.zzad());
            this.zzfa |= messageFilter.zzab();
            return this;
        }
        
        public final Builder includeIBeaconIds(final UUID uuid, @Nullable final Short n, @Nullable final Short n2) {
            this.zza("__reserved_namespace", "__i_beacon_id");
            this.zzez.add(zzgu.zza(uuid, n, n2));
            return this;
        }
        
        public final Builder includeNamespacedType(final String s, final String s2) {
            Preconditions.checkArgument(s != null && !s.isEmpty() && !s.contains("*"), "namespace(%s) cannot be null, empty or contain (*).", new Object[] { s });
            Preconditions.checkArgument(s2 != null && !s2.contains("*"), "type(%s) cannot be null or contain (*).", new Object[] { s2 });
            return this.zza(s, s2);
        }
    }
}
