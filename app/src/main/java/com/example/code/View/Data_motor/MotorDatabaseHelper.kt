package com.example.code.View.Data_motor

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.code.View.Transaksi_motor.Transaksi_motor_data

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

        private const val TABLE_TRANSAKSI_NAME = "transaksi_data_motor"
        private const val COLUMN_ID_TRANSAKSI  = "id_transaksi"
        private const val COLUMN_ID_MOTOR_FK   = "id_motor_fk"
        private const val COLUMN_PEMBELI_TR_MOTOR = "pembeli_motor"
        private const val COLUMN_KONTAK_TR_MOTOR  = "kontak_motor"
        private const val COLUMN_ALAMAT_TR_MOTOR  = "alamat_motor"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CreateTableQuery = "CREATE TABLE $TABLE_NAME($COLUMN_ID_MOTOR INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TAHUN_MOTOR TEXT, $COLUMN_WARNA_MOTOR TEXT, $COLUMN_HARGA_MOTOR TEXT, $COLUMN_MESIN_MOTOR TEXT, $COLUMN_SUS_MOTOR TEXT, $COLUMN_TRANS_MOTOR TEXT , $COLUMN_STOK_MOTOR TEXT)"
        db?.execSQL(CreateTableQuery)

        val CreateTransaksiTableQuery =
            "CREATE TABLE $TABLE_TRANSAKSI_NAME ($COLUMN_ID_TRANSAKSI INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "$COLUMN_ID_MOTOR_FK INTEGER,   " +
        "$COLUMN_PEMBELI_TR_MOTOR TEXT, " +
        "$COLUMN_KONTAK_TR_MOTOR TEXT,  " +
        "$COLUMN_ALAMAT_TR_MOTOR TEXT,  " +
        "FOREIGN KEY($COLUMN_ID_MOTOR_FK) REFERENCES $TABLE_NAME($COLUMN_ID_MOTOR))"
        db?.execSQL(CreateTransaksiTableQuery)
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

    fun getAllDataMotor(): List<Motor>{
        val motorList = mutableListOf<Motor>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MOTOR))
            val tahun_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_MOTOR))
            val warna_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WARNA_MOTOR))
            val harga_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HARGA_MOTOR))
            val mesin_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESIN_MOTOR))
            val suspensi_motor  = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUS_MOTOR))
            val transmisi_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANS_MOTOR))
            val stok_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STOK_MOTOR))

            val motor= Motor(id, tahun_motor, warna_motor, harga_motor, mesin_motor, suspensi_motor, transmisi_motor, stok_motor)
            motorList.add(motor)
        }
        cursor.close()
        db.close()
        return motorList
    }

    fun getDataMotorById(motorId: Int): Motor {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID_MOTOR = $motorId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MOTOR))
        val tahun_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_MOTOR))
        val warna_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WARNA_MOTOR))
        val harga_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HARGA_MOTOR))
        val mesin_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESIN_MOTOR))
        val suspensi_motor  = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUS_MOTOR))
        val transmisi_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANS_MOTOR))
        val stok_motor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STOK_MOTOR))

        cursor.close()
        db.close()
        return  Motor(id, tahun_motor, warna_motor, harga_motor, mesin_motor, suspensi_motor, transmisi_motor, stok_motor)
    }

    fun updateDataMotor(motor: Motor)
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
        val whereclause = "$COLUMN_ID_MOTOR =?"
        val whereArgs   = arrayOf(motor.id.toString())
        db.update(TABLE_NAME, values, whereclause, whereArgs)
        db.close()
    }

    fun deleteDataMotor(motorId: Int)
    {
        val db = writableDatabase
        val whereclause = "$COLUMN_ID_MOTOR = ?"
        val whereArgs   = arrayOf(motorId.toString())
        db.delete(TABLE_NAME, whereclause, whereArgs)
        db.close()
    }

    fun InsertDataTransaksiMotor(transaksiMotor: Transaksi_motor_data)
    {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_MOTOR_FK, transaksiMotor.idMotorFk)
            put(COLUMN_PEMBELI_TR_MOTOR, transaksiMotor.pembelimotor)
            put(COLUMN_KONTAK_TR_MOTOR, transaksiMotor.kontakmotor)
            put(COLUMN_ALAMAT_TR_MOTOR, transaksiMotor.alamatmotor)
        }
        db.insert(TABLE_TRANSAKSI_NAME, null, values)

        val updatestokMotorQuery =
            "UPDATE $TABLE_NAME SET $COLUMN_STOK_MOTOR = $COLUMN_STOK_MOTOR -1 WHERE $COLUMN_ID_MOTOR = ${transaksiMotor.idMotorFk}"

        db.execSQL(updatestokMotorQuery)
        db.close()
    }

    fun getTransaksiMotorById(motorId: Int): List<Transaksi_motor_data>{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_TRANSAKSI_NAME WHERE $COLUMN_ID_MOTOR_FK = $motorId"
        val cursor = db.rawQuery(query, null)
        val transaksiMotorList = mutableListOf<Transaksi_motor_data>()

        while (cursor.moveToNext())
        {
            val idTransaksi = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_TRANSAKSI))
            val idMotorFk   = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MOTOR_FK))
            val pembeli     = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PEMBELI_TR_MOTOR))
            val kontak      = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KONTAK_TR_MOTOR))
            val alamat      = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ALAMAT_TR_MOTOR))

            val tr_motor = Transaksi_motor_data(idTransaksi, idMotorFk, pembeli, kontak, alamat)
            transaksiMotorList.add(tr_motor)
        }

        cursor.close()
        db.close()
        return transaksiMotorList
    }
}