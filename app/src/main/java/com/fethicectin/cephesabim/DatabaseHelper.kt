package com.fethicectin.cephesabim

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.fethicectin.cephesabi.CepHesabiModel

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

    fun insertData(model:CepHesabiModel):Long{
        var sqliteDb = this.writableDatabase
        var contentvalues = ContentValues()

        contentvalues.put(COL_AMOUNT,model.amount)
        contentvalues.put(COL_MOUNTH,model.mounth)
        contentvalues.put(COL_DESCRIPTION,model.description)

        val result = sqliteDb.insert(TABLE_NAME,null,contentvalues)

        return result
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

    fun deleteAllData(){
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()
    }

}