package com.example.alchilaxo.domain.database

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.alchilaxo.database.UserRoomModel
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [
        UserRoomModel::class,
        RestaurantsRoomModel::class,
        InitPromosRoomModel::class,

    ],
    version = 4
)

abstract class AppDatabase: RoomDatabase()  {

    abstract fun RestaurantsDao(): RestaurantsDao
    abstract fun userDao(): UserDao
    abstract fun initpromosDao(): InitPromosDao



    private class AppDatabaseCallback(
        private val scope: CoroutineScope,
        private val resources: Resources
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Pre-populate
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context, coroutineScope: CoroutineScope, resources: Resources): AppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "atugusto_database")
                    .addCallback(AppDatabaseCallback(coroutineScope, resources))
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }

}