package com.example.dbsqlite.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(val context: Context) {
    val myDbHelper = MyDbHelper(context);

    var db : SQLiteDatabase? = null

    fun openDb(){
        db = myDbHelper.writableDatabase
    }
    fun insertToDb(title:String, content:String){
        val values = ContentValues().apply {
            put(MyDbName.COLUMN_NAME_TITLE,title)
            put(MyDbName.COLUMN_NAME_CONTENT,content)
        }
        db?.insert(MyDbName.TABLE_NAME, null, values)
    }

    @SuppressLint("Range") // 31-qator
    fun readDbData() : ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDbName.TABLE_NAME, null, null,
            null, null, null, null)

            while (cursor?.moveToNext()!!){
                val dataText = cursor.getString(cursor.getColumnIndex(MyDbName.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        return dataList
    }

}