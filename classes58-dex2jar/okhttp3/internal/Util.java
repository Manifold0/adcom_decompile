// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal;

import java.util.concurrent.ThreadFactory;
import java.io.InterruptedIOException;
import okio.Buffer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import java.util.Locale;
import java.net.IDN;
import java.util.concurrent.TimeUnit;
import okio.Source;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.Closeable;
import java.io.IOException;
import okio.BufferedSource;
import okhttp3.MediaType;
import java.util.regex.Pattern;
import okio.ByteString;
import java.nio.charset.Charset;
import java.util.TimeZone;
import okhttp3.ResponseBody;
import okhttp3.RequestBody;

public final class Util
{
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final RequestBody EMPTY_REQUEST;
    public static final ResponseBody EMPTY_RESPONSE;
    public static final String[] EMPTY_STRING_ARRAY;
    public static final TimeZone UTC;
    private static final Charset UTF_16_BE;
    private static final ByteString UTF_16_BE_BOM;
    private static final Charset UTF_16_LE;
    private static final ByteString UTF_16_LE_BOM;
    private static final Charset UTF_32_BE;
    private static final ByteString UTF_32_BE_BOM;
    private static final Charset UTF_32_LE;
    private static final ByteString UTF_32_LE_BOM;
    public static final Charset UTF_8;
    private static final ByteString UTF_8_BOM;
    private static final Pattern VERIFY_AS_IP_ADDRESS;
    
    static {
        EMPTY_BYTE_ARRAY = new byte[0];
        EMPTY_STRING_ARRAY = new String[0];
        EMPTY_RESPONSE = ResponseBody.create(null, Util.EMPTY_BYTE_ARRAY);
        EMPTY_REQUEST = RequestBody.create(null, Util.EMPTY_BYTE_ARRAY);
        UTF_8_BOM = ByteString.decodeHex("efbbbf");
        UTF_16_BE_BOM = ByteString.decodeHex("feff");
        UTF_16_LE_BOM = ByteString.decodeHex("fffe");
        UTF_32_BE_BOM = ByteString.decodeHex("0000ffff");
        UTF_32_LE_BOM = ByteString.decodeHex("ffff0000");
        UTF_8 = Charset.forName("UTF-8");
        UTF_16_BE = Charset.forName("UTF-16BE");
        UTF_16_LE = Charset.forName("UTF-16LE");
        UTF_32_BE = Charset.forName("UTF-32BE");
        UTF_32_LE = Charset.forName("UTF-32LE");
        UTC = TimeZone.getTimeZone("GMT");
        VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }
    
    private Util() {
    }
    
    public static Charset bomAwareCharset(final BufferedSource bufferedSource, Charset utf_8) throws IOException {
        if (bufferedSource.rangeEquals(0L, Util.UTF_8_BOM)) {
            bufferedSource.skip((long)Util.UTF_8_BOM.size());
            utf_8 = Util.UTF_8;
        }
        else {
            if (bufferedSource.rangeEquals(0L, Util.UTF_16_BE_BOM)) {
                bufferedSource.skip((long)Util.UTF_16_BE_BOM.size());
                return Util.UTF_16_BE;
            }
            if (bufferedSource.rangeEquals(0L, Util.UTF_16_LE_BOM)) {
                bufferedSource.skip((long)Util.UTF_16_LE_BOM.size());
                return Util.UTF_16_LE;
            }
            if (bufferedSource.rangeEquals(0L, Util.UTF_32_BE_BOM)) {
                bufferedSource.skip((long)Util.UTF_32_BE_BOM.size());
                return Util.UTF_32_BE;
            }
            if (bufferedSource.rangeEquals(0L, Util.UTF_32_LE_BOM)) {
                bufferedSource.skip((long)Util.UTF_32_LE_BOM.size());
                return Util.UTF_32_LE;
            }
        }
        return utf_8;
    }
    
    public static void checkOffsetAndCount(final long n, final long n2, final long n3) {
        if ((n2 | n3) < 0L || n2 > n || n - n2 < n3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static void closeQuietly(final ServerSocket serverSocket) {
        if (serverSocket == null) {
            return;
        }
        try {
            serverSocket.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static void closeQuietly(final Socket socket) {
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        }
        catch (AssertionError assertionError) {
            if (!isAndroidGetsocknameError(assertionError)) {
                throw assertionError;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static String[] concat(final String[] array, final String s) {
        final String[] array2 = new String[array.length + 1];
        System.arraycopy(array, 0, array2, 0, array.length);
        array2[array2.length - 1] = s;
        return array2;
    }
    
    private static boolean containsInvalidHostnameAsciiCodes(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 <= '\u001f' || char1 >= '\u007f' || " #%/:?@[\\]".indexOf(char1) != -1) {
                return true;
            }
        }
        return false;
    }
    
    public static int delimiterOffset(final String s, int i, final int n, final char c) {
        while (i < n) {
            if (s.charAt(i) == c) {
                return i;
            }
            ++i;
        }
        return n;
    }
    
    public static int delimiterOffset(final String s, int i, final int n, final String s2) {
        while (i < n) {
            if (s2.indexOf(s.charAt(i)) != -1) {
                return i;
            }
            ++i;
        }
        return n;
    }
    
    public static boolean discard(final Source source, final int n, final TimeUnit timeUnit) {
        try {
            return skipAll(source, n, timeUnit);
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    public static String domainToAscii(String lowerCase) {
        try {
            lowerCase = IDN.toASCII(lowerCase).toLowerCase(Locale.US);
            if (lowerCase.isEmpty()) {
                return null;
            }
            if (containsInvalidHostnameAsciiCodes(lowerCase)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            lowerCase = null;
        }
        return lowerCase;
    }
    
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static String format(final String s, final Object... array) {
        return String.format(Locale.US, s, array);
    }
    
    public static String hostHeader(final HttpUrl httpUrl, final boolean b) {
        String s;
        if (httpUrl.host().contains(":")) {
            s = "[" + httpUrl.host() + "]";
        }
        else {
            s = httpUrl.host();
        }
        if (!b) {
            final String string = s;
            if (httpUrl.port() == HttpUrl.defaultPort(httpUrl.scheme())) {
                return string;
            }
        }
        return s + ":" + httpUrl.port();
    }
    
    public static <T> List<T> immutableList(final List<T> list) {
        return Collections.unmodifiableList((List<? extends T>)new ArrayList<T>((Collection<? extends T>)list));
    }
    
    public static <T> List<T> immutableList(final T... array) {
        return Collections.unmodifiableList((List<? extends T>)Arrays.asList((T[])array.clone()));
    }
    
    public static <T> int indexOf(final T[] array, final T t) {
        for (int i = 0; i < array.length; ++i) {
            if (equal(array[i], t)) {
                return i;
            }
        }
        return -1;
    }
    
    public static int indexOfControlOrNonAscii(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 <= '\u001f' || char1 >= '\u007f') {
                return i;
            }
        }
        return -1;
    }
    
    private static <T> List<T> intersect(final T[] array, final T[] array2) {
        final ArrayList<T> list = new ArrayList<T>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final T t = array[i];
            for (int length2 = array2.length, j = 0; j < length2; ++j) {
                final T t2 = array2[j];
                if (t.equals(t2)) {
                    list.add(t2);
                    break;
                }
            }
        }
        return list;
    }
    
    public static <T> T[] intersect(final Class<T> clazz, final T[] array, final T[] array2) {
        final List<T> intersect = intersect(array, array2);
        return intersect.toArray((T[])Array.newInstance(clazz, intersect.size()));
    }
    
    public static boolean isAndroidGetsocknameError(final AssertionError assertionError) {
        return assertionError.getCause() != null && assertionError.getMessage() != null && assertionError.getMessage().contains("getsockname failed");
    }
    
    public static boolean skipAll(final Source source, final int n, final TimeUnit timeUnit) throws IOException {
        final long nanoTime = System.nanoTime();
        Label_0106: {
            if (!source.timeout().hasDeadline()) {
                break Label_0106;
            }
            long n2 = source.timeout().deadlineNanoTime() - nanoTime;
            while (true) {
                source.timeout().deadlineNanoTime(Math.min(n2, timeUnit.toNanos(n)) + nanoTime);
                Label_0113: {
                    try {
                        final Buffer buffer = new Buffer();
                        while (source.read(buffer, 8192L) != -1L) {
                            buffer.clear();
                        }
                        break Label_0113;
                    }
                    catch (InterruptedIOException ex) {
                        if (n2 == Long.MAX_VALUE) {
                            source.timeout().clearDeadline();
                        }
                        else {
                            source.timeout().deadlineNanoTime(nanoTime + n2);
                        }
                        return false;
                        // iftrue(Label_0133:, n2 != 9223372036854775807L)
                        Block_7: {
                            break Block_7;
                            n2 = Long.MAX_VALUE;
                            continue;
                            Label_0133: {
                                source.timeout().deadlineNanoTime(nanoTime + n2);
                            }
                            return true;
                        }
                        source.timeout().clearDeadline();
                        return true;
                    }
                    finally {
                        while (true) {
                            if (n2 == Long.MAX_VALUE) {
                                source.timeout().clearDeadline();
                                break Label_0186;
                            }
                            source.timeout().deadlineNanoTime(nanoTime + n2);
                            break Label_0186;
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    public static int skipLeadingAsciiWhitespace(final String s, int i, final int n) {
        while (i < n) {
            switch (s.charAt(i)) {
                default: {
                    return i;
                }
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ': {
                    ++i;
                    continue;
                }
            }
        }
        return n;
    }
    
    public static int skipTrailingAsciiWhitespace(final String s, final int n, int n2) {
        --n2;
        int n3 = 0;
    Label_0072:
        while (true) {
            n3 = n;
            if (n2 < n) {
                break;
            }
            switch (s.charAt(n2)) {
                default: {
                    n3 = n2 + 1;
                    break Label_0072;
                }
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ': {
                    --n2;
                    continue;
                }
            }
        }
        return n3;
    }
    
    public static ThreadFactory threadFactory(final String s, final boolean b) {
        return new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable runnable) {
                final Thread thread = new Thread(runnable, s);
                thread.setDaemon(b);
                return thread;
            }
        };
    }
    
    public static String toHumanReadableAscii(final String s) {
        int i = 0;
        final int length = s.length();
        String utf8;
        while (true) {
            utf8 = s;
            if (i >= length) {
                break;
            }
            final int codePoint = s.codePointAt(i);
            if (codePoint <= 31 || codePoint >= 127) {
                final Buffer buffer = new Buffer();
                buffer.writeUtf8(s, 0, i);
                while (i < length) {
                    final int codePoint2 = s.codePointAt(i);
                    int n;
                    if (codePoint2 > 31 && codePoint2 < 127) {
                        n = codePoint2;
                    }
                    else {
                        n = 63;
                    }
                    buffer.writeUtf8CodePoint(n);
                    i += Character.charCount(codePoint2);
                }
                utf8 = buffer.readUtf8();
                break;
            }
            i += Character.charCount(codePoint);
        }
        return utf8;
    }
    
    public static String trimSubstring(final String s, int skipLeadingAsciiWhitespace, final int n) {
        skipLeadingAsciiWhitespace = skipLeadingAsciiWhitespace(s, skipLeadingAsciiWhitespace, n);
        return s.substring(skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace(s, skipLeadingAsciiWhitespace, n));
    }
    
    public static boolean verifyAsIpAddress(final String s) {
        return Util.VERIFY_AS_IP_ADDRESS.matcher(s).matches();
    }
}
