// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import com.google.android.gms.common.util.MurmurHash3;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgu
{
    @Nullable
    public static String[] zzb(@Nullable final String s, final boolean b) {
        if (s == null) {
            return null;
        }
        final ArrayList<String> list = new ArrayList<String>();
        final char[] charArray = s.toCharArray();
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        int i = 0;
    Label_0216_Outer:
        while (i < length) {
            final int codePoint = Character.codePointAt(charArray, i);
            final int charCount = Character.charCount(codePoint);
            while (true) {
                Label_0306: {
                    if (!Character.isLetter(codePoint)) {
                        break Label_0306;
                    }
                    final Character.UnicodeBlock of = Character.UnicodeBlock.of(codePoint);
                    int n3;
                    if (of == Character.UnicodeBlock.BOPOMOFO || of == Character.UnicodeBlock.BOPOMOFO_EXTENDED || of == Character.UnicodeBlock.CJK_COMPATIBILITY || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS || of == Character.UnicodeBlock.HANGUL_JAMO || of == Character.UnicodeBlock.HANGUL_SYLLABLES || of == Character.UnicodeBlock.HIRAGANA || of == Character.UnicodeBlock.KATAKANA || of == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS) {
                        n3 = 1;
                    }
                    else {
                        n3 = 0;
                    }
                    if (n3 == 0) {
                        int n4;
                        if ((codePoint >= 65382 && codePoint <= 65437) || (codePoint >= 65441 && codePoint <= 65500)) {
                            n4 = 1;
                        }
                        else {
                            n4 = 0;
                        }
                        if (n4 == 0) {
                            break Label_0306;
                        }
                    }
                    final int n5 = 1;
                    int n7;
                    int n8;
                    if (n5 != 0) {
                        if (n != 0) {
                            list.add(new String(charArray, n2, i - n2));
                        }
                        list.add(new String(charArray, i, charCount));
                        final int n6 = 0;
                        n7 = n2;
                        n8 = n6;
                    }
                    else if (Character.isLetterOrDigit(codePoint) || Character.getType(codePoint) == 6 || Character.getType(codePoint) == 8) {
                        if (n == 0) {
                            n2 = i;
                        }
                        n7 = n2;
                        n8 = 1;
                    }
                    else if (b && Character.charCount(codePoint) == 1 && Character.toChars(codePoint)[0] == '\'') {
                        if (n == 0) {
                            n2 = i;
                        }
                        n7 = n2;
                        n8 = 1;
                    }
                    else if (n != 0) {
                        list.add(new String(charArray, n2, i - n2));
                        n7 = n2;
                        n8 = 0;
                    }
                    else {
                        final int n9 = n2;
                        n8 = n;
                        n7 = n9;
                    }
                    final int n10 = i + charCount;
                    final int n11 = n7;
                    n = n8;
                    n2 = n11;
                    i = n10;
                    continue Label_0216_Outer;
                }
                final int n5 = 0;
                continue;
            }
        }
        if (n != 0) {
            list.add(new String(charArray, n2, i - n2));
        }
        return list.toArray(new String[list.size()]);
    }
    
    public static int zzz(String o) {
        try {
            o = ((String)o).getBytes("UTF-8");
            return MurmurHash3.murmurhash3_x86_32((byte[])o, 0, o.length, 0);
        }
        catch (UnsupportedEncodingException ex) {
            o = ((String)o).getBytes();
            return MurmurHash3.murmurhash3_x86_32((byte[])o, 0, o.length, 0);
        }
    }
}
