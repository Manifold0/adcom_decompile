// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import java.io.IOException;
import net.hockeyapp.android.metrics.JsonHelper;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventData extends TelemetryData
{
    private Map<String, Double> measurements;
    private String name;
    private Map<String, String> properties;
    private int ver;
    
    public EventData() {
        this.ver = 2;
        this.InitializeFields();
        this.SetupAttributes();
    }
    
    @Override
    protected void InitializeFields() {
        this.QualifiedName = "com.microsoft.applicationinsights.contracts.EventData";
    }
    
    public void SetupAttributes() {
    }
    
    @Override
    public String getBaseType() {
        return "EventData";
    }
    
    @Override
    public String getEnvelopeName() {
        return "Microsoft.ApplicationInsights.Event";
    }
    
    public Map<String, Double> getMeasurements() {
        if (this.measurements == null) {
            this.measurements = new LinkedHashMap<String, Double>();
        }
        return this.measurements;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public Map<String, String> getProperties() {
        if (this.properties == null) {
            this.properties = new LinkedHashMap<String, String>();
        }
        return this.properties;
    }
    
    public int getVer() {
        return this.ver;
    }
    
    @Override
    protected String serializeContent(final Writer writer) throws IOException {
        writer.write(super.serializeContent(writer) + "\"ver\":");
        writer.write(JsonHelper.convert(this.ver));
        writer.write("," + "\"name\":");
        writer.write(JsonHelper.convert(this.name));
        if (this.properties != null) {
            writer.write("," + "\"properties\":");
            JsonHelper.writeDictionary(writer, this.properties);
        }
        if (this.measurements != null) {
            writer.write("," + "\"measurements\":");
            JsonHelper.writeDictionary(writer, this.measurements);
        }
        return ",";
    }
    
    public void setMeasurements(final Map<String, Double> measurements) {
        this.measurements = measurements;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }
    
    @Override
    public void setVer(final int ver) {
        this.ver = ver;
    }
}
