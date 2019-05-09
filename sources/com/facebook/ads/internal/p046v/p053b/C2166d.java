package com.facebook.ads.internal.p046v.p053b;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.facebook.ads.internal.v.b.d */
class C2166d {
    /* renamed from: d */
    private static final Pattern f4992d = Pattern.compile("[R,r]ange:[ ]?bytes=(\\d*)-");
    /* renamed from: e */
    private static final Pattern f4993e = Pattern.compile("GET /(.*) HTTP");
    /* renamed from: a */
    public final String f4994a;
    /* renamed from: b */
    public final long f4995b;
    /* renamed from: c */
    public final boolean f4996c;

    public C2166d(String str) {
        C2182j.m5591a(str);
        Matcher matcher = f4992d.matcher(str);
        long parseLong = matcher.find() ? Long.parseLong(matcher.group(1)) : -1;
        this.f4995b = Math.max(0, parseLong);
        this.f4996c = parseLong >= 0;
        matcher = f4993e.matcher(str);
        if (matcher.find()) {
            this.f4994a = matcher.group(1);
            return;
        }
        throw new IllegalArgumentException("Invalid request `" + str + "`: url not found!");
    }

    /* renamed from: a */
    public static C2166d m5540a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            Object readLine = bufferedReader.readLine();
            if (TextUtils.isEmpty(readLine)) {
                return new C2166d(stringBuilder.toString());
            }
            stringBuilder.append(readLine).append('\n');
        }
    }

    public String toString() {
        return "GetRequest{rangeOffset=" + this.f4995b + ", partial=" + this.f4996c + ", uri='" + this.f4994a + '\'' + '}';
    }
}
