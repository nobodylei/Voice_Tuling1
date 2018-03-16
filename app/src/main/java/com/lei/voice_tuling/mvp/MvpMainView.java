package com.lei.voice_tuling.mvp;

/**
 * Created by yanle on 2018/3/16.
 */

public interface MvpMainView extends MvpLoadingview {
    //显示信息
    void showToast(String msg);
    //变更界面
    void updateView();
}
