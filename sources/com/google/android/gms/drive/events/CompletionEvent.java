package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.drive.zzev;
import com.google.android.gms.internal.drive.zzhp;
import com.tapjoy.TapjoyConstants;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Class(creator = "CompletionEventCreator")
@Reserved({1})
public final class CompletionEvent extends AbstractSafeParcelable implements ResourceEvent {
    public static final Creator<CompletionEvent> CREATOR = new zzg();
    public static final int STATUS_CANCELED = 3;
    public static final int STATUS_CONFLICT = 2;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_SUCCESS = 0;
    private static final GmsLogger zzbx = new GmsLogger("CompletionEvent", "");
    @Field(id = 8)
    private final int status;
    @Nullable
    @Field(id = 3)
    private final String zzby;
    @Nullable
    @Field(id = 4)
    private final ParcelFileDescriptor zzbz;
    @Nullable
    @Field(id = 5)
    private final ParcelFileDescriptor zzca;
    @Nullable
    @Field(id = 6)
    private final MetadataBundle zzcb;
    @Field(id = 7)
    private final List<String> zzcc;
    @Field(id = 9)
    private final IBinder zzcd;
    private boolean zzce = false;
    private boolean zzcf = false;
    private boolean zzcg = false;
    @Field(id = 2)
    private final DriveId zzk;

    @Constructor
    CompletionEvent(@Param(id = 2) DriveId driveId, @Param(id = 3) String str, @Param(id = 4) ParcelFileDescriptor parcelFileDescriptor, @Param(id = 5) ParcelFileDescriptor parcelFileDescriptor2, @Param(id = 6) MetadataBundle metadataBundle, @Param(id = 7) List<String> list, @Param(id = 8) int i, @Param(id = 9) IBinder iBinder) {
        this.zzk = driveId;
        this.zzby = str;
        this.zzbz = parcelFileDescriptor;
        this.zzca = parcelFileDescriptor2;
        this.zzcb = metadataBundle;
        this.zzcc = list;
        this.status = i;
        this.zzcd = iBinder;
    }

    private final void zza(boolean z) {
        zzu();
        this.zzcg = true;
        IOUtils.closeQuietly(this.zzbz);
        IOUtils.closeQuietly(this.zzca);
        if (this.zzcb != null && this.zzcb.zzd(zzhp.zzka)) {
            ((BitmapTeleporter) this.zzcb.zza(zzhp.zzka)).release();
        }
        if (this.zzcd == null) {
            String str = z ? "snooze" : TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL;
            zzbx.efmt("CompletionEvent", "No callback on %s", new Object[]{str});
            return;
        }
        try {
            zzev.zza(this.zzcd).zza(z);
        } catch (Throwable e) {
            Throwable th = e;
            str = z ? "snooze" : TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL;
            zzbx.e("CompletionEvent", String.format("RemoteException on %s", new Object[]{str}), th);
        }
    }

    private final void zzu() {
        if (this.zzcg) {
            throw new IllegalStateException("Event has already been dismissed or snoozed.");
        }
    }

    public final void dismiss() {
        zza(false);
    }

    @Nullable
    public final String getAccountName() {
        zzu();
        return this.zzby;
    }

    @Nullable
    public final InputStream getBaseContentsInputStream() {
        zzu();
        if (this.zzbz == null) {
            return null;
        }
        if (this.zzce) {
            throw new IllegalStateException("getBaseInputStream() can only be called once per CompletionEvent instance.");
        }
        this.zzce = true;
        return new FileInputStream(this.zzbz.getFileDescriptor());
    }

    public final DriveId getDriveId() {
        zzu();
        return this.zzk;
    }

    @Nullable
    public final InputStream getModifiedContentsInputStream() {
        zzu();
        if (this.zzca == null) {
            return null;
        }
        if (this.zzcf) {
            throw new IllegalStateException("getModifiedInputStream() can only be called once per CompletionEvent instance.");
        }
        this.zzcf = true;
        return new FileInputStream(this.zzca.getFileDescriptor());
    }

    @Nullable
    public final MetadataChangeSet getModifiedMetadataChangeSet() {
        zzu();
        return this.zzcb != null ? new MetadataChangeSet(this.zzcb) : null;
    }

    public final int getStatus() {
        zzu();
        return this.status;
    }

    public final List<String> getTrackingTags() {
        zzu();
        return new ArrayList(this.zzcc);
    }

    public final int getType() {
        return 2;
    }

    public final void snooze() {
        zza(true);
    }

    public final String toString() {
        String str;
        if (this.zzcc == null) {
            str = "<null>";
        } else {
            str = TextUtils.join("','", this.zzcc);
            str = new StringBuilder(String.valueOf(str).length() + 2).append("'").append(str).append("'").toString();
        }
        return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", new Object[]{this.zzk, Integer.valueOf(this.status), str});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i | 1;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzk, i2, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzby, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbz, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzca, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzcb, i2, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzcc, false);
        SafeParcelWriter.writeInt(parcel, 8, this.status);
        SafeParcelWriter.writeIBinder(parcel, 9, this.zzcd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
