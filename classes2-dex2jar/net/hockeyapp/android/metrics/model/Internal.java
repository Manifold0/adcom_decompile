// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.io.Serializable;

public class Internal implements IJsonSerializable, Serializable
{
    private String accountId;
    private String agentVersion;
    private String applicationName;
    private String applicationType;
    private String dataCollectorReceivedTime;
    private String flowType;
    private String instrumentationKey;
    private String isAudit;
    private String profileClassId;
    private String profileId;
    private String requestSource;
    private String sdkVersion;
    private String telemetryItemId;
    private String trackingSourceId;
    private String trackingType;
    
    public Internal() {
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
    }
    
    public void addToHashMap(final Map<String, String> map) {
        if (this.sdkVersion != null) {
            map.put("ai.internal.sdkVersion", this.sdkVersion);
        }
        if (this.agentVersion != null) {
            map.put("ai.internal.agentVersion", this.agentVersion);
        }
        if (this.dataCollectorReceivedTime != null) {
            map.put("ai.internal.dataCollectorReceivedTime", this.dataCollectorReceivedTime);
        }
        if (this.profileId != null) {
            map.put("ai.internal.profileId", this.profileId);
        }
        if (this.profileClassId != null) {
            map.put("ai.internal.profileClassId", this.profileClassId);
        }
        if (this.accountId != null) {
            map.put("ai.internal.accountId", this.accountId);
        }
        if (this.applicationName != null) {
            map.put("ai.internal.applicationName", this.applicationName);
        }
        if (this.instrumentationKey != null) {
            map.put("ai.internal.instrumentationKey", this.instrumentationKey);
        }
        if (this.telemetryItemId != null) {
            map.put("ai.internal.telemetryItemId", this.telemetryItemId);
        }
        if (this.applicationType != null) {
            map.put("ai.internal.applicationType", this.applicationType);
        }
        if (this.requestSource != null) {
            map.put("ai.internal.requestSource", this.requestSource);
        }
        if (this.flowType != null) {
            map.put("ai.internal.flowType", this.flowType);
        }
        if (this.isAudit != null) {
            map.put("ai.internal.isAudit", this.isAudit);
        }
        if (this.trackingSourceId != null) {
            map.put("ai.internal.trackingSourceId", this.trackingSourceId);
        }
        if (this.trackingType != null) {
            map.put("ai.internal.trackingType", this.trackingType);
        }
    }
    
    public String getAccountId() {
        return this.accountId;
    }
    
    public String getAgentVersion() {
        return this.agentVersion;
    }
    
    public String getApplicationName() {
        return this.applicationName;
    }
    
    public String getApplicationType() {
        return this.applicationType;
    }
    
    public String getDataCollectorReceivedTime() {
        return this.dataCollectorReceivedTime;
    }
    
    public String getFlowType() {
        return this.flowType;
    }
    
    public String getInstrumentationKey() {
        return this.instrumentationKey;
    }
    
    public String getIsAudit() {
        return this.isAudit;
    }
    
    public String getProfileClassId() {
        return this.profileClassId;
    }
    
    public String getProfileId() {
        return this.profileId;
    }
    
    public String getRequestSource() {
        return this.requestSource;
    }
    
    public String getSdkVersion() {
        return this.sdkVersion;
    }
    
    public String getTelemetryItemId() {
        return this.telemetryItemId;
    }
    
    public String getTrackingSourceId() {
        return this.trackingSourceId;
    }
    
    public String getTrackingType() {
        return this.trackingType;
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
        if (this.sdkVersion != null) {
            writer.write("" + "\"ai.internal.sdkVersion\":");
            writer.write(JsonHelper.convert(this.sdkVersion));
            s = ",";
        }
        String s2 = s;
        if (this.agentVersion != null) {
            writer.write(s + "\"ai.internal.agentVersion\":");
            writer.write(JsonHelper.convert(this.agentVersion));
            s2 = ",";
        }
        String s3 = s2;
        if (this.dataCollectorReceivedTime != null) {
            writer.write(s2 + "\"ai.internal.dataCollectorReceivedTime\":");
            writer.write(JsonHelper.convert(this.dataCollectorReceivedTime));
            s3 = ",";
        }
        String s4 = s3;
        if (this.profileId != null) {
            writer.write(s3 + "\"ai.internal.profileId\":");
            writer.write(JsonHelper.convert(this.profileId));
            s4 = ",";
        }
        String s5 = s4;
        if (this.profileClassId != null) {
            writer.write(s4 + "\"ai.internal.profileClassId\":");
            writer.write(JsonHelper.convert(this.profileClassId));
            s5 = ",";
        }
        String s6 = s5;
        if (this.accountId != null) {
            writer.write(s5 + "\"ai.internal.accountId\":");
            writer.write(JsonHelper.convert(this.accountId));
            s6 = ",";
        }
        String s7 = s6;
        if (this.applicationName != null) {
            writer.write(s6 + "\"ai.internal.applicationName\":");
            writer.write(JsonHelper.convert(this.applicationName));
            s7 = ",";
        }
        String s8 = s7;
        if (this.instrumentationKey != null) {
            writer.write(s7 + "\"ai.internal.instrumentationKey\":");
            writer.write(JsonHelper.convert(this.instrumentationKey));
            s8 = ",";
        }
        String s9 = s8;
        if (this.telemetryItemId != null) {
            writer.write(s8 + "\"ai.internal.telemetryItemId\":");
            writer.write(JsonHelper.convert(this.telemetryItemId));
            s9 = ",";
        }
        String s10 = s9;
        if (this.applicationType != null) {
            writer.write(s9 + "\"ai.internal.applicationType\":");
            writer.write(JsonHelper.convert(this.applicationType));
            s10 = ",";
        }
        String s11 = s10;
        if (this.requestSource != null) {
            writer.write(s10 + "\"ai.internal.requestSource\":");
            writer.write(JsonHelper.convert(this.requestSource));
            s11 = ",";
        }
        String s12 = s11;
        if (this.flowType != null) {
            writer.write(s11 + "\"ai.internal.flowType\":");
            writer.write(JsonHelper.convert(this.flowType));
            s12 = ",";
        }
        String s13 = s12;
        if (this.isAudit != null) {
            writer.write(s12 + "\"ai.internal.isAudit\":");
            writer.write(JsonHelper.convert(this.isAudit));
            s13 = ",";
        }
        String s14 = s13;
        if (this.trackingSourceId != null) {
            writer.write(s13 + "\"ai.internal.trackingSourceId\":");
            writer.write(JsonHelper.convert(this.trackingSourceId));
            s14 = ",";
        }
        String s15 = s14;
        if (this.trackingType != null) {
            writer.write(s14 + "\"ai.internal.trackingType\":");
            writer.write(JsonHelper.convert(this.trackingType));
            s15 = ",";
        }
        return s15;
    }
    
    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }
    
    public void setAgentVersion(final String agentVersion) {
        this.agentVersion = agentVersion;
    }
    
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }
    
    public void setApplicationType(final String applicationType) {
        this.applicationType = applicationType;
    }
    
    public void setDataCollectorReceivedTime(final String dataCollectorReceivedTime) {
        this.dataCollectorReceivedTime = dataCollectorReceivedTime;
    }
    
    public void setFlowType(final String flowType) {
        this.flowType = flowType;
    }
    
    public void setInstrumentationKey(final String instrumentationKey) {
        this.instrumentationKey = instrumentationKey;
    }
    
    public void setIsAudit(final String isAudit) {
        this.isAudit = isAudit;
    }
    
    public void setProfileClassId(final String profileClassId) {
        this.profileClassId = profileClassId;
    }
    
    public void setProfileId(final String profileId) {
        this.profileId = profileId;
    }
    
    public void setRequestSource(final String requestSource) {
        this.requestSource = requestSource;
    }
    
    public void setSdkVersion(final String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }
    
    public void setTelemetryItemId(final String telemetryItemId) {
        this.telemetryItemId = telemetryItemId;
    }
    
    public void setTrackingSourceId(final String trackingSourceId) {
        this.trackingSourceId = trackingSourceId;
    }
    
    public void setTrackingType(final String trackingType) {
        this.trackingType = trackingType;
    }
}
