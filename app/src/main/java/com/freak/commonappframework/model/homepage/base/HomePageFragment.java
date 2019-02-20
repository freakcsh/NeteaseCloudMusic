package com.freak.commonappframework.model.homepage.base;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.freak.commonappframework.R;
import com.freak.commonappframework.base.BaseAbstractMvpFragment;
import com.freak.commonappframework.model.homepage.base.bean.LoginBean;


/**
 * @author freak
 * @date 2019/2/19
 */

public class HomePageFragment extends BaseAbstractMvpFragment<HomepagePresenter> implements HomepageContract.View, View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private ImageView image;
    private TextView text_view;

    @Override
    public void showToast(String toast) {

    }

    @Override
    protected HomepagePresenter createPresenter() {
        return new HomepagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initView(View view) {
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);
        btn8 = view.findViewById(R.id.btn8);
        btn9 = view.findViewById(R.id.btn9);
        image = view.findViewById(R.id.image);
        text_view = view.findViewById(R.id.text_view);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                mPresenter.doLogin("freak", "123456");
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
            case R.id.btn7:
                break;
            case R.id.btn8:
                break;
            case R.id.btn9:
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(LoginBean loginBean) {

    }

    @Override
    public void onError(String msg) {

    }
}
