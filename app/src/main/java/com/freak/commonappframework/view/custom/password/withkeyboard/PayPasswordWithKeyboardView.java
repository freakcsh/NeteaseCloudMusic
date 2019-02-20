package com.freak.commonappframework.view.custom.password.withkeyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freak.commonappframework.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义支付密码组件、自带密码输入数字键盘
 * @author Administrator
 */
public class PayPasswordWithKeyboardView extends RelativeLayout {
    /**
     * 上下文
     */
    private Activity mContext;
    /**
     * 支付键盘
     */
    private GridView mGridView;
    /**
     * 保存密码
     */
    private String strPass = "";
    /**
     * 密码数字控件
     */
    private TextView[] mTextViewPass;
    /**
     * 关闭
     */
    private LinearLayout mLinearLayoutCancel;
    /**
     * 忘记密码s
     */
    private TextView mTextViewForget;
    /**
     * 提示 (提示:密码错误,重新输入)
     */
    private TextView mTextViewCommonTitle;
    private List<Integer> listNumber;
    private View mPassLayout;
    /**
     * 关闭图片
     */
    private ImageView mImageViewClose;
    /**
     * 控制忘记密码是否显示
     */
    private RelativeLayout mRelativeLayoutForgetPassword;

    /**
     * 按钮对外接口
     */
    public interface OnPayClickListener {
        /**
         * 输入密码结束
         *
         * @param passContent
         */
        void onPassFinish(String passContent);

        /**
         * 关闭页面
         */
        void onPayClose();

        /**
         * 忘记密码
         */
        void onPayForget();
    }

    private OnPayClickListener mPayClickListener;

    public void setPayClickListener(OnPayClickListener listener) {
        mPayClickListener = listener;
    }

    /**
     * 在代码new使用
     *
     * @param context
     */
    public PayPasswordWithKeyboardView(Context context) {
        super(context);
    }

    /**
     * 在布局文件中使用的时候调用,多个样式文件
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public PayPasswordWithKeyboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在布局文件中使用的时候调用
     *
     * @param context
     * @param attrs
     */
    public PayPasswordWithKeyboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = (Activity) context;
        //初始化
        initView();
        //将子布局添加到父容器,才显示控件
        this.addView(mPassLayout);
    }

    /**
     * 初始化
     */
    private void initView() {
        mPassLayout = LayoutInflater.from(mContext).inflate(R.layout.view_pay_password_with_keyboard, null);
        //关闭
        mLinearLayoutCancel = (LinearLayout) mPassLayout.findViewById(R.id.linear_layout_cancel);
        //关闭
        mImageViewClose = (ImageView) mPassLayout.findViewById(R.id.image_view_close);
        //忘记密码文字
        mTextViewForget = (TextView) mPassLayout.findViewById(R.id.tv_forget);
        //忘记密码
        mRelativeLayoutForgetPassword = (RelativeLayout) mPassLayout.findViewById(R.id.relative_layout_forget_password);
        //提示文字
        mTextViewCommonTitle = (TextView) mPassLayout.findViewById(R.id.text_view_common_title);
        //密码控件
        mTextViewPass = new TextView[6];
        mTextViewPass[0] = (TextView) mPassLayout.findViewById(R.id.tv_pass1);
        mTextViewPass[1] = (TextView) mPassLayout.findViewById(R.id.tv_pass2);
        mTextViewPass[2] = (TextView) mPassLayout.findViewById(R.id.tv_pass3);
        mTextViewPass[3] = (TextView) mPassLayout.findViewById(R.id.tv_pass4);
        mTextViewPass[4] = (TextView) mPassLayout.findViewById(R.id.tv_pass5);
        mTextViewPass[5] = (TextView) mPassLayout.findViewById(R.id.tv_pass6);
        mGridView = (GridView) mPassLayout.findViewById(R.id.gv_pass);

        mLinearLayoutCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanAllTv();
                mPayClickListener.onPayClose();
            }
        });
        mTextViewForget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPayClickListener.onPayForget();
            }
        });

        //初始化数据
        listNumber = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            listNumber.add(i);
        }
        listNumber.add(10);
        listNumber.add(0);
        listNumber.add(R.drawable.select_bg_delete);

        mGridView.setAdapter(adapter);
    }


    /**
     * GridView的适配器
     */

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listNumber.size();
        }

        @Override
        public Object getItem(int position) {
            return listNumber.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.view_paypass_gridview_item, null);
                holder = new ViewHolder();
                holder.mTextViewNumber = (TextView) convertView.findViewById(R.id.btNumber);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //-------------设置数据----------------
            holder.mTextViewNumber.setText(listNumber.get(position) + "");
            if (position == 9) {
                holder.mTextViewNumber.setText("");
                holder.mTextViewNumber.setBackgroundColor(Color.parseColor("#FFD1D2D7"));
            }
            if (position == 11) {
                holder.mTextViewNumber.setText("");
                holder.mTextViewNumber.setBackgroundResource(listNumber.get(position));
            }
            holder.mTextViewNumber.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //0-9按钮
                    if (position < 11 && position != 9) {
                        if (strPass.length() == 6) {
                            return;
                        } else {
                            //得到当前数字并累加
                            strPass = strPass + listNumber.get(position);
                            //设置界面*
                            mTextViewPass[strPass.length() - 1].setText("*");
                            //输入完成
                            if (strPass.length() == 6) {
                                //请求服务器验证密码
                                mPayClickListener.onPassFinish(strPass);
                            }
                        }
                    } else if (position == 11) {
                        //删除
                        if (strPass.length() > 0) {
                            //去掉界面*
                            mTextViewPass[strPass.length() - 1].setText("");
                            //删除一位
                            strPass = strPass.substring(0, strPass.length() - 1);
                        }
                    }
                    //空按钮
                    if (position == 9) {
                    }
                }
            });

            return convertView;
        }
    };

    static class ViewHolder {
        public TextView mTextViewNumber;
    }


    /**
     * 关闭图片
     * 资源方式
     */
    public void setCloseImgView(int resId) {
        mImageViewClose.setImageResource(resId);
    }

    /**
     * 关闭图片
     * Bitmap方式
     */
    public void setCloseImgView(Bitmap bitmap) {
        mImageViewClose.setImageBitmap(bitmap);
    }

    /**
     * 关闭图片
     * drawable方式
     */
    public void setCloseImgView(Drawable drawable) {
        mImageViewClose.setImageDrawable(drawable);
    }


    /**
     * 设置忘记密码文字
     */
    public void setForgetText(String text) {
        mTextViewForget.setText(text);
    }

    /**
     * 设置忘记密码文字大小
     */
    public void setForgetSize(float textSize) {
        mTextViewForget.setTextSize(textSize);
    }

    /**
     * 设置忘记密码文字颜色
     */
    public void setForgetColor(int textColor) {
        mTextViewForget.setTextColor(textColor);
    }

    /**
     * 设置提醒的文字
     */
    public void setHintText(String text) {
        mTextViewCommonTitle.setText(text);
    }

    /**
     * 设置提醒的文字大小
     */
    public void setTvHintSize(float textSize) {
        mTextViewCommonTitle.setTextSize(textSize);
    }

    /**
     * 设置提醒的文字颜色
     */
    public void setTvHintColor(int textColor) {
        mTextViewCommonTitle.setTextColor(textColor);
    }

    /**
     * 设置是否开启忘记密码
     *
     * @param isShow
     */
    public void setHintForgetPassword(boolean isShow) {
        if (isShow) {
            mRelativeLayoutForgetPassword.setVisibility(GONE);
        } else {
            mRelativeLayoutForgetPassword.setVisibility(VISIBLE);
        }
    }

    /**
     * 清楚所有密码TextView
     */
    public void cleanAllTv() {
        strPass = "";
        for (int i = 0; i < 6; i++) {
            mTextViewPass[i].setText("");
        }
    }
}
