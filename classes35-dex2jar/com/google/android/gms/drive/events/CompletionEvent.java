// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import android.text.TextUtils;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.drive.MetadataChangeSet;
import java.io.FileInputStream;
import java.io.InputStream;
import android.os.RemoteException;
import com.google.android.gms.internal.drive.zzev;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.DriveId;
import android.os.IBinder;
import java.util.List;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.GmsLogger;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CompletionEventCreator")
@SafeParcelable$Reserved({ 1 })
public final class CompletionEvent extends AbstractSafeParcelable implements ResourceEvent
{
    public static final Parcelable$Creator<CompletionEvent> CREATOR;
    public static final int STATUS_CANCELED = 3;
    public static final int STATUS_CONFLICT = 2;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_SUCCESS = 0;
    private static final GmsLogger zzbx;
    @SafeParcelable$Field(id = 8)
    private final int status;
    @Nullable
    @SafeParcelable$Field(id = 3)
    private final String zzby;
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final ParcelFileDescriptor zzbz;
    @Nullable
    @SafeParcelable$Field(id = 5)
    private final ParcelFileDescriptor zzca;
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final MetadataBundle zzcb;
    @SafeParcelable$Field(id = 7)
    private final List<String> zzcc;
    @SafeParcelable$Field(id = 9)
    private final IBinder zzcd;
    private boolean zzce;
    private boolean zzcf;
    private boolean zzcg;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzk;
    
    static {
        zzbx = new GmsLogger("CompletionEvent", "");
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    @SafeParcelable$Constructor
    CompletionEvent(@SafeParcelable$Param(id = 2) final DriveId zzk, @SafeParcelable$Param(id = 3) final String zzby, @SafeParcelable$Param(id = 4) final ParcelFileDescriptor zzbz, @SafeParcelable$Param(id = 5) final ParcelFileDescriptor zzca, @SafeParcelable$Param(id = 6) final MetadataBundle zzcb, @SafeParcelable$Param(id = 7) final List<String> zzcc, @SafeParcelable$Param(id = 8) final int status, @SafeParcelable$Param(id = 9) final IBinder zzcd) {
        this.zzce = false;
        this.zzcf = false;
        this.zzcg = false;
        this.zzk = zzk;
        this.zzby = zzby;
        this.zzbz = zzbz;
        this.zzca = zzca;
        this.zzcb = zzcb;
        this.zzcc = zzcc;
        this.status = status;
        this.zzcd = zzcd;
    }
    
    private final void zza(final boolean b) {
        this.zzu();
        this.zzcg = true;
        IOUtils.closeQuietly(this.zzbz);
        IOUtils.closeQuietly(this.zzca);
        if (this.zzcb != null && this.zzcb.zzd(zzhp.zzka)) {
            this.zzcb.zza(zzhp.zzka).release();
        }
        if (this.zzcd == null) {
            String s;
            if (b) {
                s = "snooze";
            }
            else {
                s = "dismiss";
            }
            CompletionEvent.zzbx.efmt("CompletionEvent", "No callback on %s", new Object[] { s });
            return;
        }
        try {
            zzev.zza(this.zzcd).zza(b);
        }
        catch (RemoteException ex) {
            String s2;
            if (b) {
                s2 = "snooze";
            }
            else {
                s2 = "dismiss";
            }
            CompletionEvent.zzbx.e("CompletionEvent", String.format("RemoteException on %s", s2), (Throwable)ex);
        }
    }
    
    private final void zzu() {
        if (this.zzcg) {
            throw new IllegalStateException("Event has already been dismissed or snoozed.");
        }
    }
    
    public final void dismiss() {
        this.zza(false);
    }
    
    @Nullable
    public final String getAccountName() {
        this.zzu();
        return this.zzby;
    }
    
    @Nullable
    public final InputStream getBaseContentsInputStream() {
        this.zzu();
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
        this.zzu();
        return this.zzk;
    }
    
    @Nullable
    public final InputStream getModifiedContentsInputStream() {
        this.zzu();
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
        this.zzu();
        if (this.zzcb != null) {
            return new MetadataChangeSet(this.zzcb);
        }
        return null;
    }
    
    public final int getStatus() {
        this.zzu();
        return this.status;
    }
    
    public final List<String> getTrackingTags() {
        this.zzu();
        return new ArrayList<String>(this.zzcc);
    }
    
    public final int getType() {
        return 2;
    }
    
    public final void snooze() {
        this.zza(true);
    }
    
    public final String toString() {
        String string;
        if (this.zzcc == null) {
            string = "<null>";
        }
        else {
            final String join = TextUtils.join((CharSequence)"','", (Iterable)this.zzcc);
            string = new StringBuilder(String.valueOf(join).length() + 2).append("'").append(join).append("'").toString();
        }
        return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", this.zzk, this.status, string);
    }
    
    public final void writeToParcel(final Parcel parcel, int n) {
        n |= 0x1;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzk, n, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzby, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzbz, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzca, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzcb, n, false);
        SafeParcelWriter.writeStringList(parcel, 7, (List)this.zzcc, false);
        SafeParcelWriter.writeInt(parcel, 8, this.status);
        SafeParcelWriter.writeIBinder(parcel, 9, this.zzcd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
