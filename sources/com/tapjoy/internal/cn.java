package com.tapjoy.internal;

import java.math.BigInteger;

public final class cn extends Number {
    /* renamed from: a */
    private final String f7288a;

    public cn(String str) {
        this.f7288a = str;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Number)) {
            return false;
        }
        Number number = (Number) o;
        if (number instanceof Integer) {
            if (intValue() != number.intValue()) {
                return false;
            }
            return true;
        } else if (number instanceof Long) {
            if (longValue() != number.longValue()) {
                return false;
            }
            return true;
        } else if (number instanceof Float) {
            if (floatValue() != number.floatValue()) {
                return false;
            }
            return true;
        } else if (!(number instanceof Double)) {
            return this.f7288a.equals(number.toString());
        } else {
            if (doubleValue() != number.doubleValue()) {
                return false;
            }
            return true;
        }
    }

    public final int intValue() {
        try {
            return Integer.parseInt(this.f7288a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.f7288a);
            } catch (NumberFormatException e2) {
                return new BigInteger(this.f7288a).intValue();
            }
        }
    }

    public final long longValue() {
        try {
            return Long.parseLong(this.f7288a);
        } catch (NumberFormatException e) {
            return new BigInteger(this.f7288a).longValue();
        }
    }

    public final float floatValue() {
        return Float.parseFloat(this.f7288a);
    }

    public final double doubleValue() {
        return Double.parseDouble(this.f7288a);
    }

    public final String toString() {
        return this.f7288a;
    }
}
