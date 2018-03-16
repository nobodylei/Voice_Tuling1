package com.lei.voice_tuling.mvp.impl;

import com.lei.voice_tuling.model.Constant;
import com.lei.voice_tuling.mvp.MvpMainView;
import com.lei.voice_tuling.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanle on 2018/3/16.
 */

public class TulingPresenter extends BasePresenter {
    private MvpMainView mvpMainView;
    String text;

    public TulingPresenter(MvpMainView mainView) {
        mvpMainView = mainView;
    }
    //得到回复的消息
    public String getText() {
        return text;
    }

    public void sendHttp(String str) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Constant.TULING_KEY);
        map.put("info", str);
        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String json = object.toString();
                text = parseModelWithFastJson(json);
                mvpMainView.updateView();
            }

            @Override
            public void onFail(String error) {
                mvpMainView.showToast(error);
            }
        });
        httpUtil.sendGetHttp(Constant.TULING_API, map);
    }

    private String parseModelWithFastJson(String json) {
        String strText = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            strText = jsonObject.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strText;
    }
}
