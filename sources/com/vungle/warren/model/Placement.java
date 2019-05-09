package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.JsonObject;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Placement implements Memorable, Cloneable {
    private static final String TAG = "Placement";
    private final Set<String> advertisementIDs;
    private final boolean autoCached;
    private final String identifier;
    private final boolean incentivized;
    private long wakeupTime;

    public Placement(String identifier) {
        this.identifier = identifier;
        this.autoCached = false;
        this.incentivized = false;
        this.advertisementIDs = new LinkedHashSet();
    }

    public Placement(byte[] array) {
        boolean z = true;
        if (array.length == 0) {
            throw new IllegalArgumentException("Cannot recreate from empty array!");
        }
        ByteBuffer buffy = ByteBuffer.wrap(array);
        this.identifier = MemoryUtils.extractString(buffy);
        this.advertisementIDs = new LinkedHashSet(Arrays.asList(MemoryUtils.extractStringArray(buffy)));
        this.incentivized = buffy.get() == (byte) 1;
        if (buffy.get() != (byte) 1) {
            z = false;
        }
        this.autoCached = z;
        this.wakeupTime = buffy.getLong();
    }

    public Placement(JsonObject jsonObject) throws IllegalArgumentException {
        boolean z = true;
        if (jsonObject.has("reference_id")) {
            boolean z2;
            this.identifier = jsonObject.get("reference_id").getAsString();
            if (jsonObject.has("is_auto_cached") && jsonObject.get("is_auto_cached").getAsBoolean()) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.autoCached = z2;
            if (!(jsonObject.has("is_incentivized") && jsonObject.get("is_incentivized").getAsBoolean())) {
                z = false;
            }
            this.incentivized = z;
            this.advertisementIDs = new LinkedHashSet();
            return;
        }
        throw new IllegalArgumentException("Missing placement reference ID, cannot use placement!");
    }

    public void addAdvertisementID(String advertisementID) {
        this.advertisementIDs.add(advertisementID);
    }

    public boolean removeAdvertisementID(String advertisementID) {
        return this.advertisementIDs.remove(advertisementID);
    }

    public List<String> getAdvertisementIDs() {
        return new ArrayList(this.advertisementIDs);
    }

    public void snooze(long sleepTime) {
        this.wakeupTime = System.currentTimeMillis() + (1000 * sleepTime);
    }

    public long getWakeupTime() {
        return this.wakeupTime;
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int i;
            MemoryUtils.writeString(this.identifier, out);
            MemoryUtils.writeStringArray((String[]) this.advertisementIDs.toArray(new String[this.advertisementIDs.size()]), out);
            out.write(this.incentivized ? 1 : 0);
            if (this.autoCached) {
                i = 1;
            } else {
                i = 0;
            }
            out.write(i);
            out.write(MemoryUtils.toBytes(this.wakeupTime));
            return out.toByteArray();
        } catch (IOException e) {
            Log.e("Placement#toByteArray()", "Failed to write " + this + " to a byte array.");
            return new byte[0];
        }
    }

    @NonNull
    public String getId() {
        return this.identifier;
    }

    public boolean equalsIgnoreAdvertisements(Object obj) {
        if (!(obj instanceof Placement)) {
            return false;
        }
        Placement wrap = (Placement) obj;
        if (wrap.identifier.equals(this.identifier) && wrap.incentivized == this.incentivized && wrap.autoCached == this.autoCached) {
            return true;
        }
        return false;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Placement placement = (Placement) o;
        if (this.autoCached != placement.autoCached || this.incentivized != placement.incentivized || this.wakeupTime != placement.wakeupTime) {
            return false;
        }
        if (this.identifier != null) {
            if (!this.identifier.equals(placement.identifier)) {
                return false;
            }
        } else if (placement.identifier != null) {
            return false;
        }
        if (this.advertisementIDs != null) {
            z = this.advertisementIDs.equals(placement.advertisementIDs);
        } else if (placement.advertisementIDs != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 1;
        if (this.identifier != null) {
            result = this.identifier.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.advertisementIDs != null) {
            hashCode = this.advertisementIDs.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.autoCached) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (!this.incentivized) {
            i = 0;
        }
        return ((hashCode + i) * 31) + ((int) (this.wakeupTime ^ (this.wakeupTime >>> 32)));
    }

    public boolean isAutoCached() {
        return this.autoCached;
    }

    public boolean isIncentivized() {
        return this.incentivized;
    }

    public Placement copy() {
        try {
            return (Placement) clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Placement restore(int oldVersion, int newVersion, byte[] data) {
        if (data == null || data.length <= 0) {
            return null;
        }
        return new Placement(Arrays.copyOfRange(data, 1, data.length));
    }

    public String toString() {
        return "Placement{identifier='" + this.identifier + '\'' + ", advertisementIDs=" + this.advertisementIDs + ", autoCached=" + this.autoCached + ", incentivized=" + this.incentivized + ", wakeupTime=" + this.wakeupTime + '}';
    }
}
