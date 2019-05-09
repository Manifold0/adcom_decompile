package com.tapjoy;

import android.net.Uri;
import com.adjust.sdk.Constants;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.security.Key;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSignature {
    /* renamed from: a */
    private String f6763a;
    /* renamed from: b */
    private String f6764b;

    public HmacSignature(String method, String secret) {
        this.f6763a = method;
        this.f6764b = secret;
    }

    public String sign(String url, Map params) {
        int i = 0;
        try {
            Key secretKeySpec = new SecretKeySpec(this.f6764b.getBytes(), this.f6763a);
            Mac instance = Mac.getInstance(this.f6763a);
            instance.init(secretKeySpec);
            Uri parse = Uri.parse(url);
            String str = parse.getScheme() + "://" + parse.getHost();
            int i2 = ((parse.getScheme().equals("http") && parse.getPort() == 80) || (parse.getScheme().equals(Constants.SCHEME) && parse.getPort() == 443)) ? 1 : 0;
            if (i2 == 0 && -1 != parse.getPort()) {
                str = str + ":" + parse.getPort();
            }
            str = "POST&" + Uri.encode(str.toLowerCase() + parse.getPath()) + RequestParameters.AMPERSAND + Uri.encode(m6986a(params));
            TapjoyLog.m7130v("HmacSignature", "Base Url: " + str);
            byte[] doFinal = instance.doFinal(str.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            int length = doFinal.length;
            while (i < length) {
                String toHexString = Integer.toHexString(doFinal[i] & 255);
                if (toHexString.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(toHexString);
                i++;
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean matches(String url, Map params, String signature) {
        return sign(url, params).equals(signature);
    }

    /* renamed from: a */
    private static String m6986a(Map map) {
        TreeSet treeSet = new TreeSet(map.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            stringBuilder.append(str + RequestParameters.EQUAL + ((String) map.get(str)) + RequestParameters.AMPERSAND);
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(RequestParameters.AMPERSAND));
        TapjoyLog.m7130v("HmacSignature", "Unhashed String: " + stringBuilder.toString());
        return stringBuilder.toString();
    }
}
