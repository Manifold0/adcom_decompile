// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import java.util.Iterator;
import android.text.TextUtils;
import java.util.ArrayList;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zai;
import android.support.v4.util.ArrayMap;

public class AvailabilityException extends Exception
{
    private final ArrayMap<zai<?>, ConnectionResult> zaay;
    
    public AvailabilityException(final ArrayMap<zai<?>, ConnectionResult> zaay) {
        this.zaay = zaay;
    }
    
    public ConnectionResult getConnectionResult(final GoogleApi<? extends Api.ApiOptions> googleApi) {
        final zai<? extends Api.ApiOptions> zak = googleApi.zak();
        Preconditions.checkArgument(this.zaay.get((Object)zak) != null, (Object)"The given API was not part of the availability request.");
        return (ConnectionResult)this.zaay.get((Object)zak);
    }
    
    @Override
    public String getMessage() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<zai> iterator = this.zaay.keySet().iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final zai zai = iterator.next();
            final ConnectionResult connectionResult = (ConnectionResult)this.zaay.get((Object)zai);
            if (connectionResult.isSuccess()) {
                b = false;
            }
            final String zan = zai.zan();
            final String value = String.valueOf(connectionResult);
            list.add(new StringBuilder(String.valueOf(zan).length() + 2 + String.valueOf(value).length()).append(zan).append(": ").append(value).toString());
        }
        final StringBuilder sb = new StringBuilder();
        if (b) {
            sb.append("None of the queried APIs are available. ");
        }
        else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join((CharSequence)"; ", (Iterable)list));
        return sb.toString();
    }
    
    public final ArrayMap<zai<?>, ConnectionResult> zaj() {
        return this.zaay;
    }
}
