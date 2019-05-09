// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import org.json.JSONException;
import org.json.JSONArray;
import com.google.android.gms.common.data.DataHolder;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.drive.metadata.zzb;

public final class zzs extends zzb<String>
{
    public zzs(final String s, final int n) {
        super(s, Collections.singleton(s), Collections.emptySet(), 4300000);
    }
    
    @Nullable
    @Override
    protected final Collection<String> zzd(final DataHolder dataHolder, int i, final int n) {
        try {
            final String string = dataHolder.getString(this.getName(), i, n);
            if (string == null) {
                return null;
            }
            final ArrayList<String> list = new ArrayList<String>();
            JSONArray jsonArray;
            for (jsonArray = new JSONArray(string), i = 0; i < jsonArray.length(); ++i) {
                list.add(jsonArray.getString(i));
            }
            return (Collection<String>)Collections.unmodifiableCollection((Collection<?>)list);
        }
        catch (JSONException ex) {
            throw new IllegalStateException("DataHolder supplied invalid JSON", (Throwable)ex);
        }
    }
}
