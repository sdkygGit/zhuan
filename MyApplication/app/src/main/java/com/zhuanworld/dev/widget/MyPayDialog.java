package com.zhuanworld.dev.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.view.PasswordInputView;

/**
 * Created by Administrator on 2017/3/23.
 */

public class MyPayDialog extends Dialog{
    //定义回调事件，用于dialog的点击事件

    public interface OnCustomDialogListener{
    public void back(String name);
      }

    private OnCustomDialogListener customDialogListener;
    ImageView mBackImg;
    PasswordInputView mPasswordInputView;

        public MyPayDialog(Context context, String name, OnCustomDialogListener customDialogListener) {
        super(context);
//        this.name = name;
        this.customDialogListener = customDialogListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_pay_dialog);
        //设置标题
//        setTitle(name);
        mBackImg = (ImageView) findViewById(R.id.pay_pass_dialog_img);
        mPasswordInputView = (PasswordInputView) findViewById(R.id.pay_pass_dialog_input);
        mPasswordInputView.setOnClickListener(clickListener);
        // TODO: 2017/3/23
        mBackImg.setOnClickListener(clickListener);
    }

    /**显示软件
     * @param
     */
    protected void setKeyboardAdjustSize() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
    public void showKeyboard() {
        if(mPasswordInputView!=null){
            //设置可获得焦点
            mPasswordInputView.setFocusable(true);
            mPasswordInputView.setFocusableInTouchMode(true);
            //请求获得焦点
            mPasswordInputView.requestFocus();
            setKeyboardAdjustSize();
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pay_pass_dialog_img:
                    MyPayDialog.this.dismiss();
                    break;
                case R.id.pay_pass_dialog_input:

            }
//            customDialogListener.back(String.valueOf(etName.getText()));
            MyPayDialog.this.dismiss();
        }
    };

}

