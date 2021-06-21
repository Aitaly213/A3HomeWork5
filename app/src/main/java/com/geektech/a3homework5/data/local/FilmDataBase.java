package com.geektech.a3homework5.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.a3homework5.data.model.FilmModel;


@Database(entities = FilmModel.class, exportSchema = false, version = 1)
public abstract class FilmDataBase extends RoomDatabase {
    public abstract FilmDao filmDao();
}
