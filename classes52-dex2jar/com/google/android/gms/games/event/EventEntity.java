// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.event;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.net.Uri;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "EventEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class EventEntity extends zzd implements Event
{
    public static final Parcelable$Creator<EventEntity> CREATOR;
    @SafeParcelable$Field(getter = "getDescription", id = 3)
    private final String description;
    @SafeParcelable$Field(getter = "getName", id = 2)
    private final String name;
    @SafeParcelable$Field(getter = "getValue", id = 7)
    private final long value;
    @SafeParcelable$Field(getter = "getIconImageUrl", id = 5)
    private final String zzac;
    @SafeParcelable$Field(getter = "getPlayer", id = 6)
    private final PlayerEntity zzfh;
    @SafeParcelable$Field(getter = "getEventId", id = 1)
    private final String zzfm;
    @SafeParcelable$Field(getter = "getFormattedValue", id = 8)
    private final String zzfn;
    @SafeParcelable$Field(getter = "isVisible", id = 9)
    private final boolean zzfo;
    @SafeParcelable$Field(getter = "getIconImageUri", id = 4)
    private final Uri zzr;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public EventEntity(final Event event) {
        this.zzfm = event.getEventId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.zzr = event.getIconImageUri();
        this.zzac = event.getIconImageUrl();
        this.zzfh = (PlayerEntity)event.getPlayer().freeze();
        this.value = event.getValue();
        this.zzfn = event.getFormattedValue();
        this.zzfo = event.isVisible();
    }
    
    @SafeParcelable$Constructor
    EventEntity(@SafeParcelable$Param(id = 1) final String zzfm, @SafeParcelable$Param(id = 2) final String name, @SafeParcelable$Param(id = 3) final String description, @SafeParcelable$Param(id = 4) final Uri zzr, @SafeParcelable$Param(id = 5) final String zzac, @SafeParcelable$Param(id = 6) final Player player, @SafeParcelable$Param(id = 7) final long value, @SafeParcelable$Param(id = 8) final String zzfn, @SafeParcelable$Param(id = 9) final boolean zzfo) {
        this.zzfm = zzfm;
        this.name = name;
        this.description = description;
        this.zzr = zzr;
        this.zzac = zzac;
        this.zzfh = new PlayerEntity(player);
        this.value = value;
        this.zzfn = zzfn;
        this.zzfo = zzfo;
    }
    
    static int zza(final Event event) {
        return Objects.hashCode(new Object[] { event.getEventId(), event.getName(), event.getDescription(), event.getIconImageUri(), event.getIconImageUrl(), event.getPlayer(), event.getValue(), event.getFormattedValue(), event.isVisible() });
    }
    
    static boolean zza(final Event event, final Object o) {
        if (o instanceof Event) {
            if (event == o) {
                return true;
            }
            final Event event2 = (Event)o;
            if (Objects.equal((Object)event2.getEventId(), (Object)event.getEventId()) && Objects.equal((Object)event2.getName(), (Object)event.getName()) && Objects.equal((Object)event2.getDescription(), (Object)event.getDescription()) && Objects.equal((Object)event2.getIconImageUri(), (Object)event.getIconImageUri()) && Objects.equal((Object)event2.getIconImageUrl(), (Object)event.getIconImageUrl()) && Objects.equal((Object)event2.getPlayer(), (Object)event.getPlayer()) && Objects.equal((Object)event2.getValue(), (Object)event.getValue()) && Objects.equal((Object)event2.getFormattedValue(), (Object)event.getFormattedValue()) && Objects.equal((Object)event2.isVisible(), (Object)event.isVisible())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final Event event) {
        return Objects.toStringHelper((Object)event).add("Id", (Object)event.getEventId()).add("Name", (Object)event.getName()).add("Description", (Object)event.getDescription()).add("IconImageUri", (Object)event.getIconImageUri()).add("IconImageUrl", (Object)event.getIconImageUrl()).add("Player", (Object)event.getPlayer()).add("Value", (Object)event.getValue()).add("FormattedValue", (Object)event.getFormattedValue()).add("isVisible", (Object)event.isVisible()).toString();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Event freeze() {
        return this;
    }
    
    @Override
    public final String getDescription() {
        return this.description;
    }
    
    @Override
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }
    
    @Override
    public final String getEventId() {
        return this.zzfm;
    }
    
    @Override
    public final String getFormattedValue() {
        return this.zzfn;
    }
    
    @Override
    public final void getFormattedValue(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzfn, charArrayBuffer);
    }
    
    @Override
    public final Uri getIconImageUri() {
        return this.zzr;
    }
    
    @Override
    public final String getIconImageUrl() {
        return this.zzac;
    }
    
    @Override
    public final String getName() {
        return this.name;
    }
    
    @Override
    public final void getName(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.name, charArrayBuffer);
    }
    
    @Override
    public final Player getPlayer() {
        return this.zzfh;
    }
    
    @Override
    public final long getValue() {
        return this.value;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    @Override
    public final boolean isVisible() {
        return this.zzfo;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getEventId(), false);
        SafeParcelWriter.writeString(parcel, 2, this.getName(), false);
        SafeParcelWriter.writeString(parcel, 3, this.getDescription(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.getIconImageUri(), n, false);
        SafeParcelWriter.writeString(parcel, 5, this.getIconImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.getPlayer(), n, false);
        SafeParcelWriter.writeLong(parcel, 7, this.getValue());
        SafeParcelWriter.writeString(parcel, 8, this.getFormattedValue(), false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.isVisible());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
