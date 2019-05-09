package com.facebook.ads.internal.p025w.p026b;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.facebook.ads.internal.w.b.s */
public class C2589s {
    /* renamed from: a */
    public static final String m6655a(Throwable th) {
        if (th == null) {
            return null;
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static String m6656a(List<String> list, String str) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (String append : list) {
            stringBuilder.append(append);
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static final String m6657a(StackTraceElement[] stackTraceElementArr) {
        JSONArray jSONArray = new JSONArray();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            jSONArray.put(stackTraceElement.toString());
        }
        return jSONArray.toString();
    }
}
