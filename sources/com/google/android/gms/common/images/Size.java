package com.google.android.gms.common.images;

import com.kongregate.android.internal.browser.C0462b;

public final class Size {
    private final int zane;
    private final int zanf;

    public Size(int i, int i2) {
        this.zane = i;
        this.zanf = i2;
    }

    public final int getWidth() {
        return this.zane;
    }

    public final int getHeight() {
        return this.zanf;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        if (this.zane == size.zane && this.zanf == size.zanf) {
            return true;
        }
        return false;
    }

    public final String toString() {
        int i = this.zane;
        return i + "x" + this.zanf;
    }

    private static NumberFormatException zah(String str) {
        throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 16).append("Invalid Size: \"").append(str).append("\"").toString());
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            indexOf = str.indexOf(C0462b.f362a);
        }
        if (indexOf < 0) {
            throw zah(str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (NumberFormatException e) {
            throw zah(str);
        }
    }

    public final int hashCode() {
        return this.zanf ^ ((this.zane << 16) | (this.zane >>> 16));
    }
}
