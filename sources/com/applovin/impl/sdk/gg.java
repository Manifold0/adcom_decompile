package com.applovin.impl.sdk;

import android.util.Xml;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class gg {
    /* renamed from: a */
    private final AppLovinLogger f2576a;
    /* renamed from: b */
    private Stack<gi> f2577b;
    /* renamed from: c */
    private StringBuilder f2578c;
    /* renamed from: d */
    private long f2579d;
    /* renamed from: e */
    private gi f2580e;

    gg(AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.f2576a = appLovinSdk.getLogger();
    }

    /* renamed from: a */
    static gf m2980a(String str, AppLovinSdk appLovinSdk) throws SAXException {
        return new gg(appLovinSdk).m2989a(str);
    }

    /* renamed from: a */
    private Map<String, String> m2984a(Attributes attributes) {
        if (attributes == null) {
            return Collections.emptyMap();
        }
        int length = attributes.getLength();
        Map<String, String> hashMap = new HashMap(length);
        for (int i = 0; i < length; i++) {
            hashMap.put(attributes.getQName(i), attributes.getValue(i));
        }
        return hashMap;
    }

    /* renamed from: a */
    public gf m2989a(String str) throws SAXException {
        if (str == null) {
            throw new IllegalArgumentException("Unable to parse. No XML specified.");
        }
        this.f2578c = new StringBuilder();
        this.f2577b = new Stack();
        this.f2580e = null;
        Xml.parse(str, new gh(this));
        if (this.f2580e != null) {
            return this.f2580e;
        }
        throw new SAXException("Unable to parse XML into node");
    }
}
