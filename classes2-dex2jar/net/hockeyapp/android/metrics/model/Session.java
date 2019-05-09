// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.io.Serializable;

public class Session implements IJsonSerializable, Serializable
{
    private String id;
    private String isFirst;
    private String isNew;
    
    public Session() {
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
    }
    
    public void addToHashMap(final Map<String, String> map) {
        if (this.id != null) {
            map.put("ai.session.id", this.id);
        }
        if (this.isFirst != null) {
            map.put("ai.session.isFirst", this.isFirst);
        }
        if (this.isNew != null) {
            map.put("ai.session.isNew", this.isNew);
        }
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getIsFirst() {
        return this.isFirst;
    }
    
    public String getIsNew() {
        return this.isNew;
    }
    
    @Override
    public void serialize(final Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        this.serializeContent(writer);
        writer.write(125);
    }
    
    protected String serializeContent(final Writer writer) throws IOException {
        String s = "";
        if (this.id != null) {
            writer.write("" + "\"ai.session.id\":");
            writer.write(JsonHelper.convert(this.id));
            s = ",";
        }
        String s2 = s;
        if (this.isFirst != null) {
            writer.write(s + "\"ai.session.isFirst\":");
            writer.write(JsonHelper.convert(this.isFirst));
            s2 = ",";
        }
        String s3 = s2;
        if (this.isNew != null) {
            writer.write(s2 + "\"ai.session.isNew\":");
            writer.write(JsonHelper.convert(this.isNew));
            s3 = ",";
        }
        return s3;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setIsFirst(final String isFirst) {
        this.isFirst = isFirst;
    }
    
    public void setIsNew(final String isNew) {
        this.isNew = isNew;
    }
}
