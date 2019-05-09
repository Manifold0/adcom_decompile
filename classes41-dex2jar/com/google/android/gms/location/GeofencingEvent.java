// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import android.content.Intent;
import android.location.Location;
import java.util.List;

public class GeofencingEvent
{
    private final int errorCode;
    private final int zzam;
    private final List<Geofence> zzan;
    private final Location zzao;
    
    private GeofencingEvent(final int errorCode, final int zzam, final List<Geofence> zzan, final Location zzao) {
        this.errorCode = errorCode;
        this.zzam = zzam;
        this.zzan = zzan;
        this.zzao = zzao;
    }
    
    public static GeofencingEvent fromIntent(final Intent intent) {
        if (intent == null) {
            return null;
        }
        final int intExtra = intent.getIntExtra("gms_error_code", -1);
        int intExtra2 = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        if (intExtra2 == -1 || (intExtra2 != 1 && intExtra2 != 2 && intExtra2 != 4)) {
            intExtra2 = -1;
        }
        final ArrayList list = (ArrayList)intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        ArrayList<Geofence> list2;
        if (list == null) {
            list2 = null;
        }
        else {
            list2 = new ArrayList<Geofence>(list.size());
            final ArrayList<Object> list3 = (ArrayList<Object>)list;
            final int size = list3.size();
            int i = 0;
            while (i < size) {
                final byte[] value = list3.get(i);
                ++i;
                list2.add(zzbh.zza(value));
            }
        }
        return new GeofencingEvent(intExtra, intExtra2, list2, (Location)intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public int getGeofenceTransition() {
        return this.zzam;
    }
    
    public List<Geofence> getTriggeringGeofences() {
        return this.zzan;
    }
    
    public Location getTriggeringLocation() {
        return this.zzao;
    }
    
    public boolean hasError() {
        return this.errorCode != -1;
    }
}
