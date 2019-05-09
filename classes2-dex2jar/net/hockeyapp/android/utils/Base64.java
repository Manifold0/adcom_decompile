// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import java.io.UnsupportedEncodingException;

public class Base64
{
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    private static final String TAG = "BASE64";
    public static final int URL_SAFE = 8;
    
    private Base64() {
    }
    
    public static byte[] decode(final String s, final int n) {
        return decode(s.getBytes(), n);
    }
    
    public static byte[] decode(final byte[] array, final int n) {
        return decode(array, 0, array.length, n);
    }
    
    public static byte[] decode(byte[] array, final int n, final int n2, final int n3) {
        final Decoder decoder = new Decoder(n3, new byte[n2 * 3 / 4]);
        if (!decoder.process(array, n, n2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (decoder.op == decoder.output.length) {
            return decoder.output;
        }
        array = new byte[decoder.op];
        System.arraycopy(decoder.output, 0, array, 0, decoder.op);
        return array;
    }
    
    public static byte[] encode(final byte[] array, final int n) {
        return encode(array, 0, array.length, n);
    }
    
    public static byte[] encode(final byte[] array, final int n, final int n2, int n3) {
        final Encoder encoder = new Encoder(n3, null);
        final int n4 = n2 / 3 * 4;
        if (encoder.do_padding) {
            n3 = n4;
            if (n2 % 3 > 0) {
                n3 = n4 + 4;
            }
        }
        else {
            n3 = n4;
            switch (n2 % 3) {
                case 2: {
                    n3 = n4 + 3;
                    break;
                }
                case 1: {
                    n3 = n4 + 2;
                }
                case 0: {
                    break;
                }
                default: {
                    n3 = n4;
                    break;
                }
            }
        }
        int n5 = n3;
        if (encoder.do_newline) {
            n5 = n3;
            if (n2 > 0) {
                final int n6 = (n2 - 1) / 57;
                int n7;
                if (encoder.do_cr) {
                    n7 = 2;
                }
                else {
                    n7 = 1;
                }
                n5 = n3 + n7 * (n6 + 1);
            }
        }
        encoder.output = new byte[n5];
        encoder.process(array, n, n2, true);
        if (encoder.op != n5) {
            throw new AssertionError();
        }
        return encoder.output;
    }
    
    public static String encodeToString(final byte[] array, final int n) {
        try {
            return new String(encode(array, n), "US-ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public static String encodeToString(final byte[] array, final int n, final int n2, final int n3) {
        try {
            return new String(encode(array, n, n2, n3), "US-ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    abstract static class Coder
    {
        public int op;
        public byte[] output;
        
        public abstract int maxOutputSize(final int p0);
        
        public abstract boolean process(final byte[] p0, final int p1, final int p2, final boolean p3);
    }
    
    static class Decoder extends Coder
    {
        private static final int[] DECODE;
        private static final int[] DECODE_WEBSAFE;
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;
        
        static {
            DECODE = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
            DECODE_WEBSAFE = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        }
        
        public Decoder(final int n, final byte[] output) {
            this.output = output;
            int[] alphabet;
            if ((n & 0x8) == 0x0) {
                alphabet = Decoder.DECODE;
            }
            else {
                alphabet = Decoder.DECODE_WEBSAFE;
            }
            this.alphabet = alphabet;
            this.state = 0;
            this.value = 0;
        }
        
        @Override
        public int maxOutputSize(final int n) {
            return n * 3 / 4 + 10;
        }
        
        @Override
        public boolean process(final byte[] array, int n, int value, final boolean b) {
            if (this.state == 6) {
                return false;
            }
            int i = n;
            final int n2 = value + n;
            int state = this.state;
            value = this.value;
            int n3 = 0;
            final byte[] output = this.output;
            final int[] alphabet = this.alphabet;
            while (true) {
                while (i < n2) {
                    int n4 = n3;
                    int n5 = i;
                    int n6 = value;
                    if (state == 0) {
                        n = value;
                        while (i + 4 <= n2) {
                            value = (alphabet[array[i] & 0xFF] << 18 | alphabet[array[i + 1] & 0xFF] << 12 | alphabet[array[i + 2] & 0xFF] << 6 | alphabet[array[i + 3] & 0xFF]);
                            if ((n = value) < 0) {
                                break;
                            }
                            output[n3 + 2] = (byte)value;
                            output[n3 + 1] = (byte)(value >> 8);
                            output[n3] = (byte)(value >> 16);
                            n3 += 3;
                            i += 4;
                            n = value;
                        }
                        n4 = n3;
                        n5 = i;
                        n6 = n;
                        if (i >= n2) {
                            value = n;
                            n = n3;
                            if (!b) {
                                this.state = state;
                                this.value = value;
                                this.op = n;
                                return true;
                            }
                            switch (state) {
                                case 1: {
                                    this.state = 6;
                                    return false;
                                }
                                case 2: {
                                    final int n7 = n + 1;
                                    output[n] = (byte)(value >> 4);
                                    n = n7;
                                    break;
                                }
                                case 3: {
                                    final int n8 = n + 1;
                                    output[n] = (byte)(value >> 10);
                                    output[n8] = (byte)(value >> 2);
                                    n = n8 + 1;
                                    break;
                                }
                                case 4: {
                                    this.state = 6;
                                    return false;
                                }
                            }
                            this.state = state;
                            this.op = n;
                            return true;
                        }
                    }
                    final int n9 = alphabet[array[n5] & 0xFF];
                    switch (state) {
                        default: {
                            value = n6;
                            n = state;
                            n3 = n4;
                            break;
                        }
                        case 0: {
                            if (n9 >= 0) {
                                value = n9;
                                n = state + 1;
                                n3 = n4;
                                break;
                            }
                            n3 = n4;
                            n = state;
                            value = n6;
                            if (n9 != -1) {
                                this.state = 6;
                                return false;
                            }
                            break;
                        }
                        case 1: {
                            if (n9 >= 0) {
                                value = (n6 << 6 | n9);
                                n = state + 1;
                                n3 = n4;
                                break;
                            }
                            n3 = n4;
                            n = state;
                            value = n6;
                            if (n9 != -1) {
                                this.state = 6;
                                return false;
                            }
                            break;
                        }
                        case 2: {
                            if (n9 >= 0) {
                                value = (n6 << 6 | n9);
                                n = state + 1;
                                n3 = n4;
                                break;
                            }
                            if (n9 == -2) {
                                output[n4] = (byte)(n6 >> 4);
                                n = 4;
                                n3 = n4 + 1;
                                value = n6;
                                break;
                            }
                            n3 = n4;
                            n = state;
                            value = n6;
                            if (n9 != -1) {
                                this.state = 6;
                                return false;
                            }
                            break;
                        }
                        case 3: {
                            if (n9 >= 0) {
                                value = (n6 << 6 | n9);
                                output[n4 + 2] = (byte)value;
                                output[n4 + 1] = (byte)(value >> 8);
                                output[n4] = (byte)(value >> 16);
                                n3 = n4 + 3;
                                n = 0;
                                break;
                            }
                            if (n9 == -2) {
                                output[n4 + 1] = (byte)(n6 >> 2);
                                output[n4] = (byte)(n6 >> 10);
                                n3 = n4 + 2;
                                n = 5;
                                value = n6;
                                break;
                            }
                            n3 = n4;
                            n = state;
                            value = n6;
                            if (n9 != -1) {
                                this.state = 6;
                                return false;
                            }
                            break;
                        }
                        case 4: {
                            if (n9 == -2) {
                                n = state + 1;
                                n3 = n4;
                                value = n6;
                                break;
                            }
                            n3 = n4;
                            n = state;
                            value = n6;
                            if (n9 != -1) {
                                this.state = 6;
                                return false;
                            }
                            break;
                        }
                        case 5: {
                            n3 = n4;
                            n = state;
                            value = n6;
                            if (n9 != -1) {
                                this.state = 6;
                                return false;
                            }
                            break;
                        }
                    }
                    i = n5 + 1;
                    state = n;
                }
                n = n3;
                continue;
            }
        }
    }
    
    static class Encoder extends Coder
    {
        private static final byte[] ENCODE;
        private static final byte[] ENCODE_WEBSAFE;
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte[] tail;
        int tailLen;
        
        static {
            ENCODE = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
            ENCODE_WEBSAFE = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
        }
        
        public Encoder(int count, byte[] array) {
            final boolean b = true;
            this.output = array;
            this.do_padding = ((count & 0x1) == 0x0);
            this.do_newline = ((count & 0x2) == 0x0);
            this.do_cr = ((count & 0x4) != 0x0 && b);
            if ((count & 0x8) == 0x0) {
                array = Encoder.ENCODE;
            }
            else {
                array = Encoder.ENCODE_WEBSAFE;
            }
            this.alphabet = array;
            this.tail = new byte[2];
            this.tailLen = 0;
            if (this.do_newline) {
                count = 19;
            }
            else {
                count = -1;
            }
            this.count = count;
        }
        
        @Override
        public int maxOutputSize(final int n) {
            return n * 8 / 5 + 10;
        }
        
        @Override
        public boolean process(final byte[] array, int n, int op, final boolean b) {
            final byte[] alphabet = this.alphabet;
            final byte[] output = this.output;
            final int n2 = 0;
            final int count = this.count;
            final int n3 = n;
            final int n4 = op + n;
            op = -1;
            n = n3;
            int n5 = op;
            while (true) {
                switch (this.tailLen) {
                    default: {
                        n5 = op;
                        n = n3;
                    }
                    case 0: {
                        op = count;
                        int n6 = n2;
                        int n7 = n;
                        while (true) {
                            Label_1231: {
                                if (n5 == -1) {
                                    break Label_1231;
                                }
                                final int n8 = 0 + 1;
                                output[0] = alphabet[n5 >> 18 & 0x3F];
                                op = n8 + 1;
                                output[n8] = alphabet[n5 >> 12 & 0x3F];
                                final int n9 = op + 1;
                                output[op] = alphabet[n5 >> 6 & 0x3F];
                                final int n10 = n9 + 1;
                                output[n9] = alphabet[n5 & 0x3F];
                                final int n11 = op = count - 1;
                                n6 = n10;
                                n7 = n;
                                if (n11 != 0) {
                                    break Label_1231;
                                }
                                op = n10;
                                if (this.do_cr) {
                                    output[n10] = 13;
                                    op = n10 + 1;
                                }
                                final int n12 = op + 1;
                                output[op] = 10;
                                int count2 = 19;
                                op = n;
                                n = n12;
                                while (op + 3 <= n4) {
                                    final int n13 = (array[op] & 0xFF) << 16 | (array[op + 1] & 0xFF) << 8 | (array[op + 2] & 0xFF);
                                    output[n] = alphabet[n13 >> 18 & 0x3F];
                                    output[n + 1] = alphabet[n13 >> 12 & 0x3F];
                                    output[n + 2] = alphabet[n13 >> 6 & 0x3F];
                                    output[n + 3] = alphabet[n13 & 0x3F];
                                    final int n14 = op + 3;
                                    n += 4;
                                    final int n15 = op = count2 - 1;
                                    n6 = n;
                                    n7 = n14;
                                    if (n15 != 0) {
                                        break Label_1231;
                                    }
                                    op = n;
                                    if (this.do_cr) {
                                        output[n] = 13;
                                        op = n + 1;
                                    }
                                    n = op + 1;
                                    output[op] = 10;
                                    count2 = 19;
                                    op = n14;
                                }
                                if (b) {
                                    int n22 = 0;
                                    Label_0736: {
                                        if (op - this.tailLen == n4 - 1) {
                                            int n16 = 0;
                                            byte b2;
                                            if (this.tailLen > 0) {
                                                b2 = this.tail[0];
                                                n16 = 0 + 1;
                                            }
                                            else {
                                                final int n17 = op + 1;
                                                b2 = array[op];
                                                op = n17;
                                            }
                                            final int n18 = (b2 & 0xFF) << 4;
                                            this.tailLen -= n16;
                                            final int n19 = n + 1;
                                            output[n] = alphabet[n18 >> 6 & 0x3F];
                                            n = n19 + 1;
                                            output[n19] = alphabet[n18 & 0x3F];
                                            int n20 = n;
                                            if (this.do_padding) {
                                                final int n21 = n + 1;
                                                output[n] = 61;
                                                n20 = n21 + 1;
                                                output[n21] = 61;
                                            }
                                            n = n20;
                                            n22 = op;
                                            if (!this.do_newline) {
                                                break Label_0736;
                                            }
                                            n = n20;
                                            if (this.do_cr) {
                                                output[n20] = 13;
                                                n = n20 + 1;
                                            }
                                            final int n23 = n + 1;
                                            output[n] = 10;
                                            n = n23;
                                        }
                                        else {
                                            if (op - this.tailLen != n4 - 2) {
                                                int n24 = n;
                                                if (this.do_newline && (n24 = n) > 0) {
                                                    n24 = n;
                                                    if (count2 != 19) {
                                                        if (this.do_cr) {
                                                            final int n25 = n + 1;
                                                            output[n] = 13;
                                                            n = n25;
                                                        }
                                                        n24 = n + 1;
                                                        output[n] = 10;
                                                    }
                                                }
                                                n = n24;
                                                n22 = op;
                                                break Label_0736;
                                            }
                                            int n26 = 0;
                                            byte b3;
                                            if (this.tailLen > 1) {
                                                b3 = this.tail[0];
                                                n26 = 0 + 1;
                                            }
                                            else {
                                                final int n27 = op + 1;
                                                b3 = array[op];
                                                op = n27;
                                            }
                                            byte b4;
                                            if (this.tailLen > 0) {
                                                b4 = this.tail[n26];
                                                ++n26;
                                            }
                                            else {
                                                b4 = array[op];
                                                ++op;
                                            }
                                            final int n28 = (b3 & 0xFF) << 10 | (b4 & 0xFF) << 2;
                                            this.tailLen -= n26;
                                            final int n29 = n + 1;
                                            output[n] = alphabet[n28 >> 12 & 0x3F];
                                            final int n30 = n29 + 1;
                                            output[n29] = alphabet[n28 >> 6 & 0x3F];
                                            n = n30 + 1;
                                            output[n30] = alphabet[n28 & 0x3F];
                                            int n31 = n;
                                            if (this.do_padding) {
                                                output[n] = 61;
                                                n31 = n + 1;
                                            }
                                            n = n31;
                                            n22 = op;
                                            if (!this.do_newline) {
                                                break Label_0736;
                                            }
                                            n = n31;
                                            if (this.do_cr) {
                                                output[n31] = 13;
                                                n = n31 + 1;
                                            }
                                            final int n32 = n + 1;
                                            output[n] = 10;
                                            n = n32;
                                        }
                                        n22 = op;
                                    }
                                    if (this.tailLen != 0) {
                                        HockeyLog.error("BASE64", "Error during encoding");
                                    }
                                    op = n;
                                    if (n22 != n4) {
                                        HockeyLog.error("BASE64", "Error during encoding");
                                        op = n;
                                    }
                                }
                                else if (op == n4 - 1) {
                                    this.tail[this.tailLen++] = array[op];
                                    op = n;
                                }
                                else {
                                    if (op == n4 - 2) {
                                        this.tail[this.tailLen++] = array[op];
                                        this.tail[this.tailLen++] = array[op + 1];
                                    }
                                    op = n;
                                }
                                this.op = op;
                                this.count = count2;
                                return true;
                            }
                            final int n33 = n7;
                            n = n6;
                            int count2 = op;
                            op = n33;
                            continue;
                        }
                    }
                    case 1: {
                        n = n3;
                        n5 = op;
                        if (n3 + 2 <= n4) {
                            op = this.tail[0];
                            final int n34 = n3 + 1;
                            final byte b5 = array[n3];
                            n = n34 + 1;
                            n5 = ((op & 0xFF) << 16 | (b5 & 0xFF) << 8 | (array[n34] & 0xFF));
                            this.tailLen = 0;
                        }
                        continue;
                    }
                    case 2: {
                        n = n3;
                        n5 = op;
                        if (n3 + 1 <= n4) {
                            n5 = ((this.tail[0] & 0xFF) << 16 | (this.tail[1] & 0xFF) << 8 | (array[n3] & 0xFF));
                            this.tailLen = 0;
                            n = n3 + 1;
                        }
                        continue;
                    }
                }
                break;
            }
        }
    }
}
