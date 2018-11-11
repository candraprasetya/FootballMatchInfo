package com.kardusinfo.footballmatchinfo.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "Candra.db",null,1){

    companion object {
        private  var instance: DatabaseHelper? = null

        fun getInstance(ctx: Context): DatabaseHelper{
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavMatch.TABLE_FAV_MATCH,true,
            FavMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavMatch.ID_EVENT to TEXT + UNIQUE,
            FavMatch.DATE_EVENT to TEXT,
            FavMatch.HOME_TEAM to TEXT,
            FavMatch.AWAY_TEAM to TEXT,
            FavMatch.HOME_SCORE to TEXT,
            FavMatch.AWAY_SCORE to TEXT,
            FavMatch.HOME_GOAL_DETAIL to TEXT,
            FavMatch.AWAY_GOAL_DETAIL to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavMatch.TABLE_FAV_MATCH,true)
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)