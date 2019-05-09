// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v7.view.menu;

import android.widget.ListView;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
public interface ShowableListMenu
{
    void dismiss();
    
    ListView getListView();
    
    boolean isShowing();
    
    void show();
}
