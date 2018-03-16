package com.lei.voice_tuling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lei.voice_tuling.R;
import com.lei.voice_tuling.model.ChatRecords;
import com.lei.voice_tuling.model.Constant;

import java.util.List;

/**
 * Created by yanle on 2018/3/16.
 */

public class TextAdapter extends BaseAdapter {
    private List<ChatRecords> mList;
    private Context mContext;

    public TextAdapter(List<ChatRecords> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (mList.get(position).getWho() == Constant.LEFT) {
            convertView = (RelativeLayout) inflater.inflate(R.layout.left_text, parent, false);
        }
        if (mList.get(position).getWho() == Constant.RIGHT) {
            convertView = (RelativeLayout) inflater.inflate(R.layout.right, parent, false);
        }
        TextView tv = convertView.findViewById(R.id.tv_text);
        tv.setText(mList.get(position).getText());
        return convertView;
    }

}
