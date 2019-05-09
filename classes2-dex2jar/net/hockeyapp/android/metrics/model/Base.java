// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Base implements IJsonSerializable
{
    public LinkedHashMap<String, String> Attributes;
    public String QualifiedName;
    private String baseType;
    
    public Base() {
        this.InitializeFields();
        this.Attributes = new LinkedHashMap<String, String>();
    }
    
    protected void InitializeFields() {
    }
    
    public String getBaseType() {
        return this.baseType;
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
        if (this.baseType != null) {
            writer.write("" + "\"baseType\":");
            writer.write(JsonHelper.convert(this.baseType));
            s = ",";
        }
        return s;
    }
    
    public void setBaseType(final String baseType) {
        this.baseType = baseType;
    }
}
