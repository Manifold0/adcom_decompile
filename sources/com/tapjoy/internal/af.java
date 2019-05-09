package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

public enum af {
    UNSPECIFIED,
    PORTRAIT,
    LANDSCAPE,
    SQUARE,
    NATURAL_PORTRAIT(PORTRAIT),
    RIGHT_LANDSCAPE(LANDSCAPE, NATURAL_PORTRAIT),
    REVERSE_PORTRAIT(PORTRAIT, NATURAL_PORTRAIT),
    LEFT_LANDSCAPE(LANDSCAPE, NATURAL_PORTRAIT),
    NATURAL_LANDSCAPE(LANDSCAPE),
    RIGHT_PORTRAIT(PORTRAIT, NATURAL_LANDSCAPE),
    REVERSE_LANDSCAPE(LANDSCAPE, NATURAL_LANDSCAPE),
    LEFT_PORTRAIT(PORTRAIT, NATURAL_LANDSCAPE),
    NATURAL_SQUARE(SQUARE),
    RIGHT_SQUARE(SQUARE, NATURAL_SQUARE),
    REVERSE_SQUARE(SQUARE, NATURAL_SQUARE),
    LEFT_SQUARE(SQUARE, NATURAL_SQUARE);
    
    /* renamed from: q */
    private final af f7161q;
    /* renamed from: r */
    private final af f7162r;

    private af(af afVar) {
        this.f7161q = afVar;
        this.f7162r = this;
    }

    private af(af afVar, af afVar2) {
        this.f7161q = afVar;
        this.f7162r = afVar2;
    }

    /* renamed from: a */
    public final boolean m7155a() {
        return this == PORTRAIT || this.f7161q == PORTRAIT;
    }

    /* renamed from: b */
    public final boolean m7156b() {
        return this == LANDSCAPE || this.f7161q == LANDSCAPE;
    }

    /* renamed from: c */
    public final int m7157c() {
        return this.f7162r != null ? ordinal() - this.f7162r.ordinal() : 0;
    }

    /* renamed from: a */
    public static af m7153a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        } else {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        if (point.x < point.y) {
            switch (rotation & 3) {
                case 1:
                    return RIGHT_PORTRAIT;
                case 2:
                    return REVERSE_PORTRAIT;
                case 3:
                    return LEFT_PORTRAIT;
                default:
                    return NATURAL_PORTRAIT;
            }
        } else if (point.x > point.y) {
            switch (rotation & 3) {
                case 1:
                    return RIGHT_LANDSCAPE;
                case 2:
                    return REVERSE_LANDSCAPE;
                case 3:
                    return LEFT_LANDSCAPE;
                default:
                    return NATURAL_LANDSCAPE;
            }
        } else {
            switch (rotation & 3) {
                case 1:
                    return RIGHT_SQUARE;
                case 2:
                    return REVERSE_SQUARE;
                case 3:
                    return LEFT_SQUARE;
                default:
                    return NATURAL_SQUARE;
            }
        }
    }

    /* renamed from: b */
    public static af m7154b(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case 1:
                return PORTRAIT;
            case 2:
                return LANDSCAPE;
            default:
                return UNSPECIFIED;
        }
    }
}
