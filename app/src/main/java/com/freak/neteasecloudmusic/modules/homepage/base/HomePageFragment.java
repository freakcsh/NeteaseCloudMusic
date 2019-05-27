package com.freak.neteasecloudmusic.modules.homepage.base;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;
import com.freak.neteasecloudmusic.compress.ICompressCallBack;
import com.freak.neteasecloudmusic.compress.LuBanCompressUtils;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.LoginBean;
import com.freak.neteasecloudmusic.net.log.LogUtil;
import com.freak.neteasecloudmusic.scan.ScanActivity;
import com.freak.neteasecloudmusic.utils.imagepick.loader.ImagePickerUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @author freak
 * @date 2019/2/19
 */

public class HomePageFragment extends BaseAbstractMvpFragment<HomepagePresenter> implements HomepageContract.View, View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private ImageView image;
    private TextView text_view;
    private List<String> mStringList=new ArrayList<>();

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
            //选择图片
            case R.id.btn1:

                break;
            //网络请求
            case R.id.btn2:
                mPresenter.doLogin("freak", "123456");
                break;
            //webView
            case R.id.btn3:
                break;
            //二维码扫描
            case R.id.btn4:
                startActivity(new Intent(getActivity(), ScanActivity.class));
                break;
            //读取身份证信息
            case R.id.btn5:
                break;
            //glide
            case R.id.btn6:
                break;
            //rxBus
            case R.id.btn7:
                break;
            //imagePicker
            case R.id.btn8:
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, ImagePickerUtils.RESULT_CODE_IMAGE);
                break;
            //鲁班图片压缩
            case R.id.btn9:
//                mStringList.add("img_0709.jpg");
                mStringList.add("img.jpg");
                LuBanCompressUtils.getInstance().setContext(getActivity()).setICompressCallBack(new ICompressCallBack() {
                    @Override
                    public void CompressSuccess(File file) {
                        LogUtil.e("压缩完成");
                      Uri uri= LuBanCompressUtils.getInstance().toURI(getActivity(),file);
                      image.setImageURI(uri);
                    }
                }).withAsynchronization(LuBanCompressUtils.getInstance().assetsToUri(mStringList));
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.e("接收数据");
        List<ImageItem> imageList = ImagePickerUtils.getInstance().selectImageResult(requestCode, resultCode, data);
       if (imageList.size()>0){
           LogUtil.d(imageList.get(0).path);
           image.setImageURI(Uri.parse(imageList.get(0).path));
       }
    }

    @Override
    public void onSuccess(LoginBean loginBean) {

    }

    @Override
    public void onError(String msg) {

    }
}
