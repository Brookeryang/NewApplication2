package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yyh.newapplication.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.edit_search)
    EditText mEditSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mEditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //设置键盘为搜索
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(v.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    private void search(String trim) {
        ToastUtil.showToastDefault(this, trim);
    }
}
