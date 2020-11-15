package com.abu.sufian.roomexample.room;

import androidx.room.Dao;
import androidx.room.Insert;

import com.abu.sufian.roomexample.datamodel.StudentModel;

@Dao
public interface DAOStudent {
    @Insert
    void insert(StudentModel model);
}
