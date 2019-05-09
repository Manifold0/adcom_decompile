// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.os.Build$VERSION;
import org.json.JSONArray;
import org.json.JSONObject;

final class g
{
    private static int \u02bb;
    private static int \u02bc;
    private static int \u02bd;
    private static int \u02ca\u0971;
    private static int \u0971\u02cb;
    private static byte[] \u141d;
    private boolean \u02ca;
    private int \u02cb;
    private boolean \u02ce;
    private boolean \u02cf;
    private int \u0971;
    
    static {
        g.\u02ca\u0971 = 117;
        g.\u02bd = -1138247343;
        g.\u02bc = 565428102;
        g.\u141d = new byte[] { -73, 79, -77, 127, -123, -125, -126, 73, 125, -81, 81, -77, 81, 127, -83, -121, 73, -78, 127, 85, -82, -128, 77, -79, 77, -80, -126, 123, -124, 77, -125, -87, -123, 117, -123, 120, -122, 82, -83, -128, -69, -66, 65, -78, 65, -66, 119, -120, -72, 70, 117, -116, 77, -66, -65, 112, -108, 105, -66, 69, 71, -118, 110, -118, 66, -78, 65, 71, -65, 68, 104, -120, 107, -109, -78, 118, -119, 113, -108, -91, -81, -5, 80, -25, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        g.\u02bb = 0;
        g.\u0971\u02cb = 1;
    }
    
    g(final String s) {
        this.\u02cf = false;
        this.\u02ce = false;
        this.\u02ca = false;
        this.\u0971 = 200;
        this.\u02cb = 10;
        this.\u0971(s);
    }
    
    private static String \u02cb(int \u02bc, int n, final byte b, int n2) {
        final int n3 = 0;
        final StringBuilder sb = new StringBuilder();
        final int n4 = \u02bc + g.\u02ca\u0971;
        if (n4 == -1) {
            \u02bc = 0;
        }
        else {
            \u02bc = 1;
        }
        boolean b2 = false;
        switch (\u02bc) {
            default: {
                b2 = true;
                break;
            }
            case 1: {
                b2 = false;
                break;
            }
        }
        if (b2) {
            \u02bc = 1;
        }
        else {
            \u02bc = 0;
        }
        Label_0116: {
            switch (\u02bc) {
                default: {
                    if (g.\u141d != null) {
                        \u02bc = 0;
                    }
                    else {
                        \u02bc = 1;
                    }
                    switch (\u02bc) {
                        default: {
                            \u02bc = g.\u02bc;
                            throw new NullPointerException();
                        }
                        case 0: {
                            \u02bc = g.\u0971\u02cb + 87;
                            g.\u02bb = \u02bc % 128;
                            if (\u02bc % 2 != 0) {
                                \u02bc = 0;
                            }
                            else {
                                \u02bc = 1;
                            }
                            switch (\u02bc) {
                                case 1: {
                                    \u02bc = (byte)(g.\u141d[g.\u02bc + n2] + g.\u02ca\u0971);
                                    break Label_0116;
                                }
                                default: {
                                    \u02bc = (byte)(g.\u141d[g.\u02bc + n2] + g.\u02ca\u0971);
                                    break Label_0116;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                case 0: {
                    \u02bc = n4;
                    break;
                }
            }
        }
        int n5;
        if (\u02bc > 0) {
            n5 = 61;
        }
        else {
            n5 = 24;
        }
        switch (n5) {
            default: {
                final int \u02bc2 = g.\u02bc;
                int n6;
                if (b2) {
                    n6 = 81;
                }
                else {
                    n6 = 86;
                }
                int n7 = 0;
                switch (n6) {
                    default: {
                        n7 = n3;
                        break;
                    }
                    case 81: {
                        n7 = 1;
                        break;
                    }
                }
                final char c = (char)(g.\u02bd + n);
                sb.append(c);
                n = c;
                int n8 = \u02bc2 + (n2 + \u02bc - 2) + n7;
                final int n9 = 1;
                n2 = n;
                n = n9;
                while (true) {
                    int n10;
                    if (n < \u02bc) {
                        n10 = 85;
                    }
                    else {
                        n10 = 44;
                    }
                    switch (n10) {
                        default: {
                            final int n11 = g.\u02bb + 1;
                            g.\u0971\u02cb = n11 % 128;
                            if (n11 % 2 == 0) {}
                            int n12;
                            if (g.\u141d != null) {
                                n12 = 66;
                            }
                            else {
                                n12 = 69;
                            }
                            switch (n12) {
                                default: {
                                    \u02bc = n8 - 1;
                                    throw new NullPointerException();
                                }
                                case 66: {
                                    final byte[] \u141d = g.\u141d;
                                    final int n13 = n8 - 1;
                                    final char c2 = (char)((\u141d[n8] ^ b) + n2);
                                    n8 = n13;
                                    sb.append(c2);
                                    ++n;
                                    n2 = c2;
                                    continue;
                                }
                            }
                            break;
                        }
                        case 44: {
                            return sb.toString();
                        }
                    }
                }
                break;
            }
            case 24: {
                return sb.toString();
            }
        }
    }
    
    private static boolean \u02cb(final JSONObject jsonObject) {
        boolean b = false;
        Label_0218: {
            int n;
            int n2;
            int n3;
            String \u02cb;
            JSONArray jsonArray;
            int n4;
            String s;
            JSONArray jsonArray2;
            int n5 = 0;
            String \u02cb2;
            JSONArray jsonArray3;
            int n6 = 0;
            Label_0075_Outer:Label_0290_Outer:
            while (true) {
                b = true;
            Label_0290:
                while (true) {
                Label_0319:
                    while (true) {
                        while (true) {
                            Label_0279: {
                                while (true) {
                                    Label_0262: {
                                        try {
                                            if (!jsonObject.has(\u02cb(-115, 1138247440, (byte)(-24), -565428019).intern())) {
                                                break Label_0262;
                                            }
                                            n = 14;
                                            switch (n) {
                                                default: {
                                                    n2 = g.\u02bb + 113;
                                                    g.\u0971\u02cb = n2 % 128;
                                                    if (n2 % 2 != 0) {
                                                        break Label_0279;
                                                    }
                                                    n3 = 1;
                                                    switch (n3) {
                                                        default: {
                                                            \u02cb = r.\u02ca().\u02cb();
                                                            jsonArray = jsonObject.getJSONArray(\u02cb(-115, 1138247440, (byte)(-24), -565428019).intern());
                                                            n4 = jsonArray.length();
                                                            s = \u02cb;
                                                            jsonArray2 = jsonArray;
                                                            n5 = 0;
                                                            break Label_0290;
                                                        }
                                                        case 0: {
                                                            \u02cb2 = r.\u02ca().\u02cb();
                                                            jsonArray3 = jsonObject.getJSONArray(\u02cb(-115, 1138247440, (byte)(-24), -565428019).intern());
                                                            n4 = jsonArray3.length();
                                                            s = \u02cb2;
                                                            jsonArray2 = jsonArray3;
                                                            n5 = 0;
                                                            break Label_0290;
                                                        }
                                                    }
                                                    break;
                                                }
                                                case 18: {
                                                    break Label_0075_Outer;
                                                }
                                            }
                                            // iftrue(Label_0273:, !jsonArray2.getString(n5).contentEquals((CharSequence)s))
                                            n6 = 23;
                                            break Label_0319;
                                        }
                                        catch (Exception ex) {
                                            o.\u02ce(ex);
                                        }
                                        break;
                                    }
                                    n = 18;
                                    continue Label_0075_Outer;
                                }
                                Label_0273: {
                                    n6 = 88;
                                }
                                break Label_0319;
                            }
                            n3 = 0;
                            continue Label_0290_Outer;
                        }
                        switch (n5 >= n4) {
                            case 1: {
                                break Label_0075_Outer;
                            }
                            default: {
                                continue;
                            }
                        }
                        break;
                    }
                    switch (n6) {
                        case 23: {
                            break Label_0218;
                        }
                        default: {
                            ++n5;
                            continue Label_0290;
                        }
                    }
                    break;
                }
            }
            b = false;
            return b;
        }
        final int n7 = g.\u0971\u02cb + 71;
        g.\u02bb = n7 % 128;
        int n8;
        if (n7 % 2 != 0) {
            n8 = 4;
        }
        else {
            n8 = 79;
        }
        switch (n8) {
            case 79: {
                return b;
            }
            default: {
                return true;
            }
        }
    }
    
    private static boolean \u02ce(final JSONObject jsonObject) {
        while (true) {
        Label_0284_Outer:
            while (true) {
                int n8 = 0;
                Label_0312: {
                    while (true) {
                        int n5 = 0;
                        int n6 = 0;
                        Label_0304: {
                            try {
                                int n;
                                if (16 > Build$VERSION.SDK_INT) {
                                    n = 58;
                                }
                                else {
                                    n = 36;
                                }
                                switch (n) {
                                    default: {
                                        final int n2 = g.\u0971\u02cb + 43;
                                        g.\u02bb = n2 % 128;
                                        if (n2 % 2 != 0) {}
                                        Label_0053: {
                                            return true;
                                        }
                                    }
                                    case 36: {
                                        boolean b;
                                        if (jsonObject.has(\u02cb(-115, 1138247454, (byte)(-93), -565428020).intern())) {
                                            b = false;
                                        }
                                        else {
                                            b = true;
                                        }
                                        switch (b) {
                                            default: {
                                                final int n3 = g.\u02bb + 71;
                                                g.\u0971\u02cb = n3 % 128;
                                                int n4;
                                                if (n3 % 2 == 0) {
                                                    n4 = 96;
                                                }
                                                else {
                                                    n4 = 56;
                                                }
                                                switch (n4) {
                                                    default: {
                                                        final JSONArray jsonArray = jsonObject.getJSONArray(\u02cb(-115, 1138247454, (byte)(-93), -565428020).intern());
                                                        n5 = jsonArray.length();
                                                        n6 = 0;
                                                        break Label_0304;
                                                    }
                                                    case 96: {
                                                        final JSONArray jsonArray = jsonObject.getJSONArray(\u02cb(-115, 1138247454, (byte)(-93), -565428020).intern());
                                                        n5 = jsonArray.length();
                                                        n6 = 0;
                                                        break Label_0304;
                                                    }
                                                }
                                                break;
                                            }
                                            case 1: {
                                                return false;
                                            }
                                        }
                                        break;
                                    }
                                }
                                Label_0290: {
                                    final int n7 = 1;
                                }
                                // iftrue(Label_0290:, jsonArray.getInt(n6) != Build$VERSION.SDK_INT)
                                // iftrue(Label_0053:, n9 % 2 != 0)
                                Label_0192: {
                                    break Label_0192;
                                    Block_6: {
                                        break Block_6;
                                        Label_0235:
                                        ++n6;
                                        break Label_0304;
                                    }
                                    final int n7 = 0;
                                    break Label_0192;
                                    n8 = 24;
                                    break Label_0312;
                                    Label_0212:
                                    final int n9 = g.\u02bb + 65;
                                    g.\u0971\u02cb = n9 % 128;
                                    return true;
                                }
                            }
                            // switch([Lcom.strobel.decompiler.ast.Label;@7f93c7f1, n7)
                            catch (Exception ex) {
                                return true;
                            }
                        }
                        if (n6 >= n5) {
                            continue;
                        }
                        break;
                    }
                    n8 = 41;
                }
                switch (n8) {
                    case 24: {
                        return false;
                    }
                    default: {
                        continue Label_0284_Outer;
                    }
                }
                break;
            }
        }
    }
    
    private void \u0971(final String s) {
        final int n = 1;
        if (s == null) {
            return;
        }
    Label_0120_Outer:
        while (true) {
        Label_0247:
            while (true) {
                while (true) {
                    int \u0971 = 0;
                    Label_0922: {
                        JSONObject jsonObject = null;
                        String string;
                        boolean equals = false;
                        boolean equals2 = false;
                        int n2 = 0;
                        int n3;
                        int n4;
                        boolean b = false;
                        int int1 = 0;
                        int n5;
                        int n6;
                        int n7;
                        int n8 = 0;
                        int n9;
                        int n10;
                        int n11;
                        boolean b2;
                        int n12;
                        boolean b3;
                        int int2;
                        int n13;
                        int n14;
                        int n15;
                        int n16;
                        boolean b4;
                        int n17;
                        boolean b5;
                        Label_0120:Label_0107_Outer:
                        while (true) {
                        Label_0107:
                            while (true) {
                                while (true) {
                                Label_0191:
                                    while (true) {
                                        Label_0148_Outer:Label_0274_Outer:
                                        while (true) {
                                        Label_0274:
                                            while (true) {
                                                Block_16_Outer:Block_19_Outer:Label_0358_Outer:
                                                while (true) {
                                                Label_0358:
                                                    while (true) {
                                                        try {
                                                            jsonObject = new JSONObject(s);
                                                            string = jsonObject.getString(\u02cb(-115, 1138247458, (byte)89, -565428102).intern());
                                                            equals = string.equals(\u02cb(-77, 1138247394, (byte)(-126), -565428101).intern());
                                                            equals2 = string.equals(\u02cb(-77, 1138247399, (byte)(-70), -565428062).intern());
                                                            if (!string.equals(\u02cb(-115, 1138247454, (byte)90, -565428023).intern())) {
                                                                n2 = 66;
                                                                break Label_0191;
                                                            }
                                                            break Label_0358;
                                                            // switch([Lcom.strobel.decompiler.ast.Label;@5c3aa2b9, n3)
                                                            // switch([Lcom.strobel.decompiler.ast.Label;@ebcc9ea, n4)
                                                            // iftrue(Label_0796:, !jsonObject.has(\u02cb(-115, 1138247448, (byte)-86, -565428022).intern()))
                                                            // iftrue(Label_0802:, int1 < 100)
                                                            // switch([Lcom.strobel.decompiler.ast.Label;@15de3f43, n5)
                                                            // switch([Lcom.strobel.decompiler.ast.Label;@967d1df, n6)
                                                            // iftrue(Label_0780:, \u02ce(jsonObject))
                                                            // iftrue(Label_0812:, !jsonObject.has(\u02cb(-115, 1138247444, (byte)-11, -565428021).intern()))
                                                            // iftrue(Label_0318:, n7 % 2 != 0)
                                                            // iftrue(Label_0840:, n9 % 2 != 0)
                                                            // iftrue(Label_0414:, n11 % 2 != 0)
                                                            // switch([Lcom.strobel.decompiler.ast.Label;@418520, n10)
                                                            // iftrue(Label_0817:, !\u0971(jsonObject))
                                                            Block_17: {
                                                                Block_14: {
                                                                    while (true) {
                                                                        b = true;
                                                                        break Label_0120;
                                                                        break Block_14;
                                                                        Label_0212: {
                                                                            int1 = jsonObject.getInt(\u02cb(-115, 1138247448, (byte)(-86), -565428022).intern());
                                                                        }
                                                                        continue Label_0120_Outer;
                                                                    }
                                                                    Block_13: {
                                                                        while (true) {
                                                                            while (true) {
                                                                                n5 = 0;
                                                                                continue Label_0274_Outer;
                                                                            }
                                                                            break Block_13;
                                                                            break Block_17;
                                                                            Label_0292: {
                                                                                n7 = g.\u02bb + 53;
                                                                            }
                                                                            g.\u0971\u02cb = n7 % 128;
                                                                            n5 = n;
                                                                            continue Label_0107_Outer;
                                                                        }
                                                                        this.\u0971 = \u0971;
                                                                        continue Label_0247;
                                                                    }
                                                                    n8 = 43;
                                                                    break Label_0107;
                                                                }
                                                                n4 = 27;
                                                                continue Block_16_Outer;
                                                                while (true) {
                                                                    while (true) {
                                                                        ((f)MoatAnalytics.getInstance()).\u02cb = true;
                                                                        return;
                                                                        Label_0392: {
                                                                            return;
                                                                        }
                                                                        Label_0168:
                                                                        n9 = g.\u02bb + 77;
                                                                        g.\u0971\u02cb = n9 % 128;
                                                                        Block_15: {
                                                                            break Block_15;
                                                                            n3 = 28;
                                                                            continue Label_0148_Outer;
                                                                            Label_0336:
                                                                            this.\u02cb = jsonObject.getInt(\u02cb(-115, 1138247444, (byte)(-11), -565428021).intern());
                                                                            break Label_0358;
                                                                        }
                                                                        n10 = 1;
                                                                        break Label_0191;
                                                                        Label_0393:
                                                                        n11 = g.\u02bb + 23;
                                                                        g.\u0971\u02cb = n11 % 128;
                                                                        continue Block_19_Outer;
                                                                    }
                                                                    continue Label_0358_Outer;
                                                                }
                                                            }
                                                            n6 = 0;
                                                            continue Label_0274;
                                                        }
                                                        catch (Exception ex) {
                                                            this.\u02cf = false;
                                                            this.\u02ce = false;
                                                            this.\u0971 = 200;
                                                            o.\u02ce(ex);
                                                            return;
                                                        }
                                                        if (!equals) {
                                                            b2 = false;
                                                        }
                                                        else {
                                                            b2 = true;
                                                        }
                                                        switch (b2) {
                                                            default: {
                                                                continue Label_0107;
                                                            }
                                                            case 0: {
                                                                n12 = g.\u0971\u02cb + 57;
                                                                g.\u02bb = n12 % 128;
                                                                if (n12 % 2 != 0) {}
                                                                if (equals2) {
                                                                    b3 = false;
                                                                }
                                                                else {
                                                                    b3 = true;
                                                                }
                                                                switch (b3) {
                                                                    case 1: {
                                                                        continue Label_0120;
                                                                    }
                                                                    default: {
                                                                        continue Label_0107;
                                                                    }
                                                                }
                                                                break;
                                                            }
                                                        }
                                                        Label_0709: {
                                                            int2 = jsonObject.getInt(\u02cb(-115, 1138247448, (byte)(-86), -565428022).intern());
                                                        }
                                                        if (int2 >= 100) {
                                                            n13 = 77;
                                                        }
                                                        else {
                                                            n13 = 19;
                                                        }
                                                        switch (n13) {
                                                            case 19: {
                                                                continue Label_0247;
                                                            }
                                                            default: {
                                                                \u0971 = int2;
                                                                break Label_0922;
                                                            }
                                                        }
                                                        Label_0740:
                                                        this.\u02cb = jsonObject.getInt(\u02cb(-115, 1138247444, (byte)(-11), -565428021).intern());
                                                        continue Label_0358;
                                                    }
                                                    n2 = 2;
                                                    break Label_0191;
                                                    Label_0780: {
                                                        n8 = 56;
                                                    }
                                                    break Label_0107;
                                                    Label_0796:
                                                    n4 = 75;
                                                    continue Label_0120_Outer;
                                                }
                                                Label_0802: {
                                                    b = false;
                                                }
                                                break Label_0120;
                                                Label_0812:
                                                n6 = 1;
                                                continue Label_0274;
                                            }
                                            Label_0817: {
                                                n3 = 96;
                                            }
                                            continue Label_0148_Outer;
                                        }
                                        Label_0840: {
                                            n10 = 0;
                                        }
                                        continue Label_0191;
                                    }
                                    switch (n2) {
                                        case 66: {
                                            continue;
                                        }
                                        default: {
                                            continue Label_0107;
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            switch (n8) {
                                case 43: {
                                    n14 = g.\u02bb + 95;
                                    g.\u0971\u02cb = n14 % 128;
                                    if (n14 % 2 == 0) {
                                        n15 = 80;
                                    }
                                    else {
                                        n15 = 6;
                                    }
                                    Label_0543: {
                                        switch (n15) {
                                            case 6: {
                                                switch (\u02cb(jsonObject)) {
                                                    case 0: {
                                                        break Label_0543;
                                                    }
                                                    default: {
                                                        continue Label_0120;
                                                    }
                                                }
                                                break;
                                            }
                                            default: {
                                                if (!\u02cb(jsonObject)) {
                                                    n16 = 60;
                                                }
                                                else {
                                                    n16 = 69;
                                                }
                                                switch (n16) {
                                                    case 69: {
                                                        continue Label_0120;
                                                    }
                                                    default: {
                                                        break Label_0543;
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    this.\u02cf = true;
                                    this.\u02ce = equals;
                                    this.\u02ca = equals2;
                                    if (this.\u02ca) {
                                        b4 = true;
                                    }
                                    else {
                                        b4 = false;
                                    }
                                    switch (b4) {
                                        case 0: {
                                            continue Label_0120;
                                        }
                                        default: {
                                            n17 = g.\u0971\u02cb + 81;
                                            g.\u02bb = n17 % 128;
                                            if (n17 % 2 != 0) {
                                                b5 = false;
                                            }
                                            else {
                                                b5 = true;
                                            }
                                            switch (b5) {
                                                default: {
                                                    this.\u02ce = true;
                                                    continue Label_0120;
                                                }
                                                case 1: {
                                                    this.\u02ce = true;
                                                    continue Label_0120;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                                default: {
                                    continue Label_0120;
                                }
                            }
                            break;
                        }
                        switch (b) {
                            case 0: {
                                continue Label_0247;
                            }
                            default: {
                                \u0971 = int1;
                                break;
                            }
                        }
                    }
                    switch (\u0971 <= 1000) {
                        case 0: {
                            continue Label_0247;
                        }
                        default: {
                            continue;
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    private static boolean \u0971(final JSONObject jsonObject) {
        boolean b;
        int n;
        int n2;
        String \u02cb = null;
        JSONArray jsonArray = null;
        int length;
        int i = 0;
        int n3;
        int n4;
        int n5;
        int n6;
        boolean b2;
        int n7;
        Label_0030_Outer:Label_0115_Outer:Label_0162_Outer:
        while (true) {
            b = true;
            while (true) {
                Label_0300: {
                    while (true) {
                    Label_0294:
                        while (true) {
                        Label_0289:
                            while (true) {
                            Label_0283:
                                while (true) {
                                    Label_0237: {
                                        try {
                                            if (jsonObject.has(\u02cb(-115, 1138247440, (byte)1, -565428018).intern())) {
                                                n = 56;
                                                switch (n) {
                                                    case 56: {
                                                        n2 = g.\u02bb + 99;
                                                        g.\u0971\u02cb = n2 % 128;
                                                        if (n2 % 2 == 0) {}
                                                        \u02cb = r.\u02ca().\u02cb();
                                                        jsonArray = jsonObject.getJSONArray(\u02cb(-115, 1138247440, (byte)1, -565428018).intern());
                                                        length = jsonArray.length();
                                                        i = 0;
                                                        Label_0136: {
                                                            while (i < length) {
                                                                n3 = 0;
                                                                switch (n3) {
                                                                    default: {
                                                                        break Label_0136;
                                                                    }
                                                                    case 0: {
                                                                        n4 = g.\u02bb + 49;
                                                                        g.\u0971\u02cb = n4 % 128;
                                                                        if (n4 % 2 != 0) {
                                                                            break Label_0300;
                                                                        }
                                                                        n5 = 0;
                                                                        switch (n5) {
                                                                            default: {
                                                                                if (!jsonArray.getString(i).contentEquals(\u02cb)) {
                                                                                    break Label_0294;
                                                                                }
                                                                                n6 = 11;
                                                                                switch (n6) {
                                                                                    default: {
                                                                                        return true;
                                                                                    }
                                                                                    case 75: {
                                                                                        ++i;
                                                                                        continue Label_0030_Outer;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                            case 0: {
                                                                                break Label_0237;
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            break Label_0289;
                                                        }
                                                        break;
                                                    }
                                                }
                                                b2 = false;
                                                return b2;
                                            }
                                            break Label_0283;
                                        }
                                        catch (Exception ex) {
                                            o.\u02ce(ex);
                                            return false;
                                        }
                                    }
                                    if (jsonArray.getString(i).contentEquals(\u02cb)) {
                                        n7 = 62;
                                    }
                                    else {
                                        n7 = 73;
                                    }
                                    b2 = b;
                                    switch (n7) {
                                        case 62: {
                                            return b2;
                                        }
                                        default: {
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                n = 19;
                                continue Label_0115_Outer;
                            }
                            n3 = 1;
                            continue Label_0162_Outer;
                        }
                        n6 = 75;
                        continue;
                    }
                }
                n5 = 88;
                continue;
            }
        }
    }
    
    final boolean \u02ca() {
        return this.\u02ce;
    }
    
    final int \u02cb() {
        if (this.\u02cf) {
            return t.a.\u02ce;
        }
        return t.a.\u0971;
    }
    
    final int \u02ce() {
        return this.\u02cb;
    }
    
    final int \u02cf() {
        return this.\u0971;
    }
    
    final boolean \u0971() {
        return this.\u02ca;
    }
}
