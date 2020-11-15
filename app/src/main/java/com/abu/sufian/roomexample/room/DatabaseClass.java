package com.abu.sufian.roomexample.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abu.sufian.roomexample.datamodel.StudentModel;

@Database(entities = {StudentModel.class}, version = 1, exportSchema = false)
public abstract class DatabaseClass extends RoomDatabase {
    public static DatabaseClass mInstance = null;

    public abstract DAOStudent daoStudent();

    public static synchronized DatabaseClass getInstance(Context context){
        if(mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass.class,
                    "student.db3")
                    .build();
        return mInstance;
    }

}
