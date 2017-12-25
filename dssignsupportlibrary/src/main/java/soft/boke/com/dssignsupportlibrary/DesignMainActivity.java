package soft.boke.com.dssignsupportlibrary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DesignMainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.layout_name)
    TextInputLayout mLayoutName;
    @BindView(R.id.et_pw)
    EditText mEtPw;
    @BindView(R.id.layout_pw)
    TextInputLayout mLayoutPw;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.fl_bug)
    FloatingActionButton mFlBug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desing_activity_main);
        ButterKnife.bind(this);
        mBtLogin.setOnClickListener(this);
        mFlBug.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
            case R.id.fl_bug:
                Toast.makeText(this, "........", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    //登陆方法
    private void login() {
        //获取用户名和密码
        String userName = mLayoutName.getEditText().getText().toString().trim();
        String password = mLayoutPw.getEditText().getText().toString().trim();
        if (userName.length() == 0) {
            mLayoutName.setErrorEnabled(true);
            mLayoutName.setError("请输入正确的邮箱地址!");
        } else if (!validatePassword(password)) {
            mLayoutPw.setErrorEnabled(true);
            mLayoutPw.setError("你输入的密码位数过少");
        } else {
            mLayoutName.setErrorEnabled(false);
            mLayoutPw.setErrorEnabled(false);
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 校验密码长度的方法
     */
    private boolean validatePassword(String paw) {
        return paw.length() > 6;
    }

    /**
     * 用正则表达式校验用户名
     */
//    private Pattern mPattern = Pattern.compile(CkeckUtils.EMAIL_PATTERN);

//    private boolean validateUserName(String username) {
//        Matcher matcher;
//        matcher = mPattern.matcher(username);
//        return matcher.matches();
//    }
}
