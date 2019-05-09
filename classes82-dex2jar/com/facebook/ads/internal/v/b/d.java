// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import android.text.TextUtils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class d
{
    private static final Pattern d;
    private static final Pattern e;
    public final String a;
    public final long b;
    public final boolean c;
    
    static {
        d = Pattern.compile("[R,r]ange:[ ]?bytes=(\\d*)-");
        e = Pattern.compile("GET /(.*) HTTP");
    }
    
    public d(final String s) {
        j.a(s);
        final Matcher matcher = com.facebook.ads.internal.v.b.d.d.matcher(s);
        long long1;
        if (matcher.find()) {
            long1 = Long.parseLong(matcher.group(1));
        }
        else {
            long1 = -1L;
        }
        this.b = Math.max(0L, long1);
        this.c = (long1 >= 0L);
        final Matcher matcher2 = com.facebook.ads.internal.v.b.d.e.matcher(s);
        if (matcher2.find()) {
            this.a = matcher2.group(1);
            return;
        }
        throw new IllegalArgumentException("Invalid request `" + s + "`: url not found!");
    }
    
    public static d a(final InputStream inputStream) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        final StringBuilder sb = new StringBuilder();
        while (true) {
            final String line = bufferedReader.readLine();
            if (TextUtils.isEmpty((CharSequence)line)) {
                break;
            }
            sb.append(line).append('\n');
        }
        return new d(sb.toString());
    }
    
    @Override
    public String toString() {
        return "GetRequest{rangeOffset=" + this.b + ", partial=" + this.c + ", uri='" + this.a + '\'' + '}';
    }
}
