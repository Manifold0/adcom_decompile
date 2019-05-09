package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "EventEntityCreator")
@Reserved({1000})
public final class EventEntity extends zzd implements Event {
    public static final Creator<EventEntity> CREATOR = new zza();
    @Field(getter = "getDescription", id = 3)
    private final String description;
    @Field(getter = "getName", id = 2)
    private final String name;
    @Field(getter = "getValue", id = 7)
    private final long value;
    @Field(getter = "getIconImageUrl", id = 5)
    private final String zzac;
    @Field(getter = "getPlayer", id = 6)
    private final PlayerEntity zzfh;
    @Field(getter = "getEventId", id = 1)
    private final String zzfm;
    @Field(getter = "getFormattedValue", id = 8)
    private final String zzfn;
    @Field(getter = "isVisible", id = 9)
    private final boolean zzfo;
    @Field(getter = "getIconImageUri", id = 4)
    private final Uri zzr;

    public EventEntity(Event event) {
        this.zzfm = event.getEventId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.zzr = event.getIconImageUri();
        this.zzac = event.getIconImageUrl();
        this.zzfh = (PlayerEntity) event.getPlayer().freeze();
        this.value = event.getValue();
        this.zzfn = event.getFormattedValue();
        this.zzfo = event.isVisible();
    }

    @Constructor
    EventEntity(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) String str3, @Param(id = 4) Uri uri, @Param(id = 5) String str4, @Param(id = 6) Player player, @Param(id = 7) long j, @Param(id = 8) String str5, @Param(id = 9) boolean z) {
        this.zzfm = str;
        this.name = str2;
        this.description = str3;
        this.zzr = uri;
        this.zzac = str4;
        this.zzfh = new PlayerEntity(player);
        this.value = j;
        this.zzfn = str5;
        this.zzfo = z;
    }

    static int zza(Event event) {
        return Objects.hashCode(new Object[]{event.getEventId(), event.getName(), event.getDescription(), event.getIconImageUri(), event.getIconImageUrl(), event.getPlayer(), Long.valueOf(event.getValue()), event.getFormattedValue(), Boolean.valueOf(event.isVisible())});
    }

    static boolean zza(Event event, Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        if (event == obj) {
            return true;
        }
        Event event2 = (Event) obj;
        return Objects.equal(event2.getEventId(), event.getEventId()) && Objects.equal(event2.getName(), event.getName()) && Objects.equal(event2.getDescription(), event.getDescription()) && Objects.equal(event2.getIconImageUri(), event.getIconImageUri()) && Objects.equal(event2.getIconImageUrl(), event.getIconImageUrl()) && Objects.equal(event2.getPlayer(), event.getPlayer()) && Objects.equal(Long.valueOf(event2.getValue()), Long.valueOf(event.getValue())) && Objects.equal(event2.getFormattedValue(), event.getFormattedValue()) && Objects.equal(Boolean.valueOf(event2.isVisible()), Boolean.valueOf(event.isVisible()));
    }

    static String zzb(Event event) {
        return Objects.toStringHelper(event).add("Id", event.getEventId()).add("Name", event.getName()).add("Description", event.getDescription()).add("IconImageUri", event.getIconImageUri()).add("IconImageUrl", event.getIconImageUrl()).add("Player", event.getPlayer()).add("Value", Long.valueOf(event.getValue())).add("FormattedValue", event.getFormattedValue()).add("isVisible", Boolean.valueOf(event.isVisible())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Event freeze() {
        return this;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    public final String getEventId() {
        return this.zzfm;
    }

    public final String getFormattedValue() {
        return this.zzfn;
    }

    public final void getFormattedValue(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzfn, charArrayBuffer);
    }

    public final Uri getIconImageUri() {
        return this.zzr;
    }

    public final String getIconImageUrl() {
        return this.zzac;
    }

    public final String getName() {
        return this.name;
    }

    public final void getName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.name, charArrayBuffer);
    }

    public final Player getPlayer() {
        return this.zzfh;
    }

    public final long getValue() {
        return this.value;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isVisible() {
        return this.zzfo;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getEventId(), false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeString(parcel, 3, getDescription(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getIconImageUri(), i, false);
        SafeParcelWriter.writeString(parcel, 5, getIconImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, getPlayer(), i, false);
        SafeParcelWriter.writeLong(parcel, 7, getValue());
        SafeParcelWriter.writeString(parcel, 8, getFormattedValue(), false);
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
