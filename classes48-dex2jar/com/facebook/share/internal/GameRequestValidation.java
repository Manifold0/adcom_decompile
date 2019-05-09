// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.share.model.GameRequestContent$ActionType;
import com.facebook.internal.Validate;
import com.facebook.share.model.GameRequestContent;

public class GameRequestValidation
{
    public static void validate(final GameRequestContent gameRequestContent) {
        boolean b = false;
        Validate.notNull((Object)gameRequestContent.getMessage(), "message");
        boolean b2;
        if (gameRequestContent.getObjectId() != null) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (gameRequestContent.getActionType() == GameRequestContent$ActionType.ASKFOR || gameRequestContent.getActionType() == GameRequestContent$ActionType.SEND) {
            b = true;
        }
        if (b2 ^ b) {
            throw new IllegalArgumentException("Object id should be provided if and only if action type is send or askfor");
        }
        int n = 0;
        if (gameRequestContent.getRecipients() != null) {
            n = 0 + 1;
        }
        int n2 = n;
        if (gameRequestContent.getSuggestions() != null) {
            n2 = n + 1;
        }
        int n3 = n2;
        if (gameRequestContent.getFilters() != null) {
            n3 = n2 + 1;
        }
        if (n3 > 1) {
            throw new IllegalArgumentException("Parameters to, filters and suggestions are mutually exclusive");
        }
    }
}
