// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import com.facebook.share.ShareBuilder;

public interface ShareModelBuilder<P extends ShareModel, E extends ShareModelBuilder> extends ShareBuilder<P, E>
{
    E readFrom(final P p0);
}
