package com.example.bookshowcase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase INSTANCE;

    public static BookDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            BookDatabase.class, "db"
                    ).build();
        }
        return INSTANCE;
    }

    public abstract BookDAO getBookDao();

}