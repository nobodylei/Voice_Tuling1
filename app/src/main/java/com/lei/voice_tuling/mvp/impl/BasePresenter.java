package com.lei.voice_tuling.mvp.impl;

import android.content.Context;

/**
 * Created by yanle on 2018/3/16.
 */

public class BasePresenter {
    Context mContext;

    public void attach(Context context) {
        mContext = context;
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
        mContext = null;
    }
}
