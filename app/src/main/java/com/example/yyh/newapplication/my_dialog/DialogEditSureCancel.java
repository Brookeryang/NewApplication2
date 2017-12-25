package com.example.yyh.newapplication.my_dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yyh.newapplication.R;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/12/13 16:15
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public class DialogEditSureCancel extends MyDialog {
    private TextView mTvSure;
    private TextView mTvCancel;
    private EditText editText;

    public DialogEditSureCancel(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public DialogEditSureCancel(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public DialogEditSureCancel(Context context) {
        super(context);
        initView();
    }

    public DialogEditSureCancel(Activity context) {
        super(context);
        initView();
    }

    public DialogEditSureCancel(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public EditText getEditText() {
        return editText;
    }

    public TextView getSureView() {
        return mTvSure;
    }

    public void setSure(String strSure) {
        this.mTvSure.setText(strSure);
    }

    public TextView getCancelView() {
        return mTvCancel;
    }

    public void setCancel(String strCancel) {
        this.mTvCancel.setText(strCancel);
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog_edittext_sure_false, null);
        mTvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        mTvCancel = (TextView) dialogView.findViewById(R.id.tv_cancle);
        editText = (EditText) dialogView.findViewById(R.id.editText);
        setContentView(dialogView);
    }
}
