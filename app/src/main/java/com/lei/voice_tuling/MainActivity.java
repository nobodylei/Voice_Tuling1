package com.lei.voice_tuling;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lei.voice_tuling.adapter.TextAdapter;
import com.lei.voice_tuling.model.ChatRecords;
import com.lei.voice_tuling.model.Constant;
import com.lei.voice_tuling.mvp.MvpMainView;
import com.lei.voice_tuling.util.DbUtil;
import com.lei.voice_tuling.mvp.impl.TulingPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MvpMainView{
    private ListView lv_record;
    private ToggleButton toggleButton;
    private EditText et_message;
    private Button btn_voice, btn_send, chat_record;
    private String context_str;
    private List<ChatRecords> list;
    private TextAdapter adapter;
    private TulingPresenter tulingPresenter;
    private DbUtil dbUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermission();
        btn_send.setOnClickListener(this);
        chat_record.setOnClickListener(this);
        toggleButton.setOnClickListener(this);
        tulingPresenter = new TulingPresenter(this);
        tulingPresenter.attach(this);
        dbUtil = DbUtil.getInstance(this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initView() {
        list = new ArrayList<>();
        lv_record = findViewById(R.id.lv_record);
        toggleButton = findViewById(R.id.toggleButton);
        et_message = findViewById(R.id.et_message);
        btn_send = findViewById(R.id.btn_send);
        btn_voice = findViewById(R.id.btn_voice);
        chat_record = findViewById(R.id.btn_chat_records);
        adapter = new TextAdapter(list, MainActivity.this);
        lv_record.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_send:
                context_str = et_message.getText().toString();
                et_message.setText("");
                String str = context_str.replace(" ","");
                String str2 = str.replace("\n", "");
                ChatRecords chatRecords = new ChatRecords(Constant.TEXT_RECORED,
                        Constant.RIGHT, null, context_str);
                list.add(chatRecords);
                adapter.notifyDataSetChanged();
                dbUtil.insert(chatRecords);
                tulingPresenter.sendHttp(str2);
                break;
            case R.id.btn_chat_records:
                Intent intent = new Intent(MainActivity.this, RecordsActivity.class);
                startActivity(intent);
                break;
            case R.id.toggleButton:
                if(toggleButton.isChecked()) {
                    btn_voice.setVisibility(View.GONE);
                    btn_send.setVisibility(View.VISIBLE);
                    et_message.setVisibility(View.VISIBLE);
                } else {
                    btn_voice.setVisibility(View.VISIBLE);
                    btn_send.setVisibility(View.GONE);
                    et_message.setVisibility(View.GONE);
                }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hindenLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView() {
        String context = tulingPresenter.getText();
        ChatRecords chatRecords = new ChatRecords(Constant.TEXT_RECORED,
                Constant.LEFT, null, context);
        list.add(chatRecords);
        adapter.notifyDataSetChanged();
        dbUtil.insert(chatRecords);
    }
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }
}
