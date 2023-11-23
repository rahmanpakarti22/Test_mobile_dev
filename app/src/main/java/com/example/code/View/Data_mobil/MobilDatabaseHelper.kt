package com.example.code.View.Data_mobil

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.code.View.Transaksi_mobil.Transaksi

class MobilDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "test1_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "mobil_data"
        private const val COLUMN_ID_MOBIL = "id"
        private const val COLUMN_TAHUN_MOBIL = "tahun_mobil"
        private const val COLUMN_WARNA_MOBIL = "warna_mobil"
        private const val COLUMN_HARGA_MOBIL = "harga_mobil"
        private const val COLUMN_MESIN_MOBIL = "mesin_mobil"
        private const val COLUMN_KAPASITAS_MOBIL = "kapasitas_mobil"
        private const val COLUMN_TIPE_MOBIL = "tipe_mobil"
        private const val COLUMN_STOK_MOBIL = "stok_mobil"

        private const val TABLE_TRANSAKSI_NAME = "transaksi_data"
        private const val COLUMN_ID_TRANSAKSI  = "id_transaksi"
        private const val COLUMN_ID_MOBIL_FK   = "id_mobil_fk"
        private const val COLUMN_PEMBELI_TR_MOBIL = "pembeli_mobil"
        private const val COLUMN_KONTAK_TR_MOBIL  = "kontak_mobil"
        private const val COLUMN_ALAMAT_TR_MOBIL  = "alamat_mobil"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CreateTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID_MOBIL INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TAHUN_MOBIL TEXT, $COLUMN_WARNA_MOBIL TEXT, $COLUMN_HARGA_MOBIL TEXT, $COLUMN_MESIN_MOBIL TEXT, $COLUMN_KAPASITAS_MOBIL TEXT, $COLUMN_TIPE_MOBIL TEXT, $COLUMN_STOK_MOBIL TEXT)"
        db?.execSQL(CreateTableQuery)

        val createTransaksiTableQuery =
            "CREATE TABLE $TABLE_TRANSAKSI_NAME ($COLUMN_ID_TRANSAKSI INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_ID_MOBIL_FK INTEGER, " +
                    "$COLUMN_PEMBELI_TR_MOBIL TEXT, " +
                    "$COLUMN_KONTAK_TR_MOBIL TEXT, " +
                    "$COLUMN_ALAMAT_TR_MOBIL TEXT, " +
                    "FOREIGN KEY($COLUMN_ID_MOBIL_FK) REFERENCES $TABLE_NAME($COLUMN_ID_MOBIL))"

        db?.execSQL(createTransaksiTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropDatabase = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropDatabase)
        onCreate(db)
    }

    fun InsertDataMobil(mobil: Mobil) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TAHUN_MOBIL, mobil.tahun_mobil)
            put(COLUMN_WARNA_MOBIL, mobil.warna_mobil)
            put(COLUMN_HARGA_MOBIL, mobil.harga_mobil)
            put(COLUMN_MESIN_MOBIL, mobil.mesin_mobil)
            put(COLUMN_KAPASITAS_MOBIL, mobil.kapasitas_mobil)
            put(COLUMN_TIPE_MOBIL, mobil.tipe_mobil)
            put(COLUMN_STOK_MOBIL, mobil.stok_mobil)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllDataMobil(): List<Mobil> {
        val mobilList = mutableListOf<Mobil>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id             = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MOBIL))
            val tahun_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_MOBIL))
            val warna_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WARNA_MOBIL))
            val harga_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HARGA_MOBIL))
            val mesin_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESIN_MOBIL))
            val kapasitas_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_KAPASITAS_MOBIL
            ))
            val tipe_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPE_MOBIL))
            val stok_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STOK_MOBIL))

            val mobil = Mobil(id, tahun_mobil, warna_mobil, harga_mobil, mesin_mobil, kapasitas_mobil, tipe_mobil, stok_mobil)
            mobilList.add(mobil)

        }
        cursor.close()
        db.close()
        return mobilList
    }

    fun getDataMobilByid(mobilId: Int): Mobil{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID_MOBIL = $mobilId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id             = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MOBIL))
        val tahun_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_MOBIL))
        val warna_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WARNA_MOBIL))
        val harga_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HARGA_MOBIL))
        val mesin_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MESIN_MOBIL))
        val kapasitas_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(
            COLUMN_KAPASITAS_MOBIL
        ))
        val tipe_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPE_MOBIL))
        val stok_mobil    = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STOK_MOBIL))

        cursor.close()
        db.close()
        return Mobil(id, tahun_mobil, warna_mobil, harga_mobil, mesin_mobil, kapasitas_mobil, tipe_mobil, stok_mobil)

    }

    fun UpdateMobil(mobil: Mobil)
    {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TAHUN_MOBIL, mobil.tahun_mobil)
            put(COLUMN_WARNA_MOBIL, mobil.warna_mobil)
            put(COLUMN_HARGA_MOBIL, mobil.harga_mobil)
            put(COLUMN_MESIN_MOBIL, mobil.mesin_mobil)
            put(COLUMN_KAPASITAS_MOBIL, mobil.kapasitas_mobil)
            put(COLUMN_TIPE_MOBIL, mobil.tipe_mobil)
        }
        val whereclause = "$COLUMN_ID_MOBIL = ?"
        val whereArgs   = arrayOf(mobil.id.toString())
        db.update(TABLE_NAME, values,whereclause, whereArgs)
        db.close()
    }

    fun deleteDataMobil(mobilId: Int)
    {
        val db = writableDatabase
        val whereclause = "$COLUMN_ID_MOBIL = ?"
        val whereArgs   = arrayOf(mobilId.toString())
        db.delete(TABLE_NAME, whereclause, whereArgs)
        db.close()
    }

    fun insertDataTransaksi(transaksi: Transaksi)
    {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_MOBIL_FK, transaksi.idMobilFk)
            put(COLUMN_PEMBELI_TR_MOBIL, transaksi.pembelimobil)
            put(COLUMN_KONTAK_TR_MOBIL, transaksi.kontakmobil)
            put(COLUMN_ALAMAT_TR_MOBIL, transaksi.alamatmobil)
        }

        db.insert(TABLE_TRANSAKSI_NAME, null, values)

        val updateStockQuery =
            "UPDATE $TABLE_NAME SET $COLUMN_STOK_MOBIL = $COLUMN_STOK_MOBIL - 1 WHERE $COLUMN_ID_MOBIL = ${transaksi.idMobilFk}"

        db.execSQL(updateStockQuery)
        db.close()
    }

    fun getTransaksiByMobilId(mobilId: Int): List<Transaksi> {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_TRANSAKSI_NAME WHERE $COLUMN_ID_MOBIL_FK = $mobilId"
        val cursor = db.rawQuery(query, null)
        val transaksiList = mutableListOf<Transaksi>()

        while (cursor.moveToNext()) {
            val idTransaksi = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_TRANSAKSI))
            val idMobilFk   = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MOBIL_FK))
            val pembeli     = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PEMBELI_TR_MOBIL))
            val kontak      = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KONTAK_TR_MOBIL))
            val alamat      = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ALAMAT_TR_MOBIL))

            val tr = Transaksi(idTransaksi, idMobilFk, pembeli, kontak, alamat)
            transaksiList.add(tr)
        }

        cursor.close()
        db.close()
        return transaksiList
    }

}