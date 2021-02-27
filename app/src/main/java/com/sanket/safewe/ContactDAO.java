package com.sanket.safewe;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO { //either interface or abstract class as we dont provide method body we just annotate
    //multiple args or even a list

    @Insert
    void Insert(EmergencyContacts contact);

    @Update //(onConflict = OnConflictStrategy.REPLACE)
    void Update(EmergencyContacts contact);

    @Delete
    void Delete(EmergencyContacts contact);

    @Query("DELETE FROM EmergencyContacts")
    void DeleteAllContacts();

    @Query("SELECT * FROM EmergencyContacts Order By id Asc ")
    LiveData<List<EmergencyContacts>> getAllContacts();  //updates and returns


}
