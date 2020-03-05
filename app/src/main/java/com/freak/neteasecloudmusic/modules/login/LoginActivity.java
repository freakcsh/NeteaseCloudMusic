package com.freak.neteasecloudmusic.modules.login;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.base.MainActivity;
import com.freak.neteasecloudmusic.modules.login.entty.LoginEntity;
import com.freak.neteasecloudmusic.utils.SPUtils;

/**
 * @author Administrator
 * @date 2019/3/16
 */

public class LoginActivity extends BaseAbstractMvpActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener, TextWatcher ,IActivityStatusBar{
    private EditText mEditTextUserPhone, mEditTextLoginPassword;
    private TextView mTextViewLoginCommit;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_login_commit:
                doLogin(getPhone(), getPassword());
                break;
            default:
                break;

        }
    }

    private void doLogin(String phone, String password) {
        mPresenter.doLogin(phone, password);
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    protected int getLayout() {
        return R.layout.acitvity_login;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onCreateLoadData() {

    }

    @Override
    protected void onDestroyRelease() {

    }

    @Override
    protected void onResumeLoadData() {

    }

    @Override
    protected void initView() {
        setTitle("手机号登陆");
        mTextViewLoginCommit = findViewById(R.id.text_view_login_commit);
        mEditTextUserPhone = findViewById(R.id.edit_text_user_phone);
        mEditTextLoginPassword = findViewById(R.id.edit_text_login_password);
        mTextViewLoginCommit.setOnClickListener(this);
        mEditTextUserPhone.addTextChangedListener(this);
        mEditTextLoginPassword.addTextChangedListener(this);
        mTextViewLoginCommit.setEnabled(false);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(getPassword()) || TextUtils.isEmpty(getPhone())) {
            mTextViewLoginCommit.setEnabled(false);
        } else {
            mTextViewLoginCommit.setEnabled(true);
        }
    }

    @NonNull
    private String getPhone() {
        return mEditTextUserPhone.getText().toString().trim();
    }

    @NonNull
    private String getPassword() {
        return mEditTextLoginPassword.getText().toString().trim();
    }

    @Override
    public void onLoginSuccess(LoginEntity loginEntity) {
        SPUtils.put(this, Constants.LOGIN_URL, loginEntity.getProfile().getBackgroundUrl());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginError(String msg) {

    }

    @Override
    public int getStatusBarColor() {
        return 0;
    }

    @Override
    public int getDrawableStatusBar() {
        return 0;
    }
}
