package com.yanghaoyi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者 : YangHaoyi on 2016/7/18.
 * 邮箱 ：yanghaoyi@neusoft.com
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME  = "Analytics";
    private final static int DATABASE_VERSION = 1;
    private static final String PAGE_TABLE = "page_info";
    private final static String CLICK_TABLE = "click_info";


    /**
     * 构造函数，创建数据库
     * */
    public SQLiteHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    /***
     * 建表
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String pageSql = " CREATE TABLE "+PAGE_TABLE
                +"(_id INTEGER PRIMARY KEY,"
                +" PageName VARCHAR(20) NOT NULL,"
                +" Duration LONG)";
        db.execSQL(pageSql);

        String sql = " CREATE TABLE "+CLICK_TABLE
                +"(_id INTEGER PRIMARY KEY,"
                +" ClickEvent VARCHAR(20) NOT NULL,"
                +" Count INTEGER)";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String pageSql ="DROP TABLE IF EXISTS" +PAGE_TABLE;
        String clickSql ="DROP TABLE IF EXISTS" +CLICK_TABLE;
        db.execSQL(pageSql);
        db.execSQL(clickSql);
        onCreate(db);
    }
    /**
     * 获取游标
     * */
    public Cursor select(String tableName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null,null,null,null,null,null);
        return cursor;
    }
    /***
     * 插入一条数据
     * */
    public long insertClick(String eventName, int count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ClickEvent",eventName);
        cv.put("Count",count);
        return db.insert(CLICK_TABLE,null,cv);
    }
    public long insertPage(String pageName, long duration){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PageName",pageName);
        cv.put("Duration",duration);
        return db.insert(PAGE_TABLE,null,cv);
    }



    /***
     * 删除一条数据
     * */
    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = {Integer.toString(id)};
        db.delete(CLICK_TABLE,where,whereValue);
    }
    /***
     * 修改一条数据
     * */
    public void updateClick(String clickEvent, int count){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "clickEvent = ?";
        String[]whereValue = {clickEvent};
        ContentValues cv = new ContentValues();
        cv.put("ClickEvent",clickEvent);
        cv.put("Count",count);
        db.update(CLICK_TABLE,cv,where,whereValue);
    }

    public void updatePage(String pageName,int duration, int count){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "pageName = ?";
        String[]whereValue = {pageName};
        ContentValues cv = new ContentValues();
        cv.put("PageName",pageName);
        cv.put("Duration",duration);
        db.update(PAGE_TABLE,cv,where,whereValue);
    }


    /**
     * 查找一条数据
     * **/
    public Cursor queryClick(String[] args){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CLICK_TABLE+" WHERE ClickEvent LIKE ?",args);
        return cursor;
    }

    public Cursor queryPage(String[] args){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+PAGE_TABLE+" WHERE PageName LIKE ?",args);
        return cursor;
    }

    public Cursor queryClickTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CLICK_TABLE,null);
        return cursor;
    }

    public Cursor queryPageTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+PAGE_TABLE,null);
        return cursor;
    }

    public void truncatePageTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String pageSql ="DELETE FROM page_info";
        db.execSQL(pageSql);
    }

}
