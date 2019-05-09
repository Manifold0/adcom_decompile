// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.ArrayList;
import android.os.Bundle;
import java.util.Collection;
import com.google.android.gms.drive.metadata.zzb;
import android.os.Parcelable;

public class zzl<T extends Parcelable> extends zzb<T>
{
    public zzl(final String s, final Collection<String> collection, final Collection<String> collection2, final int n) {
        super(s, collection, collection2, n);
    }
    
    protected Collection<T> zzc(final Bundle bundle) {
        return (Collection<T>)bundle.getParcelableArrayList(this.getName());
    }
}
