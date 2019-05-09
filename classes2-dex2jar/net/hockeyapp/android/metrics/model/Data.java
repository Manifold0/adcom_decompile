// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import java.io.IOException;
import net.hockeyapp.android.metrics.JsonHelper;
import java.io.Writer;

public class Data<TDomain extends Domain> extends Base implements ITelemetryData
{
    private TDomain baseData;
    
    public Data() {
        this.InitializeFields();
        this.SetupAttributes();
    }
    
    @Override
    protected void InitializeFields() {
        this.QualifiedName = "com.microsoft.telemetry.Data";
    }
    
    public void SetupAttributes() {
        this.Attributes.put("Description", "Data struct to contain both B and C sections.");
    }
    
    public TDomain getBaseData() {
        return this.baseData;
    }
    
    @Override
    protected String serializeContent(final Writer writer) throws IOException {
        writer.write(super.serializeContent(writer) + "\"baseData\":");
        JsonHelper.writeJsonSerializable(writer, this.baseData);
        return ",";
    }
    
    public void setBaseData(final TDomain baseData) {
        this.baseData = baseData;
    }
}
