package com.kongregate.android.internal.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /* renamed from: a */
    public static final String f695a = "";
    /* renamed from: b */
    public static final String f696b = "";
    /* renamed from: c */
    private static Context f697c;
    /* renamed from: d */
    private static final Pattern f698d = Pattern.compile("['\"]");
    /* renamed from: e */
    private static final Pattern f699e = Pattern.compile("[^A-Za-z0-9]");
    /* renamed from: f */
    private static final Pattern f700f = Pattern.compile("\\s+");
    /* renamed from: g */
    private static final Pattern f701g = Pattern.compile("(-)$");
    /* renamed from: h */
    private static final Pattern f702h = Pattern.compile("^(-)");
    /* renamed from: i */
    private static final ThreadLocal<Matcher> f703i = new C05401();

    /* renamed from: com.kongregate.android.internal.util.StringUtils$1 */
    static class C05401 extends ThreadLocal<Matcher> {
        C05401() {
        }

        protected /* synthetic */ Object initialValue() {
            return m554a();
        }

        /* renamed from: a */
        protected Matcher m554a() {
            return StringUtils.f698d.matcher("");
        }
    }

    public static class URLSpanAdapter extends URLSpan {
        /* renamed from: a */
        private C0541a f694a;

        public URLSpanAdapter(String str, C0541a c0541a) {
            super(str);
            this.f694a = c0541a;
        }

        public void onClick(View view) {
            boolean z = false;
            if (this.f694a != null) {
                z = this.f694a.m555a(getURL());
            }
            if (!z) {
                super.onClick(view);
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.util.StringUtils$a */
    public interface C0541a {
        /* renamed from: a */
        boolean m555a(String str);
    }

    /* renamed from: a */
    public static void m576a(Context context) {
        f697c = context;
    }

    /* renamed from: a */
    public static boolean m581a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        } else {
            return str.equals(str2);
        }
    }

    /* renamed from: b */
    public static boolean m585b(String str, String str2) {
        if (str == null) {
            return str2 == null;
        } else {
            return str.equalsIgnoreCase(str2);
        }
    }

    /* renamed from: a */
    public static boolean m580a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /* renamed from: b */
    public static boolean m584b(CharSequence charSequence) {
        return !m580a(charSequence);
    }

    /* renamed from: c */
    public static boolean m587c(CharSequence charSequence) {
        if (charSequence != null) {
            int length = charSequence.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(charSequence.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    /* renamed from: d */
    public static boolean m591d(CharSequence charSequence) {
        return !m587c(charSequence);
    }

    /* renamed from: a */
    public static <T> String m570a(T... tArr) {
        return m573a((Object[]) tArr, null);
    }

    /* renamed from: a */
    public static String m571a(Object[] objArr, char c) {
        if (objArr == null) {
            return null;
        }
        return m572a(objArr, c, 0, objArr.length);
    }

    /* renamed from: a */
    public static String m572a(Object[] objArr, char c, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        int i4;
        if (objArr[i] == null) {
            i4 = 16;
        } else {
            i4 = objArr[i].toString().length();
        }
        StringBuilder stringBuilder = new StringBuilder((i4 + 1) * i3);
        for (i4 = i; i4 < i2; i4++) {
            if (i4 > i) {
                stringBuilder.append(c);
            }
            if (objArr[i4] != null) {
                stringBuilder.append(objArr[i4]);
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m573a(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return m574a(objArr, str, 0, objArr.length);
    }

    /* renamed from: a */
    public static String m574a(Object[] objArr, String str, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        int i4;
        if (objArr[i] == null) {
            i4 = 16;
        } else {
            i4 = objArr[i].toString().length();
        }
        StringBuilder stringBuilder = new StringBuilder((i4 + str.length()) * i3);
        for (i4 = i; i4 < i2; i4++) {
            if (i4 > i) {
                stringBuilder.append(str);
            }
            if (objArr[i4] != null) {
                stringBuilder.append(objArr[i4]);
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m561a(Iterable<?> iterable, char c) {
        if (iterable == null) {
            return null;
        }
        return m567a(iterable.iterator(), c);
    }

    /* renamed from: a */
    public static String m562a(Iterable<?> iterable, String str) {
        if (iterable == null) {
            return null;
        }
        return m568a(iterable.iterator(), str);
    }

    /* renamed from: a */
    public static String m567a(Iterator<?> it, char c) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (it.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder(256);
            if (next != null) {
                stringBuilder.append(next);
            }
            while (it.hasNext()) {
                stringBuilder.append(c);
                next = it.next();
                if (next != null) {
                    stringBuilder.append(next);
                }
            }
            return stringBuilder.toString();
        } else if (next != null) {
            return next.toString();
        } else {
            return null;
        }
    }

    /* renamed from: a */
    public static String m568a(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (it.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder(256);
            if (next != null) {
                stringBuilder.append(next);
            }
            while (it.hasNext()) {
                if (str != null) {
                    stringBuilder.append(str);
                }
                next = it.next();
                if (next != null) {
                    stringBuilder.append(next);
                }
            }
            return stringBuilder.toString();
        } else if (next != null) {
            return next.toString();
        } else {
            return null;
        }
    }

    /* renamed from: c */
    public static boolean m588c(String str, String str2) {
        return m580a((CharSequence) str) ? m580a((CharSequence) str2) : str.equals(str2);
    }

    /* renamed from: a */
    public static String m563a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m583b(String str) {
        String str2 = null;
        if (str != null) {
            try {
                str2 = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static String m559a(Context context, int i) {
        return context.getString(i);
    }

    /* renamed from: a */
    public static String m556a(int i) {
        return m559a(f697c, i);
    }

    /* renamed from: a */
    public static String m560a(Context context, int i, Object... objArr) {
        return String.format(m559a(context, i), objArr);
    }

    /* renamed from: a */
    public static String m558a(int i, Object... objArr) {
        return m560a(f697c, i, objArr);
    }

    /* renamed from: a */
    public static String[] m582a(Context context, int[] iArr) {
        String[] strArr = new String[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            strArr[i] = m559a(context, iArr[i]);
        }
        return strArr;
    }

    /* renamed from: d */
    public static String m590d(String str, String str2) {
        BigInteger bigInteger = new BigInteger(str2, 16);
        String g = m595g(str);
        for (int i = 0; i < g.length(); i++) {
            bigInteger = bigInteger.add(BigInteger.valueOf((long) Math.abs(g.charAt(i) ^ 195)));
        }
        return m586c(bigInteger.toString(10));
    }

    /* renamed from: c */
    public static String m586c(String str) {
        try {
            return C0559h.m730b(MessageDigest.getInstance("SHA1").digest(str.getBytes()));
        } catch (Throwable e) {
            C0562j.m762d("No such algorithm (SHA1)", e);
            return null;
        }
    }

    /* renamed from: d */
    public static String m589d(String str) {
        try {
            return C0559h.m730b(MessageDigest.getInstance("SHA256").digest(str.getBytes()));
        } catch (Throwable e) {
            C0562j.m762d("No such algorithm (SHA256)", e);
            return null;
        }
    }

    /* renamed from: e */
    public static boolean m593e(String str, String str2) {
        return m592e(str).compareTo(m592e(str2)) >= 0;
    }

    /* renamed from: e */
    public static String m592e(String str) {
        return m565a(str, ".", 4);
    }

    /* renamed from: a */
    public static String m565a(String str, String str2, int i) {
        String[] split = Pattern.compile(str2, 16).split(str);
        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj : split) {
            stringBuilder.append(String.format("%" + i + 's', new Object[]{obj}));
        }
        return stringBuilder.toString().replace(" ", ".");
    }

    /* renamed from: f */
    public static String m594f(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(Locale.ENGLISH);
    }

    /* renamed from: g */
    public static String m595g(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(Locale.ENGLISH);
    }

    /* renamed from: h */
    public static String m596h(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
        }
        return str;
    }

    /* renamed from: i */
    public static String m597i(String str) {
        return m583b(str);
    }

    /* renamed from: a */
    public static String m564a(String str, int i) {
        return i == 1 ? str : str + "s";
    }

    /* renamed from: a */
    public static String m557a(int i, int i2) {
        return m564a(m556a(i), i2);
    }

    /* renamed from: j */
    public static boolean m598j(String str) {
        return str != null && new StringTokenizer(str).countTokens() == 1;
    }

    /* renamed from: a */
    public static void m577a(TextView textView, String str) {
        m578a(textView, str, null);
    }

    /* renamed from: a */
    public static void m579a(TextView textView, String str, boolean z) {
        m578a(textView, str, null);
        if (!z) {
            textView.setMovementMethod(null);
            textView.setLinksClickable(false);
        }
    }

    /* renamed from: a */
    public static void m578a(TextView textView, String str, C0541a c0541a) {
        textView.setLinkTextColor(Color.parseColor("#990000"));
        if (str == null || !(str.contains("<a") || str.contains("<p>"))) {
            textView.setLinksClickable(true);
            textView.setAutoLinkMask(15);
            textView.setText(str);
            return;
        }
        CharSequence valueOf = SpannableStringBuilder.valueOf(Html.fromHtml(str));
        if (c0541a != null) {
            for (URLSpan uRLSpan : (URLSpan[]) valueOf.getSpans(0, valueOf.length(), URLSpan.class)) {
                int spanStart = valueOf.getSpanStart(uRLSpan);
                int spanEnd = valueOf.getSpanEnd(uRLSpan);
                valueOf.removeSpan(uRLSpan);
                valueOf.setSpan(new URLSpanAdapter(uRLSpan.getURL(), c0541a), spanStart, spanEnd, 0);
            }
        }
        if (valueOf.toString().endsWith("\n\n")) {
            valueOf.delete(valueOf.length() - 2, valueOf.length());
        }
        textView.setText(valueOf);
        textView.setMovementMethod(new LinkMovementMethod());
    }

    /* renamed from: k */
    public static String m599k(String str) {
        if (m587c((CharSequence) str)) {
            return str;
        }
        Matcher matcher = (Matcher) f703i.get();
        return matcher.usePattern(f702h).reset(matcher.usePattern(f701g).reset(matcher.usePattern(f700f).reset(matcher.usePattern(f699e).reset(matcher.usePattern(f698d).reset(m594f(str)).replaceAll("")).replaceAll(" ")).replaceAll("-")).replaceAll("")).replaceAll("");
    }

    /* renamed from: a */
    public static String m569a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            try {
                return new String(bArr, "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                return new String(bArr);
            }
        }
    }

    /* renamed from: l */
    public static byte[] m600l(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e2) {
                return str.getBytes();
            }
        }
    }

    /* renamed from: a */
    public static String m566a(String str, byte[] bArr) {
        int i = 0;
        byte[] bArr2 = new byte[(str.length() / 2)];
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            bArr2[i2] = Byte.decode("0x" + str.substring(i2 * 2, (i2 * 2) + 2)).byteValue();
        }
        String str2 = "";
        while (i < bArr2.length) {
            str2 = str2 + ((char) (bArr[i % bArr.length] ^ bArr2[i]));
            i++;
        }
        return str2;
    }
}
