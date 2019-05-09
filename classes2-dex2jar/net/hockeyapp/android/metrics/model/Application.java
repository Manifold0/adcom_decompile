// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.io.Serializable;

public class Application implements IJsonSerializable, Serializable
{
    private String build;
    private String typeId;
    private String ver;
    
    public Application() {
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
    }
    
    public void addToHashMap(final Map<String, String> map) {
        if (this.ver != null) {
            map.put("ai.application.ver", this.ver);
        }
        if (this.build != null) {
            map.put("ai.application.build", this.build);
        }
        if (this.typeId != null) {
            map.put("ai.application.typeId", this.typeId);
        }
    }
    
    public String getBuild() {
        return this.build;
    }
    
    public String getTypeId() {
        return this.typeId;
    }
    
    public String getVer() {
        return this.ver;
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
        if (this.ver != null) {
            writer.write("" + "\"ai.application.ver\":");
            writer.write(JsonHelper.convert(this.ver));
            s = ",";
        }
        String s2 = s;
        if (this.build != null) {
            writer.write(s + "\"ai.application.build\":");
            writer.write(JsonHelper.convert(this.build));
            s2 = ",";
        }
        String s3 = s2;
        if (this.typeId != null) {
            writer.write(s2 + "\"ai.application.typeId\":");
            writer.write(JsonHelper.convert(this.typeId));
            s3 = ",";
        }
        return s3;
    }
    
    public void setBuild(final String build) {
        this.build = build;
    }
    
    public void setTypeId(final String typeId) {
        this.typeId = typeId;
    }
    
    public void setVer(final String ver) {
        this.ver = ver;
    }
}
