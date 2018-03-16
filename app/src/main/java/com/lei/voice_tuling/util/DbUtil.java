package com.lei.voice_tuling.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lei.greendao.ChatRecordsDao;
import com.lei.greendao.DaoMaster;
import com.lei.greendao.DaoSession;
import com.lei.voice_tuling.model.ChatRecords;
import com.lei.voice_tuling.model.Constant;
import com.lei.voice_tuling.mvp.MvpMainView;

import java.util.List;

/**
 * Created by yanle on 2018/3/16.
 */

public class DbUtil {
    private DaoMaster master;
    private DaoSession session;
    private SQLiteDatabase db;
    private ChatRecordsDao recordsDao;
    private static DbUtil dbUtil = null;
    private static MvpMainView mMvpMainView;

    private DbUtil(Context context, MvpMainView mvpMainView) {

        openDB(context);
    }

    public static DbUtil getInstance(Context context, MvpMainView mvpMainView) {
        if(dbUtil == null) {
            dbUtil = new DbUtil(context, mvpMainView);
        }
        mMvpMainView = mvpMainView;
        return dbUtil;
    }
    //打开数据库
    private void openDB(Context context) {
        db = new DaoMaster.DevOpenHelper(context,
                Constant.DB_NAME,
                null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        recordsDao = session.getChatRecordsDao();
    }
    //插入一条数据
    public void insert(ChatRecords chatRecords) {
        recordsDao.insert(chatRecords);
    }
    //查询记录
    public List<ChatRecords> queryAll() {
       return recordsDao.queryBuilder().list();
    }
    //删除数据
    public void deleteAll() {
        recordsDao.deleteAll();
        mMvpMainView.showToast("删除成功！");
        mMvpMainView.updateView();
    }
}
