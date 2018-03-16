package com.lei.voice_tuling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lei.voice_tuling.adapter.TextAdapter;
import com.lei.voice_tuling.model.ChatRecords;
import com.lei.voice_tuling.mvp.MvpMainView;
import com.lei.voice_tuling.util.DbUtil;

import java.util.List;

/**
 * Created by yanle on 2018/3/16.
 */

public class RecordsActivity extends AppCompatActivity implements MvpMainView{
    private ListView lv_records;
    private TextAdapter adapter;
    private Button btn_delete;
    private List<ChatRecords> mList;
    private DbUtil dbUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_records);
        init();
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbUtil.deleteAll();
            }
        });
    }

    private void init() {
        lv_records = findViewById(R.id.lv_record);
        btn_delete = findViewById(R.id.btn_delete);
        dbUtil = DbUtil.getInstance(this, this);
        mList = dbUtil.queryAll();
        //Log.i("tag", mList.toString());
        adapter = new TextAdapter(mList, this);
        lv_records.setAdapter(adapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hindenLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView() {
        mList.clear();
        //Toast.makeText(this, "更新界面", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }
}
