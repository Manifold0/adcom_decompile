// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@ShowFirstParty
public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable
{
    @KeepForSdk
    public FastSafeParcelableJsonResponse() {
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!this.getClass().isInstance(o)) {
            return false;
        }
        final FastJsonResponse fastJsonResponse = (FastJsonResponse)o;
        for (final Field<?, ?> field : this.getFieldMappings().values()) {
            if (this.isFieldSet(field)) {
                if (!fastJsonResponse.isFieldSet(field)) {
                    return false;
                }
                if (!this.getFieldValue(field).equals(fastJsonResponse.getFieldValue(field))) {
                    return false;
                }
                continue;
            }
            else {
                if (fastJsonResponse.isFieldSet(field)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @VisibleForTesting
    public Object getValueObject(final String s) {
        return null;
    }
    
    public int hashCode() {
        final Iterator<Field<?, ?>> iterator = this.getFieldMappings().values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final Field<?, ?> field = iterator.next();
            if (this.isFieldSet(field)) {
                n = this.getFieldValue(field).hashCode() + n * 31;
            }
        }
        return n;
    }
    
    @VisibleForTesting
    public boolean isPrimitiveFieldSet(final String s) {
        return false;
    }
    
    @KeepForSdk
    public byte[] toByteArray() {
        final Parcel obtain = Parcel.obtain();
        this.writeToParcel(obtain, 0);
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
