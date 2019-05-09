package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

class gh implements ContentHandler {
    /* renamed from: a */
    final /* synthetic */ gg f2581a;

    gh(gg ggVar) {
        this.f2581a = ggVar;
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        String trim = new String(Arrays.copyOfRange(cArr, 0, i2)).trim();
        if (AppLovinSdkUtils.isValidString(trim)) {
            this.f2581a.f2578c.append(trim);
        }
    }

    public void endDocument() throws SAXException {
        this.f2581a.f2576a.mo4172d("XmlParser", "Finished parsing in " + (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - this.f2581a.f2579d) + " seconds");
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        this.f2581a.f2580e = (gi) this.f2581a.f2577b.pop();
        this.f2581a.f2580e.m2991d(this.f2581a.f2578c.toString().trim());
        this.f2581a.f2578c.setLength(0);
    }

    public void endPrefixMapping(String str) throws SAXException {
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
    }

    public void processingInstruction(String str, String str2) throws SAXException {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String str) throws SAXException {
    }

    public void startDocument() throws SAXException {
        this.f2581a.f2576a.mo4172d("XmlParser", "Begin parsing...");
        this.f2581a.f2579d = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        gf gfVar = null;
        try {
            if (!this.f2581a.f2577b.isEmpty()) {
                gfVar = (gi) this.f2581a.f2577b.peek();
            }
            gf giVar = new gi(str2, this.f2581a.m2984a(attributes), gfVar);
            if (gfVar != null) {
                gfVar.m2990a(giVar);
            }
            this.f2581a.f2577b.push(giVar);
        } catch (Throwable e) {
            this.f2581a.f2576a.mo4174e("XmlParser", "Unable to process element <" + str2 + ">", e);
            throw new SAXException("Failed to start element", e);
        }
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
    }
}
