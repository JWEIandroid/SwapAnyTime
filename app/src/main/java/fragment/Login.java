package fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.ContentUtils;

/**
 * Created by Administrator on 2017/11/16.
 */

public class Login extends baseFragment {


    @Bind(R.id.child_loginarea)
    LinearLayout childLoginarea;
    @Bind(R.id.account_et)
    AppCompatEditText accountEt;
    @Bind(R.id.psd_et)
    AppCompatEditText psdEt;
    @Bind(R.id.btn_login)
    TextView btnLogin;
    @Bind(R.id.child_regarea)
    LinearLayout childRegarea;


    @Bind(R.id.text_2reg)
    TextView text2reg;
    @Bind(R.id.phone_et)
    AppCompatEditText phoneEt;
    @Bind(R.id.code_et)
    AppCompatEditText codeEt;
    @Bind(R.id.psd_reg_et)
    AppCompatEditText psdRegEt;
    @Bind(R.id.btn_reg)
    TextView btnReg;
    @Bind(R.id.text_2login)
    TextView text2login;
    @Bind(R.id.login_iv)
    ImageView loginIv;


    private boolean res_login = true;
    private boolean res_reg = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(getTag(), "加载LoginFragment");
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View view) {

        initBackGround();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

        final ContentUtils contentUtils = ContentUtils.getInstance();


        //登录--账号输入
        accountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                boolean step_confirm = false;

                if (!contentUtils.isMobileNO(accountEt.getText().toString())) {
                    showSnackBar("手机号码不对欸 ?", ToastDuration.SHORT);
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_unenabled_shape);
                    btnLogin.setBackground(drawable);
                    return;
                }
                step_confirm = true;
                if (contentUtils.isContentLegal("" + psdEt.getText().toString()) &
                        contentUtils.isNumLegal(psdEt.getText().toString(), 6)) {
                    if (step_confirm) {
                        Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_enabled_shape);
                        btnLogin.setBackground(drawable);
                    }
                    return;
                }
            }
        });

        //登录--密码输入
        psdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                boolean step_confirm = false;

                if (!contentUtils.isNumLegal(psdEt.getText().toString(), 6)) {
                    showSnackBar("密码长度不对欸 ?", ToastDuration.SHORT);
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_unenabled_shape);
                    btnLogin.setBackground(drawable);
                    return;
                }
                step_confirm = true;
                if (contentUtils.isContentLegal("" + accountEt.getText().toString()) &
                        contentUtils.isMobileNO(accountEt.getText().toString())) {
                    if (step_confirm) {
                        Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_enabled_shape);
                        btnLogin.setBackground(drawable);
                    }
                    return;
                }

            }
        });

        //注册--账号输入
        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean step_confirm = false;

                if (!contentUtils.isMobileNO(phoneEt.getText().toString())) {
                    showSnackBar("手机号码不对欸 ?", ToastDuration.SHORT);
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_unenabled_shape);
                    btnReg.setBackground(drawable);
                    return;
                }
                step_confirm = true;
                if (contentUtils.isContentLegal("" + psdRegEt.getText().toString()) &
                        contentUtils.isNumLegal(psdRegEt.getText().toString(), 6) &
                        contentUtils.isContentLegal(codeEt.getText().toString()) &
                        contentUtils.isNumLegal(codeEt.getText().toString(), 4)
                        ) {
                    if (step_confirm) {
                        Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_enabled_shape);
                        btnReg.setBackground(drawable);
                    }
                    return;
                }
            }
        });

        //注册--验证码输入
        codeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean step_confirm = false;

                if (!contentUtils.isNumLegal(codeEt.getText().toString(), 4)) {
                    showSnackBar("验证码不对欸 ?", ToastDuration.SHORT);
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_unenabled_shape);
                    btnReg.setBackground(drawable);
                    return;
                }
                step_confirm = true;
                if (contentUtils.isContentLegal("" + phoneEt.getText().toString()) &
                        contentUtils.isMobileNO(phoneEt.getText().toString()) &
                        contentUtils.isContentLegal(psdRegEt.getText().toString()) &
                        contentUtils.isNumLegal(psdRegEt.getText().toString(), 6)
                        ) {
                    if (step_confirm) {
                        Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_enabled_shape);
                        btnReg.setBackground(drawable);
                    }
                    return;
                }
            }
        });

        //注册--密码输入
        psdRegEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean step_confirm = false;

                if (!contentUtils.isNumLegal(psdRegEt.getText().toString(), 6)) {
                    showSnackBar("密码长度不对欸 ?", ToastDuration.SHORT);
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_unenabled_shape);
                    btnReg.setBackground(drawable);
                    return;
                }
                step_confirm = true;
                if (contentUtils.isContentLegal("" + phoneEt.getText().toString()) &
                        contentUtils.isMobileNO(phoneEt.getText().toString()) &
                        contentUtils.isContentLegal("" + codeEt.getText().toString()) &
                        contentUtils.isNumLegal("" + codeEt.getText().toString(), 4)
                        ) {
                    if (step_confirm) {
                        Drawable drawable = getContext().getResources().getDrawable(R.drawable.btn_enabled_shape);
                        btnReg.setBackground(drawable);
                    }
                    return;
                }
            }
        });


    }

    @Override
    protected void initConfig(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onClick(View v) {

    }

    private void initBackGround(){

        Login login = this;
        Glide.with(login).load(R.mipmap.bg_login).centerCrop().into(loginIv);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_reg, R.id.text_2login, R.id.text_2reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                break;
            case R.id.btn_reg:
                break;
            case R.id.text_2login:
                childLoginarea.setVisibility(View.VISIBLE);
                childRegarea.setVisibility(View.GONE);
                break;
            case R.id.text_2reg:
                childLoginarea.setVisibility(View.GONE);
                childRegarea.setVisibility(View.VISIBLE);
                break;
        }
    }
}
