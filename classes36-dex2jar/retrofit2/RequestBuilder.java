// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import okio.BufferedSink;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.MultipartBody$Part;
import okio.Buffer;
import okhttp3.MultipartBody;
import okhttp3.Headers;
import okhttp3.HttpUrl$Builder;
import okhttp3.Request$Builder;
import okhttp3.MultipartBody$Builder;
import okhttp3.FormBody$Builder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.HttpUrl;

final class RequestBuilder
{
    private static final char[] HEX_DIGITS;
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private final HttpUrl baseUrl;
    private RequestBody body;
    private MediaType contentType;
    private FormBody$Builder formBuilder;
    private final boolean hasBody;
    private final String method;
    private MultipartBody$Builder multipartBuilder;
    private String relativeUrl;
    private final Request$Builder requestBuilder;
    private HttpUrl$Builder urlBuilder;
    
    static {
        HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    RequestBuilder(final String method, final HttpUrl baseUrl, final String relativeUrl, final Headers headers, final MediaType contentType, final boolean hasBody, final boolean b, final boolean b2) {
        this.method = method;
        this.baseUrl = baseUrl;
        this.relativeUrl = relativeUrl;
        this.requestBuilder = new Request$Builder();
        this.contentType = contentType;
        this.hasBody = hasBody;
        if (headers != null) {
            this.requestBuilder.headers(headers);
        }
        if (b) {
            this.formBuilder = new FormBody$Builder();
        }
        else if (b2) {
            (this.multipartBuilder = new MultipartBody$Builder()).setType(MultipartBody.FORM);
        }
    }
    
    private static String canonicalizeForPath(final String s, final boolean b) {
        int n = 0;
        final int length = s.length();
        String utf8;
        while (true) {
            utf8 = s;
            if (n >= length) {
                break;
            }
            final int codePoint = s.codePointAt(n);
            if (codePoint < 32 || codePoint >= 127 || " \"<>^`{}|\\?#".indexOf(codePoint) != -1 || (!b && (codePoint == 47 || codePoint == 37))) {
                final Buffer buffer = new Buffer();
                buffer.writeUtf8(s, 0, n);
                canonicalizeForPath(buffer, s, n, length, b);
                utf8 = buffer.readUtf8();
                break;
            }
            n += Character.charCount(codePoint);
        }
        return utf8;
    }
    
    private static void canonicalizeForPath(final Buffer buffer, final String s, int i, final int n, final boolean b) {
        Buffer buffer2 = null;
    Label_0064_Outer:
        while (i < n) {
            final int codePoint = s.codePointAt(i);
        Label_0064:
            while (true) {
                Label_0079: {
                    if (!b) {
                        break Label_0079;
                    }
                    Buffer buffer3 = buffer2;
                    if (codePoint != 9) {
                        buffer3 = buffer2;
                        if (codePoint != 10) {
                            buffer3 = buffer2;
                            if (codePoint != 12) {
                                if (codePoint != 13) {
                                    break Label_0079;
                                }
                                buffer3 = buffer2;
                            }
                        }
                    }
                    i += Character.charCount(codePoint);
                    buffer2 = buffer3;
                    continue Label_0064_Outer;
                }
                if (codePoint >= 32 && codePoint < 127 && " \"<>^`{}|\\?#".indexOf(codePoint) == -1 && (b || (codePoint != 47 && codePoint != 37))) {
                    buffer.writeUtf8CodePoint(codePoint);
                    final Buffer buffer3 = buffer2;
                    continue Label_0064;
                }
                Buffer buffer4;
                if ((buffer4 = buffer2) == null) {
                    buffer4 = new Buffer();
                }
                buffer4.writeUtf8CodePoint(codePoint);
                while (true) {
                    final Buffer buffer3 = buffer4;
                    if (buffer4.exhausted()) {
                        continue Label_0064;
                    }
                    final int n2 = buffer4.readByte() & 0xFF;
                    buffer.writeByte(37);
                    buffer.writeByte((int)RequestBuilder.HEX_DIGITS[n2 >> 4 & 0xF]);
                    buffer.writeByte((int)RequestBuilder.HEX_DIGITS[n2 & 0xF]);
                }
                break;
            }
        }
    }
    
    void addFormField(final String s, final String s2, final boolean b) {
        if (b) {
            this.formBuilder.addEncoded(s, s2);
            return;
        }
        this.formBuilder.add(s, s2);
    }
    
    void addHeader(final String s, final String s2) {
        if (!"Content-Type".equalsIgnoreCase(s)) {
            this.requestBuilder.addHeader(s, s2);
            return;
        }
        final MediaType parse = MediaType.parse(s2);
        if (parse == null) {
            throw new IllegalArgumentException("Malformed content type: " + s2);
        }
        this.contentType = parse;
    }
    
    void addPart(final Headers headers, final RequestBody requestBody) {
        this.multipartBuilder.addPart(headers, requestBody);
    }
    
    void addPart(final MultipartBody$Part multipartBody$Part) {
        this.multipartBuilder.addPart(multipartBody$Part);
    }
    
    void addPathParam(final String s, final String s2, final boolean b) {
        if (this.relativeUrl == null) {
            throw new AssertionError();
        }
        this.relativeUrl = this.relativeUrl.replace("{" + s + "}", canonicalizeForPath(s2, b));
    }
    
    void addQueryParam(final String s, final String s2, final boolean b) {
        if (this.relativeUrl != null) {
            this.urlBuilder = this.baseUrl.newBuilder(this.relativeUrl);
            if (this.urlBuilder == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
            this.relativeUrl = null;
        }
        if (b) {
            this.urlBuilder.addEncodedQueryParameter(s, s2);
            return;
        }
        this.urlBuilder.addQueryParameter(s, s2);
    }
    
    Request build() {
        final HttpUrl$Builder urlBuilder = this.urlBuilder;
        HttpUrl httpUrl;
        if (urlBuilder != null) {
            httpUrl = urlBuilder.build();
        }
        else if ((httpUrl = this.baseUrl.resolve(this.relativeUrl)) == null) {
            throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
        }
        final RequestBody body = this.body;
        Object o;
        if ((o = body) == null) {
            if (this.formBuilder != null) {
                o = this.formBuilder.build();
            }
            else if (this.multipartBuilder != null) {
                o = this.multipartBuilder.build();
            }
            else {
                o = body;
                if (this.hasBody) {
                    o = RequestBody.create((MediaType)null, new byte[0]);
                }
            }
        }
        final MediaType contentType = this.contentType;
        RequestBody requestBody = (RequestBody)o;
        if (contentType != null) {
            if (o != null) {
                requestBody = new ContentTypeOverridingRequestBody((RequestBody)o, contentType);
            }
            else {
                this.requestBuilder.addHeader("Content-Type", contentType.toString());
                requestBody = (RequestBody)o;
            }
        }
        return this.requestBuilder.url(httpUrl).method(this.method, requestBody).build();
    }
    
    void setBody(final RequestBody body) {
        this.body = body;
    }
    
    void setRelativeUrl(final Object o) {
        if (o == null) {
            throw new NullPointerException("@Url parameter is null.");
        }
        this.relativeUrl = o.toString();
    }
    
    private static class ContentTypeOverridingRequestBody extends RequestBody
    {
        private final MediaType contentType;
        private final RequestBody delegate;
        
        ContentTypeOverridingRequestBody(final RequestBody delegate, final MediaType contentType) {
            this.delegate = delegate;
            this.contentType = contentType;
        }
        
        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }
        
        public MediaType contentType() {
            return this.contentType;
        }
        
        public void writeTo(final BufferedSink bufferedSink) throws IOException {
            this.delegate.writeTo(bufferedSink);
        }
    }
}
