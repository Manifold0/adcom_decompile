// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class Envelope implements IJsonSerializable
{
    private String appId;
    private String appVer;
    private String cV;
    private Base data;
    private String epoch;
    private Map<String, Extension> ext;
    private long flags;
    private String iKey;
    private String name;
    private String os;
    private String osVer;
    private int sampleRate;
    private long seqNum;
    private Map<String, String> tags;
    private String time;
    private int ver;
    
    public Envelope() {
        this.ver = 1;
        this.sampleRate = 100;
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
    }
    
    public String getAppId() {
        return this.appId;
    }
    
    public String getAppVer() {
        return this.appVer;
    }
    
    public String getCV() {
        return this.cV;
    }
    
    public Base getData() {
        return this.data;
    }
    
    public String getEpoch() {
        return this.epoch;
    }
    
    public Map<String, Extension> getExt() {
        if (this.ext == null) {
            this.ext = new LinkedHashMap<String, Extension>();
        }
        return this.ext;
    }
    
    public long getFlags() {
        return this.flags;
    }
    
    public String getIKey() {
        return this.iKey;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getOs() {
        return this.os;
    }
    
    public String getOsVer() {
        return this.osVer;
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public long getSeqNum() {
        return this.seqNum;
    }
    
    public Map<String, String> getTags() {
        if (this.tags == null) {
            this.tags = new LinkedHashMap<String, String>();
        }
        return this.tags;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public int getVer() {
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
        writer.write("" + "\"ver\":");
        writer.write(JsonHelper.convert(this.ver));
        writer.write("," + "\"name\":");
        writer.write(JsonHelper.convert(this.name));
        writer.write("," + "\"time\":");
        writer.write(JsonHelper.convert(this.time));
        if (this.sampleRate > 0.0) {
            writer.write("," + "\"sampleRate\":");
            writer.write(JsonHelper.convert(this.sampleRate));
        }
        if (this.epoch != null) {
            writer.write("," + "\"epoch\":");
            writer.write(JsonHelper.convert(this.epoch));
        }
        if (this.seqNum != 0L) {
            writer.write("," + "\"seqNum\":");
            writer.write(JsonHelper.convert(this.seqNum));
        }
        if (this.iKey != null) {
            writer.write("," + "\"iKey\":");
            writer.write(JsonHelper.convert(this.iKey));
        }
        if (this.flags != 0L) {
            writer.write("," + "\"flags\":");
            writer.write(JsonHelper.convert(this.flags));
        }
        if (this.os != null) {
            writer.write("," + "\"os\":");
            writer.write(JsonHelper.convert(this.os));
        }
        if (this.osVer != null) {
            writer.write("," + "\"osVer\":");
            writer.write(JsonHelper.convert(this.osVer));
        }
        if (this.appId != null) {
            writer.write("," + "\"appId\":");
            writer.write(JsonHelper.convert(this.appId));
        }
        if (this.appVer != null) {
            writer.write("," + "\"appVer\":");
            writer.write(JsonHelper.convert(this.appVer));
        }
        if (this.cV != null) {
            writer.write("," + "\"cV\":");
            writer.write(JsonHelper.convert(this.cV));
        }
        if (this.tags != null) {
            writer.write("," + "\"tags\":");
            JsonHelper.writeDictionary(writer, this.tags);
        }
        if (this.ext != null) {
            writer.write("," + "\"ext\":");
            JsonHelper.writeDictionary(writer, this.ext);
        }
        if (this.data != null) {
            writer.write("," + "\"data\":");
            JsonHelper.writeJsonSerializable(writer, this.data);
        }
        return ",";
    }
    
    public void setAppId(final String appId) {
        this.appId = appId;
    }
    
    public void setAppVer(final String appVer) {
        this.appVer = appVer;
    }
    
    public void setCV(final String cv) {
        this.cV = cv;
    }
    
    public void setData(final Base data) {
        this.data = data;
    }
    
    public void setEpoch(final String epoch) {
        this.epoch = epoch;
    }
    
    public void setExt(final Map<String, Extension> ext) {
        this.ext = ext;
    }
    
    public void setFlags(final long flags) {
        this.flags = flags;
    }
    
    public void setIKey(final String iKey) {
        this.iKey = iKey;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setOs(final String os) {
        this.os = os;
    }
    
    public void setOsVer(final String osVer) {
        this.osVer = osVer;
    }
    
    public void setSampleRate(final int sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    public void setSeqNum(final long seqNum) {
        this.seqNum = seqNum;
    }
    
    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
    
    public void setTime(final String time) {
        this.time = time;
    }
    
    public void setVer(final int ver) {
        this.ver = ver;
    }
}
