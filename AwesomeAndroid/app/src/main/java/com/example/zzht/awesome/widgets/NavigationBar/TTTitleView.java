package com.example.zzht.awesome.widgets.NavigationBar;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zzht.awesome.R;

import java.lang.reflect.Type;

public class TTTitleView extends RelativeLayout {
    /**
     * the style of Title
     */
    public enum BtnStyle {
        NOBTN(0), RIGHT_ACTION(1), ONLY_BACK(3);
        BtnStyle(int style) {
            this.style = style;
        }

        final int style;
    }

    public TextView tv_rightSingle;
    public TextView tv_title;
    private ImageButton ibtn_back;
    public LinearLayout ll_title;

    public TTTitleView(Context context) {
        this(context, null);
    }

    public TTTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_title_bar,
                this, true);
        initView();
    }

    private void initView() {
        ll_title = (LinearLayout) findViewById(R.id.ll_title);
        ibtn_back = (ImageButton) findViewById(R.id.ibtn_back);
        tv_rightSingle = (TextView) findViewById(R.id.tv_action);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    public void setTitleText(String str_title) {
        this.tv_title.setText(str_title);
    }

    /**
     * 设置左侧返回按钮显示或隐藏
     */
    public void setShowBackItem(Boolean isShow) {
        this.ibtn_back.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    /**
     * 设置右侧动作按钮显示文字
     */
    public void setRightActionBtnText(String str_rightBtn) {
        this.tv_rightSingle.setText(str_rightBtn);
    }

    /**
     * 设置右侧按钮类型
     */
    public void setBtnStyle(BtnStyle style) {
        switch (style) {
            case RIGHT_ACTION:
                this.ibtn_back.setVisibility(View.VISIBLE);
                this.tv_rightSingle.setVisibility(View.VISIBLE);
                break;
            case NOBTN:
                this.ibtn_back.setVisibility(View.GONE);
                this.tv_rightSingle.setVisibility(View.GONE);
                break;
            case ONLY_BACK:
                this.ibtn_back.setVisibility(View.VISIBLE);
                this.tv_rightSingle.setVisibility(View.GONE);
                break;
        }
    }
}