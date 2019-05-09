// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.proxy;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.HashMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdkWithMembers
@SafeParcelable$Class(creator = "ProxyResponseCreator")
public class ProxyResponse extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ProxyResponse> CREATOR;
    public static final int STATUS_CODE_NO_CONNECTION = -1;
    @SafeParcelable$Field(id = 5)
    public final byte[] body;
    @SafeParcelable$Field(id = 1)
    public final int googlePlayServicesStatusCode;
    @SafeParcelable$Field(id = 2)
    public final PendingIntent recoveryAction;
    @SafeParcelable$Field(id = 3)
    public final int statusCode;
    @SafeParcelable$VersionField(id = 1000)
    private final int versionCode;
    @SafeParcelable$Field(id = 4)
    private final Bundle zzby;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @SafeParcelable$Constructor
    ProxyResponse(@SafeParcelable$Param(id = 1000) final int versionCode, @SafeParcelable$Param(id = 1) final int googlePlayServicesStatusCode, @SafeParcelable$Param(id = 2) final PendingIntent recoveryAction, @SafeParcelable$Param(id = 3) final int statusCode, @SafeParcelable$Param(id = 4) final Bundle zzby, @SafeParcelable$Param(id = 5) final byte[] body) {
        this.versionCode = versionCode;
        this.googlePlayServicesStatusCode = googlePlayServicesStatusCode;
        this.statusCode = statusCode;
        this.zzby = zzby;
        this.body = body;
        this.recoveryAction = recoveryAction;
    }
    
    public ProxyResponse(final int n, final PendingIntent pendingIntent, final int n2, final Bundle bundle, final byte[] array) {
        this(1, n, pendingIntent, n2, bundle, array);
    }
    
    private ProxyResponse(final int n, final Bundle bundle, final byte[] array) {
        this(1, 0, null, n, bundle, array);
    }
    
    public ProxyResponse(final int n, final Map<String, String> map, final byte[] array) {
        this(n, zza(map), array);
    }
    
    public static ProxyResponse createErrorProxyResponse(final int n, final PendingIntent pendingIntent, final int n2, final Map<String, String> map, final byte[] array) {
        return new ProxyResponse(1, n, pendingIntent, n2, zza(map), array);
    }
    
    private static Bundle zza(final Map<String, String> map) {
        final Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString((String)entry.getKey(), (String)entry.getValue());
        }
        return bundle;
    }
    
    public Map<String, String> getHeaders() {
        if (this.zzby == null) {
            return Collections.emptyMap();
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : this.zzby.keySet()) {
            hashMap.put(s, this.zzby.getString(s));
        }
        return hashMap;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.googlePlayServicesStatusCode);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.recoveryAction, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzby, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.body, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
