package com.example.code.View

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MotorDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "test2_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "motor_data"
        private const val COLUMN_ID_MOTOR = "id"
        private const val COLUMN_TAHUN_MOTOR = "tahun_motor"
        private const val COLUMN_WARNA_MOTOR = "warna_motor"
        private const val COLUMN_HARGA_MOTOR = "harga_motor"
        private const val COLUMN_MESIN_MOTOR = "mesin_motor"
        private const val COLUMN_SUS_MOTOR   = "suspensi_motor"
        private const val COLUMN_TRANS_MOTOR = "transmisi_motor"
        private const val COLUMN_STOK_MOTOR  = "stok_motor"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CreateTableQuery = "CREATE TABLE $TABLE_NAME($COLUMN_ID_MOTOR INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TAHUN_MOTOR TEXT, $COLUMN_WARNA_MOTOR TEXT, $COLUMN_HARGA_MOTOR TEXT, $COLUMN_MESIN_MOTOR TEXT, $COLUMN_SUS_MOTOR TEXT, $COLUMN_TRANS_MOTOR TEXT , $COLUMN_STOK_MOTOR TEXT)"
        db?.execSQL(CreateTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropDatabase = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropDatabase)
        onCreate(db)
    }

    fun InsertDataMotor(motor: Motor)
    {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TAHUN_MOTOR, motor.tahun_motor)
            put(COLUMN_WARNA_MOTOR, motor.warna_motor)
            put(COLUMN_HARGA_MOTOR, motor.harga_motor)
            put(COLUMN_MESIN_MOTOR, motor.mesin_motor)
            put(COLUMN_SUS_MOTOR, motor.suspensi_motor)
            put(COLUMN_TRANS_MOTOR, motor.transmisi_motor)
            put(COLUMN_STOK_MOTOR, motor.stok_motor)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}