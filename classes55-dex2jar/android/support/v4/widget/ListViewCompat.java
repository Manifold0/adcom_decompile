// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.widget;

import android.view.View;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.widget.ListView;

public final class ListViewCompat
{
    private ListViewCompat() {
    }
    
    public static boolean canScrollList(@NonNull final ListView listView, int n) {
        final boolean b = false;
        boolean canScrollList;
        if (Build$VERSION.SDK_INT >= 19) {
            canScrollList = listView.canScrollList(n);
        }
        else {
            final int childCount = listView.getChildCount();
            canScrollList = b;
            if (childCount != 0) {
                final int firstVisiblePosition = listView.getFirstVisiblePosition();
                if (n > 0) {
                    n = listView.getChildAt(childCount - 1).getBottom();
                    if (firstVisiblePosition + childCount >= listView.getCount()) {
                        canScrollList = b;
                        if (n <= listView.getHeight() - listView.getListPaddingBottom()) {
                            return canScrollList;
                        }
                    }
                    return true;
                }
                n = listView.getChildAt(0).getTop();
                if (firstVisiblePosition <= 0) {
                    canScrollList = b;
                    if (n >= listView.getListPaddingTop()) {
                        return canScrollList;
                    }
                }
                return true;
            }
        }
        return canScrollList;
    }
    
    public static void scrollListBy(@NonNull final ListView listView, final int n) {
        if (Build$VERSION.SDK_INT >= 19) {
            listView.scrollListBy(n);
        }
        else {
            final int firstVisiblePosition = listView.getFirstVisiblePosition();
            if (firstVisiblePosition != -1) {
                final View child = listView.getChildAt(0);
                if (child != null) {
                    listView.setSelectionFromTop(firstVisiblePosition, child.getTop() - n);
                }
            }
        }
    }
}
