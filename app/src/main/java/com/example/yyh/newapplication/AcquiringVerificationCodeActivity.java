package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.yyh.newapplication.utils.RxCaptchaUtils;
import com.example.yyh.newapplication.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.yyh.newapplication.utils.RxCaptchaUtils.TYPE.CHARS;

/**
 * <pre>
 *     author : yyh
 *     e-mail : 1355857303@qq.com
 *     time   : 2017/11/8/15:43
 *     version: 1.0
 *     desc   : 拼图获取验证码
 *
 * </pre>
 */
public class AcquiringVerificationCodeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.edit_ver_code)
    EditText mEditVerCode;
    @BindView(R.id.get_ver_code)
    Button mGetVerCode;//获取验证码
    @BindView(R.id.but_submit)
    Button mButSubmit;//提交

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquiring_verification_code);
        ButterKnife.bind(this);
        initviews();
    }

    private void initviews() {
        mGetVerCode.setOnClickListener(this);
        mButSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_ver_code://获取验证码
                ToastUtil.showToastDefault(this, "获取验证码");
                RxCaptchaUtils.build()
                        //背景颜色
                        .backColor(R.color.wheat)
                        //生成验证码的长度
                        .codeLength(4)
                        //内容的字体大小
                        .fontSize(60)
                        //干扰线 数量
                        .lineNumber(4)
                        .size(200, 80)
                        .type(CHARS)
                        .into(mIvCode);

                break;
            case R.id.but_submit://提交实际是比较验证码是否一致
                String VerCode = (mEditVerCode.getText().toString().trim()).toLowerCase();
                if (!TextUtils.isEmpty(VerCode)) {
                    if (VerCode.equals(RxCaptchaUtils.build().getCode())) {
                        ToastUtil.showToastDefault(this, "验证成功!");
                    } else {
                        ToastUtil.showToastDefault(this, "您输入的验证码有误!");
                    }
                } else {
                    ToastUtil.showToastDefault(this, "请输入验证码!");
                }

                break;
            default:
                break;
        }
    }
}
