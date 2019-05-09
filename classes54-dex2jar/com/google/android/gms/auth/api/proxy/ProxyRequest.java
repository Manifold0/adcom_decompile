// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.proxy;

import android.util.Patterns;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdkWithMembers
@SafeParcelable$Class(creator = "ProxyRequestCreator")
public class ProxyRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ProxyRequest> CREATOR;
    public static final int HTTP_METHOD_DELETE;
    public static final int HTTP_METHOD_GET;
    public static final int HTTP_METHOD_HEAD;
    public static final int HTTP_METHOD_OPTIONS;
    public static final int HTTP_METHOD_PATCH;
    public static final int HTTP_METHOD_POST;
    public static final int HTTP_METHOD_PUT;
    public static final int HTTP_METHOD_TRACE;
    public static final int LAST_CODE;
    public static final int VERSION_CODE = 2;
    @SafeParcelable$Field(id = 4)
    public final byte[] body;
    @SafeParcelable$Field(id = 2)
    public final int httpMethod;
    @SafeParcelable$Field(id = 3)
    public final long timeoutMillis;
    @SafeParcelable$Field(id = 1)
    public final String url;
    @SafeParcelable$VersionField(id = 1000)
    private final int versionCode;
    @SafeParcelable$Field(id = 5)
    private Bundle zzby;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
        HTTP_METHOD_GET = 0;
        HTTP_METHOD_POST = 1;
        HTTP_METHOD_PUT = 2;
        HTTP_METHOD_DELETE = 3;
        HTTP_METHOD_HEAD = 4;
        HTTP_METHOD_OPTIONS = 5;
        HTTP_METHOD_TRACE = 6;
        HTTP_METHOD_PATCH = 7;
        LAST_CODE = 7;
    }
    
    @SafeParcelable$Constructor
    ProxyRequest(@SafeParcelable$Param(id = 1000) final int versionCode, @SafeParcelable$Param(id = 1) final String url, @SafeParcelable$Param(id = 2) final int httpMethod, @SafeParcelable$Param(id = 3) final long timeoutMillis, @SafeParcelable$Param(id = 4) final byte[] body, @SafeParcelable$Param(id = 5) final Bundle zzby) {
        this.versionCode = versionCode;
        this.url = url;
        this.httpMethod = httpMethod;
        this.timeoutMillis = timeoutMillis;
        this.body = body;
        this.zzby = zzby;
    }
    
    public Map<String, String> getHeaderMap() {
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(this.zzby.size());
        for (final String s : this.zzby.keySet()) {
            linkedHashMap.put(s, this.zzby.getString(s));
        }
        return (Map<String, String>)Collections.unmodifiableMap((Map<?, ?>)linkedHashMap);
    }
    
    public String toString() {
        final String url = this.url;
        return new StringBuilder(String.valueOf(url).length() + 42).append("ProxyRequest[ url: ").append(url).append(", method: ").append(this.httpMethod).append(" ]").toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.url, false);
        SafeParcelWriter.writeInt(parcel, 2, this.httpMethod);
        SafeParcelWriter.writeLong(parcel, 3, this.timeoutMillis);
        SafeParcelWriter.writeByteArray(parcel, 4, this.body, false);
        SafeParcelWriter.writeBundle(parcel, 5, this.zzby, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @KeepForSdkWithMembers
    public static class Builder
    {
        private String zzbz;
        private int zzca;
        private long zzcb;
        private byte[] zzcc;
        private Bundle zzcd;
        
        public Builder(final String zzbz) {
            this.zzca = ProxyRequest.HTTP_METHOD_GET;
            this.zzcb = 3000L;
            this.zzcc = null;
            this.zzcd = new Bundle();
            Preconditions.checkNotEmpty(zzbz);
            if (Patterns.WEB_URL.matcher(zzbz).matches()) {
                this.zzbz = zzbz;
                return;
            }
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(zzbz).length() + 51).append("The supplied url [ ").append(zzbz).append("] is not match Patterns.WEB_URL!").toString());
        }
        
        public ProxyRequest build() {
            if (this.zzcc == null) {
                this.zzcc = new byte[0];
            }
            return new ProxyRequest(2, this.zzbz, this.zzca, this.zzcb, this.zzcc, this.zzcd);
        }
        
        public Builder putHeader(final String s, final String s2) {
            Preconditions.checkNotEmpty(s, (Object)"Header name cannot be null or empty!");
            final Bundle zzcd = this.zzcd;
            String s3 = s2;
            if (s2 == null) {
                s3 = "";
            }
            zzcd.putString(s, s3);
            return this;
        }
        
        public Builder setBody(final byte[] zzcc) {
            this.zzcc = zzcc;
            return this;
        }
        
        public Builder setHttpMethod(final int zzca) {
            Preconditions.checkArgument(zzca >= 0 && zzca <= ProxyRequest.LAST_CODE, (Object)"Unrecognized http method code.");
            this.zzca = zzca;
            return this;
        }
        
        public Builder setTimeoutMillis(final long zzcb) {
            Preconditions.checkArgument(zzcb >= 0L, (Object)"The specified timeout must be non-negative.");
            this.zzcb = zzcb;
            return this;
        }
    }
}
