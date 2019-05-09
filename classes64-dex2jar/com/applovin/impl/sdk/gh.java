// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.util.concurrent.TimeUnit;
import org.xml.sax.SAXException;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Arrays;
import org.xml.sax.ContentHandler;

class gh implements ContentHandler
{
    final /* synthetic */ gg a;
    
    gh(final gg a) {
        this.a = a;
    }
    
    @Override
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        final String trim = new String(Arrays.copyOfRange(array, 0, n2)).trim();
        if (AppLovinSdkUtils.isValidString(trim)) {
            this.a.c.append(trim);
        }
    }
    
    @Override
    public void endDocument() throws SAXException {
        this.a.a.d("XmlParser", "Finished parsing in " + (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - this.a.d) + " seconds");
    }
    
    @Override
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        this.a.e = this.a.b.pop();
        this.a.e.d(this.a.c.toString().trim());
        this.a.c.setLength(0);
    }
    
    @Override
    public void endPrefixMapping(final String s) throws SAXException {
    }
    
    @Override
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    @Override
    public void processingInstruction(final String s, final String s2) throws SAXException {
    }
    
    @Override
    public void setDocumentLocator(final Locator locator) {
    }
    
    @Override
    public void skippedEntity(final String s) throws SAXException {
    }
    
    @Override
    public void startDocument() throws SAXException {
        this.a.a.d("XmlParser", "Begin parsing...");
        this.a.d = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }
    
    @Override
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        gi gi = null;
        try {
            if (!this.a.b.isEmpty()) {
                gi = this.a.b.peek();
            }
            final gi gi2 = new gi(s2, this.a.a(attributes), gi);
            if (gi != null) {
                gi.a(gi2);
            }
            this.a.b.push(gi2);
        }
        catch (Exception e) {
            this.a.a.e("XmlParser", "Unable to process element <" + s2 + ">", e);
            throw new SAXException("Failed to start element", e);
        }
    }
    
    @Override
    public void startPrefixMapping(final String s, final String s2) throws SAXException {
    }
}
