package com.fethicectin.cephesabim.DbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.fethicectin.cephesabi.CepHesabiModel
import java.util.*

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context,DatabaseHelper.DATABASE_NAME,null,DatabaseHelper.DATABASE_VERSION) {
    private val TABLE_NAME="Incomeoutcome"
    private val COL_ID = "id"
    private val COL_AMOUNT = "amount"
    private val COL_MOUNTH = "mounth"
    private val COL_DESCRIPTION = "description"
    private val COL_TOTAL = "Total"

    companion object {
        private val DATABASE_NAME = "CEPHESABI"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_AMOUNT INTEGER, $COL_MOUNTH TEXT, $COL_DESCRIPTION TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME");
        onCreate(db)
    }

    fun insertData(model:CepHesabiModel){
        val dbHelper = DatabaseHelper(context)
        val sqliteDb = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(COL_AMOUNT, model.amount)
            put(COL_MOUNTH, model.mounth)
            put(COL_DESCRIPTION,model.description)
        }

        sqliteDb.insert(TABLE_NAME,null,values)
        sqliteDb.close()
        val y = this.retrieveData()
        System.out.println(y)
    }

    fun retrieveData():MutableList<CepHesabiModel> {
        val modelList = mutableListOf<CepHesabiModel>()
        val sqliteDB = this.readableDatabase
        val query =  "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        Log.d("***SELECT***",result.toString())
        if(result != null){
            if(result.moveToFirst()){
                do{
                    val model = CepHesabiModel()
                    model.id = result.getInt(result.getColumnIndex(COL_ID))
                    model.amount = result.getInt(result.getColumnIndex(COL_AMOUNT))
                    model.mounth = result.getString(result.getColumnIndex(COL_MOUNTH))
                    model.description = result.getString(result.getColumnIndex(COL_DESCRIPTION))
                    modelList.add(model)
                }while (result.moveToNext())
            }
        }
        result.close()
        sqliteDB.close()
        return modelList
    }

    fun sumOfAmounts():Int{
        val sqliteDB = this.readableDatabase
        val query =  "SELECT SUM($COL_AMOUNT) AS Total FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        var total = 0
        if (result.moveToFirst()) {
            total = result.getInt(result.getColumnIndex(COL_TOTAL))
        }
        result.close()
        sqliteDB.close()
        return total
    }

    fun getMounthlyAverages(mounthName:String):Int {
        val db = this.readableDatabase
        val mounthArray = arrayOf(mounthName)
        val query =  "SELECT SUM($COL_AMOUNT) AS Total FROM $TABLE_NAME WHERE $COL_MOUNTH = ?"
        val cursor = db.rawQuery(query,mounthArray)
        var total = 0
        if (cursor.moveToFirst()) {
            do{
                total = cursor.getInt(cursor.getColumnIndex(COL_TOTAL))
            }while(cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return total
    }

    fun getMountlyPlusNegate(mounthName:String):MutableList<Int>{
        val db = this.readableDatabase
        val mounthArray = arrayOf(mounthName)
        val query =  "SELECT $COL_AMOUNT AS Total FROM $TABLE_NAME WHERE $COL_MOUNTH = ?"
        val cursor = db.rawQuery(query,mounthArray)
        var totalPlus = 0
        var totalNegate = 0
        if (cursor.moveToFirst()) {
            do{
                if(cursor.getInt(cursor.getColumnIndex(COL_TOTAL)) > 0){
                    totalPlus += cursor.getInt(cursor.getColumnIndex(COL_TOTAL))
                }else{
                    totalNegate += cursor.getInt(cursor.getColumnIndex(COL_TOTAL))
                }
            }while(cursor.moveToNext())
        }

        val totals = mutableListOf<Int>()
        totals.add(totalPlus)
        totals.add(totalNegate)
        cursor.close()
        db.close()

        return totals
    }

    fun deleteAllData() {
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()
    }

    fun deleteData(id : Int){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"id = ?", arrayOf(id.toString()))
        db.close()
        var x = this.retrieveData()
    }

}