// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import java.io.IOException;
import net.hockeyapp.android.metrics.JsonHelper;
import java.io.Writer;
import java.util.Map;

public class SessionStateData extends TelemetryData
{
    private SessionState state;
    private int ver;
    
    public SessionStateData() {
        this.ver = 2;
        this.state = SessionState.START;
        this.InitializeFields();
        this.SetupAttributes();
    }
    
    @Override
    protected void InitializeFields() {
        this.QualifiedName = "com.microsoft.applicationinsights.contracts.SessionStateData";
    }
    
    public void SetupAttributes() {
    }
    
    @Override
    public String getBaseType() {
        return "SessionStateData";
    }
    
    @Override
    public String getEnvelopeName() {
        return "Microsoft.ApplicationInsights.SessionState";
    }
    
    @Override
    public Map<String, String> getProperties() {
        return null;
    }
    
    public SessionState getState() {
        return this.state;
    }
    
    public int getVer() {
        return this.ver;
    }
    
    @Override
    protected String serializeContent(final Writer writer) throws IOException {
        writer.write(super.serializeContent(writer) + "\"ver\":");
        writer.write(JsonHelper.convert(this.ver));
        writer.write("," + "\"state\":");
        writer.write(JsonHelper.convert(this.state.getValue()));
        return ",";
    }
    
    @Override
    public void setProperties(final Map<String, String> map) {
    }
    
    public void setState(final SessionState state) {
        this.state = state;
    }
    
    @Override
    public void setVer(final int ver) {
        this.ver = ver;
    }
}
