// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;
import javax.annotation.Nullable;
import com.google.android.gms.ads.internal.gmsg.zzag;

final class zzwt implements zzag
{
    private final /* synthetic */ zzwq zzbro;
    private final zzvs zzbrp;
    private final zzaoj zzbrq;
    
    public zzwt(final zzwq zzbro, final zzvs zzbrp, final zzaoj zzbrq) {
        this.zzbro = zzbro;
        this.zzbrp = zzbrp;
        this.zzbrq = zzbrq;
    }
    
    @Override
    public final void zzau(@Nullable final String s) {
        Label_0026: {
            if (s != null) {
                break Label_0026;
            }
            try {
                this.zzbrq.setException(new zzwe());
                return;
                this.zzbrq.setException(new zzwe(s));
            }
            catch (IllegalStateException ex) {}
            finally {
                this.zzbrp.release();
            }
        }
    }
    
    @Override
    public final void zzd(final JSONObject jsonObject) {
        try {
            this.zzbrq.set(this.zzbro.zzbri.zze(jsonObject));
        }
        catch (IllegalStateException ex2) {}
        catch (JSONException ex) {
            this.zzbrq.set(ex);
        }
        finally {
            this.zzbrp.release();
        }
    }
}
