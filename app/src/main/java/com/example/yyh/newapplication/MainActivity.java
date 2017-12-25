package com.example.yyh.newapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.example.yyh.newapplication.bean.CardBean;
import com.example.yyh.newapplication.http.HttpContentActivity;
import com.example.yyh.newapplication.login.LoginActivity;
import com.example.yyh.newapplication.utils.ToastUtil;
import com.suke.widget.SwitchButton;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.but_tips_search)
    Button mButTipsSearch;
    @BindView(R.id.but_dialog)
    Button mButDialog;
    @BindView(R.id.but_action_bar)
    Button mButActionBar;//沉浸式状态栏+ScrollView顶部伸缩+ActionBar渐变
    @BindView(R.id.but_rx_bus)
    Button mButRxBus;//RxBus
    private Button butSelect;
    private SwitchButton switchButton;
    private Button butl;
    private OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();

    private Button butUpDataApk;
    private Button butRefreshView;
    private Button butLogin, http, VerCode;//登陆

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mButRxBus.setOnClickListener(this);
        mButTipsSearch.setOnClickListener(this);
        mButDialog.setOnClickListener(this);
        mButActionBar.setOnClickListener(this);
        butLogin = (Button) findViewById(R.id.but_login);
        butLogin.setOnClickListener(this);
        switchButton = (SwitchButton) findViewById(R.id.switc_button);
        butSelect = (Button) findViewById(R.id.but_select);
        butl = (Button) findViewById(R.id.but_select_02);
        butUpDataApk = (Button) findViewById(R.id.but_updata_apk);
        butRefreshView = (Button) findViewById(R.id.but_but_refresh_view);
        butRefreshView.setOnClickListener(this);
        http = (Button) findViewById(R.id.but_http);
        VerCode = (Button) findViewById(R.id.but_ver_code);
        VerCode.setOnClickListener(this);
        butUpDataApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butUpData();
            }
        });
        http.setOnClickListener(this);
//        switchButton.setChecked(true);
//        switchButton.isChecked();
//        switchButton.toggle();
//        //启动开关
//        switchButton.toggle(true);//switch without animation
//        switchButton.setShadowEffect(true);//disable shadow effect
//        switchButton.setEnabled(false);//disable button
//        switchButton.setEnableEffect(false);//disable the switch animation
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked == true) {
                    ToastUtil.showToastDefault(MainActivity.this, "开了");
                } else {
                    ToastUtil.showToastDefault(MainActivity.this, "关了");
                }

            }
        });
        butSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            }
        });
        //
        butl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pvCustomOptions != null) {
                    pvCustomOptions.show();
                }
            }
        });

        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                ToastUtil.showToastDefault(MainActivity.this, tx);
            }
        }).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
            @Override
            public void customLayout(View v) {
                //完成
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                //取消
                ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                //完成的点击事件
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.returnData();
                        pvCustomOptions.dismiss();
                    }
                });
                //取消的点击事件
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.dismiss();
                    }
                });

            }
        }).build();
        pvCustomOptions.setPicker(getCardData());//集合
    }


    private List<CardBean> getCardData() {
        for (int i = 0; i < 5; i++) {
            cardItem.add(new CardBean(i, "No.ABC12345 " + i));
        }

        for (int i = 0; i < cardItem.size(); i++) {
            if (cardItem.get(i).getCardNo().length() > 6) {
                String str_item = cardItem.get(i).getCardNo().substring(0, 6) + "...";
                cardItem.get(i).setCardNo(str_item);
            }
        }
        return cardItem;
    }

    public void Onnext(View v) {
        startActivity(new Intent(this, DetailsActivity.class));
    }

    public void showDialog(View view) {
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)//当触摸到空白时，设置是否取消对话框
                .showCancelButton(true)//set weather show cancel button, if true,the Dialog show two buttons
                .setTitle("温馨提示！")
                .setMessage("是否确认不使用仓储系统出库？")
                .setConfirmButtonText("确认")
                .setCancelButtonText("取消")
                //弹出对话框的动画
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                    @Override
                    public void onDialogbtnClick(Context context, Dialog dialog, int i) {
                        switch (i) {
                            //确认
                            case BUTTON_CONFIRM:
                                Toast.makeText(context, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                                break;
                            //取消
                            case BUTTON_CANCEL:
                                Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                        }
                    }
                })
                .create().show();
    }

    /**
     * SpannleString 来设置TextView的样式
     */
    public void SpannableString(View v) {
        startActivity(new Intent(MainActivity.this, SettingTextViewActivity.class));
    }

    /**
     * 版本检测更新的方法
     */
    VersionParams.Builder builder = new VersionParams.Builder();

    private void butUpData() {
        //下载功能
        builder.setOnlyDownload(true)
                .setDownloadUrl("http://42.63.14.245:8001/Download/nxsx365.apk")
                .setTitle("检测到新版本")
                .setUpdateMsg("1.修复BUG\\n2.优化界面\\n3.向天空大声的呼喊说声妈卖批");
        //显示通知栏
        builder.setShowNotification(true);
        //显示下载进度对话框
        builder.setShowDownloadingDialog(true);
        AllenChecker.startVersionCheck(this, builder.build());
        /**
         * 方法二
         *
         */
//        String downloadUrl = "http://42.63.14.245:8001/Download/nxsx365.apk";
//        MainActivity.dealApk
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_but_refresh_view://刷新控件
                startActivity(new Intent(this, RefreshActivity.class));
                break;
            case R.id.but_login://登陆
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.but_http://网络请求
                startActivity(new Intent(this, HttpContentActivity.class));
                break;
            case R.id.but_ver_code://验证码
                startActivity(new Intent(this, AcquiringVerificationCodeActivity.class));
                break;
            case R.id.but_tips_search://搜索的定制
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.but_dialog://对话框
                startActivity(new Intent(this, My_DialogActivity.class));
                break;
            case R.id.but_action_bar://沉浸式状态栏+ScrollView顶部伸缩+ActionBar渐变
                startActivity(new Intent(this, ScrollViewActionBarActivity.class));
                break;
                //RxBus
            case R.id.but_rx_bus:
                startActivity(new Intent(this,RxBusActivity.class));
                break;
            default:
                break;
        }
    }
}
