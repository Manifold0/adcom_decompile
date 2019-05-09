package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertController.AlertParams;
import android.support.v7.appcompat.C0930R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    public static class Builder {
        /* renamed from: P */
        private final AlertParams f3642P;
        private final int mTheme;

        public Builder(@NonNull Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            this.f3642P = new AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, themeResId)));
            this.mTheme = themeResId;
        }

        @NonNull
        public Context getContext() {
            return this.f3642P.mContext;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.f3642P.mTitle = this.f3642P.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            this.f3642P.mTitle = title;
            return this;
        }

        public Builder setCustomTitle(@Nullable View customTitleView) {
            this.f3642P.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.f3642P.mMessage = this.f3642P.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence message) {
            this.f3642P.mMessage = message;
            return this;
        }

        public Builder setIcon(@DrawableRes int iconId) {
            this.f3642P.mIconId = iconId;
            return this;
        }

        public Builder setIcon(@Nullable Drawable icon) {
            this.f3642P.mIcon = icon;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int attrId) {
            TypedValue out = new TypedValue();
            this.f3642P.mContext.getTheme().resolveAttribute(attrId, out, true);
            this.f3642P.mIconId = out.resourceId;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, OnClickListener listener) {
            this.f3642P.mPositiveButtonText = this.f3642P.mContext.getText(textId);
            this.f3642P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            this.f3642P.mPositiveButtonText = text;
            this.f3642P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
            this.f3642P.mNegativeButtonText = this.f3642P.mContext.getText(textId);
            this.f3642P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.f3642P.mNegativeButtonText = text;
            this.f3642P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
            this.f3642P.mNeutralButtonText = this.f3642P.mContext.getText(textId);
            this.f3642P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            this.f3642P.mNeutralButtonText = text;
            this.f3642P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.f3642P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.f3642P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.f3642P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.f3642P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(@ArrayRes int itemsId, OnClickListener listener) {
            this.f3642P.mItems = this.f3642P.mContext.getResources().getTextArray(itemsId);
            this.f3642P.mOnClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, OnClickListener listener) {
            this.f3642P.mItems = items;
            this.f3642P.mOnClickListener = listener;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter, OnClickListener listener) {
            this.f3642P.mAdapter = adapter;
            this.f3642P.mOnClickListener = listener;
            return this;
        }

        public Builder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
            this.f3642P.mCursor = cursor;
            this.f3642P.mLabelColumn = labelColumn;
            this.f3642P.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            this.f3642P.mItems = this.f3642P.mContext.getResources().getTextArray(itemsId);
            this.f3642P.mOnCheckboxClickListener = listener;
            this.f3642P.mCheckedItems = checkedItems;
            this.f3642P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            this.f3642P.mItems = items;
            this.f3642P.mOnCheckboxClickListener = listener;
            this.f3642P.mCheckedItems = checkedItems;
            this.f3642P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
            this.f3642P.mCursor = cursor;
            this.f3642P.mOnCheckboxClickListener = listener;
            this.f3642P.mIsCheckedColumn = isCheckedColumn;
            this.f3642P.mLabelColumn = labelColumn;
            this.f3642P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, OnClickListener listener) {
            this.f3642P.mItems = this.f3642P.mContext.getResources().getTextArray(itemsId);
            this.f3642P.mOnClickListener = listener;
            this.f3642P.mCheckedItem = checkedItem;
            this.f3642P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
            this.f3642P.mCursor = cursor;
            this.f3642P.mOnClickListener = listener;
            this.f3642P.mCheckedItem = checkedItem;
            this.f3642P.mLabelColumn = labelColumn;
            this.f3642P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
            this.f3642P.mItems = items;
            this.f3642P.mOnClickListener = listener;
            this.f3642P.mCheckedItem = checkedItem;
            this.f3642P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
            this.f3642P.mAdapter = adapter;
            this.f3642P.mOnClickListener = listener;
            this.f3642P.mCheckedItem = checkedItem;
            this.f3642P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(OnItemSelectedListener listener) {
            this.f3642P.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(int layoutResId) {
            this.f3642P.mView = null;
            this.f3642P.mViewLayoutResId = layoutResId;
            this.f3642P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f3642P.mView = view;
            this.f3642P.mViewLayoutResId = 0;
            this.f3642P.mViewSpacingSpecified = false;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Deprecated
        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            this.f3642P.mView = view;
            this.f3642P.mViewLayoutResId = 0;
            this.f3642P.mViewSpacingSpecified = true;
            this.f3642P.mViewSpacingLeft = viewSpacingLeft;
            this.f3642P.mViewSpacingTop = viewSpacingTop;
            this.f3642P.mViewSpacingRight = viewSpacingRight;
            this.f3642P.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            this.f3642P.mForceInverseBackground = useInverseBackground;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            this.f3642P.mRecycleOnMeasure = enabled;
            return this;
        }

        public AlertDialog create() {
            AlertDialog dialog = new AlertDialog(this.f3642P.mContext, this.mTheme);
            this.f3642P.apply(dialog.mAlert);
            dialog.setCancelable(this.f3642P.mCancelable);
            if (this.f3642P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.f3642P.mOnCancelListener);
            dialog.setOnDismissListener(this.f3642P.mOnDismissListener);
            if (this.f3642P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.f3642P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

    protected AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    protected AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, resolveDialogTheme(context, themeResId));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        this(context, 0);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    static int resolveDialogTheme(@NonNull Context context, @StyleRes int resid) {
        if (((resid >>> 24) & 255) >= 1) {
            return resid;
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(C0930R.attr.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }

    public Button getButton(int whichButton) {
        return this.mAlert.getButton(whichButton);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.mAlert.setTitle(title);
    }

    public void setCustomTitle(View customTitleView) {
        this.mAlert.setCustomTitle(customTitleView);
    }

    public void setMessage(CharSequence message) {
        this.mAlert.setMessage(message);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    void setButtonPanelLayoutHint(int layoutHint) {
        this.mAlert.setButtonPanelLayoutHint(layoutHint);
    }

    public void setButton(int whichButton, CharSequence text, Message msg) {
        this.mAlert.setButton(whichButton, text, null, msg);
    }

    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, null);
    }

    public void setIcon(int resId) {
        this.mAlert.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mAlert.setIcon(icon);
    }

    public void setIconAttribute(int attrId) {
        TypedValue out = new TypedValue();
        getContext().getTheme().resolveAttribute(attrId, out, true);
        this.mAlert.setIcon(out.resourceId);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
