package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.internal.nearby.zzgp;
import com.google.android.gms.internal.nearby.zzgu;
import com.google.android.gms.nearby.messages.internal.zzad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Class(creator = "MessageFilterCreator")
public class MessageFilter extends AbstractSafeParcelable {
    public static final Creator<MessageFilter> CREATOR = new zzc();
    public static final MessageFilter INCLUDE_ALL_MY_TYPES = new Builder().includeAllMyTypes().build();
    @VersionField(id = 1000)
    private final int zzex;
    @Field(getter = "getMessageTypes", id = 1)
    private final List<zzad> zzey;
    @Field(getter = "getDeviceFilters", id = 2)
    private final List<zzgu> zzez;
    @Field(getter = "getIncludeAllMyTypes", id = 3)
    private final boolean zzfa;
    @Field(getter = "getBleFilters", id = 4)
    private final List<zzgp> zzfb;
    @Field(getter = "getNumRawAudioBytes", id = 5)
    private final int zzfc;

    public static final class Builder {
        private final List<zzgu> zzez = new ArrayList();
        private boolean zzfa;
        private int zzfc = 0;
        private final Set<zzad> zzfd = new HashSet();
        private final Set<zzgp> zzfe = new HashSet();

        private final Builder zza(String str, String str2) {
            this.zzfd.add(new zzad(str, str2));
            return this;
        }

        public final MessageFilter build() {
            boolean z = this.zzfa || !this.zzfd.isEmpty();
            Preconditions.checkState(z, "At least one of the include methods must be called.");
            return new MessageFilter(new ArrayList(this.zzfd), this.zzez, this.zzfa, new ArrayList(this.zzfe), this.zzfc);
        }

        public final Builder includeAllMyTypes() {
            this.zzfa = true;
            return this;
        }

        public final Builder includeAudioBytes(int i) {
            boolean z = true;
            Preconditions.checkArgument(this.zzfc == 0, "includeAudioBytes() can only be called once per MessageFilter instance.");
            Preconditions.checkArgument(i > 0, "Invalid value for numAudioBytes: " + i);
            if (i > 10) {
                z = false;
            }
            Preconditions.checkArgument(z, "numAudioBytes is capped by AudioBytes.MAX_SIZE = 10");
            zza(Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_AUDIO_BYTES);
            this.zzfc = i;
            return this;
        }

        public final Builder includeEddystoneUids(String str, @Nullable String str2) {
            zza(Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_EDDYSTONE_UID);
            this.zzez.add(zzgu.zzb(str, str2));
            return this;
        }

        public final Builder includeFilter(MessageFilter messageFilter) {
            this.zzfd.addAll(messageFilter.zzaa());
            this.zzez.addAll(messageFilter.zzac());
            this.zzfe.addAll(messageFilter.zzad());
            this.zzfa |= messageFilter.zzab();
            return this;
        }

        public final Builder includeIBeaconIds(UUID uuid, @Nullable Short sh, @Nullable Short sh2) {
            zza(Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_I_BEACON_ID);
            this.zzez.add(zzgu.zza(uuid, sh, sh2));
            return this;
        }

        public final Builder includeNamespacedType(String str, String str2) {
            boolean z = (str == null || str.isEmpty() || str.contains("*")) ? false : true;
            Preconditions.checkArgument(z, "namespace(%s) cannot be null, empty or contain (*).", new Object[]{str});
            z = (str2 == null || str2.contains("*")) ? false : true;
            Preconditions.checkArgument(z, "type(%s) cannot be null or contain (*).", new Object[]{str2});
            return zza(str, str2);
        }
    }

    @Constructor
    MessageFilter(@Param(id = 1000) int i, @Param(id = 1) List<zzad> list, @Param(id = 2) List<zzgu> list2, @Param(id = 3) boolean z, @Param(id = 4) List<zzgp> list3, @Param(id = 5) int i2) {
        List emptyList;
        List emptyList2;
        this.zzex = i;
        this.zzey = Collections.unmodifiableList((List) Preconditions.checkNotNull(list));
        this.zzfa = z;
        if (list2 == null) {
            emptyList = Collections.emptyList();
        }
        this.zzez = Collections.unmodifiableList(emptyList);
        if (list3 == null) {
            emptyList2 = Collections.emptyList();
        }
        this.zzfb = Collections.unmodifiableList(emptyList2);
        this.zzfc = i2;
    }

    private MessageFilter(List<zzad> list, List<zzgu> list2, boolean z, List<zzgp> list3, int i) {
        this(2, (List) list, (List) list2, z, (List) list3, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessageFilter)) {
            return false;
        }
        MessageFilter messageFilter = (MessageFilter) obj;
        return this.zzfa == messageFilter.zzfa && Objects.equal(this.zzey, messageFilter.zzey) && Objects.equal(this.zzez, messageFilter.zzez) && Objects.equal(this.zzfb, messageFilter.zzfb);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzey, this.zzez, Boolean.valueOf(this.zzfa), this.zzfb});
    }

    public String toString() {
        boolean z = this.zzfa;
        String valueOf = String.valueOf(this.zzey);
        return new StringBuilder(String.valueOf(valueOf).length() + 53).append("MessageFilter{includeAllMyTypes=").append(z).append(", messageTypes=").append(valueOf).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzey, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzez, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzfa);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzfb, false);
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
}
