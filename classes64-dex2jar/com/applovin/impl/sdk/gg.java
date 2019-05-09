// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.xml.sax.ContentHandler;
import android.util.Xml;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import com.applovin.sdk.AppLovinSdk;
import java.util.Stack;
import com.applovin.sdk.AppLovinLogger;

class gg
{
    private final AppLovinLogger a;
    private Stack<gi> b;
    private StringBuilder c;
    private long d;
    private gi e;
    
    gg(final AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.a = appLovinSdk.getLogger();
    }
    
    static gf a(final String s, final AppLovinSdk appLovinSdk) throws SAXException {
        return new gg(appLovinSdk).a(s);
    }
    
    private Map<String, String> a(final Attributes attributes) {
        Map<String, String> emptyMap;
        if (attributes != null) {
            final int length = attributes.getLength();
            final HashMap hashMap = new HashMap<String, String>(length);
            int n = 0;
            while (true) {
                emptyMap = (Map<String, String>)hashMap;
                if (n >= length) {
                    break;
                }
                hashMap.put(attributes.getQName(n), attributes.getValue(n));
                ++n;
            }
        }
        else {
            emptyMap = Collections.emptyMap();
        }
        return emptyMap;
    }
    
    public gf a(final String s) throws SAXException {
        if (s == null) {
            throw new IllegalArgumentException("Unable to parse. No XML specified.");
        }
        this.c = new StringBuilder();
        this.b = new Stack<gi>();
        this.e = null;
        Xml.parse(s, (ContentHandler)new gh(this));
        if (this.e == null) {
            throw new SAXException("Unable to parse XML into node");
        }
        return this.e;
    }
}
