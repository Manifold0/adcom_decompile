// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.model;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import java.util.Collection;
import java.util.Arrays;
import com.vungle.warren.persistence.MemoryUtils;
import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import com.google.gson.JsonObject;
import java.util.Set;
import com.vungle.warren.persistence.Memorable;

public class Placement implements Memorable, Cloneable
{
    private static final String TAG = "Placement";
    private final Set<String> advertisementIDs;
    private final boolean autoCached;
    private final String identifier;
    private final boolean incentivized;
    private long wakeupTime;
    
    public Placement(final JsonObject jsonObject) throws IllegalArgumentException {
        final boolean b = true;
        if (jsonObject.has("reference_id")) {
            this.identifier = jsonObject.get("reference_id").getAsString();
            this.autoCached = (jsonObject.has("is_auto_cached") && jsonObject.get("is_auto_cached").getAsBoolean());
            this.incentivized = (jsonObject.has("is_incentivized") && jsonObject.get("is_incentivized").getAsBoolean() && b);
            this.advertisementIDs = new LinkedHashSet<String>();
            return;
        }
        throw new IllegalArgumentException("Missing placement reference ID, cannot use placement!");
    }
    
    public Placement(final String identifier) {
        this.identifier = identifier;
        this.autoCached = false;
        this.incentivized = false;
        this.advertisementIDs = new LinkedHashSet<String>();
    }
    
    public Placement(final byte[] array) {
        final boolean b = true;
        if (array.length == 0) {
            throw new IllegalArgumentException("Cannot recreate from empty array!");
        }
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        this.identifier = MemoryUtils.extractString(wrap);
        this.advertisementIDs = new LinkedHashSet<String>(Arrays.asList(MemoryUtils.extractStringArray(wrap)));
        this.incentivized = (wrap.get() == 1);
        this.autoCached = (wrap.get() == 1 && b);
        this.wakeupTime = wrap.getLong();
    }
    
    public static Placement restore(final int n, final int n2, final byte[] array) {
        if (array == null || array.length <= 0) {
            return null;
        }
        return new Placement(Arrays.copyOfRange(array, 1, array.length));
    }
    
    public void addAdvertisementID(final String s) {
        this.advertisementIDs.add(s);
    }
    
    public Placement copy() {
        try {
            return (Placement)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            Log.e("Placement", Log.getStackTraceString((Throwable)ex));
            return null;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final Placement placement = (Placement)o;
                    b3 = b2;
                    if (this.autoCached == placement.autoCached) {
                        b3 = b2;
                        if (this.incentivized == placement.incentivized) {
                            b3 = b2;
                            if (this.wakeupTime == placement.wakeupTime) {
                                if (this.identifier != null) {
                                    b3 = b2;
                                    if (!this.identifier.equals(placement.identifier)) {
                                        return b3;
                                    }
                                }
                                else if (placement.identifier != null) {
                                    return false;
                                }
                                boolean equals;
                                if (this.advertisementIDs != null) {
                                    equals = this.advertisementIDs.equals(placement.advertisementIDs);
                                }
                                else {
                                    equals = b;
                                    if (placement.advertisementIDs != null) {
                                        equals = false;
                                    }
                                }
                                return equals;
                            }
                        }
                    }
                }
            }
        }
        return b3;
    }
    
    public boolean equalsIgnoreAdvertisements(final Object o) {
        if (o instanceof Placement) {
            final Placement placement = (Placement)o;
            if (placement.identifier.equals(this.identifier) && placement.incentivized == this.incentivized && placement.autoCached == this.autoCached) {
                return true;
            }
        }
        return false;
    }
    
    public List<String> getAdvertisementIDs() {
        return new ArrayList<String>(this.advertisementIDs);
    }
    
    @NonNull
    @Override
    public String getId() {
        return this.identifier;
    }
    
    public long getWakeupTime() {
        return this.wakeupTime;
    }
    
    @Override
    public int hashCode() {
        int n = 1;
        int hashCode;
        if (this.identifier != null) {
            hashCode = this.identifier.hashCode();
        }
        else {
            hashCode = 0;
        }
        int hashCode2;
        if (this.advertisementIDs != null) {
            hashCode2 = this.advertisementIDs.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int n2;
        if (this.autoCached) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (!this.incentivized) {
            n = 0;
        }
        return (((hashCode * 31 + hashCode2) * 31 + n2) * 31 + n) * 31 + (int)(this.wakeupTime ^ this.wakeupTime >>> 32);
    }
    
    public boolean isAutoCached() {
        return this.autoCached;
    }
    
    public boolean isIncentivized() {
        return this.incentivized;
    }
    
    public boolean removeAdvertisementID(final String s) {
        return this.advertisementIDs.remove(s);
    }
    
    public void snooze(final long n) {
        this.wakeupTime = System.currentTimeMillis() + 1000L * n;
    }
    
    @Override
    public byte[] toByteArray() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MemoryUtils.writeString(this.identifier, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.advertisementIDs.toArray(new String[this.advertisementIDs.size()]), byteArrayOutputStream);
            int n;
            if (this.incentivized) {
                n = 1;
            }
            else {
                n = 0;
            }
            byteArrayOutputStream.write(n);
            int n2;
            if (this.autoCached) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            byteArrayOutputStream.write(n2);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.wakeupTime));
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            Log.e("Placement#toByteArray()", "Failed to write " + this + " to a byte array.");
            return new byte[0];
        }
    }
    
    @Override
    public String toString() {
        return "Placement{identifier='" + this.identifier + '\'' + ", advertisementIDs=" + this.advertisementIDs + ", autoCached=" + this.autoCached + ", incentivized=" + this.incentivized + ", wakeupTime=" + this.wakeupTime + '}';
    }
}
