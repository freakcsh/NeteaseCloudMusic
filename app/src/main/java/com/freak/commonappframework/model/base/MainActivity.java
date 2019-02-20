package com.freak.commonappframework.model.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freak.commonappframework.R;
import com.freak.commonappframework.model.homepage.base.HomePageFragment;
import com.freak.commonappframework.model.merchant.base.MerchantFragment;
import com.freak.commonappframework.model.myself.MyselfFragment;
import com.freak.commonappframework.model.share.base.ShareFragment;
import com.freak.commonappframework.model.shop.base.ShopFragment;


/**
 * @author freak
 * @date 2019/02/19
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLinearLayoutHomepage, mLinearLayoutMerchant, mLinearLayoutShop, mLinearLayoutShare, mLinearLayoutMyself;
    private ImageView mImageHomepage, mImageMerchant, mImageShop, mImageShare, mImageMyself;
    private TextView mTextViewHomepage, mTextViewMerchant, mTextViewShop, mTextViewShare, mTextViewMyself;

    private HomePageFragment mHomePageFragment;
    private MerchantFragment mMerchantFragment;
    private ShopFragment mShopFragment;
    private ShareFragment mShareFragment;
    private MyselfFragment mMyselfFragment;

    /**
     * 定义FragmentManager对象
     */
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        initView();
    }


    private void initView() {
        mLinearLayoutHomepage = findViewById(R.id.linear_layout_homepage);
        mLinearLayoutMerchant = findViewById(R.id.linear_layout_merchant);
        mLinearLayoutShop = findViewById(R.id.linear_layout_shop);
        mLinearLayoutShare = findViewById(R.id.linear_layout_share);
        mLinearLayoutMyself = findViewById(R.id.linear_layout_myself);

        mImageHomepage = findViewById(R.id.image_homepage);
        mImageMerchant = findViewById(R.id.image_merchant);
        mImageShop = findViewById(R.id.image_shop);
        mImageShare = findViewById(R.id.image_share);
        mImageMyself = findViewById(R.id.image_myself);

        mTextViewHomepage = findViewById(R.id.text_view_homepage);
        mTextViewMerchant = findViewById(R.id.text_view_merchant);
        mTextViewShop = findViewById(R.id.text_view_shop);
        mTextViewShare = findViewById(R.id.text_view_share);
        mTextViewMyself = findViewById(R.id.text_view_myself);

        mLinearLayoutHomepage.setOnClickListener(this);
        mLinearLayoutMerchant.setOnClickListener(this);
        mLinearLayoutShop.setOnClickListener(this);
        mLinearLayoutShare.setOnClickListener(this);
        mLinearLayoutMyself.setOnClickListener(this);
        setChoiceItem(0);
    }

    /**
     * 底部导航栏选择，展示对应的fragment
     *
     * @param index
     */
    private void setChoiceItem(int index) {
        // 重置选项+隐藏所有Fragment
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                showHomepageFragment(transaction);
                break;
            case 1:
                showMerchantFragment(transaction);
                break;
            case 2:
                showShopFragment(transaction);
                break;
            case 3:
                showShareFragment(transaction);
                break;
            case 4:
                showMyselfFragment(transaction);
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 展示MyselfFragment
     *
     * @param transaction
     */
    private void showMyselfFragment(FragmentTransaction transaction) {
        if (mMyselfFragment == null) {
            // 如果Fragment为空，则创建一个并添加到界面上
            mMyselfFragment = new MyselfFragment();
            transaction.add(R.id.frame_layout_main, mMyselfFragment);
        } else {
            // 如果Fragment不为空，则直接将它显示出来
            transaction.show(mMyselfFragment);
        }

        mImageMyself.setSelected(true);
        mImageHomepage.setSelected(false);
        mImageMerchant.setSelected(false);
        mImageShop.setSelected(false);
        mImageShare.setSelected(false);

        mTextViewMyself.setSelected(true);
        mTextViewHomepage.setSelected(false);
        mTextViewMerchant.setSelected(false);
        mTextViewShop.setSelected(false);
        mTextViewShare.setSelected(false);
    }

    /**
     * 展示ShareFragment
     *
     * @param transaction
     */
    private void showShareFragment(FragmentTransaction transaction) {
        if (mShareFragment == null) {
            // 如果Fragment为空，则创建一个并添加到界面上
            mShareFragment = new ShareFragment();
            transaction.add(R.id.frame_layout_main, mShareFragment);
        } else {
            // 如果Fragment不为空，则直接将它显示出来
            transaction.show(mShareFragment);
        }

        mImageShare.setSelected(true);
        mImageHomepage.setSelected(false);
        mImageMerchant.setSelected(false);
        mImageShop.setSelected(false);
        mImageMyself.setSelected(false);

        mTextViewShare.setSelected(true);
        mTextViewHomepage.setSelected(false);
        mTextViewMerchant.setSelected(false);
        mTextViewShop.setSelected(false);
        mTextViewMyself.setSelected(false);
    }

    /***
     * 展示ShopFragment
     * @param transaction
     */
    private void showShopFragment(FragmentTransaction transaction) {
        if (mShopFragment == null) {
            // 如果Fragment为空，则创建一个并添加到界面上
            mShopFragment = new ShopFragment();
            transaction.add(R.id.frame_layout_main, mShopFragment);
        } else {
            // 如果Fragment不为空，则直接将它显示出来
            transaction.show(mShopFragment);
        }

        mImageShop.setSelected(true);
        mImageHomepage.setSelected(false);
        mImageMerchant.setSelected(false);
        mImageShare.setSelected(false);
        mImageMyself.setSelected(false);

        mTextViewShop.setSelected(true);
        mTextViewHomepage.setSelected(false);
        mTextViewMerchant.setSelected(false);
        mTextViewShare.setSelected(false);
        mTextViewMyself.setSelected(false);
    }

    /**
     * 展示MerchantFragment
     *
     * @param transaction
     */
    private void showMerchantFragment(FragmentTransaction transaction) {
        if (mMerchantFragment == null) {
            // 如果Fragment为空，则创建一个并添加到界面上
            mMerchantFragment = new MerchantFragment();
            transaction.add(R.id.frame_layout_main, mMerchantFragment);
        } else {
            // 如果Fragment不为空，则直接将它显示出来
            transaction.show(mMerchantFragment);
        }

        mImageMerchant.setSelected(true);
        mImageHomepage.setSelected(false);
        mImageShop.setSelected(false);
        mImageShare.setSelected(false);
        mImageMyself.setSelected(false);

        mTextViewMerchant.setSelected(true);
        mTextViewHomepage.setSelected(false);
        mTextViewShop.setSelected(false);
        mTextViewShare.setSelected(false);
        mTextViewMyself.setSelected(false);
    }

    /**
     * 展示HomePageFragment
     *
     * @param transaction
     */
    private void showHomepageFragment(FragmentTransaction transaction) {
        if (mHomePageFragment == null) {
            // 如果Fragment为空，则创建一个并添加到界面上
            mHomePageFragment = new HomePageFragment();
            transaction.add(R.id.frame_layout_main, mHomePageFragment);
        } else {
            // 如果Fragment不为空，则直接将它显示出来
            transaction.show(mHomePageFragment);
        }

        mImageHomepage.setSelected(true);
        mImageMerchant.setSelected(false);
        mImageShop.setSelected(false);
        mImageShare.setSelected(false);
        mImageMyself.setSelected(false);

        mTextViewHomepage.setSelected(true);
        mTextViewMerchant.setSelected(false);
        mTextViewShop.setSelected(false);
        mTextViewShare.setSelected(false);
        mTextViewMyself.setSelected(false);
    }


    /**
     * 隐藏所有的Fragment,避免fragment混乱
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mHomePageFragment != null) {
            transaction.hide(mHomePageFragment);
        }

        if (mMerchantFragment != null) {
            transaction.hide(mMerchantFragment);
        }

        if (mShopFragment != null) {
            transaction.hide(mShopFragment);
        }

        if (mShareFragment != null) {
            transaction.hide(mShareFragment);
        }

        if (mMyselfFragment != null) {
            transaction.hide(mMyselfFragment);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_homepage:
                setChoiceItem(0);
                break;
            case R.id.linear_layout_merchant:
                setChoiceItem(1);
                break;
            case R.id.linear_layout_shop:
                setChoiceItem(2);
                break;
            case R.id.linear_layout_share:
                setChoiceItem(3);
                break;
            case R.id.linear_layout_myself:
                setChoiceItem(4);
                break;
            default:
                break;
        }
    }
}
