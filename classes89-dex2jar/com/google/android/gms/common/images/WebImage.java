// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.images;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.Objects;
import org.json.JSONException;
import com.google.android.gms.common.annotation.KeepForSdk;
import org.json.JSONObject;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "WebImageCreator")
public final class WebImage extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(getter = "getWidth", id = 3)
    private final int zane;
    @SafeParcelable$Field(getter = "getHeight", id = 4)
    private final int zanf;
    @SafeParcelable$Field(getter = "getUrl", id = 2)
    private final Uri zang;
    
    static {
        CREATOR = (Parcelable$Creator)new zae();
    }
    
    @SafeParcelable$Constructor
    WebImage(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final Uri zang, @SafeParcelable$Param(id = 3) final int zane, @SafeParcelable$Param(id = 4) final int zanf) {
        this.zalf = zalf;
        this.zang = zang;
        this.zane = zane;
        this.zanf = zanf;
    }
    
    public WebImage(final Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }
    
    public WebImage(final Uri uri, final int n, final int n2) throws IllegalArgumentException {
        this(1, uri, n, n2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (n < 0 || n2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }
    
    @KeepForSdk
    public WebImage(final JSONObject jsonObject) throws IllegalArgumentException {
        this(zaa(jsonObject), jsonObject.optInt("width", 0), jsonObject.optInt("height", 0));
    }
    
    private static Uri zaa(final JSONObject jsonObject) {
        Uri parse = null;
        if (!jsonObject.has("url")) {
            return parse;
        }
        try {
            parse = Uri.parse(jsonObject.getString("url"));
            return parse;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || !(o instanceof WebImage)) {
                return false;
            }
            final WebImage webImage = (WebImage)o;
            if (!Objects.equal((Object)this.zang, (Object)webImage.zang) || this.zane != webImage.zane || this.zanf != webImage.zanf) {
                return false;
            }
        }
        return true;
    }
    
    public final int getHeight() {
        return this.zanf;
    }
    
    public final Uri getUrl() {
        return this.zang;
    }
    
    public final int getWidth() {
        return this.zane;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zang, this.zane, this.zanf });
    }
    
    @KeepForSdk
    public final JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", (Object)this.zang.toString());
            jsonObject.put("width", this.zane);
            jsonObject.put("height", this.zanf);
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", this.zane, this.zanf, this.zang.toString());
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.getUrl(), n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.getWidth());
        SafeParcelWriter.writeInt(parcel, 4, this.getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
