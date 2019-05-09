// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import android.content.Intent;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.Iterator;
import java.util.Collections;
import java.util.TreeSet;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.ClientIdentity;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import java.util.Comparator;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ActivityTransitionRequestCreator")
@SafeParcelable$Reserved({ 1000 })
public class ActivityTransitionRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ActivityTransitionRequest> CREATOR;
    public static final Comparator<ActivityTransition> IS_SAME_TRANSITION;
    @Nullable
    @SafeParcelable$Field(getter = "getTag", id = 2)
    private final String tag;
    @SafeParcelable$Field(getter = "getActivityTransitions", id = 1)
    private final List<ActivityTransition> zzl;
    @SafeParcelable$Field(getter = "getClients", id = 3)
    private final List<ClientIdentity> zzm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
        IS_SAME_TRANSITION = new zze();
    }
    
    public ActivityTransitionRequest(final List<ActivityTransition> list) {
        this(list, null, null);
    }
    
    @SafeParcelable$Constructor
    public ActivityTransitionRequest(@SafeParcelable$Param(id = 1) final List<ActivityTransition> list, @Nullable @SafeParcelable$Param(id = 2) final String tag, @Nullable @SafeParcelable$Param(id = 3) final List<ClientIdentity> list2) {
        Preconditions.checkNotNull((Object)list, (Object)"transitions can't be null");
        Preconditions.checkArgument(list.size() > 0, (Object)"transitions can't be empty.");
        final TreeSet<ActivityTransition> set = new TreeSet<ActivityTransition>(ActivityTransitionRequest.IS_SAME_TRANSITION);
        for (final ActivityTransition activityTransition : list) {
            Preconditions.checkArgument(set.add(activityTransition), (Object)String.format("Found duplicated transition: %s.", activityTransition));
        }
        this.zzl = Collections.unmodifiableList((List<? extends ActivityTransition>)list);
        this.tag = tag;
        List<ClientIdentity> zzm;
        if (list2 == null) {
            zzm = Collections.emptyList();
        }
        else {
            zzm = Collections.unmodifiableList((List<? extends ClientIdentity>)list2);
        }
        this.zzm = zzm;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ActivityTransitionRequest activityTransitionRequest = (ActivityTransitionRequest)o;
            if (!Objects.equal((Object)this.zzl, (Object)activityTransitionRequest.zzl) || !Objects.equal((Object)this.tag, (Object)activityTransitionRequest.tag) || !Objects.equal((Object)this.zzm, (Object)activityTransitionRequest.zzm)) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = this.zzl.hashCode();
        int hashCode3;
        if (this.tag != null) {
            hashCode3 = this.tag.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        if (this.zzm != null) {
            hashCode = this.zzm.hashCode();
        }
        return (hashCode3 + hashCode2 * 31) * 31 + hashCode;
    }
    
    public void serializeToIntentExtra(final Intent intent) {
        SafeParcelableSerializer.serializeToIntentExtra((SafeParcelable)this, intent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_REQUEST");
    }
    
    public String toString() {
        final String value = String.valueOf(this.zzl);
        final String tag = this.tag;
        final String value2 = String.valueOf(this.zzm);
        return new StringBuilder(String.valueOf(value).length() + 61 + String.valueOf(tag).length() + String.valueOf(value2).length()).append("ActivityTransitionRequest [mTransitions=").append(value).append(", mTag='").append(tag).append('\'').append(", mClients=").append(value2).append(']').toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.zzl, false);
        SafeParcelWriter.writeString(parcel, 2, this.tag, false);
        SafeParcelWriter.writeTypedList(parcel, 3, (List)this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
