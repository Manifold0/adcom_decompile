package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.zzb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.json.JSONArray;

public final class zzs extends zzb<String> {
    public zzs(String str, int i) {
        super(str, Collections.singleton(str), Collections.emptySet(), GmsVersion.VERSION_JARLSBERG);
    }

    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        bundle.putStringArrayList(getName(), new ArrayList((Collection) obj));
    }

    @Nullable
    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return bundle.getStringArrayList(getName());
    }

    @Nullable
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zzd(dataHolder, i, i2);
    }

    @Nullable
    protected final Collection<String> zzd(DataHolder dataHolder, int i, int i2) {
        try {
            String string = dataHolder.getString(getName(), i, i2);
            if (string == null) {
                return null;
            }
            Collection arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(string);
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                arrayList.add(jSONArray.getString(i3));
            }
            return Collections.unmodifiableCollection(arrayList);
        } catch (Throwable e) {
            throw new IllegalStateException("DataHolder supplied invalid JSON", e);
        }
    }
}
