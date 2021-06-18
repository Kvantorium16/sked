package com.example.sked

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "Timetable"
val TABLE_NAME = "Subjects"
val COL_SUBJECT = "subject"
val COL_DATE = "date"
val COL_TIME = "time"
val COL_TEACHER = "teacher"
val COL_ID = "id"

class DataBaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_SUBJECT + " VARCHAR(256)," +
                COL_DATE + " VARCHAR(256)," +
                COL_TIME + " VARCHAR(256)," +
                COL_TEACHER + " VARCHAR(256))";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun  insertData(subject: Subject){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_SUBJECT, subject.subject)
        cv.put(COL_DATE, subject.date)
        cv.put(COL_TIME, subject.time)
        cv.put(COL_TEACHER, subject.teacher)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())(
                Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                )
        else(
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        )
    }

    fun readData() : MutableList<Subject>{
        var list : MutableList<Subject> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                var subject = Subject()
                subject.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                subject.subject = result.getString(result.getColumnIndex(COL_SUBJECT))
                subject.date = result.getString(result.getColumnIndex(COL_DATE))
                subject.time = result.getString(result.getColumnIndex(COL_TIME))
                subject.teacher = result.getString(result.getColumnIndex(COL_TEACHER))
                list.add(subject)
            }while (result.moveToNext())
        }
        result.close()
        db.close()

        return list
    }

    fun deleteData(){
        var db = this.writableDatabase

        db.delete(TABLE_NAME, null, null)

        db.close()
    }

//    fun updateData() {
//        val db = this.writableDatabase
//        val query = "Select * from " + TABLE_NAME
//        val result = db.rawQuery(query, null)
//
//        if (result.moveToFirst()){
//            do {
//                var cv = ContentValues()
//                db.update(TABLE_NAME)
//            }while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//    }
}
