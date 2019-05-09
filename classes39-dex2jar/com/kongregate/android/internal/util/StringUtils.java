// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.view.View;
import java.util.StringTokenizer;
import java.util.Locale;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.net.URLDecoder;
import android.text.method.MovementMethod;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.SpannableStringBuilder;
import android.text.Html;
import android.graphics.Color;
import android.widget.TextView;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;

public class StringUtils
{
    public static final String a = "";
    public static final String b = "";
    private static Context c;
    private static final Pattern d;
    private static final Pattern e;
    private static final Pattern f;
    private static final Pattern g;
    private static final Pattern h;
    private static final ThreadLocal<Matcher> i;
    
    static {
        d = Pattern.compile("['\"]");
        e = Pattern.compile("[^A-Za-z0-9]");
        f = Pattern.compile("\\s+");
        g = Pattern.compile("(-)$");
        h = Pattern.compile("^(-)");
        i = new ThreadLocal<Matcher>() {
            protected Matcher a() {
                return StringUtils.d.matcher("");
            }
        };
    }
    
    public static String a(final int n) {
        return a(StringUtils.c, n);
    }
    
    public static String a(final int n, final int n2) {
        return a(a(n), n2);
    }
    
    public static String a(final int n, final Object... array) {
        return a(StringUtils.c, n, array);
    }
    
    public static String a(final Context context, final int n) {
        return context.getString(n);
    }
    
    public static String a(final Context context, final int n, final Object... array) {
        return String.format(a(context, n), array);
    }
    
    public static String a(final Iterable<?> iterable, final char c) {
        if (iterable == null) {
            return null;
        }
        return a(iterable.iterator(), c);
    }
    
    public static String a(final Iterable<?> iterable, final String s) {
        if (iterable == null) {
            return null;
        }
        return a(iterable.iterator(), s);
    }
    
    public static String a(String encode) {
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
            return encode;
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    public static String a(final String s, final int n) {
        if (n == 1) {
            return s;
        }
        return s + "s";
    }
    
    public static String a(final String s, final String s2, final int n) {
        final String[] split = Pattern.compile(s2, 16).split(s);
        final StringBuilder sb = new StringBuilder();
        for (int length = split.length, i = 0; i < length; ++i) {
            sb.append(String.format("%" + n + 's', split[i]));
        }
        return sb.toString().replace(" ", ".");
    }
    
    public static String a(String string, final byte[] array) {
        final int n = 0;
        final byte[] array2 = new byte[string.length() / 2];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = Byte.decode("0x" + string.substring(i * 2, i * 2 + 2));
        }
        string = "";
        for (int j = n; j < array2.length; ++j) {
            string += (char)(array[j % array.length] ^ array2[j]);
        }
        return string;
    }
    
    public static String a(final Iterator<?> iterator, final char c) {
        if (iterator != null) {
            if (!iterator.hasNext()) {
                return "";
            }
            final Object next = iterator.next();
            if (iterator.hasNext()) {
                final StringBuilder sb = new StringBuilder(256);
                if (next != null) {
                    sb.append(next);
                }
                while (iterator.hasNext()) {
                    sb.append(c);
                    final Object next2 = iterator.next();
                    if (next2 != null) {
                        sb.append(next2);
                    }
                }
                return sb.toString();
            }
            if (next != null) {
                return next.toString();
            }
        }
        return null;
    }
    
    public static String a(final Iterator<?> iterator, final String s) {
        if (iterator != null) {
            if (!iterator.hasNext()) {
                return "";
            }
            final Object next = iterator.next();
            if (iterator.hasNext()) {
                final StringBuilder sb = new StringBuilder(256);
                if (next != null) {
                    sb.append(next);
                }
                while (iterator.hasNext()) {
                    if (s != null) {
                        sb.append(s);
                    }
                    final Object next2 = iterator.next();
                    if (next2 != null) {
                        sb.append(next2);
                    }
                }
                return sb.toString();
            }
            if (next != null) {
                return next.toString();
            }
        }
        return null;
    }
    
    public static String a(final byte[] array) {
        if (array == null) {
            return null;
        }
        try {
            return new String(array, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            try {
                return new String(array, "UTF-8");
            }
            catch (UnsupportedEncodingException ex2) {
                return new String(array);
            }
        }
    }
    
    public static <T> String a(final T... array) {
        return a((Object[])array, null);
    }
    
    public static String a(final Object[] array, final char c) {
        if (array == null) {
            return null;
        }
        return a(array, c, 0, array.length);
    }
    
    public static String a(final Object[] array, final char c, final int n, final int n2) {
        if (array == null) {
            return null;
        }
        final int n3 = n2 - n;
        if (n3 <= 0) {
            return "";
        }
        int length;
        if (array[n] == null) {
            length = 16;
        }
        else {
            length = array[n].toString().length();
        }
        final StringBuilder sb = new StringBuilder((length + 1) * n3);
        for (int i = n; i < n2; ++i) {
            if (i > n) {
                sb.append(c);
            }
            if (array[i] != null) {
                sb.append(array[i]);
            }
        }
        return sb.toString();
    }
    
    public static String a(final Object[] array, final String s) {
        if (array == null) {
            return null;
        }
        return a(array, s, 0, array.length);
    }
    
    public static String a(final Object[] array, final String s, final int n, final int n2) {
        if (array == null) {
            return null;
        }
        String s2;
        if ((s2 = s) == null) {
            s2 = "";
        }
        final int n3 = n2 - n;
        if (n3 <= 0) {
            return "";
        }
        int length;
        if (array[n] == null) {
            length = 16;
        }
        else {
            length = array[n].toString().length();
        }
        final StringBuilder sb = new StringBuilder((length + s2.length()) * n3);
        for (int i = n; i < n2; ++i) {
            if (i > n) {
                sb.append(s2);
            }
            if (array[i] != null) {
                sb.append(array[i]);
            }
        }
        return sb.toString();
    }
    
    public static void a(final Context c) {
        StringUtils.c = c;
    }
    
    public static void a(final TextView textView, final String s) {
        a(textView, s, null);
    }
    
    public static void a(final TextView textView, final String text, final a a) {
        textView.setLinkTextColor(Color.parseColor("#990000"));
        if (text != null && (text.contains("<a") || text.contains("<p>"))) {
            final SpannableStringBuilder value = SpannableStringBuilder.valueOf((CharSequence)Html.fromHtml(text));
            if (a != null) {
                final URLSpan[] array = (URLSpan[])value.getSpans(0, value.length(), (Class)URLSpan.class);
                for (int length = array.length, i = 0; i < length; ++i) {
                    final URLSpan urlSpan = array[i];
                    final int spanStart = value.getSpanStart((Object)urlSpan);
                    final int spanEnd = value.getSpanEnd((Object)urlSpan);
                    value.removeSpan((Object)urlSpan);
                    value.setSpan((Object)new URLSpanAdapter(urlSpan.getURL(), a), spanStart, spanEnd, 0);
                }
            }
            if (value.toString().endsWith("\n\n")) {
                value.delete(value.length() - 2, value.length());
            }
            textView.setText((CharSequence)value);
            textView.setMovementMethod((MovementMethod)new LinkMovementMethod());
            return;
        }
        textView.setLinksClickable(true);
        textView.setAutoLinkMask(15);
        textView.setText((CharSequence)text);
    }
    
    public static void a(final TextView textView, final String s, final boolean b) {
        a(textView, s, null);
        if (!b) {
            textView.setMovementMethod((MovementMethod)null);
            textView.setLinksClickable(false);
        }
    }
    
    public static boolean a(final CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
    
    public static boolean a(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s.equals(s2);
    }
    
    public static String[] a(final Context context, final int[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = a(context, array[i]);
        }
        return array2;
    }
    
    public static String b(String decode) {
        if (decode == null) {
            return null;
        }
        try {
            decode = URLDecoder.decode(decode, "UTF-8");
            return decode;
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    public static boolean b(final CharSequence charSequence) {
        return !a(charSequence);
    }
    
    public static boolean b(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s.equalsIgnoreCase(s2);
    }
    
    public static String c(String b) {
        try {
            b = com.kongregate.android.internal.util.h.b(MessageDigest.getInstance("SHA1").digest(b.getBytes()));
            return b;
        }
        catch (NoSuchAlgorithmException ex) {
            j.d("No such algorithm (SHA1)", ex);
            return null;
        }
    }
    
    public static boolean c(final CharSequence charSequence) {
        final boolean b = false;
        if (charSequence != null) {
            final int length = charSequence.length();
            if (length != 0) {
                for (int i = 0; i < length; ++i) {
                    final boolean b2 = b;
                    if (!Character.isWhitespace(charSequence.charAt(i))) {
                        return b2;
                    }
                }
                return true;
            }
        }
        return true;
    }
    
    public static boolean c(final String s, final String s2) {
        if (a((CharSequence)s)) {
            return a((CharSequence)s2);
        }
        return s.equals(s2);
    }
    
    public static String d(String b) {
        try {
            b = com.kongregate.android.internal.util.h.b(MessageDigest.getInstance("SHA256").digest(b.getBytes()));
            return b;
        }
        catch (NoSuchAlgorithmException ex) {
            j.d("No such algorithm (SHA256)", ex);
            return null;
        }
    }
    
    public static String d(final String s, final String s2) {
        final BigInteger bigInteger = new BigInteger(s2, 16);
        final String g = g(s);
        int i = 0;
        BigInteger add = bigInteger;
        while (i < g.length()) {
            add = add.add(BigInteger.valueOf(Math.abs(g.charAt(i) ^ '\u00c3')));
            ++i;
        }
        return c(add.toString(10));
    }
    
    public static boolean d(final CharSequence charSequence) {
        return !c(charSequence);
    }
    
    public static String e(final String s) {
        return a(s, ".", 4);
    }
    
    public static boolean e(final String s, final String s2) {
        return e(s).compareTo(e(s2)) >= 0;
    }
    
    public static String f(final String s) {
        if (s == null) {
            return null;
        }
        return s.toLowerCase(Locale.ENGLISH);
    }
    
    public static String g(final String s) {
        if (s == null) {
            return null;
        }
        return s.toUpperCase(Locale.ENGLISH);
    }
    
    public static String h(final String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
        }
        catch (UnsupportedEncodingException ex) {
            return s;
        }
    }
    
    public static String i(final String s) {
        return b(s);
    }
    
    public static boolean j(final String s) {
        return s != null && new StringTokenizer(s).countTokens() == 1;
    }
    
    public static String k(final String s) {
        if (c((CharSequence)s)) {
            return s;
        }
        final String f = f(s);
        final Matcher matcher = StringUtils.i.get();
        return matcher.usePattern(StringUtils.h).reset(matcher.usePattern(StringUtils.g).reset(matcher.usePattern(StringUtils.f).reset(matcher.usePattern(StringUtils.e).reset(matcher.usePattern(StringUtils.d).reset(f).replaceAll("")).replaceAll(" ")).replaceAll("-")).replaceAll("")).replaceAll("");
    }
    
    public static byte[] l(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return s.getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            try {
                return s.getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException ex2) {
                return s.getBytes();
            }
        }
    }
    
    public static class URLSpanAdapter extends URLSpan
    {
        private a a;
        
        public URLSpanAdapter(final String s, final a a) {
            super(s);
            this.a = a;
        }
        
        public void onClick(final View view) {
            boolean a = false;
            if (this.a != null) {
                a = this.a.a(this.getURL());
            }
            if (!a) {
                super.onClick(view);
            }
        }
    }
    
    public interface a
    {
        boolean a(final String p0);
    }
}
