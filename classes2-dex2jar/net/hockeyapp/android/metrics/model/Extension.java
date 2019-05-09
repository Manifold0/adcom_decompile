// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;

public class Extension implements IJsonSerializable
{
    private String ver;
    
    public Extension() {
        this.ver = "1.0";
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
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
            writer.write("" + "\"ver\":");
            writer.write(JsonHelper.convert(this.ver));
            s = ",";
        }
        return s;
    }
    
    public void setVer(final String ver) {
        this.ver = ver;
    }
}
