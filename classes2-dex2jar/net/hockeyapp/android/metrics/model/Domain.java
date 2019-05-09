// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Domain implements IJsonSerializable
{
    public LinkedHashMap<String, String> Attributes;
    public String QualifiedName;
    
    public Domain() {
        this.Attributes = new LinkedHashMap<String, String>();
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
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
        return "";
    }
}
