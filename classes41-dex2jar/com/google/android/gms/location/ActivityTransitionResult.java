// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.Collections;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ActivityTransitionResultCreator")
@SafeParcelable$Reserved({ 1000 })
public class ActivityTransitionResult extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ActivityTransitionResult> CREATOR;
    @SafeParcelable$Field(getter = "getTransitionEvents", id = 1)
    private final List<ActivityTransitionEvent> zzn;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    @SafeParcelable$Constructor
    public ActivityTransitionResult(@SafeParcelable$Param(id = 1) final List<ActivityTransitionEvent> list) {
        Preconditions.checkNotNull((Object)list, (Object)"transitionEvents list can't be null.");
        if (!list.isEmpty()) {
            for (int i = 1; i < list.size(); ++i) {
                Preconditions.checkArgument(((ActivityTransitionEvent)list.get(i)).getElapsedRealTimeNanos() >= list.get(i - 1).getElapsedRealTimeNanos());
            }
        }
        this.zzn = Collections.unmodifiableList((List<? extends ActivityTransitionEvent>)list);
    }
    
    @Nullable
    public static ActivityTransitionResult extractResult(final Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        return (ActivityTransitionResult)SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT", (Parcelable$Creator)ActivityTransitionResult.CREATOR);
    }
    
    public static boolean hasResult(@Nullable final Intent intent) {
        return intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT");
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && this.zzn.equals(((ActivityTransitionResult)o).zzn));
    }
    
    public List<ActivityTransitionEvent> getTransitionEvents() {
        return this.zzn;
    }
    
    public int hashCode() {
        return this.zzn.hashCode();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.getTransitionEvents(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
