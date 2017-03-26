package com.zhuanworld.dev.bean;

/**
 * Created by Administrator on 2017/3/11 0011.
 */
public interface SelectImageContract {
    interface Operator {
        void requestCamera();

        void requestExternalStorage();

        void onBack();

        void setDataView(View view);
    }

    interface View {

        void onOpenCameraSuccess();

        void onCameraPermissionDenied();
    }
}
