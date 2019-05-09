// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.widget.TextView;
import android.widget.TextView$OnEditorActionListener;
import android.widget.RelativeLayout$LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout;
import android.text.Editable;
import android.text.InputFilter$LengthFilter;
import android.text.InputFilter;
import android.view.View;
import android.view.View$OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.content.Context;
import android.view.View$OnClickListener;
import android.text.TextWatcher;
import android.app.Dialog;

public final class k extends Dialog implements TextWatcher, View$OnClickListener
{
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private Context a;
    private UnityPlayer b;
    
    static {
        k.c = 1627389952;
        k.d = -1;
        k.e = 134217728;
        k.f = 67108864;
    }
    
    public k(final Context a, final UnityPlayer b, final String s, final int n, final boolean b2, final boolean b3, final boolean b4, final String s2, final int n2) {
        super(a);
        this.a = null;
        this.b = null;
        this.a = a;
        this.b = b;
        this.getWindow().setGravity(80);
        this.getWindow().requestFeature(1);
        this.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.setContentView(this.createSoftInputView());
        this.getWindow().setLayout(-1, -2);
        this.getWindow().clearFlags(2);
        if (j.a) {
            this.getWindow().clearFlags(k.e);
            this.getWindow().clearFlags(k.f);
        }
        final EditText editText = (EditText)this.findViewById(1057292289);
        final Button button = (Button)this.findViewById(1057292290);
        this.a(editText, s, n, b2, b3, b4, s2, n2);
        button.setOnClickListener((View$OnClickListener)this);
        editText.setOnFocusChangeListener((View$OnFocusChangeListener)new View$OnFocusChangeListener() {
            public final void onFocusChange(final View view, final boolean b) {
                if (b) {
                    k.this.getWindow().setSoftInputMode(5);
                }
            }
        });
    }
    
    private static int a(final int n, final boolean b, final boolean b2, final boolean b3) {
        int n2 = 0;
        int n3;
        if (b) {
            n3 = 32768;
        }
        else {
            n3 = 524288;
        }
        int n4;
        if (b2) {
            n4 = 131072;
        }
        else {
            n4 = 0;
        }
        if (b3) {
            n2 = 128;
        }
        final int n5 = n2 | (n4 | n3);
        if (n < 0 || n > 10) {
            return n5;
        }
        final int[] array2;
        final int[] array = array2 = new int[11];
        array2[array2[0] = 1] = 16385;
        array2[2] = 12290;
        array2[3] = 17;
        array2[4] = 2;
        array2[5] = 3;
        array2[6] = 8289;
        array2[7] = 33;
        array2[8] = 1;
        array2[9] = 16417;
        array2[10] = 17;
        if ((array[n] & 0x2) != 0x0) {
            return array[n];
        }
        return n5 | array[n];
    }
    
    private String a() {
        final EditText editText = (EditText)this.findViewById(1057292289);
        if (editText == null) {
            return null;
        }
        return editText.getText().toString().trim();
    }
    
    private void a(final EditText editText, final String text, final int n, final boolean b, final boolean b2, final boolean b3, final String hint, final int n2) {
        editText.setImeOptions(6);
        editText.setText((CharSequence)text);
        editText.setHint((CharSequence)hint);
        editText.setHintTextColor(k.c);
        editText.setInputType(a(n, b, b2, b3));
        editText.setImeOptions(33554432);
        if (n2 > 0) {
            editText.setFilters(new InputFilter[] { (InputFilter)new InputFilter$LengthFilter(n2) });
        }
        editText.addTextChangedListener((TextWatcher)this);
        editText.setSelection(editText.getText().length());
        editText.setClickable(true);
    }
    
    private void a(final String s, final boolean b) {
        ((EditText)this.findViewById(1057292289)).setSelection(0, 0);
        this.b.reportSoftInputStr(s, 1, b);
    }
    
    public final void a(final int n) {
        final EditText editText = (EditText)this.findViewById(1057292289);
        if (editText != null) {
            if (n <= 0) {
                editText.setFilters(new InputFilter[0]);
                return;
            }
            editText.setFilters(new InputFilter[] { (InputFilter)new InputFilter$LengthFilter(n) });
        }
    }
    
    public final void a(final String text) {
        final EditText editText = (EditText)this.findViewById(1057292289);
        if (editText != null) {
            editText.setText((CharSequence)text);
            editText.setSelection(text.length());
        }
    }
    
    public final void afterTextChanged(final Editable editable) {
        this.b.reportSoftInputStr(editable.toString(), 0, false);
    }
    
    public final void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    protected final View createSoftInputView() {
        final RelativeLayout relativeLayout = new RelativeLayout(this.a);
        relativeLayout.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(k.d);
        final EditText editText = new EditText(this.a) {
            public final boolean onKeyPreIme(final int n, final KeyEvent keyEvent) {
                if (n == 4) {
                    k.this.a(k.this.a(), true);
                }
                else if (n != 84) {
                    return super.onKeyPreIme(n, keyEvent);
                }
                return true;
            }
            
            public final void onWindowFocusChanged(final boolean b) {
                super.onWindowFocusChanged(b);
                if (b) {
                    ((InputMethodManager)k.this.a.getSystemService("input_method")).showSoftInput((View)this, 0);
                }
            }
        };
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        editText.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        editText.setId(1057292289);
        relativeLayout.addView((View)editText);
        final Button button = new Button(this.a);
        button.setText(this.a.getResources().getIdentifier("ok", "string", "android"));
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        button.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        button.setId(1057292290);
        button.setBackgroundColor(0);
        relativeLayout.addView((View)button);
        ((EditText)((View)relativeLayout).findViewById(1057292289)).setOnEditorActionListener((TextView$OnEditorActionListener)new TextView$OnEditorActionListener() {
            public final boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
                if (n == 6) {
                    k.this.a(k.this.a(), false);
                }
                return false;
            }
        });
        ((View)relativeLayout).setPadding(16, 16, 16, 16);
        return (View)relativeLayout;
    }
    
    public final void onBackPressed() {
        this.a(this.a(), true);
    }
    
    public final void onClick(final View view) {
        this.a(this.a(), false);
    }
    
    public final void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
}
